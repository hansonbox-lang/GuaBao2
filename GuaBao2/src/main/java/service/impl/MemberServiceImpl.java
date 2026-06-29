package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import dao.MemberDao;
import dao.PorderDao;
import dao.impl.MemberDaoImpl;
import dao.impl.PorderDaoImpl;
import exception.GuaBaoException;
import model.Member;
import model.Porder;
import model.PorderDetail;
import service.MemberService;
import util.DbConnection;

public class MemberServiceImpl implements MemberService {
    private MemberDao memberDao = new MemberDaoImpl();
    private PorderDao porderDao = new PorderDaoImpl();

    @Override
    public Member login(String memberId) throws GuaBaoException {
        try (Connection conn = DbConnection.getConnection()) {
            Member m = memberDao.findById(conn, memberId);
            if (m == null) throw new GuaBaoException("查無此會員卡號！");
            return m;
        } catch (SQLException e) {
            throw new GuaBaoException("資料庫連線異常: " + e.getMessage());
        }
    }

    @Override
    public Member queryMember(String memberId) throws GuaBaoException {
        return login(memberId);
    }

    @Override
    public void registerMember(String memberId, int initPoints) throws GuaBaoException {
        try (Connection conn = DbConnection.getConnection()) {
            if (memberDao.findById(conn, memberId) != null) {
                throw new GuaBaoException("此會員卡號已存在，不可重複新增！");
            }
            memberDao.insert(conn, new Member(memberId, initPoints));
        } catch (SQLException e) {
            throw new GuaBaoException("新增失敗: " + e.getMessage());
        }
    }

    @Override
    public void updateMemberPoints(String memberId, int points) throws GuaBaoException {
        try (Connection conn = DbConnection.getConnection()) {
            int rows = memberDao.updatePoints(conn, memberId, points);
            if (rows == 0) throw new GuaBaoException("找不到該會員，修改失敗！");
        } catch (SQLException e) {
            throw new GuaBaoException("更新失敗: " + e.getMessage());
        }
    }

    @Override
    public void deleteMember(String memberId) throws GuaBaoException {
        try (Connection conn = DbConnection.getConnection()) {
            int rows = memberDao.delete(conn, memberId);
            if (rows == 0) throw new GuaBaoException("找不到該會員，刪除失敗！");
        } catch (SQLException e) {
            throw new GuaBaoException("刪除失敗: " + e.getMessage());
        }
    }

    @Override
    public String checkout(Porder order) throws GuaBaoException {
        Connection conn = null;
        try {
            conn = DbConnection.getConnection();
            conn.setAutoCommit(false); 

            int newEarnedPoints = order.getTotalAmount() / 100;
            int totalPoints = 0;
            int giftCount = 0;

            if (order.getMemberId() != null) {
                Member m = memberDao.findById(conn, order.getMemberId());
                if (m != null) {
                    totalPoints = m.getTotalPoints() + newEarnedPoints;
                    giftCount = totalPoints / 10;
                    totalPoints = totalPoints % 10;
                    memberDao.updatePoints(conn, order.getMemberId(), totalPoints);
                }
            }

            order.setEarnedPoints(newEarnedPoints);
            order.setGiftCount(giftCount);

            int orderId = porderDao.insertOrder(conn, order);
            
            for (PorderDetail detail : order.getDetails()) {
                detail.setOrderId(orderId);
                porderDao.insertOrderDetail(conn, detail);
            }

            conn.commit();

            StringBuilder sb = new StringBuilder();
            sb.append("訂單成功寫入資料庫！編號: ").append(orderId).append("\n");
            if (order.getMemberId() != null) {
                sb.append("本次獲得積點：").append(newEarnedPoints).append(" 點\n");
                if (giftCount > 0) sb.append("★ 滿10點，免費贈送綜合割包 ").append(giftCount).append(" 個！\n");
                sb.append("會員剩餘點數：").append(totalPoints).append(" 點\n");
            }
            return sb.toString();

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            throw new GuaBaoException("結帳失敗，交易已回滾: " + e.getMessage());
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}