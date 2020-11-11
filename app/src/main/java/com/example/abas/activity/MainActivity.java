package com.example.abas.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.abas.R;
import com.example.abas.fragment.ClubesBrasileirosFragment;
import com.example.abas.fragment.ClubesEuropeusFragment;
import com.example.abas.fragment.SelecoesFragment;
import com.example.abas.model.Time;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    // declaração de times europeus
    private Time ajax = new Time(R.drawable.ajax, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinoajax.mp3?alt=media&token=51e1f5eb-3b06-4810-86c6-86202e73a99a", "ajax");
    private Time arsenal = new Time(R.drawable.arsenal, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinoarsenal.mp3?alt=media&token=e40ba6cd-01ff-440c-a141-059fb5ba0f99", "arsenal");
    private Time atleticomadrid = new Time(R.drawable.atleticomadrid, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinoatleticomadrid.mp3?alt=media&token=91f5ca4c-46b3-411c-a4df-0ed8d3a74bbd", "atleticomadrid");
    private Time barcelona = new Time(R.drawable.barcelona, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinobarcelona.mp3?alt=media&token=e2784c36-b5ca-4953-8bea-296c3457136c", "barcelona");
    private Time bayern = new Time(R.drawable.bayern, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinobayern.mp3?alt=media&token=e43a99c8-275a-4af1-9e66-942cfbd414ac", "bayern");
    private Time benfica = new Time(R.drawable.benfica, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinobenfica.mp3?alt=media&token=a9412214-bb3d-46e1-b0d0-1ad67d38aa0c", "benfica");
    private Time bvb = new Time(R.drawable.bvb, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinoborussia.mp3?alt=media&token=4197b6f0-e33e-4c89-9bc5-2cf00d72a485", "bvb");
    private Time chelsea = new Time(R.drawable.chelsea, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinochelsea.mp3?alt=media&token=7f7adb0c-0988-4284-b326-64015fb897cc", "chelsea");
    private Time city = new Time(R.drawable.city, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinocity.mp3?alt=media&token=4b47db60-5a8a-4c75-bea0-bc8c12feeca6", "city");
    private Time inter = new Time(R.drawable.inter, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinointer.mp3?alt=media&token=8e35ff87-5363-4745-8ca2-1cc222e3cf70", "inter");
    private Time juventus = new Time(R.drawable.juventus, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinojuventus.mp3?alt=media&token=2ce3c615-21e3-46fb-bbe5-8b5f42795bbf", "juventus");
    private Time liverpool = new Time(R.drawable.liverpool, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinoliverpool.mp3?alt=media&token=696c2ea1-9d07-42f7-b560-1bf3f89f901c", "liverpool");
    private Time milan = new Time(R.drawable.milan, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinomilan.mp3?alt=media&token=33bb9421-a7a7-4a14-879f-8f7b3bb4bb26", "milan");
    private Time porto = new Time(R.drawable.porto, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinoporto.mp3?alt=media&token=ac992ff5-ed72-4195-b6ef-1e8044bf617c", "porto");
    private Time psg = new Time(R.drawable.psg, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinopsg.mp3?alt=media&token=531e9b8a-cec7-4ce5-9ea0-a36aca9a65ec", "psg");
    private Time real = new Time(R.drawable.real, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinoreal.mp3?alt=media&token=c33d3e21-e285-4239-8dde-88a8a930fd69", "real");
    private Time tottenham = new Time(R.drawable.tottenham, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinotottenham.mp3?alt=media&token=38099ec2-5687-409d-b4c8-a51edb965311", "tottenham");
    private Time united = new Time(R.drawable.united, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/europeus%2Fhinounited.mp3?alt=media&token=a42c615a-f24d-42cc-b6c1-31d3fed97635", "united");

    private List<Time> timesEuropeus = new ArrayList<>();

    // declaração de seleções
    private Time brasil = new Time(R.drawable.brasil, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinobrasil.mp3?alt=media&token=bbc47720-5a9e-4fb0-a05a-a4cba34a8da7", "brasil");
    private Time alemanha = new Time(R.drawable.alemanha, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinoalemanha.mp3?alt=media&token=cbfa3019-a0aa-4267-9e88-d088a5622a0b", "alemanha");
    private Time argentina = new Time(R.drawable.argentina, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinoargentina.mp3?alt=media&token=0cd17db0-4d7b-42f6-bee1-7cc6fce35d5d", "argentina");
    private Time espanha = new Time(R.drawable.espanha, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinoespanha.mp3?alt=media&token=c491bbfb-3497-4178-9383-b08c079db873", "espanha");
    private Time franca = new Time(R.drawable.franca, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinofranca.mp3?alt=media&token=b08c9821-ce7e-46d4-a2fc-a6211ea5121d", "franca");
    private Time holanda = new Time(R.drawable.holanda, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinoholanda.mp3?alt=media&token=fcc39c70-5956-4fc3-818d-9e9d89c6cf95", "holanda");
    private Time inglaterra = new Time(R.drawable.inglaterra, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinoinglaterra.mp3?alt=media&token=7860758f-95b7-4845-ad8e-591f134e2ae7", "inglaterra");
    private Time italia = new Time(R.drawable.italia, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinoitalia.mp3?alt=media&token=e33c6def-345e-4366-83d4-e86e41d02b91", "italia");
    private Time mexico = new Time(R.drawable.mexico, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinomexico.mp3?alt=media&token=3aa8aec4-b96c-4ed8-a8b3-b06e7e74d313", "mexico");
    private Time portugal = new Time(R.drawable.portugal, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinoportugal.mp3?alt=media&token=bb68c178-efe6-4824-9968-acdd7e2a622f", "portugal");
    private Time uruguai = new Time(R.drawable.uruguai, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinouruguai.mp3?alt=media&token=206db607-a406-45fd-a953-122a3418381d", "uruguai");
    private Time usa = new Time(R.drawable.usa, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/selecao%2Fhinoestadosunidos.mp3?alt=media&token=a97b8e34-0c3f-4497-919b-bcb56b0d4ebe", "usa");

    private List<Time> selecoes = new ArrayList<>();

    // declaração de times
    private Time atletico = new Time(R.drawable.atletico, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinoatletico.mp3?alt=media&token=13a04baa-e966-4589-9b77-b7fd05a8fbb8", "atletico");
    private Time botafogo = new Time(R.drawable.botafogo, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinobotafogo.mp3?alt=media&token=c7536c25-a3ce-42bb-9deb-be51936e20f2", "botafogo");
    private Time corinthians = new Time(R.drawable.corinthians, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinocorinthians.mp3?alt=media&token=565053cb-1813-42e9-ac0f-ceae66444c63", "corinthians");
    private Time cruzeiro = new Time(R.drawable.cruzeiro, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinocruzeiro.mp3?alt=media&token=8012d640-d033-4de7-bd69-d26dd9fde3c3", "cruzeiro");
    private Time flamengo = new Time(R.drawable.flamengo, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinoflamengo.mp3?alt=media&token=23d725dd-361e-4fdf-a8aa-41a12d94082d", "flamengo");
    private Time fluminense = new Time(R.drawable.fluminense, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinofluminense.mp3?alt=media&token=6291db0b-3cf0-4fd2-9295-dd7e1bfe8bed", "fluminense");
    private Time gremio = new Time(R.drawable.gremio, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinogremio.mp3?alt=media&token=1ee9a0a4-876c-43ab-97cc-337f75bcf2de", "gremio");
    private Time internacional = new Time(R.drawable.internacional, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinointernacional.mp3?alt=media&token=1405f079-1698-410b-a7b3-307c0ab2cf8f", "internacional");
    private Time palmeiras = new Time(R.drawable.palmeiras, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinopalmeiras.mp3?alt=media&token=f99687a4-c21f-4636-b05e-d2755759c765", "palmeiras");
    private Time santos = new Time(R.drawable.santos, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinosantos.mp3?alt=media&token=02da7b62-d811-4e0b-9d4f-910e45935aa7", "santos");
    private Time saoPaulo = new Time(R.drawable.saopaulo, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinosaopaulo.mp3?alt=media&token=22e42dd0-f28e-47f9-8008-be212720987c", "saopaulo");
    private Time vasco = new Time(R.drawable.vasco, "https://firebasestorage.googleapis.com/v0/b/test-463bf.appspot.com/o/brasileiros%2Fhinovasco.mp3?alt=media&token=ad446c7b-6e5d-43c1-8d43-12ecaaef1345", "vasco");

    private List<Time> timesBrasileiros = new ArrayList<>();

    private List<Time> todos = new ArrayList<>();

    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smartTabLayout = findViewById(R.id.smartTabLayout);
        viewPager = findViewById(R.id.viewPager);

        getSupportActionBar().setElevation(0);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Clubes Brasileiros", ClubesBrasileirosFragment.class)
                        .add("Clubes Europeus", ClubesEuropeusFragment.class)
                        .add("Seleções", SelecoesFragment.class)

                        .create()
        );

        timesEuropeus.addAll(Arrays.asList(ajax, arsenal, atleticomadrid, barcelona,
                bayern, benfica, bvb, chelsea, city, inter, juventus,
                liverpool, milan, porto, psg, real, tottenham, united));

        selecoes.addAll(Arrays.asList(brasil, alemanha, argentina, espanha,
                franca, holanda, inglaterra, italia, mexico, portugal, uruguai, usa));

        timesBrasileiros.addAll(Arrays.asList(atletico, botafogo, corinthians, cruzeiro,
                flamengo, fluminense, gremio, internacional, palmeiras, santos, saoPaulo, vasco));

        todos = Stream.of(timesEuropeus, timesBrasileiros, selecoes).flatMap(x -> x.stream()).collect(Collectors.toList());

        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, PlayerActivity.class);
        for (Time time : todos) {
            if (time.getNome().equals(v.getTag())) {
                intent.putExtra("time", time);
                break;
            }
        }
        startActivity(intent);

    }

    @Override
    public boolean onTouch(View v, MotionEvent evt) {
        switch (evt.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                float reducedValue = 0.93f;
                v.setScaleX(reducedValue);
                v.setScaleY(reducedValue);
                break;
            }
            case MotionEvent.ACTION_UP: {
                v.setScaleX(1);
                v.setScaleY(1);
                break;
            }
        }
        return false;
    }

    public void startButton(ImageButton bt) {
        bt.setOnTouchListener(this);
        bt.setOnClickListener(this);
    }
}