package ca.roumani.tabulator;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TabulatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabulator);
    }

    public void buttonClicked(View x)
    {
        String fromBox = ((EditText)findViewById(R.id.fromBox)).getText().toString();
        Double from = Double.parseDouble(fromBox);

        String toBox = ((EditText)findViewById(R.id.toBox)).getText().toString();
        Double to = Double.parseDouble(toBox);

        String incBox = ((EditText)findViewById(R.id.incBox)).getText().toString();
        Double inc = Double.parseDouble(incBox);

        //
        TableLayout output = (TableLayout)findViewById(R.id.tableLayout);
        output.removeAllViews();

        TableRow titleTable = new TableRow(this);

        TextView incomeTitle = new TextView(this);
        incomeTitle.setText("INCOME");
        titleTable.addView(incomeTitle);

        TextView taxTitle = new TextView(this);
        taxTitle.setText("TAX");
        titleTable.addView(taxTitle);

        TextView aveTitle = new TextView(this);
        aveTitle.setText("Avg");
        titleTable.addView(aveTitle);

        TextView marTitle = new TextView(this);
        marTitle.setText("Mgn");
        titleTable.addView(marTitle);

        TextView cppTitle = new TextView(this);
        cppTitle.setText("CPP");
        titleTable.addView(cppTitle);

        TextView eiTitle = new TextView(this);
        eiTitle.setText("EI");
        titleTable.addView(eiTitle);

        output.addView(titleTable);

        for (double i=from; i<=to; i=i+inc)

        {
            TaxModel model = new TaxModel();
            model.setIncome(i);

            TableRow outTable = new TableRow(this);

            String income = model.income();
            String tax = model.getTax();
            String avRate = model.getAverageRate();
            String marRate = model.getMarginalRate();
            String cpp = model.getCPP();
            String ei = model.getEI();

            TextView inLabel = new TextView(this);
            inLabel.setText(income);
            outTable.addView(inLabel);

            TextView taxLabel = new TextView(this);
            taxLabel.setText(tax);
            outTable.addView(taxLabel);


            TextView avRateLabel = new TextView(this);
            avRateLabel.setText(avRate);
            outTable.addView(avRateLabel);

            TextView marginalLabel = new TextView(this);
            marginalLabel.setText(marRate);
            outTable.addView(marginalLabel);

            TextView cppLabel = new TextView(this);
            cppLabel.setText(cpp);
            outTable.addView(cppLabel);

            TextView eiLabel = new TextView(this);
            eiLabel.setText(ei);
            outTable.addView(eiLabel);

            output.addView(outTable);
        }
    }

}
