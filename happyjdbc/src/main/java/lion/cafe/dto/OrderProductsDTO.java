package lion.cafe.dto;

public class OrderProductsDTO {

    private int productId;
    private int quantity;
    private int extraShot;

    public OrderProductsDTO(int productId, int quantity, int extraShot) {
        this.productId = productId;
        this.quantity = quantity;
        this.extraShot = extraShot;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getExtraShot() {
        return extraShot;
    }
}
