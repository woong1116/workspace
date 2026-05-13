package day09;

public class Ca {

    public static int spaceCount(String str) {

        int i;
        int count = 0;

        for (i = 0; i < str.length(); i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                count++;
            }
        }

        return count;

    }

    public static int alphaCount(String str) {

        int i;
        int count = 0;

        for (i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                count++;
            }
        }

        return count;

    }

    public static void main(String[] args) {
        System.out.println(spaceCount("test tset ts ttt"));
        System.out.println(alphaCount("test tset ts ttt"));
    }

}
