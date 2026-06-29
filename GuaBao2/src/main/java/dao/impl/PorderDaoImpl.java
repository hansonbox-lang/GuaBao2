package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dao.PorderDao;
import model.Porder;
import model.PorderDetail;

public class PorderDaoImpl implements PorderDao {
    @Override
    public int insertOrder(Connection conn, Porder order) throws SQLException {
        String sql = "INSERT INTO orders (member_id, total_amount, earned_points, gift_count) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (order.getMemberId() != null) {
                ps.setString(1, order.getMemberId());
            } else {
                ps.setNull(1, java.sql.Types.VARCHAR);
            }
            ps.setInt(2, order.getTotalAmount());
            ps.setInt(3, order.getEarnedPoints());
            ps.setInt(4, order.getGiftCount());
            ps.executeUpdate();
            
            try (ResultSet gk = ps.getGeneratedKeys()) {
                if (gk.next()) return gk.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public void insertOrderDetail(Connection conn, PorderDetail detail) throws SQLException {
        String sql = "INSERT INTO order_details (order_id, item_name, unit_price, quantity, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getOrderId());
            ps.setString(2, detail.getItemName());
            ps.setInt(3, detail.getUnitPrice());
            ps.setInt(4, detail.getQuantity());
            ps.setInt(5, detail.getSubtotal());
            ps.executeUpdate();
        }
    }
}