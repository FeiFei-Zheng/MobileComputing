package ca.roumani.rex2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.ToneGenerator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    RexModel model = new RexModel();
    ScoreModel score = new ScoreModel();
    private ToneGenerator alarm; //declare tonegenerator

    SensorManager mSensorManager;
    Sensor mAcclerometer;
    private float mAccel;
    private float mCurrent;
    private float mLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newRegex();
        alarm = new ToneGenerator(1,100);// tone volume

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAcclerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this,mAcclerometer,SensorManager.SENSOR_DELAY_NORMAL);

        mAccel = 0.00f;
        mCurrent = SensorManager.GRAVITY_EARTH;
        mLast = SensorManager.GRAVITY_EARTH;
    }

    private void newRegex()
    {
        CheckBox num = (CheckBox)findViewById(R.id.numBox);
        CheckBox letter = (CheckBox)findViewById(R.id.azBox);
        CheckBox se = (CheckBox)findViewById(R.id.seBox);

        if(num.isChecked())
        {
            model.setDigit(true);
        } else {
            model.setDigit(false);
        }
        if(letter.isChecked())
        {
            model.setLetter(true);
        } else {
            model.setLetter(false);
        }
        if(se.isChecked())
        {
            model.setAnchors(true);
        } else {
            model.setAnchors(false);
        }

        model.generate(2);
        String result = model.getRex();
        ((TextView)findViewById(R.id.regexLabel)).setText(result);
    }

    public void checkButton(View x)
    {

        String text = ((EditText)findViewById(R.id.string)).getText().toString();

        String result2 = "" + model.doesMatch(text);
        TextView output = (TextView)findViewById(R.id.log);
        output.append("regex = " + model.getRex() + ",string =" + text +"--->" + result2 + "\n");

        if(text.matches(model.getRex()))
        {
            newRegex();
            score.record(true);
        } else
        {
            alarm.startTone(1,200); //starttone
            score.record(false);
        }

        TextView result = (TextView)findViewById(R.id.score);
        int scoreResult = (int)score.getAverageScore();

        if (score.getElapsedTime() >= 60)
        {
            double mins = Math.floor(score.getElapsedTime() / 60);
            double secs = score.getElapsedTime() % 60;

            String overMinute = "Score=" + scoreResult + "% (" + score.getAttempts() + " attempts in " + mins + " min " + secs + " sec)";
            result.setText(overMinute);
        } else {
            String lessMinute = "Score=" + scoreResult + "% (" + score.getAttempts() + " attempts in " + score.getElapsedTime() + " sec)";
            result.setText(lessMinute);
        }


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        mLast = mCurrent;
        mCurrent = (float)Math.sqrt((double)(x*x + y*y + z*z));

        float delta = mCurrent - mLast;
        mAccel = mAccel * 0.9f + delta;

        if(mCurrent > 14 && mAccel > 2)
        {
            ((EditText)findViewById(R.id.string)).setText("");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }



}
