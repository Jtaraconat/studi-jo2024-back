package com.jtaraconat.jo2024backend.DTO;

public class CartItemDTO{
    private int cartItemID;
    private int ticketID;
    private String eventName;
    private int price;
    private int quantity;

    public CartItemDTO(int cartItemID, int ticketID, String eventName, int price, int quantity) {
        this.cartItemID = cartItemID;
        this.ticketID = ticketID;
        this.eventName = eventName;
        this.price = price;
        this.quantity = quantity;
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
}
