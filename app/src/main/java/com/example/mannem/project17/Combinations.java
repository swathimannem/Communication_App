package com.example.mannem.project17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class Combinations extends Activity {

    boolean mode;
    //  String[] changes = new String[240];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combinations);
        Intent intent= getIntent();
        mode = intent.getBooleanExtra("workOHome", false);
        //     arrayTransfer(intent.getStringArrayExtra("toChange"));
    } // onCreate - match this activity to the correct layout

    public void openScreen (View view) {
        String button_text;
        button_text = ((Button)view).getText().toString();
        if (button_text.equals("Instructions")) {
            Intent intent = new Intent(this, Instructions.class);
            intent.putExtra("workOHome", mode);
            //         intent.putExtra("toChange", changes);
            startActivity(intent);
        } // go back to instructions
    } // openScreen - what to do when buttons are clicked

    public void arrayTransfer (String[] changes){
        for (int x = 0; x<changes.length; x++){
            //       this.changes[x] = changes[x];
        } // go through entire array
    } // arrayTransfer

} // combinations
