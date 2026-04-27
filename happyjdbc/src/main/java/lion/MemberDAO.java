package lion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//DAO - data access object
//DTO   -- VO(Value Object)
public class MemberDAO {
    public boolean insertMember(MemberDTO memberDTO){
//        String url = "jdbc:mysql://localhost:3306/liondb";
//        String user = "lion";
//        String password = "lion1234";

        String sql = "insert into member(name,email, password) values(?,?,?)";

        boolean result = false;
        try(/*Connection conn = DriverManager.getConnection(url,user,password);*/
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1,memberDTO.getName());
            ps.setString(2,memberDTO.getEmail());
            ps.setString(3,memberDTO.getPassword());
            int count = ps.executeUpdate();
            if(count == 1)
                result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
    //수정
    public int updateMember(MemberDTO memberDTO){
        int resultCount = 0;
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";

        String sql = "update member set name=?,email=?,password=? where id=?";

        try(Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1,memberDTO.getName());
            ps.setString(2,memberDTO.getEmail());
            ps.setString(3,memberDTO.getPassword());
            ps.setInt(4,memberDTO.getId());


            resultCount = ps.executeUpdate();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultCount;
    }

    //삭제
    public void deleteMember(int id){
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";

        String sql = "delete from member where id=?";

        try(Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //조회 (한건 조회)
    public MemberDTO getMember(int id){
        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";

        MemberDTO memberDTO = null;

        String sql = "SELECT * FROM member WHERE id = ?";
        try( Connection conn = DriverManager.getConnection(url,user,password);
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1,id);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    memberDTO = new MemberDTO();
                    memberDTO.setId(rs.getInt("id"));
                    memberDTO.setName(rs.getString("name"));
                    memberDTO.setEmail(rs.getString("email"));
                    memberDTO.setPassword(rs.getString("password"));
                    memberDTO.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                    memberDTO.setUpdateAt(rs.getObject("update_at", LocalDateTime.class));
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return memberDTO;
    }

    //조회 (여러건 조회)
    public List<MemberDTO> getMembers(){
        List<MemberDTO> members = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/liondb";
        String user = "woong";
        String password = "lion1234";

        String sql = "select * from member";
        try(
                Connection conn = DriverManager.getConnection(url,user,password);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()){
                MemberDTO memberDTO = new MemberDTO();

                memberDTO.setId(rs.getInt("id"));
                memberDTO.setName(rs.getString("name"));
                memberDTO.setEmail(rs.getString("email"));
                memberDTO.setPassword(rs.getString("password"));
                memberDTO.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                memberDTO.setUpdateAt(rs.getObject("update_at", LocalDateTime.class));

                members.add(memberDTO);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return members;
    }
}
