package service;

import exception.GuaBaoException;
import model.Member;
import model.Porder;

public interface MemberService {
	//會員登入
    Member login(String memberId) throws GuaBaoException;
	//查詢點數
    Member queryMember(String memberId) throws GuaBaoException;
	//新增會員
    void registerMember(String memberId, int initPoints) throws GuaBaoException;
	//修改點數
    void updateMemberPoints(String memberId, int points) throws GuaBaoException;
	//刪除會員
    void deleteMember(String memberId) throws GuaBaoException;
	//確認結帳
    String checkout(Porder porder) throws GuaBaoException;
}