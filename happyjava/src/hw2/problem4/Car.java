package hw2.problem4;

public class Car {
    private String brand;
    private String model;
    private int year;
    private double fuel;
    private double fuelCapacity;
    private double totalDistance;
    private boolean engineRunning;

    public Car(String brand, String model, int year, double fuelCapacity) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelCapacity = fuelCapacity;
        this.fuel = fuelCapacity / 2;
        this.totalDistance = 0;
        this.engineRunning = false;
    }

    // 엔진 시동
    public void startEngine() {
        if (fuel <= 0) {
            System.out.println("연료 부족");
            return;
        }

        engineRunning = true;
        System.out.println("엔진 ON");
    }

    // 엔진 정지
    public void stopEngine() {
        if (!engineRunning) {
            System.out.println("이미 엔진이 꺼져 있음");
            return;
        }

        engineRunning = false;
        System.out.println("엔진 OFF");
    }

    // 주행 가능 여부
    public void canDrive(double distance) {
        double required = distance / 10;

        if (!engineRunning) {
            System.out.println("주행 불가 (엔진 OFF)");
            return;
        }

        if (fuel >= required) {
            System.out.println(distance + "km 주행 가능");
        } else {
            System.out.println("연료 부족으로 주행 불가");
        }
    }

    // 주행
    public void drive(double distance) {
        if (!engineRunning) {
            System.out.println("엔진 OFF");
            return;
        }

        double required = distance / 10;

        if (fuel >= required) {
            fuel -= required;
            totalDistance += distance;
            System.out.println(distance + "km 주행");
            return;
        }

        double possibleDistance = fuel * 10;
        totalDistance += possibleDistance;
        fuel = 0;
        engineRunning = false;

        System.out.println("연료 부족 - " + possibleDistance + "km까지 주행 가능");
    }

    // 주유
    public void refuel(double amount) {
        if (amount <= 0) {
            System.out.println("입력값 오류");
            return;
        }

        double newFuel = fuel + amount;

        if (newFuel > fuelCapacity) {
            fuel = fuelCapacity;
            System.out.println("최대치까지 주유 완료");
        } else {
            fuel = newFuel;
            System.out.println("주유 완료");
        }
    }

    // 자동차 현재 상태 출력
    public void showCarStatus() {
        System.out.println("[차량 상태]");
        System.out.println("브랜드: " + brand);
        System.out.println("모델: " + model);
        System.out.println("연식: " + year);
        System.out.println("연료: " + fuel + " / " + fuelCapacity);
        System.out.println("총 주행거리: " + totalDistance);
        System.out.println("엔진: " + (engineRunning ? "켜짐" : "꺼짐"));
        System.out.println();
    }
}