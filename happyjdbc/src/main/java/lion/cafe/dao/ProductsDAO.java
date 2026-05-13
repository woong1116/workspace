package lion.cafe.dao;

import lion.cafe.common.DBUtil;
import lion.cafe.dto.ProductsDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {

    public List<ProductsDTO> findAll() {
        List<ProductsDTO> list = new ArrayList<>();
        String sql = "SELECT product_id, name, price FROM products";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductsDTO product = new ProductsDTO(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("price")
                );
                list.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ProductsDTO findById(int productId) {
        String sql = "SELECT product_id, name, price FROM products WHERE product_id = ?";
        ProductsDTO product = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new ProductsDTO(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            rs.getInt("price")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}