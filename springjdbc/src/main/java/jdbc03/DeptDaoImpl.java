//package jdbc03;
//
//import jdbc02.UserNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.dao.DataAccessException;
//import org.springframework.dao.DataAccessResourceFailureException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class DeptDaoImpl implements DeptDao{
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Override
//    public int insertDept(Dept dept) {
//        String sql = "insert into dept(dname, loc) values(?,?)";
//        try {
//            return jdbcTemplate.update(sql, dept.getDname(), dept.getLoc());
//        }catch (DataAccessException e) {
//            throw new DataAccessResourceFailureException("잘못된 값을 입력 - "+ dept.getDname(),e);
//        }
//    }
//
//    @Override
//    public List<Dept> findAllDepts() {
//        String sql = "select * from dept";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dept.class));
//    }
//
//    @Override
//    public Dept findDeptByDeptno(int deptno) {
//        String sql = "select * from users where deptno = ?";
//
//        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Dept.class), deptno);
//
//    }
//
//    @Override
//    public void updateDept(Dept dept) {
//        String sql = "update dept set dname=?, loc=? where deptno=?";
//        int updateCount = jdbcTemplate.update(sql, dept.getDname(), dept.getLoc(), dept.getDeptno());
//        if(updateCount == 0){
//            throw new UserNotFoundException(dept.getDname()+" dept를 찾을수 없어요.");
//        }
//    }
//
//    @Override
//    public void deleteDept(int deptno) {
//        String sql = "delete from dept where deptno=?";
//        int deleteCount = jdbcTemplate.update(sql, deptno);
//        if(deleteCount == 0){
//            throw new UserNotFoundException(deptno + " dept를 찾을수 없어요.");
//        }
//
//    }
//}
