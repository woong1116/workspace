package day04;

public class WhileExam4 {
    public static void main(String[] args){
        int i = 0;
        while(i++ < 20){
            if(i % 2 != 1)
                continue;
            System.out.println(i);
        }
    }
}