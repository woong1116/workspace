package day04;

public class StringExam1 {
    public static void main(String[] args){
        String str1 = "hello";

        int n = Integer.parseInt(args[0]);

        for(int i = 0; i < n; i++ ){
            System.out.println(str1);
        }
    }
}