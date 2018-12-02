package ca.roumani.stocktrader;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import jba.roumani.lib.Stock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickAnalyze(View x) throws ParseException {

        TextView summary = (TextView)findViewById(R.id.summary);
        /*--------------------------------Table Layout under this line--------------------------------------------*/
        TableLayout output = (TableLayout)findViewById(R.id.myTableLayout);
        output.removeAllViews();

        //title row
        TableRow titleRow = new TableRow(this);
        //titleRow.setGravity(5);

        TextView symbolTitle = new TextView(this);
        symbolTitle.setText("Symbol");
        titleRow.addView(symbolTitle);

        TextView qtyTitle = new TextView(this);
        qtyTitle.setText("Quantity");
        titleRow.addView(qtyTitle);

        TextView bookValueTitle = new TextView(this);
        bookValueTitle.setText("BookValue");
        titleRow.addView(bookValueTitle);

        TextView acquiredTitle = new TextView(this);
        //padding left; padding right("%1$-" + num + "s", x)
        String space = String.format("%1$" + 10 + "s","Acquired");
        acquiredTitle.setText(space);
        titleRow.addView(acquiredTitle);

        TextView marketValueTitle = new TextView(this);
        marketValueTitle.setText("MarketValue");
        titleRow.addView(marketValueTitle);

        TextView yieldTitle = new TextView(this);
        yieldTitle.setText("Yield");
        titleRow.addView(yieldTitle);

        output.addView(titleRow);

        /*--------------------------------portfolio data under this line----------------------------------------*/

        String name = ((EditText)findViewById(R.id.portfolioName)).getText().toString();
        name = name.toLowerCase();

        //jump into ListActivity page.
        if(name.equals("?"))
        {
            Intent intent = new Intent(this, ListActivity.class);
            this.startActivity(intent);
            summary.setText("");

            return;
        }

        //get XML data
        int holder = getResources().getIdentifier(name, "array", this.getPackageName());
        String[] data = getResources().getStringArray(holder);
        //int size = getResources().getStringArray(holder).length;

        //portfolio summary
        PortfolioAnalyzer pa = new PortfolioAnalyzer(name, data);
        int size = pa.getPortfolioSize();

        for(int i = 0; i < data.length; i++)
        {
            //get every single string in array
            String[] item = Arrays.copyOfRange(data, i, i + 1);
            String getItem = item[0];                           //single item in XML
            String[] elements = getItem.split(",");

            String symbol = elements[0];
            int qty = Integer.parseInt(elements[1]);
            double bookValue = Double.parseDouble(elements[2]);
            String date = elements[3];
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date d = df.parse(date);

            Stock stock = new Stock(symbol);  //Stock API
            double mk = stock.getPrice();

            //calculate yield.
            pa.getQty(qty);
            double yield = pa.getInvestmentYield(bookValue,mk,d);
            pa.getInvestmentMarketValue(symbol);
            double inverstMV = pa.getPorffolioMarketValue();
            double pYield = pa.getPortfolioYield();

            //invoke equity API
            Equity equity = new Equity(symbol,qty,bookValue,d,mk,yield);

            String getSymbol = equity.getSymbol();
            int getQty = equity.getQty();
            double getBookValue = equity.getBookValue();
            Date getAquired = equity.getAcquired();
            double getMarketValue = equity.getMarketValue();
            double getYield = equity.getYield();

            String result = equity.toString();
            String[] resultArr = result.split(" ");

            //create data rows
            TableRow dataRow = new TableRow(this);

            TextView symData = new TextView(this);
            String symAlign = String.format("%8s",resultArr[0]);
            symData.setText(symAlign);
            dataRow.addView(symData);

            TextView qtyData = new TextView(this);
            String qtyAlign = String.format("%8s",resultArr[1]);
            qtyData.setText(qtyAlign);
            dataRow.addView(qtyData);

            TextView bvData = new TextView(this);
            String bvAlgin = String.format("%10s",resultArr[2]);
            bvData.setText(bvAlgin);
            dataRow.addView(bvData);

            TextView acqData = new TextView(this);
            String acAlign = String.format("%10s",resultArr[3]);
            acqData.setText(acAlign);
            dataRow.addView(acqData);

            TextView mkData = new TextView(this);
            String mvAlign = String.format("%10s",resultArr[4]);
            mkData.setText(mvAlign);
            dataRow.addView(mkData);

            TextView yieldData = new TextView(this);
            String yieldString = resultArr[5] + "%";
            yieldData.setText(yieldString);
            dataRow.addView(yieldData);

            output.addView(dataRow);

            String investmentMV = String.format("%,.2f",inverstMV);
            String pYieldString = String.format("%.1f",pYield);

            /*--------------------------------summary under this line--------------------------------------------*/

            String equities = "The " + name + " portfolio consists of " + size + " equities." + "\n";
            String value = "It has a market value of $ " + investmentMV + " and a yield of " + pYieldString + "% (annualized).";
            String finalSummary = equities + value;
            summary.setText(finalSummary);
        }
    }

}
