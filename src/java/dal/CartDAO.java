/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.CartItem;

/**
 *
 * @author anh21
 */
public class CartDAO extends DBContext{
    public int insert(int memberID,int bookID, int quantity){
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String sql = "insert into Cart (MemberID,BookID,Time,Quantity) values(" + memberID + "," + bookID + ", CONVERT(datetime, '"+time+"')," + quantity +")";
        return executeUpdate(sql);
    }
    
    public int delete(int memberID){
        return executeUpdate("delete from Cart where MemberID=" + memberID);
    }

    public int delete(int memberID,int bookID){
        return executeUpdate("delete from Cart where MemberID=" + memberID + " and BookID="+bookID);
    }
    
    public int update(int memberID,int bookID, int quantity){
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return executeUpdate("update Cart set Quantity="+quantity+", Time=CONVERT(datetime, '"+time+"')"+" where MemberID="+memberID+" and BookID="+bookID);
    }
    
    public ArrayList<CartItem> getListCartItemBy(int memberID){
        return getListCartItem("select * from Cart where MemberID="+memberID);
    }
    
    public ArrayList<CartItem> getListCartItem(String sql){
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            ArrayList<CartItem> cart = new ArrayList<>();
            while(rs.next()){
                cart.add(getCartItem(rs));
            }
            return cart;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public CartItem getCartItem(int memberID,int bookID){
        return getCartItem("select * from Cart where MemberID="+memberID+" and BookID="+bookID);
    }
    
    public CartItem getCartItem(String sql){
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            if(rs.next()){
                return getCartItem(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public CartItem getCartItem(ResultSet rs) throws SQLException{
        return new CartItem(new BookDAO().getBookBy(rs.getInt("BookID")), rs.getDate("Time"), rs.getInt("Quantity"));
    }
}
