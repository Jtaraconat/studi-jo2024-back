package com.jtaraconat.jo2024backend.DTO;

public class QRCodeOrderItemDTO {

    private String securityKey;



    public QRCodeOrderItemDTO(String securityKey) {
        this.securityKey = securityKey;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }
}
