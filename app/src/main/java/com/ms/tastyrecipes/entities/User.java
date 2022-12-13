package com.ms.tastyrecipes.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public Long userID;
    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "user_password")
    public String userPassword;
    @ColumnInfo(name = "user_natural_name")
    public String userNaturalName;
    @ColumnInfo(name = "user_mail")
    public String userMail;

    public User(Long userID, String userName, String userPassword, String userNaturalName, String userMail) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userNaturalName = userNaturalName;
        this.userMail = userMail;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNaturalName() {
        return userNaturalName;
    }

    public void setUserNaturalName(String userNaturalName) {
        this.userNaturalName = userNaturalName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userNaturalName='" + userNaturalName + '\'' +
                ", userMail='" + userMail + '\'' +
                '}';
    }
}
