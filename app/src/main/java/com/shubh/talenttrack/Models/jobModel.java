package com.shubh.talenttrack.Models;

public class jobModel {

    String des , expiresDate, gender, location, postedDate, recruiter, role, title, img, Key;
    int applicants, highAge, highPay, lowAge, lowPay;



    public jobModel(){}

    public jobModel(String des, String expiresDate, String gender, String  img, String location, String postedDate, String recruiter, String role, String title, int applicants, int highAge, int highPay, int lowAge, int lowPay) {
        this.des = des;
        this.expiresDate = expiresDate;
        this.gender = gender;
        this.location = location;
        this.postedDate = postedDate;
        this.recruiter = recruiter;
        this.role = role;
        this.title = title;
        this.applicants = applicants;
        this.highAge = highAge;
        this.highPay = highPay;
        this.lowAge = lowAge;
        this.lowPay = lowPay;
        this.img = img;
    }

    public jobModel(String key){
        Key = key;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(String expiresDate) {
        this.expiresDate = expiresDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter = recruiter;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getApplicants() {
        return applicants;
    }

    public void setApplicants(int applicants) {
        this.applicants = applicants;
    }

    public int getHighAge() {
        return highAge;
    }

    public void setHighAge(int highAge) {
        this.highAge = highAge;
    }

    public int getHighPay() {
        return highPay;
    }

    public void setHighPay(int highPay) {
        this.highPay = highPay;
    }

    public int getLowAge() {
        return lowAge;
    }

    public void setLowAge(int lowAge) {
        this.lowAge = lowAge;
    }

    public int getLowPay() {
        return lowPay;
    }

    public void setLowPay(int lowPay) {
        this.lowPay = lowPay;
    }
}
