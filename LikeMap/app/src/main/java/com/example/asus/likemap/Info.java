package com.example.asus.likemap;

/**
 * Created by ruttapongpaleegui on 11/30/2016 AD.
 */

public class Info {
    private String ID;
    private String building;
    private String room;
    private String pic;
    private String text;

    public Info(){

    }
    public Info(String ID, String building, String room, String pic, String text){
        this.ID =ID;
        this.building = building;
        this.room = room;
        this.pic = pic;
        this.text = text;
    }
    public void setID(String id){
        ID = id;
    }
    public void setBuilding(String building){
        this.building = building;
    }
    public void setRoom(String room){
        this.room = room;
    }
    public void setPic(String pic){
        this.pic = pic;
    }
    public void  setText(String text){
        this.text = text;
    }
    public String getID(){
        return ID;
    }
    public String getBuilding(){
        return building;
    }
    public String getRoom(){
        return room;
    }
    public int getPic(){
        return Integer.valueOf(pic);
    }
    public String getText(){
        return text;
    }
}
