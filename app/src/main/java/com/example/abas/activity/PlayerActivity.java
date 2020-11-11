package com.example.abas.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.abas.R;
import com.example.abas.model.Time;
import com.example.abas.services.DownloadService;
import com.example.abas.services.ThreadPlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)

public class PlayerActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener, View.OnTouchListener {
    private ImageView escudo;
    private ImageButton btnPause, btnRewind, btnForward, btnDownload;
    private Time time;
    private MediaPlayer player;
    private SeekBar seekBar;
    private TextView currentTime, totalTime, letra;
    private File file;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initialize();
        preparePlayer();
        updateButton();

        // extrair letra
        List<String> l = getLetra();
        letra.setText(l.get(0));
        if(l.size()>1){
            Toast.makeText(this, "Toque na letra para alternar entre traduções.", Toast.LENGTH_SHORT).show();
            letra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(letra.getText().equals(l.get(0))) letra.setText(l.get(1));
                    else letra.setText(l.get(0));
                }
            });
        }

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()) {
                    player.pause();
                    btnPause.setBackgroundResource(R.drawable.ic_play);
                } else {
                    player.start();
                    btnPause.setBackgroundResource(R.drawable.ic_pause);
                }
            }
        });

        btnRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.seekTo(player.getCurrentPosition() - 5000);
            }
        });
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.seekTo(player.getCurrentPosition() + 5000);
            }
        });
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!file.exists())
                    new DownloadService(PlayerActivity.this, path).execute(time.getHino());
                else
                    Toast.makeText(PlayerActivity.this, "O arquivo já existe, segure para apagar!", Toast.LENGTH_SHORT).show();
            }
        });

        btnDownload.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDownload();
                return true;
            }
        });
    }

    private void initialize() {
        escudo = findViewById(R.id.escudo);
        btnPause = findViewById(R.id.btnPause);
        btnRewind = findViewById(R.id.btnRewind);
        btnForward = findViewById(R.id.btnForward);
        seekBar = findViewById(R.id.seekBar);
        currentTime = findViewById(R.id.currentTime);
        totalTime = findViewById(R.id.totalTime);
        letra = findViewById(R.id.letra);
        btnDownload = findViewById(R.id.btnDownload);
        time = (Time) getIntent().getExtras().getSerializable("time");
        escudo.setImageResource(time.getEscudo());
        path = getFilesDir() + "/hino" + time.getNome() + ".mp3";
        file = new File(path);
        player = new MediaPlayer();
        btnForward.setOnTouchListener(this);
        btnRewind.setOnTouchListener(this);
        btnPause.setOnTouchListener(this);
        btnDownload.setOnTouchListener(this);
    }

    // preparar o player
    private void preparePlayer() {
        try {
            if (file.exists()) {
                player = MediaPlayer.create(getApplicationContext(), Uri.parse(file.getAbsolutePath()));
                initPlayer();
            } else {
                player.setDataSource(time.getHino());
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        initPlayer();
                    }
                });
                player.prepareAsync();
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    // inicializar o player
    private void initPlayer() {
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(player.getDuration());
        player.start();

        String total = createTimer(player.getDuration());
        totalTime.setText(total);

        new ThreadPlayer(handler, player).start();
        player.setOnCompletionListener(this);
    }

    public String createTimer(int duration) {
        String timer = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        timer += min + ":";

        if (sec < 10) timer += "0";

        timer += sec;

        return timer;
    }

    private List<String> getLetra() {
        String nome = time.getNome();
        String letra = "";
        String letraTraduzida = "";
        List<String> letras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.letra)))) {
            String line = br.readLine();
            while (line != null) {
                if (nome.equals(line)) {
                    line = br.readLine();
                    while (!line.equals("break")) {
                        letra += line + "\n";
                        line = br.readLine();
                    }
                }if ((time.getNome()+"traduzido").equals(line)){
                    line = br.readLine();
                    while (!line.equals("break")){
                        letraTraduzida += line + "\n";
                        line = br.readLine();
                    }
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        letras.add(letra);
        if(!letraTraduzida.equals("")){
            letras.add(letraTraduzida);
        }
        return letras;
    }

    public void updateButton() {
        int buttonDownload = file.exists() ? R.drawable.ic_download_true : R.drawable.ic_download;
        btnDownload.setBackgroundResource(buttonDownload);
    }

    private void alertDownload() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir Hino");
        builder.setMessage("Deseja excluir o hino selecionado?");
        builder.setCancelable(true);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (file.exists()) {
                    file.delete();
                    Toast.makeText(PlayerActivity.this, "Arquivo Excluído com Sucesso!", Toast.LENGTH_SHORT).show();
                    updateButton();
                } else
                    Toast.makeText(PlayerActivity.this, "Houve um erro ao excluir!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onStop() {
        if (player != null) player.stop();
        super.onStop();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            player.seekTo(i);
            seekBar.setProgress(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            currentTime.setText(createTimer(msg.what));
            totalTime.setText(createTimer(player.getDuration() - msg.what));
            seekBar.setProgress(msg.what);
        }
    };

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        btnPause.setBackgroundResource(R.drawable.ic_play);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);
                break;
            case MotionEvent.ACTION_UP:
                v.getBackground().clearColorFilter();
                break;
        }
        return false;
    }
}