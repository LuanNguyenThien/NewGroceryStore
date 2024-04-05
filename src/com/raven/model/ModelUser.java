package com.raven.model;

import javax.swing.Icon;

public class ModelUser {

    public String getquyen() {
        return quyen;
    }

    public void setquyen(String quyen) {
        this.quyen = quyen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }

    public ModelUser(String quyen, String userName, byte[] profile) {
        this.quyen = quyen;
        this.userName = userName;
        this.profile = profile;
    }

    public ModelUser() {
    }

    private String quyen;
    private String userName;
    private byte[] profile;
}
