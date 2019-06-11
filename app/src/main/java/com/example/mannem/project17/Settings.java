package com.example.mannem.project17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class Settings extends Activity{

    boolean mode;
 //   String[] changes = new String[240];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.workHome);

        Intent intent= getIntent();
        mode = intent.getBooleanExtra("workOHome", false);
        toggle.setChecked(mode);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mode = true;
                } else {
                    mode = false;
                } // if else statement
            } // onCheckedChanged
        }); // onCheckedChangeListener

    } // onCreate - what to do when created

    public void openScreen(View view) {

        String button_text;
        button_text = ((Button)view).getText().toString();

        if (button_text.equals("Main")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("workOHome", mode);
            startActivity(intent);
        } // go back to main screen

        if (button_text.equals("Instructions")) {
            Intent intent = new Intent(this, Instructions.class);
            intent.putExtra("workOHome", mode);
            startActivity(intent);
        } // go to Instructions

        if (button_text.equals("Calibration")) {
            Intent intent = new Intent(this, Calibration.class);
            intent.putExtra("workOHome", mode);
            startActivity(intent);
        } // go to Calibration

        if (button_text.equals("Text to Speech")) {
            Intent intent = new Intent(this, TextSpeech.class);
            intent.putExtra("workOHome", mode);
            startActivity(intent);
        } // go to TextSpeech

        if (button_text.equals("Words")) {
            Intent intent = new Intent(this, WordChanges.class);
            intent.putExtra("workOHome", mode);
            startActivity(intent);
        } // go to TextSpeech

    } // openScreen - what to do when transition buttons are clicked

    public void arrayTransfer (String[] changes){
        for (int x = 0; x<changes.length; x++){
        } // go through entire array
    } // arrayTransfer

} // Settings class