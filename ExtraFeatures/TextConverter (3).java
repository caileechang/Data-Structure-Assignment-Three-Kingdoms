package DSAssignment.ExtraFeatures;

public class TextConverter {
    
    private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final static String encryptAlphabet = "qwertyuiopasdfghjklzxcvbnm";

    public static void main(String[] args) {
        String text = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
        int shift = 7;
        
        System.out.println("Text: " + text);
        System.out.println("Shift: " + shift);
        System.out.println("Encrypted text: "+text);
        
        String securedEncrypt = securedEncrypt(text);
        System.out.println("Secured encrypted text: "+securedEncrypt);
        
        String securedDecrypt = securedDecrypt(securedEncrypt);
        System.out.println("Secured decrypted text: "+securedDecrypt);
        
        String decryptedText = decrypt(securedDecrypt, shift);
        System.out.println("Complete decrypted text: "+decryptedText);
    }

    public static String securedEncrypt(String text){
        String encrypted = "";
        for (int i=0; i<text.length(); i++){
            char a = text.charAt(i);
            if (Character.isAlphabetic(a)){
                int index = (int)a-97;
                encrypted+=String.valueOf(encryptAlphabet.charAt(index));
            }
            else{
                encrypted+= String.valueOf(a);
            }
        }
        return encrypted;
    }
    
    public static String securedDecrypt(String text){
        String decrypted = "";
        for (int i=0; i<text.length(); i++){
            char a = text.charAt(i);
            if (Character.isAlphabetic(a)){
                for (int j=0; j<encryptAlphabet.length(); j++){
                    if (a==encryptAlphabet.charAt(j)){
                        char b = (char)(j+97);
                        decrypted+=String.valueOf(b);
                        break;
                    }
                }
            }
            else{
                decrypted+= String.valueOf(a);
            }
        }
        return decrypted;
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