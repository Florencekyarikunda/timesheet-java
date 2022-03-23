/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author HP
 */
@ManagedBean
@SessionScoped
public class Mode implements Serializable {
  
//implements Serializable
     private static final long serialVersionUID = 1L;
     public int mode_id;
     public String mode_name;

    public int getMode_id() {
        return mode_id;
    }

    public void setMode_id(int mode_id) {
        this.mode_id = mode_id;
    }

    public String getMode_name() {
        return mode_name;
    }

    public void setMode_name(String mode_name) {
        this.mode_name = mode_name;
    }    
    
}
