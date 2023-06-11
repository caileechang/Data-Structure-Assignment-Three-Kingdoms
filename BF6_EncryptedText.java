package DSAssignment;

import java.util.Scanner;


public class BF6_EncryptedText {
public static void main(String[] args) {
        String text = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
        int shift = 7;
        System.out.println("Text: " + text);
        System.out.println("Shift: " + shift);

        String decryptedText = decrypt(text, shift);
        System.out.println(decryptedText);
    }

    public static String decrypt(String text, int shift) {
        String decrypted = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isAlphabetic(c)) {
                decrypted += (char) (((int) c - shift - 97 + 26) % 26 + 97);
            } else if (c == '^') {
                char a = (char) (((int) text.charAt(++i) - shift - 97 + 26) % 26 + 97);
                decrypted += Character.toUpperCase(a);
            } else if (c == '(') {
                int end_index = text.indexOf(')', i);
                String reversed = new StringBuilder(text.substring(i + 1, end_index)).reverse().toString();
                for (int j = 0; j < reversed.length(); j++) {
                    decrypted += (char) (((int) reversed.charAt(j) - shift - 97 + 26) % 26 + 97);
                }
                i = end_index;
            } else if (c == '$') {
                decrypted += " ";
            } else {
                decrypted += c;
            }
        }
        return decrypted;
    }
}
