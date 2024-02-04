package com.papasmurfie.rent_a_car_oop2.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "role_id")
    private Integer roleId;

    public Users(String username, String password, Integer roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public Users() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userId == users.userId && Objects.equals(username, users.username) && Objects.equals(password, users.password) && Objects.equals(roleId, users.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, roleId);
    }
}
