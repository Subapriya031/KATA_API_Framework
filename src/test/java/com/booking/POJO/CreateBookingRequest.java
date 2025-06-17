package com.booking.POJO;

public class CreateBookingRequest {
    private int roomid;
    private boolean depositpaid;
    private String firstname, lastname, email, phone;
    private BookingDates bookingdates;
    public CreateBookingRequest() {}

    public CreateBookingRequest(String firstname, String lastname, boolean depositpaid,
                                BookingDates bookingdates,String email,String phone,int roomid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.roomid = roomid;

    }


    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }



    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public void setRoomId(int randomId) {
        this.roomid = randomId;
    }
}
