package ca.roumani.stocktrader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jba.roumani.lib.Stock;

/**
 * Created by sophia on 2017-03-16.
 */
public class PortfolioAnalyzer {
    private String title;
    private String[] rows;
    private int porfolioSize;
    private List pList;
    private double bookValue;
    private double marketValue;
    private double investmentMarketValue;
    private double investmentYield;
    private double pMarketValue;
    private int qty;
    private double eLeft;
    private double eRight;
    private double pYield;

    public  PortfolioAnalyzer(String title, String[] rows)
    {
        this.title = title;
        this.rows = rows;
    }

    public List getPortfolio()
    {
        for(int i=0;i<rows.length;i++)
        {
            pList.add(rows[i]);
        }
        return pList;  //not use
    }

    public int getPortfolioSize()
    {
        porfolioSize = rows.length;
        return this.porfolioSize;
    }

    public void getQty(int qty)
    {
        this.qty = qty;
    }

    public double getInvestmentMarketValue(String symbol)
    {
        Stock st = new Stock(symbol);
        this.marketValue = st.getPrice();
        investmentMarketValue = marketValue * this.qty;
        return investmentMarketValue;
    }

    public double getInvestmentYield(double bookValue, double marketValue, Date acquired)
    {
        Date now = new Date();
        long diff = now.getTime() - acquired.getTime();
        double days = diff / 86400000;

        this.bookValue = bookValue;
        investmentYield = ((marketValue - bookValue) / bookValue)*(365/days);
        investmentYield = investmentYield * 100;

        return investmentYield;
    }

    public double getPorffolioMarketValue()
    {
        pMarketValue += investmentMarketValue;
        return pMarketValue;
    }

    public double getPortfolioYield()
    {
        eLeft += qty * bookValue * investmentYield;
        eRight += qty * bookValue;
        pYield = eLeft / eRight;
        return pYield;
    }

    //not use this method.
    public String toString()
    {
        String pMarketValue = String.format("%,.2f",investmentMarketValue);
        String pYieldString = String.format("%.2f",pYield);
        return pMarketValue + " " + pYieldString;
    }

}
