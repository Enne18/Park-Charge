package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DbPrenotazioni {
    public DbPrenotazioni()
    {
    }

    public ArrayList<HashMap<String, Object>> query(String comandoSql)
    {
        Connection conn;
        Statement stmt;
        ResultSet rs;
        ArrayList<HashMap<String, Object>> list = null;
        int columns;
        HashMap<String, Object> row;
        ResultSetMetaData md;

        System.out.println("DbPrenotazioni: " + comandoSql + "\n");

        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:dati/DbPrenotazioni");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(comandoSql);
            md = rs.getMetaData();
            columns = md.getColumnCount();
            list = new ArrayList<HashMap<String, Object>>();
            while (rs.next())
            {
                row = new HashMap<String, Object>(columns);
                for(int i=1; i<=columns; ++i)
                    row.put(md.getColumnName(i), rs.getObject(i));
                list.add(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return list;
    }

    public void update(String comandoSql)
    {
        Connection conn;
        Statement stmt;

        System.out.println("DbPrenotazioni: " + comandoSql + "\n");

        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:dati/DbPrenotazioni");
            stmt = conn.createStatement();
            stmt.executeUpdate(comandoSql);
            stmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
