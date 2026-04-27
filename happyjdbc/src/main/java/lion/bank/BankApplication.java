package lion.bank;

import lion.bank.dto.AccountDTO;
import lion.bank.service.BankService;
import lion.bank.service.BankServiceImpl;

import java.util.List;
import java.util.Scanner;

public class BankApplication {

    private static final BankService bankService = new BankServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            printMenu();
            System.out.print("선택 > ");
            String menu = scanner.nextLine();

            switch (menu) {
                case "1":
                    createAccount(scanner);
                    break;
                case "2":
                    getAccount(scanner);
                    break;
                case "3":
                    getAllAccounts();
                    break;
                case "4":
                    deposit(scanner);
                    break;
                case "5":
                    withdraw(scanner);
                    break;
                case "6":
                    transfer(scanner);
                    break;
                case "0":
                    run = false;
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("==================================");
        System.out.println("1. 계좌 생성");
        System.out.println("2. 계좌 조회");
        System.out.println("3. 전체 계좌 조회");
        System.out.println("4. 입금");
        System.out.println("5. 출금");
        System.out.println("6. 이체");
        System.out.println("0. 종료");
        System.out.println("==================================");
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("계좌번호 입력 > ");
        String accountNumber = scanner.nextLine();

        System.out.print("예금주 입력 > ");
        String ownerName = scanner.nextLine();

        System.out.print("초기 입금액 입력 > ");
        long initialBalance = Long.parseLong(scanner.nextLine());

        boolean result = bankService.createAccount(accountNumber, ownerName, initialBalance);

        if (result) {
            System.out.println("계좌가 생성되었습니다.");
        } else {
            System.out.println("계좌 생성에 실패했습니다.");
        }
    }

    private static void getAccount(Scanner scanner) {
        System.out.print("조회할 계좌번호 입력 > ");
        String accountNumber = scanner.nextLine();

        AccountDTO accountDTO = bankService.getAccount(accountNumber);

        if (accountDTO == null) {
            System.out.println("해당 계좌가 없습니다.");
            return;
        }

        printAccount(accountDTO);
    }

    private static void getAllAccounts() {
        List<AccountDTO> list = bankService.getAllAccounts();

        if (list.isEmpty()) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }

        for (AccountDTO accountDTO : list) {
            printAccount(accountDTO);
            System.out.println("----------------------------------");
        }
    }

    private static void deposit(Scanner scanner) {
        System.out.print("입금할 계좌번호 입력 > ");
        String accountNumber = scanner.nextLine();

        System.out.print("입금액 입력 > ");
        long amount = Long.parseLong(scanner.nextLine());

        boolean result = bankService.deposit(accountNumber, amount);

        if (result) {
            System.out.println("입금이 완료되었습니다.");
        } else {
            System.out.println("입금에 실패했습니다.");
        }
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("출금할 계좌번호 입력 > ");
        String accountNumber = scanner.nextLine();

        System.out.print("출금액 입력 > ");
        long amount = Long.parseLong(scanner.nextLine());

        boolean result = bankService.withdraw(accountNumber, amount);

        if (result) {
            System.out.println("출금이 완료되었습니다.");
        } else {
            System.out.println("출금에 실패했습니다.");
        }
    }

    private static void transfer(Scanner scanner) {
        System.out.print("출금 계좌번호 입력 > ");
        String fromAccount = scanner.nextLine();

        System.out.print("입금 계좌번호 입력 > ");
        String toAccount = scanner.nextLine();

        System.out.print("이체 금액 입력 > ");
        long amount = Long.parseLong(scanner.nextLine());

        boolean result = bankService.transfer(fromAccount, toAccount, amount);

        if (result) {
            System.out.println("이체가 완료되었습니다.");
        } else {
            System.out.println("이체에 실패했습니다.");
        }
    }

    private static void printAccount(AccountDTO accountDTO) {
        System.out.println("계좌ID    > " + accountDTO.getAccountId());
        System.out.println("계좌번호  > " + accountDTO.getAccountNumber());
        System.out.println("예금주    > " + accountDTO.getOwnerName());
        System.out.println("잔액      > " + accountDTO.getBalance());
        System.out.println("생성일시  > " + accountDTO.getCreatedAt());
    }
}
