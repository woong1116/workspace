package day07.Exam;

import java.util.Scanner;

public class Exam7 {

    String[] fruit;

    public Exam7() {
        this.fruit = new String[]{"사과", "바나나", "딸기", "포도"};
    }

    public void inputFruit() {
        Scanner sc = new Scanner(System.in);

        System.out.print("과일 이름 입력: ");
        String input = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < fruit.length; i++) {
            if (input.equals(fruit[i])) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("존재하는 과일입니다.");
        } else {
            System.out.println("존재하지 않는 과일입니다.");
        }
    }
    public static void main (String[]args){
        Exam7 fruit = new Exam7();
        fruit.inputFruit();
        }
    }
