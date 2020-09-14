package com.qa.ims.persistence.domain;

public class Order {

    private Long orderId;
    private Long pId;
    private Long customerId;

    public Order(Long pId, Long customerId) {
        this.setProductId(pId);
        this.setCustomerId(customerId);
    }

    public Order(Long orderId, Long pId, Long customerId) {
        this.setId(orderId);
        this.setProductId(pId);
        this.setCustomerId(customerId);
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

    @Override
    public String toString() {
        return "id:" + orderId + " product Id:" + pId + " customer Id:" + customerId;
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
