package ca.roumani.rex2;

import java.util.Random;

/**
 * Created by sophia on 2017-03-04.
 */
public class RexModel
{
    public static final int SET_SIZE = 3;
    private boolean hasDigits;
    private boolean hasLetters;
    private boolean hasAnchors;
    private String myRegex;
    private Random rng;

    public RexModel()
    {
        this.hasDigits = true;
        this.hasLetters = true;
        this.hasAnchors = true;
        this.myRegex = "";
        this.rng = new Random();
    }
    public void setDigit(boolean digit)
    {
        this.hasDigits = digit;
    }
    public void setLetter(boolean letter)
    {
        this.hasLetters = letter;
    }
    public void setAnchors(boolean anchors)
    {
        this.hasAnchors = anchors;
    }
    public String getRex()
    {
        return this.myRegex;
    }
    public boolean doesMatch(String s)
    {
        return s.matches(this.myRegex);
    }
    public void generate(int repeat)
    {
        this.myRegex ="";
        int i;
        for (i = 0; i<repeat; i++)
        {
            if (hasDigits)
            {
                genDigit();
            }
            if (hasLetters)
            {
                genLetter();
            }
        }
        if (hasAnchors)
        {
            this.myRegex = "^" + this.myRegex + "$";
        }
    }

    private void genDigit()
    {

        if (rng.nextDouble() < 0.5)
        {
            this.myRegex = this.myRegex + "[0-9]";
        } else {
            String temp = "[";

            while (temp.length()<SET_SIZE+1)
            {
                String newInt = Integer.toString(this.rng.nextInt(10));
                // make the digits uniqueness.
                temp = (temp.contains(newInt) ? temp : temp + newInt);
            }
            temp = temp + "]";
            this.myRegex = this.myRegex + temp;

        }

        genQuantifier();

    }

    private void genLetter()
    {
        int ran = rng.nextInt(100);
        String chars = "abcdefghigklmnopqrstuvwxyz";
        if (ran < 50 && ran > 0)
        {
            this.myRegex = this.myRegex + "[a-z]";
        }
        if (ran >= 50 && ran < 75)
        {
            String temp = "[^";
            for (int i = 0; i < SET_SIZE ; i++)
            {
                //make the letter uniqueness.
                String newLetter = String.valueOf(chars.charAt(rng.nextInt(26)));
                //temp = (temp.contains(newLetter) ? temp : temp + newLetter);
                if (!temp.contains(newLetter))
                {
                    temp += newLetter;
                }
                else
                {
                    i--;
                }
            }
            this.myRegex = this.myRegex + temp + "]";
        }

        if (ran >= 75)
        {
            String temp = "[";
            for (int i = 0; i < SET_SIZE ; i++)
            {
                //make the letter uniqueness.
                String newLetter = String.valueOf(chars.charAt(rng.nextInt(26)));
                if (!temp.contains(newLetter))
                {
                    temp += newLetter;
                }
                else
                {
                    i--;
                }
            }
            this.myRegex = this.myRegex + temp + "]";
        }

        genQuantifier();
    }

    private void genQuantifier()
    {
        int ranNum = rng.nextInt(6) ;

        if (ranNum < 3 && ranNum > 0)
        {
            this.myRegex = this.myRegex + "{" + Integer.toString(1+rng.nextInt(SET_SIZE)) + "}";
        } else if (ranNum < 4  && ranNum >= 3)
        {
            this.myRegex = this.myRegex + "*";
        } else if (ranNum <5 && ranNum >=4)
        {
            this.myRegex = this.myRegex + "+";
        } else if (ranNum >= 5)
        {
            this.myRegex = this.myRegex + "?";
        }


    }

    public static void main(String[] args) {
        RexModel model = new RexModel();
        model.setLetter(true);
        model.setDigit(true);
        model.setAnchors(true);
        model.generate(2);
        System.out.println(model.getRex());
    }

}
