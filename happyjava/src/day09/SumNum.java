package day09;

public class SumNum {

    public static int sumOfDigits(String str) {
        int sum = 0;

        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);

            if (Character.isDigit(num)) {
                sum += Character.getNumericValue(num);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sumOfDigits("abc123"));
        System.out.println(sumOfDigits("a1b2c3d9"));

    }
}