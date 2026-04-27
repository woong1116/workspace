package lion.cafe.dao;

import lion.cafe.common.DBUtil;
import lion.cafe.dto.OrdersDTO;

import java.sql.*;

public class OrdersDAO {

    public int insertOrder(String paymentMethod, String takeout) {
        String sql = "INSERT INTO Orders (statement, total_price, payment_method, takeout) VALUES ('MAKING', 0, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, paymentMethod);
            ps.setString(2, takeout);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void updateTotalPrice(int orderId, int totalPrice) {
        String sql = "UPDATE orders SET total_price = ? WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, totalPrice);
            ps.setInt(2, orderId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatement(int orderId, String statement) {
        String sql = "UPDATE orders SET statement = ? WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, statement);
            ps.setInt(2, orderId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OrdersDTO findById(int orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        OrdersDTO order = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order = new OrdersDTO(
                            rs.getInt("order_id"),
                            rs.getString("statement"),
                            rs.getInt("total_price"),
                            rs.getString("payment_method"),
                            rs.getString("takeout"),
                            rs.getTimestamp("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}