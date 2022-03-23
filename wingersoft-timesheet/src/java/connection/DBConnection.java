/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author kolynz
 */

public class DBConnection implements Serializable{

      private static final long serialVersionUID = 1L;

    static Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static Connection dbconn;

    public static Connection getMySQLConnection() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/timesheet");
            dbconn = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            LOGGER.log(Level.ERROR, e);
        }
        return dbconn;
    }

  
}
