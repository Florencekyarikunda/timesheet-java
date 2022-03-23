/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import connection.DBConnection;
import static dao.DAOUtil.prepareStatement;
import entity.Status;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author HP
 */
@ManagedBean
@SessionScoped
public class StatusBean implements Serializable {

    private static final long serialVersionUID = 1L;
    static Logger LOGGER = Logger.getLogger(StatusBean.class.getName());
    private List<Status> statusList;
    private Status status;
    

// create 
    public int createStatus(Status status) {
                int myStatus = 0;

        if(status.getStatus_id() != 0){
//            System.out.println("Status already created, the status id is not null.");
        }
        String sql = "INSERT INTO status(status_name)VALUES(?)"; 
        Object[] values = {
            status.getStatus_name(),
            
        };
       
        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = prepareStatement(con, sql, true, values);) {
           
            result = psmt.executeUpdate();
            if(result == 0)
                System.out.println("Creating status failed, no rows affected");
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
//            return "timesheet.xhtml?faces-redirect=true";
          myStatus = 1;

        } else {
//            return "status.xhtml?faces-redirect=true";
            myStatus = 0;

        }
        return myStatus;
    }

//    Read all
    public List<Status> getStatusList() {
        String sql = "SELECT * FROM status ORDER BY status_id ";
        statusList = new ArrayList<>();
        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);
                ResultSet result = psmt.executeQuery();) {
            while (result.next()) {
                statusList.add(mapStatus(result));
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        return statusList;
    }
    
    
    //get one status
      public void getStatus(int statusId) {
        System.out.println("STATUS ID: " + statusId);
        String sql = "SELECT * FROM status WHERE status_id = " + statusId;
        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            ResultSet result = psmt.executeQuery();
            if(result.next()) {
//                setStatus(mapStatus(result));
                status = mapStatus(result);
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        //return getStatus();        
        System.out.println("From Bean Status ID: " + status.status_id);
        //return status;
    }


    private Status mapStatus(ResultSet result) {
        Status status_object = new Status();

        try {
            status_object.setStatus_id(result.getInt("status_id"));
            status_object.setStatus_name(result.getString("status_name"));
            
            
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return status_object;
    }
    
    
      //delete
    public String deleteStatus(Status status) {
        if (status.getStatus_id() != 0) {
            System.out.println("Status already deleted, the status id is not null.");
        }
        String sql = "DELETE FROM status WHERE status_id=?";
        Object[] values = {
            status.getStatus_id()
        };

        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setLong(1, status.getStatus_id());
            psmt.executeUpdate();
            System.out.println("Deleted successfully");

            result = psmt.executeUpdate();
            if (result == 0) {
                System.out.println("Deleting status failed, no rows affected");
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
            return "timesheet.xhtml?faces-redirect=true";
        } else {
            return "status.xhtml?faces-redirect=true";

        }
    }

    //update
    public String updateStatus(Status status) {
        if (status.getStatus_id() != 0) {
            System.out.println("Status already updated, the status id is not null.");
        }
        String sql = "UPDATE FROM status WHERE status_id=?";
        Object[] values = {
            status.getStatus_id()
        };

        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setLong(1, status.getStatus_id());
            psmt.executeUpdate();
            System.out.println("Updated successfully");

            result = psmt.executeUpdate();
            if (result == 0) {
                System.out.println("Updating status failed, no rows affected");
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
            return "timesheet.xhtml?faces-redirect=true";
        } else {
            return "status.xhtml?faces-redirect=true";

        }
    }
    
    
    //save status
    public String saveStatus(Status astatus){
         if (astatus.getStatus_id() != 0) {
            System.out.println("Status already saved, the status id is not null.");
        }
        String sql = "UPDATE FROM status WHERE status_id=?";
        Object[] values = {
            astatus.getStatus_id()
        };

        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setLong(1, astatus.getStatus_id());
            psmt.executeUpdate();
            System.out.println("Saved successfully");

            result = psmt.executeUpdate();
            if (result == 0) {
                System.out.println("Saving status failed, no rows affected");
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
            return "timesheet.xhtml?faces-redirect=true";
        } else {
            return "status.xhtml?faces-redirect=true";
 
    }
    }
    
   public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
