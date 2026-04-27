package lion.cafe.dao;

import lion.cafe.common.DBUtil;
import lion.cafe.dto.OrderProductsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductsDAO {

    public void insertOrderProducts(int orderId, int productId, int quantity, int extraShot) {

        String sql = "INSERT INTO OrderProducts (order_id, product_id, quantity, extra_shot) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setInt(4, extraShot);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderProductsDTO> findById(int orderId) {

        List<OrderProductsDTO> list = new ArrayList<>();

        String sql = "SELECT product_id, quantity, extra_shot FROM OrderProducts WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    OrderProductsDTO dto = new OrderProductsDTO(
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getInt("extra_shot")
                    );

                    list.add(dto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}