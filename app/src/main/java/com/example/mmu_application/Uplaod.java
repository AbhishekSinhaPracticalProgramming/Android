package com.example.mmu_application;

public class Uplaod {

    private String name, email, phone, session;
    private String imageUrl;

    public Uplaod() {

    }

    public Uplaod(String name, String email, String phone, String session, String imageUrl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.session = session;
        this.imageUrl = imageUrl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}