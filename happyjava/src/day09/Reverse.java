package day09;

public class Reverse {

    public static String reverseChange(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseChange("abc"));
        System.out.println(reverseChange("Hello"));
    }
}