package hw2.problem4;

// CarTest.java - 테스트 클래스
class CarTest {
    public static void main(String[] args) {
        System.out.println("===== 자동차 관리 시스템 테스트 =====\n");

        // 자동차 생성
        Car myCar = new Car("현대", "아반떼", 2023, 50.0);

        // 초기 상태 확인
        myCar.showCarStatus();

        // 엔진 시동 테스트
        myCar.startEngine();
        System.out.println();

        // 주행 가능 여부 확인
        myCar.canDrive(100);
        myCar.canDrive(300);
        System.out.println();

        // 주행 테스트
        myCar.drive(50);
        myCar.drive(100);
        myCar.drive(200); // 연료 부족
        System.out.println();

        // 현재 상태 확인
        myCar.showCarStatus();

        // 주유 테스트
        myCar.refuel(30);
        myCar.refuel(50); // 최대 용량 초과
        System.out.println();

        // 엔진 시동 후 주행
        myCar.startEngine();
        myCar.drive(100);
        System.out.println();

        // 최종 상태
        myCar.showCarStatus();

        // 엔진 정지
        myCar.stopEngine();
        myCar.canDrive(50); // 엔진 꺼진 상태에서 주행 시도
    }
}