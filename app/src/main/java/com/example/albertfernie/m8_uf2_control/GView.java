package com.example.albertfernie.m8_uf2_control;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;

/**
 * Created by Albert Fernandez on 28/02/2017.
 */

public class GView extends View {

    Bitmap fondo, raqueta, bola;
    private MediaPlayer mp1, mp2, mp3;
    float tamRaqueta, xRaqueta = 0, yRaqueta, xBola=0, yBola=0;
    int width, height, sentidoX=1, sentidoY=1;
    boolean start = true, tStart = true;
    data data = new data();

    public GView(Context context) {
        super(context);
        inicio();
    }

    private void inicio(){
        fondo = BitmapFactory.decodeResource(getResources(), R.drawable.sunshine);
        raqueta = BitmapFactory.decodeResource(getResources(), R.drawable.raqueta);
        bola = BitmapFactory.decodeResource(getResources(), R.drawable.tennis_ball);
        mp1 = MediaPlayer.create(super.getContext(), R.raw.golpecomico1);
        mp2 = MediaPlayer.create(super.getContext(), R.raw.golpemetalico3);
        mp3 = MediaPlayer.create(super.getContext(), R.raw.game_sound);
        mp3.setLooping(true);
        tamRaqueta = 150;
        MyThread myThread = new MyThread(100, this);
        myThread.setAlive(true);
        myThread.start();
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(fondo, 0, 0, null);
        width = canvas.getWidth();
        height = canvas.getHeight();
        yRaqueta = height / 10 * 8;
        if(start) {
            xRaqueta = width / 2;
            xBola = width / 2;
            yBola = height / 2;
            start = false;
        }
        canvas.drawBitmap(raqueta, xRaqueta - tamRaqueta, yRaqueta, null);
        canvas.drawBitmap(bola, xBola, yBola, null);
    }

    public boolean onTouchEvent(MotionEvent event) {
        xRaqueta = event.getX();
        if(tStart) {
            mp3.start();
            tStart = false;
        }
        this.invalidate();
        return true;
    }

    public void taskTimer(){
        posicionBola();
        //this.invalidate();
    }

    public void posicionBola(){
        int deltaX = 40, deltaY = 20;
        if(data.points%10==0){
            deltaX += 2;
            deltaY += 1;
        }
        if(xBola>=width - 90) {
            sentidoX = -1;
            mp1.start();
        }
        if(xBola<xRaqueta){}
        if (yBola>=yRaqueta - 90 && xBola>=xRaqueta && xBola<xRaqueta+raqueta.getWidth()) {
            sentidoY = -1;
            mp2.start();
            data.points += 10;
        }
        if(xBola<=0) {
            sentidoX = 1;
            mp1.start();
        }
        if(yBola<=0) {
            sentidoY = 1;
            mp1.start();
        }
        if(yBola > yRaqueta){
            msToast("Ha conseguido " + String.valueOf(data.points) + " puntos");
            sentidoX = 0;
            sentidoY = 0;
            data.points = 0;
        }
        xBola += deltaX * sentidoX;
        yBola += deltaY * sentidoY;
    }

    public void msToast(String text){
        Context context = this.getContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
