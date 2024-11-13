/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Member;
import java.util.ArrayList;
/**
 *
 * @author anh21
 */
public class MemberDAO extends DBContext{
    public int insert(Member member){
        return executeUpdate("INSERT INTO Member (AccountName,Name,Password,PhoneNumber,Address,Role) VALUES ('"+member.getAccountName()+"','"+member.getName()+"','"+member.getPassword()+"','"+member.getPhoneNumber()+"','"+member.getAddress()+"',"+member.getRole()+")");
    }
    
    public int update(Member member){
        return executeUpdate("update Member set Name='"+member.getName()+"',Password='"+member.getPassword()+"',PhoneNumber='"+member.getPhoneNumber()+"',Address='"+member.getAddress()+"',Role='"+member.getRole()+"' where MemberID="+member.getId());
    }
    
    //get list member have role > input role
    public ArrayList<Member> getListMemberBy(int role){
        return getListMember("select * from Member where Role>"+role);
    }
    
    //get list member by use sql command
    public ArrayList<Member> getListMember(String sql){
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            ArrayList<Member> memberList = new ArrayList<>();
            while(rs.next()){
                memberList.add(getMemberBy(rs));
            }
            return memberList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    //for login
    public Member getMemberBy(String username, String password){
        return getMember("select * from Member where AccountName='"+username+"' and Password='"+password+"'");
    }
    
    public Member getMemberBy(String username){
        return getMember("select * from Member where AccountName='"+username+"'");
    }
    
    //get member by use sql command
    public Member getMember(String sql){
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            if(rs.next()){
                return getMemberBy(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    //return new Member object by use data in ResultSet
    public Member getMemberBy(ResultSet rs) throws SQLException{
        Member member = new Member();
        member.setAccountName(rs.getString("AccountName"));
        member.setPassword(rs.getString("Password"));
        member.setId(rs.getInt("MemberID"));
        member.setName(rs.getString("Name"));
        member.setPhoneNumber(rs.getString("PhoneNumber"));
        member.setAddress(rs.getString("Address"));
        member.setRole(rs.getInt("Role"));
        member.setOrderHistory(new OrderDAO().getListOrderBy(member.getId()));
        member.setCart(new CartDAO().getListCartItemBy(member));
        return member;
    }
}
