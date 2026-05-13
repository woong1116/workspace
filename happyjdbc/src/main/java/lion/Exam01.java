package lion;

import java.util.Scanner;

public class Exam01 {
    public static void main(String[] args) {
        MemberDAO dao = new MemberDAO();

        MemberDTO dto = new MemberDTO();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("이름을 입력하세요.");
//        String name = keyboard.nextLine();
//        dto.setName(name);
        dto.setName(keyboard.nextLine());
        System.out.println("이메일을 입력하세요.");
        dto.setEmail(keyboard.nextLine());

        System.out.println("비밀번호를 입력하세요.");
        dto.setPassword(keyboard.nextLine());

        boolean resultFlag = dao.insertMember(dto);
        if(resultFlag)
            System.out.println("입력성공!!");
        else
            System.out.println("입력실패ㅠㅠ");
    }
}