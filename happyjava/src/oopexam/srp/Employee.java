package oopexam.srp;

public class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    // 직원 정보 관리 + 급여 계산 + 데이터베이스 저장을 모두 담당
    public class Pay {
        public double calculatePay() {
            // 급여 계산 로직
            double employeeSalary = getSalary();
            System.out.println("급여: " + employeeSalary);
            return employeeSalary;
        }
    }

public class DB {
    public void saveToDatabase() {
        // DB 저장 로직


    }
}

    public String generateReport() {
        // 보고서 생성 로직
    }
}