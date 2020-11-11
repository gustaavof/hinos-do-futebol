package com.example.abas.services;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.abas.R;
import com.example.abas.activity.PlayerActivity;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


@RequiresApi(api = Build.VERSION_CODES.O)
public class DownloadService extends AsyncTask<String, String, String> {

    private PlayerActivity activity;
    private NotificationCompat.Builder notification;
    private NotificationManagerCompat notificationManagerActivity;
    private String path;

    public DownloadService(PlayerActivity activity, String path) {
        this.activity = activity;
        this.path = path;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity, "O hino está sendo baixado!", Toast.LENGTH_SHORT).show();
        buildNotification();
    }


    @Override
    protected String doInBackground(String... strings) {
        try {
            // conexão
            URL url = new URL(strings[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            // tamanho do arquivo
            int fileLength = connection.getContentLength();

            // input
            InputStream input = connection.getInputStream();

            // output
            OutputStream output = new FileOutputStream(path);

            // download
            byte data[] = new byte[4096];
            long total = 0;
            int count;

            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);
                updateNotification((int) total * 100, fileLength, false);
            }
            SystemClock.sleep(1000);
            updateNotification(0, 0, true);
            output.flush();
            output.close();
            input.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    private void updateNotification(int current, int total, boolean isDone) {
        if (!isDone) {
            notification.setProgress(100, current / total, false);
            notificationManagerActivity.notify(1, notification.build());
        } else {
            notification.setContentText("Download Finalizado!")
                    .setProgress(0, 0, false)
                    .setOngoing(false);
            notificationManagerActivity.notify(1, notification.build());
            activity.updateButton();
        }

    }

    private void buildNotification() {
        notificationManagerActivity = NotificationManagerCompat.from(activity);
        NotificationChannel c1 = new NotificationChannel("c1", "Hinos", NotificationManager.IMPORTANCE_LOW);
        NotificationManager manager = activity.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(c1);

        notification = new NotificationCompat.Builder(activity, "c1")
                .setSmallIcon(R.drawable.ic_football)
                .setContentTitle("Download Hino")
                .setContentText("Download em Progresso!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setProgress(100, 0, false);
        notificationManagerActivity.notify(1, notification.build());
    }
}