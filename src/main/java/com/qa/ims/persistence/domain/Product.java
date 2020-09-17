package com.qa.ims.persistence.domain;

public class Product {

    private Long pId;
    private String product_name;
    private String category;
    private double price;

    public Product(String product_name, String category, double price) {
        this.SetProduct_name(product_name);
        this.setCategory(category);
        this.setPrice(price);
    }

    public Product(Long pId, String product_name, String category, double price) {
        this.setId(pId);
        this.SetProduct_name(product_name);
        this.setCategory(category);
        this.setPrice(price);
    }

    public Long getId() {
        return pId;
    }

    public void setId(Long pId) {
        this.pId = pId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void SetProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice (){ return price;}
    public void setPrice(double price){this.price =price;}

    @Override
    public String toString() {
        return "\t pId : " + pId + "\t product : " + product_name + "\t category : " + category + "\t price : £"+ price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
        result = prime * result + ((pId == null) ? 0 : pId.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + Double.hashCode(price);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Double value = price;
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (getProduct_name() == null) {
            if (other.getProduct_name() != null)
                return false;
        } else if (!getProduct_name().equals(other.getProduct_name()))
            return false;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (getCategory() == null) {
            if (other.getCategory() != null)
                return false;
        } else if (!getCategory().equals(other.getCategory()))
            return false;
        if (value== null) {
            Double otherDouble = other.getPrice();
            if (otherDouble!= null)
                return false;
        } else if (!value.equals(other.getPrice()))
            return false;
        return true;
    }

}
