package org.example.if2210_tb2_fck.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakhlukHidup extends Kartu{
    private String type;
    private Map<String, Integer> item_aktif;

    //constructor
    public MakhlukHidup(String name, String type){
        super(name);
        this.type = type;
        Map<String, Integer> item_aktif = new HashMap<>();
    }

    public String getType(){
        return type;
    }

    public Map<String, Integer> getItem(){
        return item_aktif;
    }

    public void addItem(String item){
        if (item_aktif.containsKey(item)){
            item_aktif.put(item, item_aktif.get(item)+1);
        } else {
            item_aktif.put(item, 1);
        };
    }

//    public void removeItem(String item){
//        item_aktif.remove(item);
//    }

    public boolean isProtected() {
        return (item_aktif.containsKey("Protect"));
    }

}
