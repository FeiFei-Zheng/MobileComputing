package ca.roumani.stocktrader;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sophia on 2017-03-16.
 */
public class Equity {
    private String symbol;
    private double bookValue;
    private int qty;
    private Date acquired;
    private String acquiredDate;
    private double marketValue;
    private double yield;


    public Equity(String symbol, int qty, double bookValue, Date acquired, double marketValue, double yield)
    {
        this.symbol = symbol;
        this.qty = qty;
        this.acquired = acquired;
        this.bookValue = bookValue;
        this.marketValue = marketValue;
        this.yield = yield;
    }

    public String getSymbol()
    {
        return this.symbol;
    }

    public int getQty()
    {
        return this.qty;
    }

    public double getBookValue()
    {
        DecimalFormat df2 = new DecimalFormat(".##"); //2 decimal
        bookValue = Double.parseDouble(df2.format(bookValue));
        return this.bookValue;
    }

    public Date getAcquired()
    {
        acquiredDate = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA).format(acquired);
        return this.acquired;
    }

    public double getMarketValue()
    {
        return this.marketValue;
    }

    public double getYield()
    {
        DecimalFormat df2 = new DecimalFormat(".#");
        yield = Double.parseDouble(df2.format(yield));
        return this.yield;
    }

    public String toString()
    {
        String bv = String.format("%.2f",bookValue);
        String mv = String.format("%.2f",marketValue);

        return symbol + " " + qty + " " + bv + " " + acquiredDate + " " + mv + " " + yield;
    }


}
