package entity;

import java.io.Serializable;
import java.sql.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kolynz
 */
@ManagedBean
@SessionScoped
public class Timesheet implements Serializable {

    private static final long serialVersionUID = 1L;
    public int timesheet_id;
    public int transactor_id;
    public int category_activity_id;
    public int mode_id;
    public int status_id;
    public int staff_id;
    public String activity;
    public int time_taken;
    public String unit_of_time;
    public Date activity_date;

    

    public int getTimesheet_id() {
        return timesheet_id;
    }

    public void setTimesheet_id(int timesheet_id) {
        this.timesheet_id = timesheet_id;
    }

    public int getTransactor_id() {
        return transactor_id;
    }

    public void setTransactor_id(int transactor_id) {
        this.transactor_id = transactor_id;
    }

    public int getCategory_activity_id() {
        return category_activity_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public void setCategory_activity_id(int category_activity_id) {
        this.category_activity_id = category_activity_id;
    }

    public int getMode_id() {
        return mode_id;
    }

    public void setMode_id(int mode_id) {
        this.mode_id = mode_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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

    public Date getActivity_date() {
        return activity_date;
    }

    public void setActivity_date(Date activity_date) {
        this.activity_date = activity_date;
    }

}
