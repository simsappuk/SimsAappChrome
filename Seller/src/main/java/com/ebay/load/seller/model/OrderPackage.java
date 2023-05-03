package com.ebay.load.seller.model;

import java.util.ArrayList;
import java.util.List;

public class OrderPackage {
    private List<Orders> totalOrders;
    private Boolean hasMoreOrders;
    private int currentPage;

    public List<Orders> getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(List<Orders> totalOrders) {
        this.totalOrders = totalOrders;
    }

    public void appendOrders(List<Orders> totalOrders) {
        if(this.totalOrders==null)  this.totalOrders = new ArrayList<Orders>();
        this.totalOrders.addAll(totalOrders);
    }

    public Boolean getHasMoreOrders() {
        if(this.hasMoreOrders==null) this.hasMoreOrders=false;
        return hasMoreOrders;
    }

    public void setHasMoreOrders(Boolean hasMoreOrders) {
        this.hasMoreOrders = hasMoreOrders;
        if(this.hasMoreOrders) currentPage++;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
