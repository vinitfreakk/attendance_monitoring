package com.example.attendancemontering.Models;

public class RetriveAtt {
    String timeStampPunchIN, timeStampPunchOut;
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
