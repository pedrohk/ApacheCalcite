package Schema;


import Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HrSchema {
    public final User[] users = {
            new User(1, "Pedro", "pedro@pedro.com"),
            new User(2, "Pedrinho", "pedrinho@gmail.com"),
            new User(3, "Pedro Silva", "pedro.silva@brturbo.com.br")
    };

    public List<String> executeJoinQuery(String modelPath) throws Exception {
        List<String> results = new ArrayList<>();

        Properties info = new Properties();
        info.setProperty("model", modelPath);
        info.setProperty("caseSensitive", "false");

        String sql = "SELECT u.name, o.product FROM MEMORY.users AS u JOIN FILES.\"orders\" AS o ON u.id = o.userId";

        try (Connection conn = DriverManager.getConnection("jdbc:calcite:", info);
             Statement stmt = conn.createStatement();

             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                results.add(rs.getString("name") + " comprou " + rs.getString("product"));
            }
        }
        return results;
    }
}