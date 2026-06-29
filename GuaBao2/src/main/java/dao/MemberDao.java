package dao;

import java.sql.Connection;
import java.sql.SQLException;
import model.Member;

public interface MemberDao {
	//create
    int insert(Connection conn, Member member) throws SQLException;
	
	//read
    Member findById(Connection conn, String id) throws SQLException;
	
	//update
    int updatePoints(Connection conn, String id, int points) throws SQLException;
	
	//delete
    int delete(Connection conn, String id) throws SQLException;

}