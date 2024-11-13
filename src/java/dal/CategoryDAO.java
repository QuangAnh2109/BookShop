/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author anh21
 */
public class CategoryDAO extends DBContext {
    public int update(int id, String category){
        return executeUpdate("update Category set Category=N'"+category+"' where CategoryID="+id);
    }

    public int insert(String category) {
        return executeUpdate("insert into Category (Category) values(N'" + category + "')");
    }

    public int delete(int id) {
        return executeUpdate("delete from Category where CategoryID=" + id);
    }

    public ArrayList<Category> getAllCategory() {
        try {
            ResultSet rs = connection.prepareStatement("select * from Category").executeQuery();
            ArrayList<Category> category = new ArrayList<>();
            while (rs.next()) {
                category.add(getCategory(rs));
            }
            return category;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Category getCategoryBy(int id) {
        String sql = "select * from Category where CategoryID=" + id;
        return getCategoryBy(sql);
    }
    
    public Category getCategoryBy(Category category) {
        String sql = "select * from Category where Category='" + category.getCategory()+"'";
        return getCategoryBy(sql);  
    }
    
    public Category getCategoryBy(String sql) {
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return getCategory(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Category getCategory(ResultSet rs) throws SQLException {
        return new Category(rs.getInt("CategoryID"), rs.getString("Category"));
    }
}
