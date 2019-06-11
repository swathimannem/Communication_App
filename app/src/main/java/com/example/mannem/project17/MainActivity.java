package com.example.mannem.project17;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;

import java.util.Locale;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends Activity implements SensorEventListener {
    //using the functions from SensorEventListener
    private  SensorManager smanagerhandle;
    private Sensor rotationshandle;

    private static final int SENSOR_DELAY = 500* 1000;
    // the time between two readings from the sensor in milliseconds
    private static final int FROM_RADS_TO_DEGS = -57;
    // the readings from the sensors are in radians, to be used for this project must be converted to degrees

    Button sector1, sector2, sector3, sector4, sector5, sector6, sector7, sector8, sector9, sector10, collector;
    TextToSpeech t2s;
    String StringtoSpeak = "";
    String currentChar = "";
    Boolean mode = false;
    int sector = 0;
    int count = 0;

    String[][] wordValues = {
            {"Hello ", "How are you ", " How was your day ", "Nice to meet you ", "good ", "bad ", "and you ", "too ", "Thank you ", "I'm sorry ", "Hi my name is ", "Swathi "},
            {"I need ", "I am at ", "Can you help me with ", "Can we go", "Can we do ", "I am ", "We are ", "I feel ", "They are ", "Will you ", "Do ", "Can we play "},
            {"tired ", "awake ", "hot ", "cold ", "happy ", "sad ", "excited ", "upset ", "weird ", "crazy ", "dull ", "fresh "},
            {"homework ", "friends ", "help ", "home ", "car ", "bag ", "pencil ", "life ", "school ", "technology ", "time ", "temperature"},
            {"", "", "", "", "", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", "", "", "", "", ""},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"},
            {"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X"},
            {"stress ", "anxiety ", "Science Fair ", "scrabble ", "Keerthi ", "Aparna ", "Tennis ", "Y", "Z", "Olivia ", "Robotics ", "jasmine"},

            { "byebye", " Hi my name is", " Nice to meet you", " How are you", " How was your day", " Thank you", " good", " bad", " and you", " too", " i'm sorry" , "forthewin"},
            {"", "", "", "", "Can we go", "Can we do", "", "I need", "I am at", "Can you help me with", "", ""},
            {"", "", "", "", "", "", "", "", "", "", "", ""},
            {"", "", "", "", " home", "", "", " homework", " friends", " help", "", ""},
            {"", "", "", "", "", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", "", "", "", "", ""},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"},
            {"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X"},
            {"Y", "Z", "Science Fair", "", "Keerthi", "Aparna", "Tennis", "", "", "Olivia", "Robotics", ""}
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sector1 = (Button) findViewById(R.id.sector1);
        sector2 = (Button) findViewById(R.id.sector2);
        sector3 = (Button) findViewById(R.id.sector3);
        sector4 = (Button) findViewById(R.id.sector4);
        sector5 = (Button) findViewById(R.id.sector5);
        sector6 = (Button) findViewById(R.id.sector6);
        sector7 = (Button) findViewById(R.id.sector7);
        sector8 = (Button) findViewById(R.id.sector8);
        sector9 = (Button) findViewById(R.id.sector9);
        sector10 = (Button) findViewById(R.id.sector10);
        collector = (Button) findViewById(R.id.collector);


        sector1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector = 0;
            }
        }); // if button 1 pressed

        sector2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector = 1;
            }
        }); // if button 2 pressed

        sector3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector = 2;
            }
        }); // if button 3 pressed

        sector4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector = 3;
            }
        }); // if button 4 pressed

        sector5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector = 4;
            }
        }); // if button 5 pressed

        sector6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector = 5;
            }
        }); // if button 6 pressed

        sector7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector = 6;
            }
        }); // if button 7 pressed

        sector8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector = 7;
            }
        }); // if button 8 pressed

        sector9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector = 8;
            }
        }); // if button 9 pressed

        sector10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sector = 9;
            }
        }); // if button 10 pressed

        collector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
            }
        }); // if collect is clicked

        smanagerhandle = (SensorManager) getSystemService(Activity.SENSOR_SERVICE);
        // handle for all sensors
        rotationshandle = smanagerhandle.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        // handle for rotation sensor
        smanagerhandle.registerListener(this, rotationshandle, SENSOR_DELAY);

        t2s = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            // calling text to speech command
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    t2s.setLanguage(Locale.US);
                }
            }
        }); // creating text to speech object

        Intent intent= getIntent();
        mode = intent.getBooleanExtra("workOHome", false);

    } // onCreate - what to do when created

    public void openScreen(View view) {
        String button_text;
        button_text = ((Button)view).getText().toString();
        if (button_text.equals("Settings")) {
            Intent intent = new Intent(this, Settings.class);
            intent.putExtra("workOHome", mode);
            startActivity(intent);
        } // go to settings
    } // what screen to change to

    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == rotationshandle) {
            if (event.values.length > 4) {
                //some devices throw an error if length is > 4. So, take the first 4 values
                float[] truncatedRotationVector = new float[4];
                System.arraycopy(event.values, 0, truncatedRotationVector, 0, 4);
                update(truncatedRotationVector);
            } else {
                update(event.values);
            }
        }
    } // what to do when the sensor values change

    private void update(float[] vectors) {
        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, vectors);
        //remap the axes to make the device display as the instrument
        int worldAxisX = SensorManager.AXIS_X;
        //  int WorldAxisY = SensorManager.AXIS_Y;
        int worldAxisZ = SensorManager.AXIS_Z;
        float[] adjustedRotationMatrix = new float[9];
        SensorManager.remapCoordinateSystem(rotationMatrix, worldAxisX, worldAxisZ, adjustedRotationMatrix);
        float[] orientation = new float[3];
        SensorManager.getOrientation(adjustedRotationMatrix, orientation);
        //  float yaw = orientation[0] * FROM_RADS_TO_DEGS;
        float pitch = orientation[1] * FROM_RADS_TO_DEGS;
        float roll = orientation[2] * FROM_RADS_TO_DEGS;

        if ((count%2) != 0){

            if ((pitch >= -112.5) && (pitch <= -67.5) && (roll >= -22.5) && (roll <=
                    22.5)) {
                t2s.speak(StringtoSpeak, TextToSpeech.QUEUE_ADD, null, null);
                ((TextView) findViewById(R.id.typestring)).setText(StringtoSpeak);
                StringtoSpeak = "";
                currentChar = "";
            } // if flat = say the word

            for (int count = 0; count < 12; count++){
                withinAngles(pitch, roll, count);
            } // loop through all the different positions and check to see if device is in one of them

        } // only get words if the user wants them - facilitates easier use on user

    } // update (used within onSensorChanged)

    public void withinAngles(double pitch, double roll, int position){ // put in min and max for speech
        double[] pitchMin = { -67.5, -22.5,   22.5,   67.5,  22.5, -22.5, 22.5, -67.5, -22.5, -67.5, -22.5, 22.5};
        double[] pitchMax = { -22.5,  22.5,   67.5,  112.5,  67.5,  22.5, 67.5, -22.5,  22.5, -22.5,  22.5, 67.5};
        double[] rollMin =  {-112.5,-112.5, -112.5, -112.5, 167.5,  67.5, 67.5, 167.5, 167.5, -22.5, -22.5,-22.5};
        double[] rollMax =  { -67.5, -67.5,  -67.5,  -67.5,   180, 112.5,112.5,   180,   180,  22.5,  22.5, 22.5};


        if ( (pitch >= pitchMin[position]) && (pitch <= pitchMax[position]) && (roll >= rollMin[position]) && (roll <= rollMax[position])){
            if (mode){ // work values
                if (!(currentChar.equals(wordValues[sector][position]))){
                    currentChar= wordValues[sector][position];
                    StringtoSpeak = StringtoSpeak + currentChar; // change currentChar  to currentString
                    ((TextView) findViewById(R.id.typestring)).setText(StringtoSpeak);
                } // if not the same as the thing immediately before (to prevent continual repetition of the same thing
            } else { // home values
                if (!(currentChar.equals(wordValues[sector+10][position]))) {
                    currentChar = wordValues[sector+10][position];
                    StringtoSpeak = StringtoSpeak + currentChar;
                    ((TextView) findViewById(R.id.typestring)).setText(StringtoSpeak);
                } // if not the same as the thing immediately before (to prevent continual repetition of the same thing
            }
        } // if within that range of angles
    } // withinAngles (used in update)

} //main activity