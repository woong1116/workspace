package lion;

import java.util.Scanner;

public class MemberExam {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("멤버이름을 입력하세요. ");
        String name = keyboard.nextLine();
        System.out.println("멤버이메일을 입력하세요. ");
        String email = keyboard.nextLine();
        System.out.println("멤버비밀번호을 입력하세요. ");
        String password = keyboard.nextLine();

        InsertExam insertExam = new InsertExam();
        try {
            insertExam.insertMember(name, email, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}