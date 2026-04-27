package day04;

public class LabelExam1 {
    public static void main(String[] args){
        outter:
        for(int i = 0; i < 5; i++){
            for(int k = 0; k < 5; k++){
                if(i == 0 && k == 4)
                    break outter; // 바깥쪽 반복문까지 종료
                System.out.println(i + ", " + k);
            }
        }
    }
}