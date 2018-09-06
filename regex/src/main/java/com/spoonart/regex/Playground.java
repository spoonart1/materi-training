package com.spoonart.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Playground {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(".xx+.*");
        Matcher matcher = pattern.matcher("MxxY23s");
        System.out.println("RegexHelper.main : " + matcher.matches());

        String str = "+62 234 4345 3423";
        System.out.println("Using string method matches " + str.matches("^\\+(\\d{2}\\s?+\\d{3,4}\\s?+\\d{3,4}\\s?+\\d{4})"));
        String superman = "affgfking";
//        System.out.println("is contains whitespace? " + superman.matches("(.*\\s+.*)+") + " full str: "+ superman);
        System.out.println("is contains whitespace? " + superman.matches(".*[^\\s]fg[^o][^.\\p{Upper}].*") + " full str: "+ superman);

        String answer1 = "pit|.*p.t.*";
        String answer2 = "apeth|.*ap.?t.*"; //apth|.*ap.t.*

        String format = "^[a-z]+[\\p{Upper}]+";
        String[] words = new String[]{"A. B", "c! d", "e f", "g.   H", "i?  J", "k L", "ggwpAA", "GGWP"};

        for(String word : words)
            System.out.println("Playground.main: " + word.matches(format));
    }

}
