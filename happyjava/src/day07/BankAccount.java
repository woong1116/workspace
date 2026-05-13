package day07;

public class BankAccount {
    int accNum;
    String name;
    int balance;

    public BankAccount(int accNum, String name, int balance) {
        this.accNum = accNum;
        this.name = name;
        this.balance = balance;
    }

    public void deposit(int money) {
        if (money > 0) {
            balance += money;
        }
    }

    public void withdraw(int money) {
        if (money <= balance) {
            balance -= money;
        }
    }

    public int getBalance() {
        return balance;
    }


    public static void main(String[] args) {
        BankAccount acc = new BankAccount(1111, "홍길동", 100000);

        acc.deposit(10000);
        acc.withdraw(1000);

        System.out.println("잔액: " + acc.getBalance());
    }
}
