package com.snkinard.coding;

public class ReverseSentence {

    public static String reverseSentence(String input) {
        String[] words = input.split(" ");
        StringBuffer output = new StringBuffer();
        for (String word : words) {
            output.append(reverse(word));
            output.append(" ");
        }
        return output.toString().trim();
    }

    public static String reverse(String input) {
        StringBuffer output = new StringBuffer();
        for (int index = input.length() - 1; index >= 0; index--) {
            output.append(input.charAt(index));
        }
        return output.toString();
    }

}
