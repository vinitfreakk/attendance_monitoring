package com.example.attendancemontering.Models;

public class RetriveAtt {
    String timeStampPunchIN, timeStampPunchOut, date,day,month;

    public RetriveAtt(String timeStampPunchIN, String timeStampPunchOut, String date, String day, String month) {
        this.timeStampPunchIN = timeStampPunchIN;
        this.timeStampPunchOut = timeStampPunchOut;
        this.date = date;
        this.day = day;
        this.month = month;
    }

    public RetriveAtt(Boolean punchIn) {
        this.punchIn = punchIn;
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

    public RetriveAtt(String timeStampPunchIN, String timeStampPunchOut, String day) {
        this.timeStampPunchIN = timeStampPunchIN;
        this.timeStampPunchOut = timeStampPunchOut;
        this.date = day;
    }


    Boolean punchIn,punchOut;

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

    public RetriveAtt(String timeStampPunchIN, String timeStampPunchOut, Boolean punchIn, Boolean punchOut) {
        this.timeStampPunchIN = timeStampPunchIN;
        this.timeStampPunchOut = timeStampPunchOut;
        this.punchIn = punchIn;
        this.punchOut = punchOut;
    }

    public RetriveAtt(String timeStampPunchIN, String timeStampPunchOut) {
        this.timeStampPunchIN = timeStampPunchIN;
        this.timeStampPunchOut = timeStampPunchOut;
    }

    public RetriveAtt() {
    }

    public String getTimeStampPunchIN() {
        return timeStampPunchIN;
    }

    public void setTimeStampPunchIN(String timeStampPunchIN) {
        this.timeStampPunchIN = timeStampPunchIN;
    }

    public String getTimeStampPunchOut() {
        return timeStampPunchOut;
    }

    public void setTimeStampPunchOut(String timeStampPunchOut) {
        this.timeStampPunchOut = timeStampPunchOut;
    }
}
