/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class DAOUtil {
    
    //hide the constructor
    private DAOUtil(){
        
    }
    
    //Returns a preparedStatement of the given connection, set with the given SQL query and the given parameter values
    public static PreparedStatement prepareStatement(Connection connection, 
            String sql, boolean returnGeneratedKeys, Object... values)throws SQLException{
        PreparedStatement statement = connection.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(statement, values);
        return statement;
    }
    
    //Set the fiven paramenter values in the given PreparedStatement
    public static void setValues(PreparedStatement statement, Object... values)throws SQLException{
        for(int i = 0; i < values.length; i++){
            statement.setObject(i + 1, values[i]);
        }
    }
}
