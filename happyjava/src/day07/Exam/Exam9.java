package day07.Exam;

import java.util.Scanner;

public class Exam9 {

    int num;
    
    public Exam9() {
        this.num = (int)((Math.random() * 100) + 1);
    }
    
    public void numGame() {
        Scanner sc = new Scanner(System.in);
        int answer = 0;

        System.out.println("1~100 사이의 숫자를 입력하세요");

        while (true) {
            System.out.print("숫자 입력: ");
            answer = Integer.parseInt(sc.nextLine());

            if (answer > num) {
                System.out.println("더 작은 수를 입력하세요.");
            } else if (answer < num) {
                System.out.println("더 큰 수를 입력하세요.");
            } else {
                System.out.println("정답입니다!");
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        Exam9 numgame = new Exam9(); // 객체 생성
        numgame.numGame();
    }
}