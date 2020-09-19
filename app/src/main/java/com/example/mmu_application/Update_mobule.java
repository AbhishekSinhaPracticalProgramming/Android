package com.example.mmu_application;

public class Update_mobule {

    String name;
    String phone;
    String session;
    String DOB;
    String gender;




    public Update_mobule() {

    }

    public Update_mobule(String name, String phone, String session, String gender) {
        this.name = name;
        this.phone = phone;
        this.session = session;
        this.gender=gender;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }




}
