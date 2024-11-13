/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.SQLException;
import model.CoverType;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author anh21
 */
public class CoverTypeDAO extends DBContext{
    public int update(int id, String Cover){
        return executeUpdate("update CoverType set Cover='"+Cover+"' where CoverTypeID="+id);
    }
    
    public int insert(String cover) {
        return executeUpdate("insert into CoverType (Cover) values('" + cover + "')");
    }

    public int delete(int id) {
        return executeUpdate("delete from CoverType where CoverTypeID=" + id);
    }

    public ArrayList<CoverType> getAllCoverType() {
        try {
            ResultSet rs = connection.prepareStatement("select * from CoverType").executeQuery();
            ArrayList<CoverType> cover = new ArrayList<>();
            while (rs.next()) {
                cover.add(getCoverType(rs));
            }
            return cover;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public CoverType getCoverTypeBy(int id) {
        String sql = "select * from CoverType where CoverTypeID=" + id;
        return getCoverTypeBy(sql);
    }
    
    public CoverType getCoverTypeBy(CoverType coverType) {
        String sql = "select * from CoverType where Cover='" + coverType.getCover()+"'";
        return getCoverTypeBy(sql);  
    }
    
    public CoverType getCoverTypeBy(String sql) {
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return getCoverType(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public CoverType getCoverType(ResultSet rs) throws SQLException {
        return new CoverType(rs.getInt("CoverTypeID"), rs.getString("Cover"));
    }
}
