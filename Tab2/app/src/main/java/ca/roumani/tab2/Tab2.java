package ca.roumani.tab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Tab2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
    }

    public void buttonClicked(View x)
    {
        String fromBox = ((EditText)findViewById(R.id.fromBox)).getText().toString();
        String toBox = ((EditText)findViewById(R.id.toBox)).getText().toString();
        String incBox = ((EditText)findViewById(R.id.incBox)).getText().toString();

        double from = Double.parseDouble(fromBox);
        double to = Double.parseDouble(toBox);
        double inc = Double.parseDouble(incBox);

        TableLayout output = (TableLayout)findViewById(R.id.tableLayout);

        TableRow title = new TableRow(this);

        TextView income = new TextView(this);
        income.setText("INCOME");
        title.addView(income);

        TextView tax = new TextView(this);
        tax.setText("TAX");
        title.addView(tax);

        TextView avRate = new TextView(this);
        avRate.setText("Avg");
        title.addView(avRate);

        TextView marRate = new TextView(this);
        marRate.setText("Mgn");
        title.addView(marRate);

        TextView cpp = new TextView(this);
        cpp.setText("CPP");
        title.addView(cpp);

        TextView ei = new TextView(this);
        ei.setText("EI");
        title.addView(ei);

        output.addView(title);

        for (double i = from; i <= to; i = i + inc)
        {

        }
    }
}
