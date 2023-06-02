package com.example.attendancemontering.Models;

public class UserAttendance {
    String name,Timestamp;
    Boolean punchIn,punchOut;

    public UserAttendance(Boolean punchIn, Boolean punchOut, String name, String timestamp) {
        this.punchIn = punchIn;
        this.punchOut = punchOut;
        this.name = name;
        Timestamp = timestamp;
    }

    public UserAttendance() {

    }


    /*public UserAttendance(String timestamp) {
        Timestamp = timestamp;
    }*/

    public UserAttendance(Boolean punchIn) {
        this.punchIn = punchIn;
    }


    public Boolean getPunchIn() {
        return punchIn;
    }

    public void setPunchIn(Boolean punchIn) {
        this.punchIn = punchIn;
    }

    public Boolean getPunchOut() {
        return punchOut;
    }

    public void setPunchOut(Boolean punchOut) {
        this.punchOut = punchOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }
}
