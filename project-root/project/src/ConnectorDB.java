
import java.sql.*;
import javax.swing.*;


    
public class ConnectorDB {
    
    Connection conn=null;
    
    public static Connection ConnectDB()
    {

    try{

    Class.forName("org.sqlite.JDBC");
    Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\ACTIVE RSEARCH CLEAN AREA\\ABDULLAH\\Final Project\\Final_project.sqlite");
    return conn;
    }
    catch(Exception e){

    JOptionPane.showMessageDialog(null, e);
    return null;

    }


    }


}
