package com.example.abas.services;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;

public class ThreadPlayer extends Thread {
    private Handler handler;
    private MediaPlayer player;

    public ThreadPlayer(Handler handler, MediaPlayer player) {
        this.handler = handler;
        this.player = player;
    }


    @Override
    public void run() {
        while (player != null) {
            try {
                Message msg = new Message();
                msg.what = player.getCurrentPosition();
                handler.sendMessage(msg);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
