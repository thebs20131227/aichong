package com.example.aichong2.Util;

import com.example.aichong2.Bean.UserBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    public static final String URL = "jdbc:mysql://192.168.1.122/aichong";
    public static final String USER = "thebs";
    public static final String PASSWORD = "20131227";
    private static Connection conn = null;




    public static UserBean login(String sql) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = (Statement) conn.createStatement();
            ResultSet res = st.executeQuery(sql);
            UserBean user = null;
            if (res == null) {

                return null;
            } else {
                if (res.next()) {
                    user = new UserBean();
                    user.setRole(res.getString("U_role"));
                    user.setU_address(res.getString("U_address"));
                    user.setU_birthday(res.getDate("U_birthday"));
                    user.setU_fame(res.getInt("U_fame"));
                    user.setU_password(res.getString("U_password"));
                    user.setU_sex(res.getString("U_sex"));
                    user.setU_id(res.getString("U_id"));
                    user.setU_phone(res.getString("U_phone"));
                    user.setU_name(res.getString("U_name"));
                }
                conn.close();
                st.close();
                res.close();
//                System.out.println(user.getU_name());
//                System.out.println("连接数据库成功666666");
                return user;

            }

        } catch (ClassNotFoundException e) {
            System.out.println("连接数据库失败");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("连接数据库失败");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("连接数据库失败");
            e.printStackTrace();
            return null;
        }

    }


    public static String register(String sql1,String sql2,String name) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = (Statement) conn.createStatement();
            ResultSet res1 = st.executeQuery(sql1);
            boolean res2 = Boolean.parseBoolean(null);
//            ResultSet res2 = st.executeQuery(sql2);
            String res=null;
            if (res1 == null) {
                conn.close();
                st.close();
                res1.close();
                System.out.println("连接数据库成功，没有重复的用户名");
                return "failed1";
            } else {
                if(res1.next()){
                    if(res1.getString("U_name").equals(name)) {
                        System.out.println("连接数据库成功，但是数据库中已经有了该用户");
                        System.out.println(res1.next());
                        res = "failed2";
                    }
                }else {
                    res2 = st.execute(sql2);
                    res="success";
                }
//                System.out.println(res+"666666666666");
                conn.close();
                st.close();
                res1.close();
                return res;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("连接数据库失败1");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("连接数据库失败2");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("连接数据库失败3");
            e.printStackTrace();
            return null;
        }

    }



}
