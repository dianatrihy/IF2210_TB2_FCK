package org.example.if2210_tb2_fck;

public class Kartu {
    private String name;
    private Integer location;

    //constructor
    public Kartu(String name) {
        this.name = name;
        this.location = null;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location){
        this.location = location;
    }
}
