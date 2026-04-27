package day09;

public class CharNum {

    public static int countChar(String str, char c) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countChar("banana", 'a'));
        System.out.println(countChar("hello", 'l'));
    }
}