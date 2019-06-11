package com.example.mannem.project17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;



public class TextSpeech extends Activity {

    TextToSpeech t2s;
    EditText textValues;
    boolean mode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_speech);
        t2s = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            // calling text to speech command
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t2s.setLanguage(Locale.US);
                }
            }
        });

        Intent intent = getIntent();
        mode = intent.getBooleanExtra("workOHome", false);

    } //// onCreate - what to do when created

    public void openScreen(View view) {
        String button_text;
        button_text = ((Button) view).getText().toString();

        if (button_text.equals("Main")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("workOHome", mode);
            startActivity(intent);
        } // go back to main screen

        if (button_text.equals("Settings")) {
            Intent intent = new Intent(this, Settings.class);
            intent.putExtra("workOHome", mode);
            startActivity(intent);
        } // go to Settings

        if (button_text.equals("Speak")) {
            textValues = (EditText) findViewById(R.id.textToSpeak);
            String textValuesReturn = textValues.getText().toString();
            t2s.speak(textValuesReturn, TextToSpeech.QUEUE_ADD, null, null);
            ((TextView) findViewById(R.id.textToSpeak)).setText("");
        }

    } // openScreen - what to do when transition buttons are clicked
} // TextSpeech