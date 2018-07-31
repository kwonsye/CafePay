package com.example.kwons.cafepay;
//paid 리스트뷰를 위한 클래스
public class Paid {


    String paidDate;
    String paidCafeName;
    String paidPrice;

    public Paid(String paidDate, String paidCafeName, String paidPrice) {
        this.paidDate = paidDate;
        this.paidCafeName = paidCafeName;
        this.paidPrice = paidPrice;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
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
}
