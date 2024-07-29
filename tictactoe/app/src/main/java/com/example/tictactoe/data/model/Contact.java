package com.example.tictactoe.data.model;

public class Contact {
    private int id;
    private String name;
    private String phoneno;
    public Contact(String name,String phoneno){
        this.name=name;
        this.phoneno=phoneno;
    }
    public Contact(int id,String name,String phoneno){
        this.name=name;
        this.phoneno=phoneno;
        this.id=id;
    }
    public Contact(){
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
