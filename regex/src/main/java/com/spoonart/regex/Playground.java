package com.spoonart.regex;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class Playground {

    public static void main(String[] args) {
//        Pattern pattern = Pattern.compile(".xx+.*");
//        Matcher matcher = pattern.matcher("MxxY23s");
//        System.out.println("RegexHelper.main : " + matcher.matches());
//
//        String str = "+62 234 4345 3423";
//        System.out.println("Using string method matches " + str.matches("^\\+(\\d{2}\\s?+\\d{3,4}\\s?+\\d{3,4}\\s?+\\d{4})"));
//        String superman = "affgfking";
////        System.out.println("is contains whitespace? " + superman.matches("(.*\\s+.*)+") + " full str: "+ superman);
//        System.out.println("is contains whitespace? " + superman.matches(".*[^\\s]fg[^o][^.\\p{Upper}].*") + " full str: "+ superman);
//
//        String answer1 = "pit|.*p.t.*";
//        String answer2 = "apeth|.*ap.?t.*"; //apth|.*ap.t.*
//
//        String format = "^[a-z]+[\\p{Upper}]+";
//        String[] words = new String[]{"A. B", "c! d", "e f", "g.   H", "i?  J", "k L", "ggwpAA", "GGWP"};
//
//        for(String word : words)
//            System.out.println("Playground.main: " + word.matches(format));


        LinkedList<String> list1 = new LinkedList<String>();
        list1.add(0, "g");
        list1.add(1, "e");
        list1.add(2, "e");
        list1.add(3, "k");
        list1.add(4, "s");
        list1.add(5, "a");

        LinkedList<String> list2 = new LinkedList<String>();
        list2.add(0, "g");
        list2.add(1, "e");
        list2.add(2, "e");
        list2.add(3, "k");
        list2.add(4, "s");
        list2.add(5, "b");


//        try {
//            new Comparator("a", "E");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("Playground.main : " + compareList(list1, list2));
    }

    static int compareList(LinkedList<String> list1, LinkedList<String> list2) {
        String words1 = "";
        String words2 = "";
        for (String s : list1) {
            words1 += s;
        }

        for (String s2 : list2) {
            words2 += s2;
        }

        System.out.println("Playground.compareList: "+words1 + " " + words2);
        if(words1.equalsIgnoreCase(words2)){
            return 0;
        }

        return words1.compareToIgnoreCase(words2);
    }

    static class Comparator {

        Comparator(String a, String b) throws Exception {
            String result = getResult(a, b);
            System.out.println("Comparator.Comparator: " + result);
        }

        private String getResult(String a, String b) throws Exception {
            Pattern pattern = Pattern.compile("[a-z]");
            if (!pattern.matcher(a).matches() || !pattern.matcher(b).matches()) {
                throw new Exception("oops Uppercased not allowed");
            }

            boolean isEarlier = a.compareTo(b) < 0;
            if (isEarlier) {
                return a;
            }
            return b;
        }


        static void test(){

        }

    }







}
