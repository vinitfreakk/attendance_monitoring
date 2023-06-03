package com.example.attendancemontering.Models;

public class UserAttendance {
    String TimeStampPunchIN, TimeStampPunchOut, date,day,month;

    public UserAttendance(String timeStampPunchIN, String timeStampPunchOut, String date, String day, String month, Boolean punchIn, Boolean punchOut) {
        TimeStampPunchIN = timeStampPunchIN;
        TimeStampPunchOut = timeStampPunchOut;
        this.date = date;
        this.day = day;
        this.month = month;
        this.punchIn = punchIn;
        this.punchOut = punchOut;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    Boolean punchIn,punchOut;

    public UserAttendance(String timeStampPunchIN, String timeStampPunchOut, String day, Boolean punchIn, Boolean punchOut) {
        TimeStampPunchIN = timeStampPunchIN;
        TimeStampPunchOut = timeStampPunchOut;
        this.date = day;
        this.punchIn = punchIn;
        this.punchOut = punchOut;
    }
    public UserAttendance(Boolean punchIn, Boolean punchOut, String timestampin, String timestampout) {
        this.punchIn = punchIn;
        this.punchOut = punchOut;
        this.TimeStampPunchIN = timestampin;
        TimeStampPunchOut = timestampout;
    }

    public UserAttendance(String timeStampPunchOut, Boolean punchIn, Boolean punchOut) {
        TimeStampPunchOut = timeStampPunchOut;
        this.punchIn = punchIn;
        this.punchOut = punchOut;
    }

    public UserAttendance(String timeStampPunchIN, String timeStampPunchOut) {
        TimeStampPunchIN = timeStampPunchIN;
        TimeStampPunchOut = timeStampPunchOut;
    }
    /*public UserAttendance(String timeStampPunchIN, Boolean punchIn) {
        TimeStampPunchIN = timeStampPunchIN;
        this.punchIn = punchIn;
    }
    public UserAttendance(String timeStampPunchOut, Boolean punchOut) {
        TimeStampPunchOut = timeStampPunchOut;
        this.punchOut = punchOut;
    }*/

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

    public String getTimeStampPunchIN() {
        return TimeStampPunchIN;
    }

    public void setTimeStampPunchIN(String timeStampPunchIN) {
        this.TimeStampPunchIN = timeStampPunchIN;
    }

    public String getTimeStampPunchOut() {
        return TimeStampPunchOut;
    }

    public void setTimeStampPunchOut(String timeStampPunchOut) {
        TimeStampPunchOut = timeStampPunchOut;
    }
}
