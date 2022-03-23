/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
//import java.sql.Time;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class TimesheetView implements Serializable {

    private static final long serialVersionUID = 1L;
    Date activity_date;
    String staff_name;
    String transactor_name;
    String category_name;
    String mode_name;
    int time_taken;
    String unit_of_time;
    String activity;
    String status_name;
    Time time_of_submission;

    public Time getTime_of_submission() {
        return time_of_submission;
    }

    public void setTime_of_submission(Time time_of_submission) {
        this.time_of_submission = time_of_submission;
    }


    public Date getActivity_date() {
        return activity_date;
    }

    public void setActivity_date(Date activity_date) {
        this.activity_date = activity_date;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getTransactor_name() {
        return transactor_name;
    }

    public void setTransactor_name(String transactor_name) {
        this.transactor_name = transactor_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getMode_name() {
        return mode_name;
    }

    public void setMode_name(String mode_name) {
        this.mode_name = mode_name;
    }

    public int getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(int time_taken) {
        this.time_taken = time_taken;
    }

    public String getUnit_of_time() {
        return unit_of_time;
    }

    public void setUnit_of_time(String unit_of_time) {
        this.unit_of_time = unit_of_time;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

}
