package com.example.profilecruddb.model;

import android.net.Uri;

import java.util.List;

public class User {

    private long id;
    private String name;
    private  String address;
    private String email;
    private String contactNumber;
    private String saying;
    private String workplace;
    private String birthdate;
    private String age;
    private List<SchoolAttended> schoolAttendedList;


    private String imageUri;

    public User(){

    }

    public User(long id, String name, String address, String email, String contactNumber, String saying, String workplace, String birthdate, String age, String imageUri) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contactNumber = contactNumber;
        this.saying = saying;
        this.workplace = workplace;
        this.birthdate = birthdate;
        this.age = age;
        this.imageUri = imageUri;
    }

    public List<SchoolAttended> getSchoolAttendedList() {
        return schoolAttendedList;
    }

    public void setSchoolAttendedList(List<SchoolAttended> schoolAttendedList) {
        this.schoolAttendedList = schoolAttendedList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String  imageUri) {
        this.imageUri = imageUri;
    }
}
