package hw2.problem1;

public class Student {
    private String name;
    private int sNum;
    private int age;
    private double gpa;

    public Student(String name, int sNum, int age, double gpa) {
        this.name = name;
        this.sNum = sNum;
        this.age = age;
        this.gpa = gpa;
    }

    public Student(String name, int sNum) {
        this.name = name;
        this.sNum = sNum;
        this.age = 18;
        this.gpa = 0.0;
    }

    // 나이 업데이트
    public void updateAge(int newAge) {
        if (newAge >= 15 && newAge <= 100) {
            this.age = newAge;
        } else {
            System.out.println("나이는 15~100 사이");
        }
    }

    // GPA 업데이트
    public void updateGpa(double newGpa) {
        if (newGpa >= 0.0 && newGpa <= 4.0) {
            this.gpa = newGpa;
        } else {
            System.out.println("GPA는 0.0~4.0 사이");
        }
    }

    // 정보 출력
    public void displayInfo() {
        System.out.println("이름: " + name + ", 학번: " + sNum + ", 나이: " + age + ", GPA: " + gpa);
    }

    // 우수학생 여부
    public boolean isExcellent() {
        return gpa >= 3.5;
    }

    // getter 사용
    public String getName() {
        return name;
    }
}