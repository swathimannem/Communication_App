package com.example.mannem.project17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class Instructions extends Activity {

    boolean mode;
  //  String[] changes = new String[240];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        Intent intent= getIntent();
        mode = intent.getBooleanExtra("workOHome", false);
   //     arrayTransfer(intent.getStringArrayExtra("toChange"));
    } // onCreate - match this activity to the correct layout

    public void openScreen (View view) {

        String button_text;
        button_text = ((Button)view).getText().toString();

        if (button_text.equals("Settings")) {
            Intent intent = new Intent(this, Settings.class);
            intent.putExtra("workOHome", mode);
   //         intent.putExtra("toChange", changes);
            startActivity(intent);
        } // go back to settings

        if (button_text.equals("Main")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("workOHome", mode);
    //        intent.putExtra("toChange", changes);
            startActivity(intent);
        } // go back to main

        if (button_text.equals("Combinations")) {
            Intent intent = new Intent(this, Combinations.class);
            intent.putExtra("workOHome", mode);
            //        intent.putExtra("toChange", changes);
            startActivity(intent);
        } // go to Combinations

        if (button_text.equals("Positions")) {
            Intent intent = new Intent(this, Positions.class);
            intent.putExtra("workOHome", mode);
            //        intent.putExtra("toChange", changes);
            startActivity(intent);
        } // go to Settings

    } // openScreen - what to do when buttons are clicked

    public void arrayTransfer (String[] changes){
        for (int x = 0; x<changes.length; x++){
     //       this.changes[x] = changes[x];
        } // go through entire array
    } // arrayTransfer

} // Instructions class