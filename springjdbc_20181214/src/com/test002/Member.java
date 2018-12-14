package com.test002;
 
public class Member {
    
    
    private String mid_, name_, phone;
 
    public Member() {
 
    }
 
    public Member(String mid_, String name_, String phone) {
        this.mid_ = mid_;
        this.name_ = name_;
        this.phone = phone;
    }
 
    public String getmid_() {
        return mid_;
    }
 
    public void setmid_(String mid_) {
        this.mid_ = mid_;
    }
 
    public String getName_() {
        return name_;
    }
 
    public void setName_(String name_) {
        this.name_ = name_;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }
 
    @Override
    public String toString() {
        return String.format("%s / %s / %s", this.getmid_(), this.getName_(), this.getPhone());
    }
    
    
    
 
}