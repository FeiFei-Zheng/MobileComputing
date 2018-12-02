package ca.roumani.tab2;

/**
 * Created by sophia on 2017-02-15.
 */
public class TabModel {
    private  double income;
    private double tax, av, mar, cpp, ei;

    public static final double b0 = 11475.0;
    public static final double b1 = 33808.0;
    public static final double b2 = 40895.0;
    public static final double b3 = 63823.0;

    public static final double r1 = 22.79 / 100.0;
    public static final double r2 = 33.23 / 100.0;
    public static final double r3 = 45.93 / 100.0;
    public static final double r4 = 52.75 / 100.0;

    public static final double eiRate = 4.95 / 100.0;
    public static final double eiMax = 836.19;

    public static final double cppRate = 4.95 / 100.0;
    public static final double cppMax = 2564.10;
    public static final double cppExempt = 3500.0;

    public Double setIncome(double n)
    {
        this.income = n;
    }

    public getTax()
    {
        
    }
}
