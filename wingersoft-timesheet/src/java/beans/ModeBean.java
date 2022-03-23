/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static beans.ModeBean.LOGGER;
import com.mysql.cj.xdevapi.Result;
import connection.DBConnection;
import dao.DAOUtil;
import static dao.DAOUtil.prepareStatement;
import entity.Mode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author HP
 */
@ManagedBean
@RequestScoped
public class ModeBean {

    private static final long serialVersionUID = 1L;
    static Logger LOGGER = Logger.getLogger(ModeBean.class.getName());
//    private String mode_name;
    private List<Mode> modeList;
    private Mode mode;

//    create 
    public int createMode(Mode mode) {
        int status = 0;
        if (mode.getMode_id() != 0) {
            //System.out.println("User already created, the mode id is not null.");
            //think through what you can display to the user
        }
        String sql = "INSERT INTO mode(mode_name)VALUES(?)";
        Object[] values = {
            mode.getMode_name(),};
        //Mode mode = new Mode();

        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = prepareStatement(con, sql, true, values);) {

            result = psmt.executeUpdate();
            if (result == 0) {
                System.out.println("Creating user failed, no rows affected");
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
            //return "timesheet.xhtml?faces-redirect=true";
            //return "mode.xhtml?faces-redirect=true";
            status = 1;
        } else {
            //return "mode.xhtml?faces-redirect=true";
            status = 0;

        }
        return status;
    }

//    Read 
    public List<Mode> getModeList() {
        String sql = "SELECT * FROM mode ORDER BY mode_id";

        modeList = new ArrayList<>();
        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                modeList.add(mapMode(result));
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        return modeList;
    }
    
    //get one mode to be edited and deleted
    public void getMode(int modeId) {
        System.out.println("Mode ID: " + modeId);
        String sql = "SELECT * FROM mode WHERE mode_id = " + modeId;
        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            ResultSet result = psmt.executeQuery();
            if (result.next()) {
                //setMode(mapMode(result));
                mode = mapMode(result);
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        //return getMode();        
        System.out.println("From Bean Mode ID: " + mode.mode_id);
        //return mode;
    }

    private Mode mapMode(ResultSet result) {
        Mode mode_object = new Mode();

        try {
            mode_object.setMode_id(result.getInt("mode_id"));
            mode_object.setMode_name(result.getString("mode_name"));

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return mode_object;
    }

    //delete
    public String deleteMode(Mode mode) {
        if (mode.getMode_id() != 0) {
            System.out.println("Mode already deleted, the mode id is not null.");
        }
        String sql = "DELETE FROM mode WHERE mode_id=?";
//        String sql = "DELETE FROM 'timesheet'.'mode' WHERE 'mode'.'mode_id'(?)";
        Object[] values = {
            mode.getMode_id()
        };

        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setLong(1, mode.getMode_id());
            psmt.executeUpdate();
            System.out.println("Deleted successfully");

            result = psmt.executeUpdate();
            if (result == 0) {
                System.out.println("Deleting mode failed, no rows affected");
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
            return "timesheet.xhtml?faces-redirect=true";
        } else {
            return "mode.xhtml?faces-redirect=true";

        }
    }

    //update
    public String updateMode(Mode mode) {
        if (mode.getMode_id() != 0) {
            System.out.println("Mode already updated, the mode id is not null.");
        }
        String sql = "UPDATE FROM mode WHERE mode_id=?";
        Object[] values = {
//            mode.getMode_id()
            mode.getMode_id()
        };

        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setLong(1, mode.getMode_id());
            psmt.executeUpdate();
            System.out.println("Updated successfully");

            result = psmt.executeUpdate();
            if (result == 0) {
                System.out.println("Updating mode failed, no rows affected");
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
            return "timesheet.xhtml?faces-redirect=true";
        } else {
            return "mode.xhtml?faces-redirect=true";

        }
    }


    /**
     * @return the mode
     */
    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

}
