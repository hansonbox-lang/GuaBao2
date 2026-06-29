package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.MemberDao;
import model.Member;

public class MemberDaoImpl implements MemberDao {
    @Override
    public Member findById(Connection conn, String id) throws SQLException {
        String sql = "SELECT * FROM members WHERE member_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Member m = new Member();
                    m.setMemberId(rs.getString("member_id"));
                    m.setTotalPoints(rs.getInt("total_points"));
                    return m;
                }
            }
        }
        return null;
    }

    @Override
    public int insert(Connection conn, Member member) throws SQLException {
        String sql = "INSERT INTO members (member_id, total_points) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, member.getMemberId());
            ps.setInt(2, member.getTotalPoints());
            return ps.executeUpdate();
        }
    }

    @Override
    public int updatePoints(Connection conn, String id, int points) throws SQLException {
        String sql = "UPDATE members SET total_points = ? WHERE member_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, points);
            ps.setString(2, id);
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(Connection conn, String id) throws SQLException {
        String sql = "DELETE FROM members WHERE member_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate();
        }
    }
}