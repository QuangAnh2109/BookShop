/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import model.ItemOrder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author anh21
 */
public class ItemOrderDAO extends DBContext{
    public int insert(int orderID, int bookID, BigDecimal price, int quantity){
        String sql = "insert into BookOrder (OrderID,BookID,Price,Quantity) values(" +orderID+","+bookID+","+price+","+quantity+")";
        return executeUpdate(sql);
    }
    
    public int insert(int orderID, ArrayList<ItemOrder> itemsOrder){
        String sql="";
        for(ItemOrder item:itemsOrder){
            sql += "insert into BookOrder (OrderID,BookID,Price,Quantity) values(" +orderID+","+item.getBook().getId()+","+item.getPrice()+","+item.getQuantity()+")";
        }
        return executeUpdate(sql);
    }
    
    public int delete(int orderID, int bookID){
        return executeUpdate("delete from BookOrder where OrderID=" + orderID + " and BookID="+bookID);
    }
    
    public int delete(int orderID){
        return executeUpdate("delete from BookOrder where OrderID=" + orderID);
    }
    
    public int update(int orderID, int bookID, BigDecimal price, int quantity){
        return executeUpdate("update BookOrder set Price="+price+", Quantity="+quantity+")"+" where OrderID="+orderID+" and BookID="+bookID);
    }
    
    public ArrayList<ItemOrder> getListItemOrderBy(int orderID){
        return getListItemOrder("select * from BookOrder where OrderID="+orderID);
    }
    
    public ArrayList<ItemOrder> getListItemOrder(String sql){
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            ArrayList<ItemOrder> itemOrder = new ArrayList<>();
            while(rs.next()){
                itemOrder.add(getItemOrder(rs));
            }
            return itemOrder;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public ItemOrder getItemOrder(ResultSet rs) throws SQLException{
        return new ItemOrder(new BookDAO().getBookBy(rs.getInt("BookID")), rs.getBigDecimal("Price"), rs.getInt("Quantity"));
    }
}
