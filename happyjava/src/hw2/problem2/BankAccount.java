package hw2.problem2;

public class BankAccount {

    private double balance;
    private static int totalAccounts;
    private String accountNum;
    private String name;

    public BankAccount(String accountNum, String name) {
        this.balance = 0;
        totalAccounts++;
        this.accountNum = accountNum;
        this.name = name;
    }

    // 입금
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("입금 완료 - 잔액: " + balance);
            return true;
        } else {
            System.out.println("입금 실패 - 입력값 오류");
            return false;
        }
    }

    // 출금
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("출금 성공 - 잔액: " + balance);
            return true;
        } else {
            System.out.println("출금 실패 - 잔액 부족 or 입력값 오류");
            return false;
        }
    }

    // 계좌 이체
    public boolean transfer(BankAccount target, double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            target.balance += amount;
            System.out.println("이체 성공 - 잔액: " + balance);
            return true;
        } else {
            System.out.println("이체 실패 - 잔액 부족 or 입력값 오류");
            return false;
        }
    }

    // 잔액 출력
    public void showBalance() {
        System.out.println("잔액: " + balance);
    }

    // 계좌 정보 출력
    public void showAccountInfo() {
        System.out.println("- " + name + "님의 계좌 정보 -");
        System.out.println("계좌번호: " + accountNum);
        System.out.println("잔액: " + balance);
    }

    // 전체 계좌 수 출력
    public static void showTotalAccounts() {
        System.out.println("전체 계좌 수: " + totalAccounts);
    }
}