/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author anh21
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBContext {

    protected Connection connection;

    public DBContext() {
        // Edit URL , username, password to authenticate with your MS SQL Server
        String username = "sa", password = "123", port="1433", dataBaseName="ShopDev", url = "jdbc:sqlserver://localhost:"+port+";databaseName= "+dataBaseName;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
    
    protected int executeUpdate(String sql){
        try {
            return connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}