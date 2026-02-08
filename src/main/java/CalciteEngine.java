import Schema.HrSchema;
import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CalciteEngine {
    public void runQuery() throws SQLException {
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        info.setProperty("caseSensitive", "false");

        try (Connection connection = DriverManager.getConnection("jdbc:calcite:", info)) {
            CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
            SchemaPlus rootSchema = calciteConnection.getRootSchema();

            rootSchema.add("hr", new ReflectiveSchema(new HrSchema()));

            String sql = "SELECT name, email FROM hr.users WHERE id > 1 ORDER BY name DESC";
            try (Statement statement = calciteConnection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name") + " - " + resultSet.getString("email"));
                }
            }
        }
    }
}
