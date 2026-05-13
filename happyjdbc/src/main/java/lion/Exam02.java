package lion;

import java.util.List;

public class Exam02 {
    public static void main(String[] args) {
        MemberDAO dao = new MemberDAO();
        MemberDTO dto = new MemberDTO(12,"카라미","kkk@gmail.com","1111");

//        int count = dao.updateMember(dto);
//        System.out.println(count+"건 수정완료!!");

//        dao.deleteMember(15);

//        MemberDTO resultDto = dao.getMember(3);
//        System.out.println(resultDto.getName());
//        System.out.println(resultDto);

        List<MemberDTO> memberList = dao.getMembers();
        for(MemberDTO member : memberList){
            System.out.println(member);
        }
    }


}
