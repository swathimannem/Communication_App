package com.example.mannem.project17;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calibration extends Activity implements SensorEventListener{

    //using the functions from SensorEventListener
    private SensorManager smanagerhandle;
    private Sensor rotationshandle;

    float pitch;
    float roll;

    private static final int SENSOR_DELAY = 500* 1000;
    // the time between two readings from the sensor in milliseconds
    private static final int FROM_RADS_TO_DEGS = -57;
    // the readings from the sensors are in radians, to be used for this project must be converted to degrees */

    boolean mode;
    int startStopCount;
    TextView position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calibration);

        Intent intent= getIntent();
        mode = intent.getBooleanExtra("workOHome", false);

        smanagerhandle = (SensorManager) getSystemService(Activity.SENSOR_SERVICE);
        // handle for all sensors
        rotationshandle = smanagerhandle.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        // handle for rotation sensor
        smanagerhandle.registerListener(this, rotationshandle, SENSOR_DELAY);

        position = (TextView) findViewById(R.id.toTurn);
        position.setText("");

    } // onCreate - match this activity to the correct layout

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

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    private void update(float[] vectors) {
        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, vectors);
        //remap the axes to make the device display as the instrument
        int worldAxisX = SensorManager.AXIS_X;
        int worldAxisZ = SensorManager.AXIS_Z;
        float[] adjustedRotationMatrix = new float[9];
        SensorManager.remapCoordinateSystem(rotationMatrix, worldAxisX, worldAxisZ, adjustedRotationMatrix);
        float[] orientation = new float[3];
        SensorManager.getOrientation(adjustedRotationMatrix, orientation);
        pitch = orientation[1] * FROM_RADS_TO_DEGS;
        roll = orientation[2] * FROM_RADS_TO_DEGS;

        if ((startStopCount%2) != 0){

            if ((pitch >= -112.5) && (pitch <= -67.5) && (roll >= -22.5) && (roll <= 22.5)) {
                position.setText("You are at the position flat");
            } // if flat = say the word

            for (int count = 0; count < 12; count++){
                withinAngles(pitch, roll, count);
            } // loop through all the different positions and check to see if device is in one of them

        } // only get words if the user wants them - facilitates easier use on user

    } // update (used within onSensorChanged)

    public void openScreen (View view) {
        String button_text;
        button_text = ((Button)view).getText().toString();
        if (button_text.equals("Settings")) {
            Intent intent = new Intent(this, Settings.class);
            intent.putExtra("workOHome", mode);
            startActivity(intent);
        } // go back to settings
        if (button_text.equals("Main")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("workOHome", mode);
            startActivity(intent);
        } // go back to settings
    } // openScreen - what to do when buttons are clicked

    public void startStop (View view) {
        startStopCount ++;
    } // startStop method

    public void withinAngles(double pitch, double roll, int position){ // put in min and max for speech
        double[] pitchMin = { -67.5, -22.5,   22.5,   67.5,  22.5, -22.5, 22.5, -67.5, -22.5, -67.5, -22.5, 22.5};
        double[] pitchMax = { -22.5,  22.5,   67.5,  112.5,  67.5,  22.5, 67.5, -22.5,  22.5, -22.5,  22.5, 67.5};
        double[] rollMin =  {-112.5,-112.5, -112.5, -112.5, 167.5,  67.5, 67.5, 167.5, 167.5, -22.5, -22.5,-22.5};
        double[] rollMax =  { -67.5, -67.5,  -67.5,  -67.5,   180, 112.5,112.5,   180,   180,  22.5,  22.5, 22.5};
        String[] positionName = {"Position 1", "Position 2", "Position 3", "Position 4", "Position 5", "Position 6", "Position 7", "Position 8", "Position 9", "Position 10", "Position 11", "Position 12"};

        if ( (pitch >= pitchMin[position]) && (pitch <= pitchMax[position]) && (roll >= rollMin[position]) && (roll <= rollMax[position])){
            this.position.setText(positionName[position]);
        } // if within that range of angles
    } // withinAngles (used in update)

    public void arrayTransfer (String[] changes){
        for (int x = 0; x<changes.length; x++){
        } // go through entire array
    } // arrayTransfer

} // Calibration class
