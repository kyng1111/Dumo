package com.example.myapplication;


public class PersonalData {
    private String member_id;
    private String member_name;
    private String member_lat;
    private String member_lon;
    private String member_update;
    private String member_isok;
    private String member_num;


    public String getMember_id() { return member_id; }
    public String getMember_name() { return member_name; }
    public String getMember_lat() { return member_lat; }
    public String getMember_lon() { return member_lon; }
    public String getMember_update() { return member_update; }
    public String getMember_isok() { return member_isok; }
    public String getMember_num() { return member_num; }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }
    public void setMember_lat(String member_lat) { this.member_lat = member_lat; }
    public void setMember_lon(String member_lon) { this.member_lon = member_lon; }
    public void setMember_update(String member_update) { this.member_update = member_update; }
    public void setMember_isok(String member_isok) { this.member_isok = member_isok; }
    public void setMember_num(String member_num) { this.member_num = member_num; }

}