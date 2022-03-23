/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import connection.DBConnection;
import static dao.DAOUtil.prepareStatement;
import entity.ActivityCategory;
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
public class ActivityCategoryBean implements Serializable {

    private static final long serialVersionUID = 1L;
    static Logger LOGGER = Logger.getLogger(ActivityCategoryBean.class.getName());
    private List<ActivityCategory> activityCategoryList;
    private ActivityCategory activityCategory;
    

    //    create 
    public int createActivityCategory(ActivityCategory activityCategory) {
        int category = 0;
        if (activityCategory.getCategory_activity_id() != 0) {
            //System.out.println("User already created, the mode id is not null.");
            //think through what you can display to the user
        }
        String sql = "INSERT INTO category_activity(category_name)VALUES(?)";
        Object[] values = {
            activityCategory.getCategory_name(),};
        //ActivityCategory activityCategory = new ActivityCategory();

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
            //return "category.xhtml?faces-redirect=true";
            category = 1;
        } else {
            //return "category.xhtml?faces-redirect=true";
            category = 0;

        }
        return category;
    }

    //    Read all
    public List<ActivityCategory> getActivityCategoryList() {
        String sql = "SELECT * FROM category_activity ORDER BY category_activity_id";
        activityCategoryList = new ArrayList<>();
        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);
                ResultSet result = psmt.executeQuery();) {
            while (result.next()) {
                activityCategoryList.add(mapActivityCategory(result));
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        return activityCategoryList;
    }

    private ActivityCategory mapActivityCategory(ResultSet result) {
        ActivityCategory actCategory = new ActivityCategory();

        try {
            actCategory.setCategory_activity_id(result.getInt("category_activity_id"));
            actCategory.setCategory_name(result.getString("category_name"));

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return actCategory;
    }

    //delete
    public String deleteCategory_activity(ActivityCategory activityCategory) {
        if (activityCategory.getCategory_activity_id() != 0) {
            System.out.println("category_activity already deleted, the category_activity_id is not null.");
        }
        String sql = "DELETE FROM category_activity WHERE category_activity_id=?";
        Object[] values = {
            activityCategory.getCategory_activity_id()
        };

        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setLong(1, activityCategory.getCategory_activity_id());
            psmt.executeUpdate();
            System.out.println("Deleted successfully");

            result = psmt.executeUpdate();
            if (result == 0) {
                System.out.println("Deleting category_activity failed, no rows affected");
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
            return "timesheet.xhtml?faces-redirect=true";
        } else {
            return "category.xhtml?faces-redirect=true";

        }
    }

    //update
    public String updateCategory_activity(ActivityCategory activityCategory) {
        if (activityCategory.getCategory_activity_id() != 0) {
            System.out.println("category_activity already updated, the category_activity_id is not null.");
        }
        String sql = "UPDATE FROM category_activity WHERE category_activity_id=?";
        Object[] values = {
            //            activityCategory.getCategory_activity_id()
            activityCategory.getCategory_activity_id()
        };

        int result = 0;

        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setLong(1, activityCategory.getCategory_activity_id());
            psmt.executeUpdate();
            System.out.println("Updated successfully");

            result = psmt.executeUpdate();
            if (result == 0) {
                System.out.println("Updating activityCategory failed, no rows affected");
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        if (result > 0) {
            return "timesheet.xhtml?faces-redirect=true";
        } else {
            return "category.xhtml?faces-redirect=true";

        }

    }

    //get one ActivityCategory to be edited and deleted
    public void getCategory_activity(int activityCategoryId) {
        System.out.println("ActivityCategory ID: " + activityCategoryId);
        String sql = "SELECT * FROM category_activity WHERE category_activity_id = " + activityCategoryId;
        try (
                Connection con = DBConnection.getMySQLConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            ResultSet result = psmt.executeQuery();
            if (result.next()) {
                setActivityCategory(mapActivityCategory(result));
//                activityCategory = mapActivityCategory(result);
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        //return getActivityCategory();        
        System.out.println("From Bean ActivityCategory ID: " + activityCategory.category_activity_id);
        //return activityCategory;
    }

    

    public ActivityCategory getActivityCategory() {
        return activityCategory;
    }

    public void setActivityCategory(ActivityCategory activityCategory) {
        this.activityCategory = activityCategory;
    }

}
