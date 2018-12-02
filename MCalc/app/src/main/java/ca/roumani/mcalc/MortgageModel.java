package ca.roumani.mcalc;

import java.io.PrintStream;
import java.util.Scanner;

import hr.YumModel;

/**
 * Created by sophia on 2017-01-26.
 */
public class MortgageModel {
    private double principle;
    private int amort;
    private double interest;

    public MortgageModel(String p, String n, String r)
    {
        this.principle = Double.parseDouble(p);
        this.amort = Integer.parseInt(n);
        this.interest = Double.parseDouble(r);
    }

    public String computePay()
    {
        int month = this.amort * 12;
        double rate = this.interest / 100 / 12;

        double temp = month * (month - 1) * rate * rate / 2;
        temp = temp + month * rate + 1;
        temp = 1 / temp;
        temp = 1 - temp;
        double compute = (double) Math.round(rate * this.principle / temp * 100) / 100;

        String result = String.format("%,.2f", compute);
        return "$" + result;
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        PrintStream output = System.out;

        MortgageModel model = new MortgageModel("700000","25","4.5");
        output.println(model.computePay());

        model = new MortgageModel("950000","21","2.9");
        output.println(model.computePay());

        model = new MortgageModel("650000","27","3.7");
        output.println(model.computePay());
    }

}
