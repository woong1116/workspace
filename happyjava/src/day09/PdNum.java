package day09;

public class PdNum {
    public static boolean checkProductNumber(String productNumber) {

        if (productNumber.length() == 6) {
        } else {
            return false;
        }

        if (Character.isLetter(productNumber.charAt(0)) && Character.isLetter(productNumber.charAt(1))) {
        } else {
            return false;
        }

        for (int i = 2; i < 6; i++) {
            if (Character.isDigit(productNumber.charAt(i))) {
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkProductNumber("te3456"));
        System.out.println(checkProductNumber("t33456"));
        System.out.println(checkProductNumber("ar49786"));
        System.out.println(checkProductNumber("test56"));
    }
}
