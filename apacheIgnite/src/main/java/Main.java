import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.ClientConnectionException;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args){
        String jdbcURL = "jdbc:ignite:thin://127.0.0.1:32772/";

        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            Connection con = DriverManager.getConnection(jdbcURL);

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM SUBSCRIBER");

            while (rs.next()){
                int subscId = rs.getInt("SUBSC_ID");
                String subscName = rs.getString("SUBSC_NAME");
                String subscLName = rs.getString("SUBSC_SURNAME");

                System.out.println(subscId + " " + subscName + " " + subscLName);
            }

        }catch(ClientConnectionException | ClassNotFoundException ce){
            System.out.println(ce.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}