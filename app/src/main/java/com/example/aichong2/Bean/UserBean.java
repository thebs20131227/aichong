package com.example.aichong2.Bean;

import java.sql.Date;
public class UserBean {
    private String U_id;
    private String U_password;
    private String U_name;
    private String U_sex;
    private Date U_birthday;
    private String U_address;
    private String U_phone;
    private String role;
    private int U_fame;

    public String getU_id() {
        return U_id;
    }

    public void setU_id(String u_id) {
        U_id = u_id;
    }

    public String getU_password() {
        return U_password;
    }

    public void setU_password(String u_password) {
        U_password = u_password;
    }

    public String getU_name() {
        return U_name;
    }

    public void setU_name(String u_name) {
        U_name = u_name;
    }

    public String getU_sex() {
        return U_sex;
    }

    public void setU_sex(String u_sex) {
        U_sex = u_sex;
    }

    public Date getU_birthday() {
        return U_birthday;
    }

    public void setU_birthday(Date u_birthday) {
        U_birthday = u_birthday;
    }

    public String getU_address() {
        return U_address;
    }

    public void setU_address(String u_address) {
        U_address = u_address;
    }

    public String getU_phone() {
        return U_phone;
    }

    public void setU_phone(String u_phone) {
        U_phone = u_phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getU_fame() {
        return U_fame;
    }

    public void setU_fame(int u_fame) {
        U_fame = u_fame;
    }
}
