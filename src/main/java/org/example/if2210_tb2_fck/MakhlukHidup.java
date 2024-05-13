package org.example.if2210_tb2_fck;

import java.util.ArrayList;
import java.util.List;

public class MakhlukHidup extends Kartu{
    private String type;
    private List<String> item_aktif;

    //constructor
    public MakhlukHidup(String name, String type){
        super(name);
        this.type = type;
        item_aktif = new ArrayList<String>();
    }

    public String getType(){
        return type;
    }

    public List<String> getItem(){
        return item_aktif;
    }

    public void addItem(String item){
        item_aktif.add(item);
    }

    public void removeItem(String item){
        item_aktif.remove(item);
    }


}
