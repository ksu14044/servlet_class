package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuthDao {
    private static AuthDao authDao;
    private DBConnectionMgr mgr;

    private AuthDao() {
         mgr = DBConnectionMgr.getInstance();
    }
    public static AuthDao getInstance() {
        if (authDao == null) {
            authDao = new AuthDao();
        }
        return authDao;
    }

    public  User signup(User user) {
        User insertUser = null;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = mgr.getConnection();
            String sql = """
                insert into user_tb
                values(default,?,?,?,?)
            """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertUser = User.builder()
                        .user_id(rs.getInt(1))
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build();
            }
            System.out.println("insert user into database");
        } catch (Exception e) {
            System.out.println("insert user into database failed");
            e.printStackTrace();
        } finally {
            mgr.freeConnection(con, ps);
        }

        return insertUser;
    }
}
