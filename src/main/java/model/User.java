package model;

import java.sql.Date;
import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String role;
    private LocalDate dateOfBirth;
    private String address;
    private String desc;
    private String imgLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String username, String password, String fullName, String role, LocalDate dateOfBirth, String address, String desc, String imgLink) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.desc = desc;
        this.imgLink = imgLink;
    }

    public User(int id, String username, String password, String fullName, String role, LocalDate dateOfBirth, String address, String desc, String imgLink) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.desc = desc;
        this.imgLink = imgLink;
    }

    public User() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
