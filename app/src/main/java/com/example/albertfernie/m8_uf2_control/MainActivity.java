package com.example.albertfernie.m8_uf2_control;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button start, newP;
    private MediaPlayer mp;
    private TextView user, password;
    BD bd = new BD(this, "DB_Game", null, 1);
    data data = new data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicio();
    }

    public void newPlayer(View view){
        String name = user.getText().toString();
        String pass = password.getText().toString();
        String point = String.valueOf(data.points);
        //if(name.equals("") || pass.equals("")) msToast("Please, insert all data");
        int _res = bd.insertar(name, pass, point); // se envian los datos para insertar
        if(_res == 1){
            msToast("Welcome, " + name);
        }
        else{
            msToast("We have a problem, please repeat");
        }
    }

    public void StartGame(View view){
        String name = user.getText().toString();
        String pass = password.getText().toString();
        //if(name.equals("") || pass.equals("")) msToast("Please, insert all data");
        //else {
            Intent intent= new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
            mp.stop();
        //}
    }

    private void inicio(){
        mp = MediaPlayer.create(this, R.raw.start_layout);
        traerReferencias();
        mp.setLooping(true);
        mp.start();
    }

    private void traerReferencias(){
        start = (Button) findViewById(R.id.btStart);
        newP = (Button) findViewById(R.id.btNew);
        user = (EditText) findViewById(R.id.etUser);
        password = (EditText) findViewById(R.id.etPassword);
    }

    public void msToast(String text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
