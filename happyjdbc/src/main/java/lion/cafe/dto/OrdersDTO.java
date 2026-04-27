package lion.cafe.dto;

import java.sql.Timestamp;

public class OrdersDTO {

    private int orderId;
    private String statement;
    private int totalPrice;
    private String paymentMethod;
    private String takeout;
    private Timestamp createdAt;

    public OrdersDTO() {
    }

    public OrdersDTO(int orderId, String statement, int totalPrice,
                     String paymentMethod, String takeout, Timestamp createdAt) {
        this.orderId = orderId;
        this.statement = statement;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.takeout = takeout;
        this.createdAt = createdAt;
    }

    public String getStatement() {
        return statement;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getTakeout() {
        return takeout;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}