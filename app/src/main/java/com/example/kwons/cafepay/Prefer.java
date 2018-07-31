package com.example.kwons.cafepay;

public class Prefer {

    int preferImg;
    String preferCafeName;
    String preferMenu;

    public Prefer(int preferImg, String preferCafeName, String preferMenu) {
        this.preferImg = preferImg;
        this.preferCafeName = preferCafeName;
        this.preferMenu = preferMenu;
    }

    public int getPreferImg() {
        return preferImg;
    }

    public void setPreferImg(int preferImg) {
        this.preferImg = preferImg;
    }

    public String getPreferCafeName() {
        return preferCafeName;
    }

    public void setPreferCafeName(String preferCafeName) {
        this.preferCafeName = preferCafeName;
    }

    public String getPreferMenu() {
        return preferMenu;
    }

    public void setPreferMenu(String preferMenu) {
        this.preferMenu = preferMenu;
    }
}


