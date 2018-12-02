package ca.roumani.taxcalc;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);
    }

    public void buttonClicked (View x)
    {
        LinearLayout output = (LinearLayout) findViewById(R.id.output);
        output.removeAllViews();

        String in = ((EditText)findViewById(R.id.incomeBox)).getText().toString();
        Double income = Double.parseDouble(in);
        TaxModel model = new TaxModel();
        model.setIncome(income);

        String tax = model.getTax();
        String averageRate = model.getAverageRate();
        String marginalRate = model.getMarginalRate();
        String cpp = model.getCPP();
        String ei = model.getEI();

        TextView taxLabel = new TextView(this);
        taxLabel.setText("Income Tax:");
        output.addView(taxLabel);

        TextView taxNum = new TextView(this);
        taxNum.setText(tax);
        taxNum.setTypeface(null, Typeface.BOLD);
        output.addView(taxNum);

        TextView averageRateLabel = new TextView(this);
        averageRateLabel.setText("Average Rate:");
        output.addView(averageRateLabel);

        TextView averageRateNum = new TextView(this);
        averageRateNum.setText(averageRate);
        averageRateNum.setTypeface(null, Typeface.BOLD);
        output.addView(averageRateNum);

        TextView marginalLabel = new TextView(this);
        marginalLabel.setText("Marginal Rate:");
        output.addView(marginalLabel);

        TextView marginalRateNum = new TextView(this);
        marginalRateNum.setText(marginalRate);
        marginalRateNum.setTypeface(null, Typeface.BOLD);
        output.addView(marginalRateNum);

        TextView cppLabel = new TextView(this);
        cppLabel.setText("CPP:");
        output.addView(cppLabel);

        TextView cppNum = new TextView(this);
        cppNum.setText(cpp);
        cppNum.setTypeface(null, Typeface.BOLD);
        output.addView(cppNum);

        TextView eiLabel = new TextView(this);
        eiLabel.setText("EI:");
        output.addView(eiLabel);

        TextView eiNum = new TextView(this);
        eiNum.setText(ei);
        eiNum.setTypeface(null, Typeface.BOLD);
        output.addView(eiNum);
    }
}
