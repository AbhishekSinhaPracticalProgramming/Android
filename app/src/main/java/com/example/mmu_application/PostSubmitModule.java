package com.example.mmu_application;

public class PostSubmitModule {

    private String emailM;
    private String postM;

    public PostSubmitModule() {

    }

    public PostSubmitModule(String emailM, String postM) {
        this.emailM = emailM;
        this.postM = postM;
    }

    public String getEmailM() {
        return emailM;
    }

    public void setEmailM(String emailM) {
        this.emailM = emailM;
    }

    public String getPostM() {
        return postM;
    }

    public void setPostM(String postM) {
        this.postM = postM;
    }
}