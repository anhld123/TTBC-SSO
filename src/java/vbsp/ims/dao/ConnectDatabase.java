package vbsp.ims.dao;

// Thực hiện thêm các thư việc cho kết nối CSDL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDatabase {

    Connection conn;

    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.63.8.58:1521:DBREPORT", "intellect", "imsrpt321#");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public void closeConnection(){
        if(conn!=null){
            try {
                if(!conn.isClosed()){
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        conn=null;
        }
    }
}
