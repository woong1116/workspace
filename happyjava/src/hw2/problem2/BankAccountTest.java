package hw2.problem2;

// BankAccountTest.java - 테스트 클래스
class BankAccountTest {
    public static void main(String[] args) {
        System.out.println("===== 은행 계좌 시스템 테스트 =====\n");

        // 계좌 생성
        BankAccount account1 = new BankAccount("123-456-789", "김은행");
        BankAccount account2 = new BankAccount("987-654-321", "이저축");

        // 전체 계좌 수 확인
        BankAccount.showTotalAccounts();
        System.out.println();

        // 입금 테스트
        account1.deposit(50000);
        account1.deposit(-1000); // 잘못된 금액
        account2.deposit(30000);
        System.out.println();

        // 출금 테스트
        account1.withdraw(20000);
        account1.withdraw(50000); // 잔액 부족
        System.out.println();

        // 계좌 이체 테스트
        account1.transfer(account2, 15000);
        account1.transfer(account2, 30000); // 잔액 부족
        System.out.println();

        // 계좌 정보 출력
        account1.showAccountInfo();
        account2.showAccountInfo();
    }
}