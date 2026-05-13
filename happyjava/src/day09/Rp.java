package day09;

public class Rp {

    public static String replaceAllWords(String original, String target, String replacement) {
        return original.replaceAll("\\b" + target + "\\b", replacement);
    }

    public static void main(String[] args) {
        String result = replaceAllWords("I like cat. Cat is cute.","cat","dog");

        System.out.println(result);
    }
}