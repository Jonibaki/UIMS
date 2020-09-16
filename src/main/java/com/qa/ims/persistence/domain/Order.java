package com.qa.ims.persistence.domain;

public class Order {

    private Long orderId;
    private Long pId;
    private Long customerId;
    private double price;
    private String productName, customer;
    private Long quantity;
    private  double total;


    public Order(Long customerId) {
        this.setCustomerId(customerId);
    }
    public Order(Long orderId,Long customerId) {
        this.setId(orderId);
        this.setCustomerId(customerId);
    }
    public Order(Long orderId, Long pId, Long quantity) {
        this.setId(orderId);
        this.setProductId(pId);
        this.quantity = quantity;
    }

    public Order (Long orderId, Long pId, String customer, String productName, Long quantity, Double price, Double total){
        this.setId(orderId);
        this.setProductId(pId);
        this.customer = customer;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
    public Long getId() {
        return orderId;
    }

    public void setId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return pId;
    }

    public void setProductId(long pId) {
        this.pId = pId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Long getQuantity(){
        return quantity;
    }

    @Override
    public String toString() {
        return "\t oId\t\t: " + orderId + "\n\t pId\t\t: " + pId + "\n\t customer\t: "+customer + "\n\t pName \t\t: " + productName+ "\n\t Qty\t\t: "+
                quantity+"\n\t price\t\t: £"+price+ "\n\t total\t\t: £"+total+"\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pId == null) ? 0 : pId.hashCode());
        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (pId == null) {
            if (other.pId != null)
                return false;
        } else if (!pId.equals(other.getProductId()))
            return false;
        if (orderId == null) {
            if (other.orderId != null)
                return false;
        } else if (!orderId.equals(other.orderId))
            return false;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        return true;
    }

}
