package com.example.xilofono;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1,button2, button3, button4,  button5, button6, button7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void presionRe(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.note1);
        mp.start();
    }
    public void presionMi(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.note2);
        mp.start();
    }
    public void presionFa(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.note3);
        mp.start();
    }
    public void presionSol(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.note4);
        mp.start();
    }
    public void presionLa(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.note5);
        mp.start();
    }
    public void presionSi(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.note6);
        mp.start();
    }
    public void presionDo(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.note7);
        mp.start();
    }

}
