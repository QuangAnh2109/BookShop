/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Book;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author anh21
 */
public class BookDAO extends DBContext{
    public int insert(Book book){
        CompanyDAO companyDAO = new CompanyDAO();
        CoverTypeDAO coverTypeDAO = new CoverTypeDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        companyDAO.insert(book.getCompany().getCompanyName());
        coverTypeDAO.insert(book.getType().getCover());
        categoryDAO.insert(book.getCategory().getCategory());
        book.setCompany(companyDAO.getCompanyBy(book.getCompany()));
        book.setCategory(categoryDAO.getCategoryBy(book.getCategory()));
        book.setType(coverTypeDAO.getCoverTypeBy(book.getType()));
        return executeUpdate("insert into Book (Name,Quantity,Price,Discount,Introduce,NumberPages,Weight,Size,PublishingYear,AuthorName,CompanyID,CoverTypeID,CategoryID) values(N'"+book.getName()+"',"+book.getQuantity()+","+book.getPrice()+","+book.getDiscount()+",N'"+book.getIntroduce()+"',"+book.getNumberPages()+","+book.getWeight()+",'"+book.getSize()+"',"+book.getPublishingYear()+",N'"+book.getAuthorName()+"',"+book.getCompany().getId()+","+book.getType().getId()+","+book.getCategory().getId()+")");
    }
    
    public int update(Book book){
        CompanyDAO companyDAO = new CompanyDAO();
        CoverTypeDAO coverTypeDAO = new CoverTypeDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        companyDAO.insert(book.getCompany().getCompanyName());
        coverTypeDAO.insert(book.getType().getCover());
        categoryDAO.insert(book.getCategory().getCategory());
        book.setCompany(companyDAO.getCompanyBy(book.getCompany()));
        book.setCategory(categoryDAO.getCategoryBy(book.getCategory()));
        book.setType(coverTypeDAO.getCoverTypeBy(book.getType()));
        String sql = "update Book set Name = N'"+book.getName()+"' , Quantity = "+book.getQuantity()+", Price = "+book.getPrice()+", Discount = "+book.getDiscount()+", Introduce = N'"+book.getIntroduce()+"' , NumberPages = "+book.getNumberPages()+", Weight = "+book.getWeight()+", Size = '"+book.getSize()+"' , PublishingYear = "+book.getPublishingYear()+", AuthorName = N'"+book.getAuthorName()+"' , CompanyID = "+book.getCompany().getId()+", CoverTypeID = "+book.getType().getId()+", CategoryID = "+book.getCategory().getId()+" where BookID = "+book.getId();
        try {
            int i = connection.prepareStatement(sql).executeUpdate();
            companyDAO.delete(book.getType().getId());
            coverTypeDAO.delete(book.getType().getId());
            categoryDAO.delete(book.getCategory().getId());
            return i;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public ArrayList<Book> getListBookBy(String searchName, ArrayList<String> companys, ArrayList<String> categorys, ArrayList<String> covers, String order){
        if(searchName==null) searchName="";
        String sql = "select * from Book where Name like N'%"+searchName.trim()+"%'", companyQuery = " and CompanyID in (", categoryQuery = " and CategoryID in (", coverQuery = " and CoverTypeID in (";
        int i;
        if(companys.isEmpty()&&categorys.isEmpty()&&covers.isEmpty()){
            return getListBookBy(sql+" order by "+order);
        }
        
        for(i=0;i<companys.size();i++){
            if(i!=companys.size()-1){
                companyQuery += companys.get(i)+",";
            }else sql += companyQuery + companys.get(i) + ") ";
        }
        for(i=0;i<categorys.size();i++){
            if(i!=categorys.size()-1){
                categoryQuery += categorys.get(i)+",";
            }else sql += categoryQuery + categorys.get(i) + ") ";
        }
        for(i=0;i<covers.size();i++){
            if(i!=covers.size()-1){
                coverQuery += covers.get(i)+",";
            }else sql += coverQuery + covers.get(i) + ") ";
        }
        return getListBookBy(sql+" order by "+order);
    }
    
    public ArrayList<Book> getAllBook(){
        return getListBookBy("select * from Book");
    }
    
    public Book getBookBy(int id){
        return getBookBy("select * from Book where BookID = "+id);
    }
    
    public ArrayList<Book> getListBookBy(String sql){
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            ArrayList<Book> book = new ArrayList<>();
            while(rs.next()){
                book.add(getBook(rs));
            }
            return book;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public Book getBookBy(String sql){
        try {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            if(rs.next()){
                return getBook(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    //String introduce, String size, String authorName, int quantity, int numberPages, int publishingYear, BigDecimal price, float discount, float weight, int typeID, int categoryID, int companyID
    public Book getBook(ResultSet rs) throws SQLException{
        return new Book(rs.getInt("BookID"),rs.getString("Name"),rs.getString("Introduce"),rs.getString("Size"),rs.getString("AuthorName"),rs.getInt("Quantity"),rs.getInt("NumberPages"),rs.getInt("PublishingYear"),rs.getInt("Price"),rs.getFloat("Discount"),rs.getFloat("Weight"),new CoverTypeDAO().getCoverTypeBy(rs.getInt("CoverTypeID")),new CategoryDAO().getCategoryBy(rs.getInt("CategoryID")),new CompanyDAO().getCompanyBy(rs.getInt("CompanyID")));
    }
}
