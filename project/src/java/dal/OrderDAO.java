package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.ItemOrder;
import model.Order;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anh21
 */
public class OrderDAO extends DBContext{
    public int insert(int memberID, String status, ArrayList<ItemOrder> itemsOrder){
        LocalDateTime date = LocalDateTime.now();
        String time = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return executeUpdate("insert into OrderHistory (MemberID,Status,Time) values("+memberID+",'"+status+"', CONVERT(datetime,'"+time+"'))")+new ItemOrderDAO().insert(getOrderBy(memberID, status, date).getOrderID(), itemsOrder);
    }
    
    public int delete(int orderID){
        return executeUpdate("delete from OrderHistory where OrderID=" + orderID);
    }
    
    public int update(int orderID, int memberID, String status){
        return executeUpdate("update OrderHistory set MemberID="+memberID+", Status='"+status+"', Time=CONVERT(datetime,'"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"') where OrderID="+orderID);
    }
    
    public ArrayList<Order> getListOrderBy(int memberID){
        return getListOrder("select * from OrderHistory where MemberID="+memberID);
    }
    
    public ArrayList<Order> getListOrder(String sql){
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            ArrayList<Order> order = new ArrayList<>();
            while(rs.next()){
                order.add(getOrder(rs));
            }
            return order;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public Order getOrderBy(int orderID){
        return getOrderBy("select * from OrderHistory where OrderID="+orderID);
    }
    
    public Order getOrderBy(int memberID, String status, LocalDateTime date){
        return getOrderBy("select * from OrderHistory where MemberID="+memberID+" and Status='"+status+"' and date=CONVERT(datetime,'"+date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"')");
    }
    
    public Order getOrderBy(String sql){
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            return getOrder(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public Order getOrder(ResultSet rs) throws SQLException{
        return new Order(rs.getInt("OrderID"), rs.getInt("MemberID"), rs.getString("Status"), rs.getDate("Time"), new ItemOrderDAO().getListItemOrderBy(rs.getInt("OrderID")));
    }
}
