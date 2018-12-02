package ca.roumani.taxcalc;

/**
 * Created by sophia on 2017-02-08.
 */
public class TaxModel {
    private double income;
    private double tax, average, marginal, cpp, ei;

    public static final double BRACKET_0 = 11475.0;
    public static final double BRACKET_1 = 33808.0;
    public static final double BRACKET_2 = 40895.0;
    public static final double BRACKET_3 = 63823.0;
    public static final double RATE_1 = 22.79 / 100.0;
    public static final double RATE_2 = 33.23 / 100.0;
    public static final double RATE_3 = 45.93 / 100.0;
    public static final double RATE_4 = 52.75 / 100.0;

    public static final double EI_RATE = 1.63/100.0;
    public static final double EI_MAX = 836.19;

    public static final double CPP_RATE = 4.95/100.0;
    public static final double CPP_MAX = 2564.10;
    public static final double CPP_EXEMPT = 3500.0;

    public void setIncome(double k)
    {
        this.income = k;
    }

    public String getTax()
    {
        this.tax= 0;
        double part1 = this.income - BRACKET_0;
        double part2 = this.income - BRACKET_0 - BRACKET_1;
        double part3 = this.income - BRACKET_0 - BRACKET_1 - BRACKET_2;
        double part4 = this.income - BRACKET_0 - BRACKET_1 - BRACKET_2 - BRACKET_3;

        if (this.income < BRACKET_0)
        {
            this.marginal = 0;
        }
        else if (this.income >= BRACKET_0 && part1 <= BRACKET_1)
        {
            this.tax = part1 * RATE_1;
            this.marginal = RATE_1;
        } else if (part1 > BRACKET_1 && part2 <= BRACKET_2)
        {
            this.tax = BRACKET_1 * RATE_1 + part2 * RATE_2;
            this.marginal = RATE_2;
        } else if (part2 > BRACKET_2 && part3 <= BRACKET_3)
        {
            this.tax = BRACKET_1 * RATE_1 + BRACKET_2 * RATE_2 + part3 * RATE_3;
            this.marginal = RATE_3;
        } else if (part3 > BRACKET_3)
        {
            this.tax = BRACKET_1 * RATE_1 + BRACKET_2 * RATE_2 + BRACKET_3 * RATE_3 + part4 * RATE_4;
            this.marginal = RATE_4;
        }

        String taxResult = String.format("%,.2f",Math.round(this.tax));
        return taxResult;
    }

    public String getAverageRate()
    {
        this.average = Math.round(this.tax / this.income * 100);
        String averageRateResult = String.format("%.0f",this.average);
        return averageRateResult + "%";
    }

    public String getMarginalRate()
    {
        this.marginal = Math.round(this.marginal * 100);
        String marginalRateResult = String.format("%.0f",this.marginal);
        return marginalRateResult + "%";
    }

    public String getCPP()
    {
        if (this.income <= CPP_EXEMPT)
        {
            this.cpp = 0;
        } else
        {
            this.cpp = (this.income - CPP_EXEMPT) * CPP_RATE;
        }

        if (this.cpp > CPP_MAX)
        {
            this.cpp = CPP_MAX;
        }

        String cppResult = String.format("%,.0f",Math.round(this.cpp));
        return cppResult;
    }

    public String getEI()
    {
        this.ei = this.income * EI_RATE;
        if (this.ei > EI_MAX)
        {
            this.ei = EI_MAX;
        }

        String eiResult= String.format("%.0f",Math.round(this.ei));

        return eiResult;
    }

    public static void main(String[] args) {
        TaxModel model = new TaxModel();
        model.setIncome(55000);
        System.out.println(model.getTax());
        System.out.println(model.getAverageRate());
        System.out.println(model.getMarginalRate());
        System.out.println(model.getCPP());
        System.out.println(model.getEI());

        model.setIncome(52500);
        System.out.println(model.getTax());
        System.out.println(model.getAverageRate());
        System.out.println(model.getMarginalRate());
        System.out.println(model.getCPP());
        System.out.println(model.getEI());
    }


}
