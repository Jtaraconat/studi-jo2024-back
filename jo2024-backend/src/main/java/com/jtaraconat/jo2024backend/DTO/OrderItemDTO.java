package com.jtaraconat.jo2024backend.DTO;

public class OrderItemDTO {

    private int orderItemId;
    private int orderId;
    private int ticketId;
    private int price;
    private int quantity;

    public OrderItemDTO(int orderItemId, int orderId, int ticketId, int price, int quantity) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.ticketId = ticketId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
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
