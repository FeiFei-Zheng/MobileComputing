package ca.roumani.piglatin;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class PigLatinActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    PigLatinModel model = new PigLatinModel();
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.tts = new TextToSpeech(this,this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pig_latin);
    }

    public void convertButton(View x)
    {
        String text = ((EditText)findViewById(R.id.inputText)).getText().toString();
        model.setEnglish(text);
        String pig = model.getPig();
        say(text);

        TextView output = (TextView)findViewById(R.id.convertedText);
        //output.append(pig + "\n");
        output.setText(pig);

    }

    public void clearButton(View x)
    {
        EditText input = (EditText)findViewById(R.id.inputText);
        input.setText("");
    }

    public void onInit(int initStatus)
    {
        if (initStatus != TextToSpeech.ERROR)
        {
            if (tts.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
            {
                tts.setLanguage(Locale.US);
            }
        } else
        {
            Toast.makeText(this, "Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    public void say(String s)
    {
        if (tts != null)
        {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void speakButton(View x)
    {
        String text = ((TextView)findViewById(R.id.convertedText)).getText().toString();
        say(text);
    }

}
