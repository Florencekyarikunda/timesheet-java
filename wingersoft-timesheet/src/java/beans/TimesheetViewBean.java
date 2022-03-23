/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import connection.DBConnection;
import entity.Timesheet;
import entity.TimesheetView;
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
 * @author user
 */
@ManagedBean
@SessionScoped
public class TimesheetViewBean {

    static Logger LOGGER = Logger.getLogger(TimesheetViewBean.class.getName());
    private List<TimesheetView> timesheetList;
    private TimesheetView timesheetView;

      //    Read 
    public List<TimesheetView> getTimesheetList() {
       
        String sql ="SELECT activity_date,staff_name,transactor_name,category_name,mode_name,time_taken,unit_of_time,activity,time_of_submission,status_name FROM timesheet_track";
        
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
            e.printStackTrace();
            LOGGER.log(Level.ERROR, e);
        }
        return timesheetList;
    }
    
    
//    public String Edit(int timesheet_id){
//         
//    
//    }

//    // create 
//    public String createTimesheet() {
//        TimesheetView timesheet = new TimesheetView();
//        String sql = "INSERT INTO timesheet (status_id,transactor_id,mode_id ,staff_id,category_activity_id, time_taken,activity,activity_date,unit_of_time) values(?,?,?,?,?,?,?,?,?)";
//        int result = 0;
//
//        try (
//            Connection con = DBConnection.getMySQLConnection();
//            PreparedStatement psmt = con.prepareStatement(sql);) {
//            psmt.setString(1, timesheet.getStatus_name());
//            psmt.setString(2, timesheet.getTransactor_name());
//            psmt.setString(3, timesheet.getMode_name());
//            psmt.setString(4, timesheet.getStaff_name());
//            psmt.setString(5, timesheet.getCategory_name());
//            psmt.setInt(6, timesheet.getTime_taken());
//            psmt.setString(7, timesheet.getActivity());
//            psmt.setDate(8, timesheet.getActivity_date());
//            psmt.setString(9, timesheet.getUnit_of_time());
//            
//
//            result = psmt.executeUpdate();
//        } catch (Exception e) {
//            LOGGER.log(Level.ERROR, e);
//        }
//        if (result != 0) {
//            return "timesheet.xhtml?faces-redirect=true";
//        } else {
//            return "entryForm.xhtml?faces-redirect=true";
//
//        }
//    }

    private TimesheetView mapTimesheet(ResultSet result) {
        TimesheetView timesheet_object = new TimesheetView();

        try {
//            timesheet_object.setTimesheet_id(result.getInt("timesheet_id"));
            timesheet_object.setStatus_name(result.getString("status_name"));
            timesheet_object.setTransactor_name(result.getString("transactor_name"));
            timesheet_object.setMode_name(result.getString("mode_name"));
            timesheet_object.setStaff_name(result.getString("staff_name"));
            timesheet_object.setCategory_name(result.getString("category_name"));
            timesheet_object.setTime_taken(result.getInt("time_taken"));
            timesheet_object.setActivity(result.getString("activity"));
            timesheet_object.setActivity_date(result.getDate("activity_date"));
            timesheet_object.setTime_of_submission(result.getTime("time_of_submission"));
            timesheet_object.setUnit_of_time(result.getString("unit_of_time"));

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return timesheet_object;
    }

    /**
     * @return the timesheet
     */
    public TimesheetView getTimesheet() {
        if(this.timesheetView == null)
            this.timesheetView = new TimesheetView();
        return timesheetView;
    }

    /**
     * @param timesheet the timesheet to set
     */
    public void setTimesheet(TimesheetView timesheet) {
        this.timesheetView = timesheet;
    }

    
    
    
}
