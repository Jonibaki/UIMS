package com.qa.ims.persistence.domain;

public class Product {

    private Long pId;
    private String product_name;
    private String category;
    private double price;

    public Product(String product_name, String category) {
        this.SetProduct_name(product_name);
        this.setCategory(category);
    }

    public Product(Long pId, String product_name, String category) {
        this.setId(pId);
        this.SetProduct_name(product_name);
        this.setCategory(category);
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

    @Override
    public String toString() {
        return "id:" + pId + " Product name:" + product_name + " category:" + category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
        result = prime * result + ((pId == null) ? 0 : pId.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
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
        Product other = (Product) obj;
        if (getProduct_name() == null) {
            if (other.getProduct_name() != null)
                return false;
        } else if (!getProduct_name().equals(other.getProduct_name()))
            return false;
        if (pId == null) {
            if (other.pId != null)
                return false;
        } else if (!pId.equals(other.pId))
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        return true;
    }

}
