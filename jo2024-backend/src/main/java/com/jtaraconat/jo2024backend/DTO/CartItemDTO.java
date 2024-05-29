package com.jtaraconat.jo2024backend.DTO;

public class CartItemDTO{
    private int cartItemID;
    private int ticketID;
    private String eventName;
    private String eventLocation;
    private String time;
    private String day;
    private String sport;
    private String city;
    private String ticketType;
    private int price;
    private int quantity;
    private String imageURL;


    public CartItemDTO(int cartItemID, int ticketID, String eventName, String eventLocation, String time, String day, String sport, String city, String ticketType, int price, int quantity, String imageURL) {
        this.cartItemID = cartItemID;
        this.ticketID = ticketID;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.time = time;
        this.day = day;
        this.sport = sport;
        this.city = city;
        this.ticketType = ticketType;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
    }

    public int getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(int cartItemID) {
        this.cartItemID = cartItemID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
