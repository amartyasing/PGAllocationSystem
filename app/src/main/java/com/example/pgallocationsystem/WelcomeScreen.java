package com.example.pgallocationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity implements TextToSpeech.OnInitListener {
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        textToSpeech=new TextToSpeech(WelcomeScreen.this,WelcomeScreen.this);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomeScreen.this,MainActivity.class);
                startActivity(intent);
                WelcomeScreen.this.finish();

            }
        }, 6000);
    }

    @Override
    public void onInit(int i) {
        String msg="Welcome to PG Allocation System";
        textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH,null);
        if(i==TextToSpeech.LANG_NOT_SUPPORTED || i==TextToSpeech.LANG_MISSING_DATA)
        {
            Toast.makeText(this, "not supported", Toast.LENGTH_SHORT).show();
        }



    }
}
