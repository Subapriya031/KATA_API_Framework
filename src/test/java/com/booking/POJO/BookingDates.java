package com.booking.POJO;

public class BookingDates {
    private String checkin;
    private String checkout;

    public BookingDates() {}

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // getters and setters
    public String getCheckin() {
        return checkin;
    }
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
