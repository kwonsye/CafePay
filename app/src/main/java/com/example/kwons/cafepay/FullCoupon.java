package com.example.kwons.cafepay;

public class FullCoupon {

    //나의 쿠폰 보관함에 들어있는 리스트뷰의 하나의 행

    String cafeNameOfCoupon;//쿠폰의 카페이름

    public FullCoupon(String cafeNameOfCoupon) {

        this.cafeNameOfCoupon = cafeNameOfCoupon;
    }

    public String getCafeNameOfCoupon() {
        return cafeNameOfCoupon;
    }

    public void setCafeNameOfCoupon(String cafeNameOfCoupon) {
        this.cafeNameOfCoupon = cafeNameOfCoupon;
    }
}
