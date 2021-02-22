package com.example.pixaflip;

public class MyState {

    // variables for our course
    // name and description.
    String loc;
    String confirmedCasesIndian;
    String confirmedCasesForeign;
    String discharged;
    String deaths;
    String totalConfirmed;

    public MyState(String loc, String conInd, String conFor, String discharge, String death, String totalCon) {
        this.loc = loc;
        this.confirmedCasesIndian = conInd;
        this.confirmedCasesForeign = conFor;
        this.discharged = discharge;
        this.deaths = death;
        this.totalConfirmed = totalCon;
    }



    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getConfirmedCasesIndian() {
        return confirmedCasesIndian;
    }

    public void setConfirmedCasesIndian(String confirmedCasesIndian) {
        this.confirmedCasesIndian = confirmedCasesIndian;
    }

    public String getConfirmedCasesForeign() {
        return confirmedCasesForeign;
    }

    public void setConfirmedCasesForeign(String confirmedCasesForeign) {
        this.confirmedCasesForeign = confirmedCasesForeign;
    }

    public String getDischarged() {
        return discharged;
    }

    public void setDischarged(String discharged) {
        this.discharged = discharged;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }
}