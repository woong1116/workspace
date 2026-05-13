package hw2.problem1;


class StudentTest {
    public static void main(String[] args) {

        System.out.println("===== 학생 정보 관리 시스템 테스트 =====\n");

        // 학생 객체 생성 테스트
        Student student1 = new Student("강경미", 20241001, 20, 3.8);
        Student student2 = new Student("김멋사", 20241002);

        // 정보 출력 테스트
        student1.displayInfo();
        student2.displayInfo();

        // 우수학생 여부 테스트
        System.out.println(student1.getName() + " 우수학생 여부: " + student1.isExcellent());
        System.out.println(student2.getName() + " 우수학생 여부: " + student2.isExcellent());
        System.out.println();

        // GPA 업데이트 테스트
        student2.updateGpa(3.2);
        student2.updateGpa(5.0); // 잘못된 값

        // 나이 업데이트 테스트
        student1.updateAge(21);
        student1.updateAge(150); // 잘못된 값

        System.out.println("\n=== 업데이트 후 정보 ===");
        student1.displayInfo();
        student2.displayInfo();
    }
}
