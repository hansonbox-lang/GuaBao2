package dao;

import java.sql.Connection;
import java.sql.SQLException;
import model.Porder;
import model.PorderDetail;

public interface PorderDao {
	//create
    int insertOrder(Connection conn, Porder order) throws SQLException;
    void insertOrderDetail(Connection conn, PorderDetail detail) throws SQLException;
	
	//read
	
	//update
	
	//delete
}