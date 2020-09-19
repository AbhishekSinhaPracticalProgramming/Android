package com.example.mmu_application;

public class Add_Alumni_module {

    String fullname;
    String email;
    String linkedin;
    String phone;
    String session;
    String company;
    String designation;
    String berif;


    public Add_Alumni_module() {

    }


    public Add_Alumni_module(String fullname, String email, String linkedin, String phone, String session, String company, String designation, String berif) {
        this.fullname = fullname;
        this.email = email;
        this.linkedin = linkedin;
        this.phone = phone;
        this.session = session;
        this.company = company;
        this.designation = designation;
        this.berif = berif;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBerif() {
        return berif;
    }

    public void setBerif(String berif) {
        this.berif = berif;
    }
}
