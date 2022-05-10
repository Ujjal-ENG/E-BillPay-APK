package com.example.e_billpay;

public class RegistrationData {
    private String users, name,c_no,me_no,mo_no,em_id,us_na,pass,c_pass;

    public RegistrationData(){

    }
    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getC_no() {
        return c_no;
    }

    public void setC_no(String c_no) {
        this.c_no = c_no;
    }

    public String getMe_no() {
        return me_no;
    }

    public void setMe_no(String me_no) {
        this.me_no = me_no;
    }

    public String getMo_no() {
        return mo_no;
    }

    public void setMo_no(String mo_no) {
        this.mo_no = mo_no;
    }

    public String getEm_id() {
        return em_id;
    }

    public void setEm_id(String em_id) {
        this.em_id = em_id;
    }

    public String getUs_na() {
        return us_na;
    }

    public void setUs_na(String us_na) {
        this.us_na = us_na;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getC_pass() {
        return c_pass;
    }

    public void setC_pass(String c_pass) {
        this.c_pass = c_pass;
    }

    public RegistrationData(String users, String name, String c_no, String me_no, String mo_no, String em_id, String us_na, String pass, String c_pass) {
        this.users = users;
        this.name = name;
        this.c_no = c_no;
        this.me_no = me_no;
        this.mo_no = mo_no;
        this.em_id = em_id;
        this.us_na = us_na;
        this.pass = pass;
        this.c_pass = c_pass;
    }
}
