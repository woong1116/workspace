//package jdbc03;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.List;
//
//@SpringBootApplication
//public class Jdbc03Exam implements CommandLineRunner {
//    public static void main(String[] args) {
//        SpringApplication.run(jdbc03.Jdbc03Exam.class, args);
//    }
//
//    @Autowired
//    DeptDao deptDao;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Dept dept = new Dept();
//        dept.setDname("Marketing");
//        dept.setLoc("Seoul");
//
//        List<Dept> allDepts = deptDao.findAllDepts();
//        for (Dept d : allDepts) {
//            System.out.println(d);
//        }
//
//        Dept dept1 = deptDao.findDeptByDeptno(2);
//        System.out.println(dept1);
//    }
//}
