package com.example.profilecruddb.model;

public class SchoolAttended {

    private String schoolName;

    public SchoolAttended(String schoolName) {
        this.schoolName = schoolName;
    }

    public SchoolAttended(){

    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
