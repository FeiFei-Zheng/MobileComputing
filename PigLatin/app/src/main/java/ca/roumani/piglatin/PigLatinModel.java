package ca.roumani.piglatin;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by sophia on 2017-03-10.
 */
public class PigLatinModel {
    private String english;
    private String pig;

    public PigLatinModel()
    {
        this.english = "";
        this.pig = "";
    }

    public String getEnglish()
    {
        return this.english;
    }


    public void setEnglish(String text)
    {
        this.english = text.toLowerCase();

        //for each word in string.
        String[] words = english.split(" ");
        String result = "";
        for(int i = 0; i< words.length;i++)
        {
            this.english = words[i];
            translate();
            result += pig + " ";
        }
        pig = result;
    }

    public String getPig()
    {
        return this.pig;
    }

    public void translate()
    {
        boolean consSingleStart = true;

        //start with consonant, no combination
        String vowels = "[aeiou]";
        String beforeVowels = "";
        char letter;
        String prefix = english.substring(0,1);

            if(!prefix.matches(vowels))
            {
                for(int i=0; i<english.length();i++)
                {
                    letter = english.charAt(i);
                    if(vowels.indexOf(letter) >= 0)
                    {
                        {
                            beforeVowels += english.substring(0,i);
                            pig = english.substring(i) + beforeVowels + "ay";
                        }

                        break;
                    }
                    else {
                        pig = english + "ay";
                    }
                }
            }

            //start with vowels
            else if(prefix.matches(vowels))
            {
                pig = english + "way";
            }
            else {
                consSingleStart = false;
            }


        if (!consSingleStart && english.length() > 3)
        {
            //start with common consonant combination form one sound
            String[] consonantCombo = {"ch","gh","gu","ph","th","wh","sh","ps","rh","wr","kn"};
            String firstTwoLetter = english.substring(0,2);

            for (int i = 0; i < consonantCombo.length; i++)
            {
                if (firstTwoLetter.equals(consonantCombo[i]))
                {
                    this.pig = english.substring(2) + firstTwoLetter + "ay";
                }

            }

            //sc special pattern follow by eiy.
            String sc = "sc";
            String scPattern = "[eiy]";
            String scFollow = english.substring(2,3);
            boolean scMatch = scFollow.matches(scPattern);

            if(firstTwoLetter.equals(sc) && scMatch)
            {
                this.pig = english.substring(2) + firstTwoLetter + "ay";
                consSingleStart = false;
            }
        }


    }

    public static void main(String[] args) {
        PigLatinModel model = new PigLatinModel();
        model.setEnglish("hello");
        System.out.println(model.getEnglish());
        System.out.println(model.getPig());

        /*model = new PigLatinModel();
        model.setEnglish("this is a test");
        System.out.println(model.getEnglish());
        System.out.println(model.getPig());*/
    }

}
