/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomado2016.modulojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Chaz
 */
public class DBUtil {
    
    public static Connection getProxoolConexion() throws Exception {        
        Connection conn = DriverManager.getConnection("proxool.postgresLocal");
        conn.setAutoCommit(false);
        return conn;
    }
    
    public static Connection getPoolConnection() throws Exception {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/postgresLocal");
        Connection conn = ds.getConnection();
        conn.setAutoCommit(false);
        return conn;
    }

    public static void cierraConexion(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.rollback();
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
        }
    }

    public static Connection getConexion() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/diplomado",
                                                      "diplomadoUser",
                                                      "diplomadoUser");
        conn.setAutoCommit(false);
        return conn;
    }
}
