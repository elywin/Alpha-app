package com.example.alpha;

public class StudentModel {
    String id="";
    String fname="";
    String lname="";
    String residence="";
    String yearofstudy="";
    String programofstudy="";
    String courseunit="";
    String created_at="";

    public StudentModel(String id, String fname, String lname, String programofstudy, String yearofstudy, String courseunit,String residence, String created_at) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.residence = residence;
        this.programofstudy = programofstudy;
        this.yearofstudy = yearofstudy;
        this.courseunit=courseunit;
        this.created_at = created_at;
    }

    public StudentModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fname;
    }

    public void setfName(String fname) {
        this.fname = fname;
    }

    public String getlName() {
        return lname;
    }

    public void setlName(String lname) {
        this.lname = lname;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getProgramofstudy() {
        return programofstudy;
    }

    public void setProgramofstudy(String programofstudy) {
        this.programofstudy = programofstudy;
    }

    public String getCourseunit() {
        return courseunit;
    }

    public void setCourseunit(String courseunit) {
        this.courseunit = courseunit;
    }

    public String getYearofstudy() {
        return yearofstudy;
    }

    public void setYearofstudy(String yearofstudy) {
        this.yearofstudy = yearofstudy;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
