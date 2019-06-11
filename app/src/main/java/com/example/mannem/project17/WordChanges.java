package com.example.mannem.project17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WordChanges extends Activity {

    boolean mode;
    EditText modeValue, buttonValue, positionValue, wordPhrase;
    String modeVal;
    int modeValInt;
    int buttonValInt;
    int positionValInt;
  //  String[] changes = new String[240];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_changes);
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
    //        intent.putExtra("toChange", changes);
            startActivity(intent);
        } // go back to settings
    } // openScreen - what to do when buttons are clicked

    public void saveValues (View view) {
        //textValues = (EditText)findViewById(R.id.textToSpeak);
        //String textValuesReturn = textValues.getText().toString();
        modeValue = (EditText)findViewById(R.id.modeWOH);
        buttonValue = (EditText)findViewById(R.id.buttonNumber);
        positionValue = (EditText)findViewById(R.id.positionNumber);
        wordPhrase = (EditText)findViewById(R.id.editWord);
        modeVal = modeValue.getText().toString();
        buttonValInt= Integer.valueOf(buttonValue.getText().toString());
        positionValInt = Integer.valueOf(positionValue.getText().toString());
        if (modeVal.equals("Work") || modeVal.equals("work")){
            modeValInt = 0;
        } else {
            modeValInt = 10;
        } // set the row number to the appropriate row for the mode
        //changes[modeValInt + buttonValInt - 1][positionValInt-1] =  wordPhrase.getText().toString();
    //    changes[10*(modeValInt+buttonValInt-1) + positionValInt-1] = wordPhrase.getText().toString();
    } // saveValues - what to do when saveValues is clicked

    public void arrayTransfer (String[] changes){
        for (int x = 0; x<changes.length; x++){
     //       this.changes[x] = changes[x];
        } // go through entire array
    } // arrayTransfer

} // WordChanges
