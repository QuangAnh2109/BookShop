/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author anh21
 */
public class Order {
    private int orderID, memberID;
    private String Status;
    private Date time;
    private ArrayList<ItemOrder> order;

    public Order() {
    }

    public Order(int orderID,int memberID, String Status, Date time, ArrayList<ItemOrder> order) {
        this.memberID = memberID;
        this.orderID = orderID;
        this.Status = Status;
        this.time = time;
        this.order = order;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }
    
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ArrayList<ItemOrder> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<ItemOrder> order) {
        this.order = order;
    }
    
    
}
