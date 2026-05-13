package lion.cafe.service;

import lion.cafe.dao.OrderProductsDAO;
import lion.cafe.dao.OrdersDAO;
import lion.cafe.dao.ProductsDAO;
import lion.cafe.dto.OrderProductsDTO;
import lion.cafe.dto.OrdersDTO;
import lion.cafe.dto.ProductsDTO;

import java.util.List;

public class OrderService {

    private final OrdersDAO ordersDAO = new OrdersDAO();
    private final OrderProductsDAO orderProductsDAO = new OrderProductsDAO();
    private final ProductsDAO productsDAO = new ProductsDAO();

    // 주문
    public int createOrder(String paymentMethod, String takeout, List<OrderProductsDTO> products) {

        int orderId = ordersDAO.insertOrder(paymentMethod, takeout);

        int totalPrice = 0;

        for (OrderProductsDTO product : products) {

            ProductsDTO pd = productsDAO.findById(product.getProductId());

            if (pd == null) {
                throw new RuntimeException("상품 없음");
            }

            int price = pd.getPrice();

            int itemTotal = price * product.getQuantity();
            itemTotal += product.getExtraShot() * 500;

            totalPrice += itemTotal;

            orderProductsDAO.insertOrderProducts(orderId, product.getProductId(), product.getQuantity(), product.getExtraShot());
        }

        ordersDAO.updateTotalPrice(orderId, totalPrice);

        return orderId;
    }

    // 메뉴
    public List<ProductsDTO> getMenu() {
        return productsDAO.findAll();
    }

    // 주문 조회
    public void printOrder(int orderId) {

        OrdersDTO order = ordersDAO.findById(orderId);

        if (order == null) {
            return;
        }

        List<OrderProductsDTO> products = orderProductsDAO.findById(orderId);

        int totalPrice = 0;

        System.out.println("\n========= 주문 상세 =========");
        System.out.println("주문 번호: " + orderId);
        System.out.println("주문 시간: " + order.getCreatedAt());

        for (OrderProductsDTO product : products) {

            ProductsDTO pd = productsDAO.findById(product.getProductId());
            if (pd == null) continue;

            int price = pd.getPrice();

            int productTotal = price * product.getQuantity();
            int shotTotal = product.getExtraShot() * 500;

            productTotal += shotTotal;
            totalPrice += productTotal;

            System.out.println(
                    "상품명: " + pd.getName() +
                            " | 수량: " + product.getQuantity() +
                            " | 샷추가: " + product.getExtraShot() +
                            " | 금액: " + productTotal);
        }

        System.out.println("결제 방식: " + (order.getPaymentMethod().equals("1") ? "카드" : "현금"));
        System.out.println("테이크아웃 여부: " + (order.getTakeout().equals("1") ? "매장" : "포장"));
        System.out.println("총 결제 금액: " + order.getTotalPrice());
        System.out.println("주문 상태: " + order.getStatement());
        System.out.println();
    }

    // 제조 완료 처리
    public void completeOrder(int orderId) {
        ordersDAO.updateStatement(orderId, "제조 완료");
    }

    // 주문 취소
    public void cancelOrder(int orderId) {

        OrdersDTO order = ordersDAO.findById(orderId);

        if (order == null) {
            System.out.println("주문 없음");
            return;
        }

        if ("제조 완료".equals(order.getStatement())) {
            System.out.println("제조 완료된 주문은 취소 불가");
            return;
        }

        if ("취소".equals(order.getStatement())) {
            System.out.println("이미 취소된 주문");
            return;
        }

        ordersDAO.updateStatement(orderId, "취소");
        System.out.println("주문 취소");
    }


}