package beans;

import connection.DBConnection;
import static dao.DAOUtil.prepareStatement;
import entity.Staff;
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
 * @author user
 */
@ManagedBean
@SessionScoped
public class StaffBean implements Serializable {

    private static final long serialVersionUID = 1L;
    static Logger LOGGER = Logger.getLogger(StaffBean.class.getName());
    private List<Staff> staffList;
    private Staff staff;


    
//    create 
    public int createStaff(Staff staff) {
        int status1 = 0;
        if (staff.getStaff_id() != 0) {
            //System.out.println("User already created, the mode id is not null.");
            //think through what you can display to the user
        }
        String sql = "INSERT INTO staff(first_name,last_name)VALUES(?,?)";
        Object[] values = {
            staff.getFirstName(),
            staff.getLastName(),

        };
        //Staff staff = new Staff();

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
            status1 = 1;
        } else {
            //return "mode.xhtml?faces-redirect=true";
            status1 = 0;

        }
        return status1;
    }


//    Read all
    public List<Staff> getStaffList() {
        String sql = "select * from staff order by staff_id ";
        staffList = new ArrayList<>();
        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);
                ResultSet result = psmt.executeQuery();) {
            while (result.next()) {
                staffList.add(mapStaff(result));
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        return staffList;
    }

    //getting one staff
    public void getStaff(int staffId) {
        System.out.println("Staff ID: " + staffId);
        String sql = "SELECT * FROM staff WHERE staff_id = " + staffId;
        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                setStaff(mapStaff(result));
//                staff = mapStaff(result);
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        //return getStaff();        
        System.out.println("From Bean Staff ID: " + staff.staff_id);
        //return staff;
    }

    private Staff mapStaff(ResultSet result) {
        Staff staff_object = new Staff();

        try {
            staff_object.setStaff_id(result.getInt("staff_id"));
            staff_object.setFirstName(result.getString("first_name"));
            staff_object.setLastName(result.getString("last_name"));

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return staff_object;
    }

    //delete
    public String deleteStaff(Staff staff) {
        if (staff.getStaff_id() != 0) {
            System.out.println("Staff already deleted, the staff id is not null.");
        }
        String sql = "DELETE FROM staff WHERE staff_id=?";
        Object[] values = {
            staff.getStaff_id()
        };

        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setLong(1, staff.getStaff_id());
            psmt.executeUpdate();
            System.out.println("Deleted successfully");

            result = psmt.executeUpdate();
            if (result == 0) {
                System.out.println("Deleting staff failed, no rows affected");
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
            return "timesheet.xhtml?faces-redirect=true";
        } else {
            return "staff.xhtml?faces-redirect=true";

        }
    }
    
//update
    public String updateStaff(Staff staff) {
        if (staff.getStaff_id() != 0) {
            System.out.println("Staff already updated, the staff id is not null.");
        }
        String sql = "UPDATE FROM staff WHERE staff_id=?";
        Object[] values = {
            staff.getStaff_id()
        };

        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setLong(1, staff.getStaff_id());
            psmt.executeUpdate();
            System.out.println("Updated successfully");

            result = psmt.executeUpdate();
            if (result == 0) {
                System.out.println("Updating staff failed, no rows affected");
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
            return "timesheet.xhtml?faces-redirect=true";
        } else {
            return "staff.xhtml?faces-redirect=true";

        }
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
