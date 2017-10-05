package app;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DbXmlGenerator {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/google_wh_dev_3";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
        IDatabaseConnection dbconn = new DatabaseConnection(conn);
        QueryDataSet partDS = new QueryDataSet(dbconn);
            partDS.addTable("fs_item", "select * from fs_item where itemid IN (3600000013);");
            partDS.addTable("fs_itemdetail", "select * from fs_itemdetail where itemid IN (3600000013);");

            FlatXmlDataSet.write(partDS, new FileOutputStream(
                    "/home/sasingh/Aarondev/test.xml"));
            System.out.println("Done !!!!!!!!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
 finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
