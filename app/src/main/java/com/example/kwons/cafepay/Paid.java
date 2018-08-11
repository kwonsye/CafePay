package com.example.kwons.cafepay;
//paid 리스트뷰를 위한 클래스
public class Paid {


    String paidLocation;
    String paidCafeName;
    String paidPrice;
    String paidMenu;


    public Paid(String paidLocation, String paidCafeName, String paidPrice, String paidMenu) {
        this.paidLocation = paidLocation;
        this.paidCafeName = paidCafeName;
        this.paidPrice = paidPrice;
        this.paidMenu = paidMenu;
    }

    public String getPaidLocation() {
        return paidLocation;
    }

    public void setPaidLocation(String paidLocation) {
        this.paidLocation = paidLocation;
    }

    public String getPaidCafeName() {
        return paidCafeName;
    }

    public void setPaidCafeName(String paidCafeName) {
        this.paidCafeName = paidCafeName;
    }

    public String getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(String paidPrice) {
        this.paidPrice = paidPrice;
    }


    public String getPaidMenu() {
        return paidMenu;
    }

    public void setPaidMenu(String paidMenu) {
        this.paidMenu = paidMenu;
    }

}
