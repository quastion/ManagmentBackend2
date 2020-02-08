package com.opencode.managment.dto;

public class FinishStepIntentionDTO {
    private String userName;
    private  boolean finish;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
