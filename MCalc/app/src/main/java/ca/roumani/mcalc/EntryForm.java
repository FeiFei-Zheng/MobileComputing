package ca.roumani.mcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import hr.YumModel;

public class EntryForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_form);
    }

    public void buttonClicked(View x)
    {
        String principle = ((EditText) findViewById(R.id.principleBox)).getText().toString();
        String amort = ((EditText) findViewById(R.id.amortBox)).getText().toString();
        String interest = ((EditText) findViewById(R.id.interestBox)).getText().toString();

        MortgageModel mortgageModel = new MortgageModel(principle, amort, interest);
        String result = mortgageModel.computePay();

        ((TextView) findViewById(R.id.result)).setText(result);
    }

    public void buttonClickedYum(View y)
    {
        String principle = ((EditText) findViewById(R.id.principleBox)).getText().toString();
        String amort = ((EditText) findViewById(R.id.amortBox)).getText().toString();
        String interest = ((EditText) findViewById(R.id.interestBox)).getText().toString();

        YumModel yumModel = new YumModel();
        yumModel.setAmortization(amort);
        yumModel.setPrinciple(principle);
        yumModel.setInterest(interest);
        String yumResult = yumModel.computePayment();

        ((TextView) findViewById(R.id.yuResult)).setText(yumResult);
    }
}
