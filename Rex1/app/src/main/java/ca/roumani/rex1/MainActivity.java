package ca.roumani.rex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RexModel model = new RexModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generateButton(View x)
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
        String text = ((EditText)findViewById(R.id.regexBox)).getText().toString();
        String result2 = "" + model.doesMatch(text);
        TextView output = (TextView)findViewById(R.id.log);
        output.append("regex = " + model.getRex() + ",string =" + text +"--->" + result2 + "\n");

    }
}
