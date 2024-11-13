/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.SQLException;
import model.Company;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author anh21
 */
public class CompanyDAO extends DBContext{
    public int update(int id, String companyName){
        return executeUpdate("update PublishingCompany set CompanyName=N'"+companyName+"' where CompanyID="+id);
    }
    
    public int insert(String company) {
        return executeUpdate("insert into PublishingCompany (CompanyName) values(N'" + company + "')");
    }

    public int delete(int id) {
        return executeUpdate("delete from PublishingCompany where CompanyID=" + id);
    }

    public ArrayList<Company> getAllCompany() {
        try {
            ResultSet rs = connection.prepareStatement("select * from PublishingCompany").executeQuery();
            ArrayList<Company> company = new ArrayList<>();
            while (rs.next()) {
                company.add(getCompany(rs));
            }
            return company;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    
    public Company getCompanyBy(int id) {
        String sql = "select * from PublishingCompany where CompanyID=" + id;
        return getCompanyBy(sql);
    }
    
    public Company getCompanyBy(Company company) {
        String sql = "select * from PublishingCompany where CompanyName='" + company.getCompanyName()+"'";
        return getCompanyBy(sql);  
    }
    
    public Company getCompanyBy(String sql) {
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return getCompany(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Company getCompany(ResultSet rs) throws SQLException {
        return new Company(rs.getInt("CompanyID"), rs.getString("CompanyName"));
    }
}
