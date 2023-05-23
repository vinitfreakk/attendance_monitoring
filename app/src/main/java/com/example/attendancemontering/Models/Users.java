package com.example.attendancemontering.Models;

public class Users {
    String ProfilePic,mail,password,name;

    public Users(String profilePic, String mail, String password, String name) {
        ProfilePic = profilePic;
        this.mail = mail;
        this.password = password;
        this.name = name;
    }

    //registration constructor
    public Users(String mail, String password, String name) {
        this.mail = mail;
        this.password = password;
        this.name = name;
    }

    public Users() {
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
