package lion.cafe.service;

import lion.cafe.dto.OrderProductsDTO;
import lion.cafe.dto.ProductsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderService orderService = new OrderService();

        while (true) {

            System.out.println("\n===== 카페 포스기 =====");
            System.out.println("1. 주문");
            System.out.println("2. 주문 조회");
            System.out.println("3. 제조 완료 처리");
            System.out.println("4. 주문 취소");
            System.out.println("0. 종료");
            System.out.print("선택: ");


            String menu = sc.nextLine();

            switch (menu) {

                // 주문
                case "1":
                    System.out.print("\n결제 방식 (1. 카드 / 2. 현금): ");
                    String paymentMethod = sc.nextLine();

                    System.out.print("테이크아웃 여부 (1. 매장 / 2. 포장): ");
                    String takeout = sc.nextLine();

                    System.out.println("\n===== 메뉴 =====");

                    List<ProductsDTO> productList = orderService.getMenu();

                    for (ProductsDTO p : productList) {
                        System.out.println(p.getProductId() + ". " + p.getName() + " - " + p.getPrice());
                    }

                    List<OrderProductsDTO> products = new ArrayList<>();

                    while (true) {
                        System.out.print("\n주문 상품 번호 입력 (0 종료): ");
                        int productId = sc.nextInt();
                        if (productId == 0) {
                            sc.nextLine();
                            break;
                        }

                        System.out.print("수량: ");
                        int quantity = sc.nextInt();

                        System.out.print("샷추가: ");
                        int extraShot = sc.nextInt();
                        sc.nextLine();

                        products.add(new OrderProductsDTO(productId, quantity, extraShot));
                    }

                    int orderId = orderService.createOrder(paymentMethod, takeout, products);

                    System.out.println("\n주문 완료 - 주문번호: " + orderId);
                    break;

                // 주문 조회
                case "2":
                    System.out.print("\n주문 번호 입력: ");
                    int printOrderId = sc.nextInt();
                    sc.nextLine();
                    orderService.printOrder(printOrderId);
                    break;

                // 제조 완료 처리
                case "3":
                    System.out.print("\n주문 번호 입력: ");
                    int completeId = sc.nextInt();
                    sc.nextLine();

                    orderService.completeOrder(completeId);
                    System.out.println("주문 번호 " + completeId + "번 제조 완료");
                    break;

                // 주문
                case "4":
                    System.out.print("\n주문 번호 입력: ");
                    int cancelId = sc.nextInt();
                    sc.nextLine();
                    orderService.cancelOrder(cancelId);
                    break;

                // 종료
                case "0":
                    System.out.println("\n프로그램 종료");
                    sc.close();
                    return;

                default:
                    System.out.println("잘못된 입력");
            }
        }
    }
}