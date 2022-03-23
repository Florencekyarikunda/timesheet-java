
package beans;


import connection.DBConnection;

import entity.Timesheet;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.management.Query;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author kolynz
 */
@ManagedBean
@SessionScoped
public class TimesheetBean implements Serializable {

    static Logger LOGGER = Logger.getLogger(TimesheetBean.class.getName());
    private List<Timesheet>timesheetList;
    private Timesheet timesheet;
    private Object entityManager;
    

    //    Read 
    public List<Timesheet> getTimesheetTrack() {
        String sql1 = "select timesheet_id,status_id,transactor_id,mode_id ,staff_id,category_activity_id, time_taken,activity,activity_date,unit_of_time  from timesheet order by timesheet_id ";
        String sql ="SELECT activity_date,staff_name,transactor_name,category_name,mode_name,time_taken,unit_of_time,activity,status_name FROM timesheet_track";
        
        timesheetList = new ArrayList<>();
        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);
                ) {
            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                timesheetList.add(mapTimesheet(result));
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        return timesheetList;
    }
    
      //    Read 
    public List<Timesheet> getTimesheetList() {
        String sql = "select timesheet_id,status_id,transactor_id,mode_id ,staff_id,category_activity_id, time_taken,activity,activity_date,unit_of_time  from timesheet order by timesheet_id ";
//        String sql ="SELECT activity_date,staff_name,transactor_name,category_name,mode_name,time_taken,unit_of_time,activity,status_name FROM timesheet_track";
        
        timesheetList = new ArrayList<>();
        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);
                ) {
            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                timesheetList.add(mapTimesheet(result));
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        return timesheetList;
    }

    // create 
    public String createTimesheet() {
        Timesheet timesheet = new Timesheet();
        String sql = "INSERT INTO timesheet (status_id,transactor_id,mode_id ,staff_id,category_activity_id, time_taken,activity,activity_date,unit_of_time) values(?,?,?,?,?,?,?,?,?)";
        int result = 0;

        try (
            Connection con = DBConnection.getMySQLConnection();
            PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, timesheet.getStatus_id());
            psmt.setInt(2, timesheet.getTransactor_id());
            psmt.setInt(3, timesheet.getMode_id());
            psmt.setInt(4, timesheet.getStaff_id());
            psmt.setInt(5, timesheet.getCategory_activity_id());
            psmt.setInt(6, timesheet.getTime_taken());
            psmt.setString(7, timesheet.getActivity());
            psmt.setDate(8, timesheet.getActivity_date());
            psmt.setString(9, timesheet.getUnit_of_time());
            

            result = psmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result != 0) {
            return "timesheet.xhtml?faces-redirect=true";
        } else {
            return "entryForm.xhtml?faces-redirect=true";

        }
    }

    
    private Timesheet mapTimesheet(ResultSet result) {
        Timesheet timesheet_object = new Timesheet();

        try {
            timesheet_object.setTimesheet_id(result.getInt("timesheet_id"));
            timesheet_object.setStatus_id(result.getInt("status_id"));
            timesheet_object.setTransactor_id(result.getInt("transactor_id"));
            timesheet_object.setMode_id(result.getInt("mode_id"));
            timesheet_object.setStaff_id(result.getInt("staff_id"));
            timesheet_object.setCategory_activity_id(result.getInt("category_activity_id"));
            timesheet_object.setTime_taken(result.getInt("time_taken"));
            timesheet_object.setActivity(result.getString("activity"));
            timesheet_object.setActivity_date(result.getDate("activity_date"));
            timesheet_object.setUnit_of_time(result.getString("unit_of_time"));

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return timesheet_object;
    }
    
    
    public int updateTimesheet(Timesheet aMode_activity) {
        int IsUpdated = 0;
        String sql = "UPDATE mode_activity SET mode_name=? WHERE mode_activity_id=?";
        try (
                Connection conn = DBConnection.getMySQLConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, aMode_activity.getMode_name());
            ps.setInt(2, aMode_activity.getMode_activity_id());
            ps.executeUpdate();
            IsUpdated = 1;
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        return IsUpdated;
    }

    /**
     * @return the timesheet
     */
    public Timesheet getTimesheet() {
        if(this.timesheet == null)
            this.timesheet = new Timesheet();
        return timesheet;
    }

    /**
     * @param timesheet the timesheet to set
     */
    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }
    

}
