package org.example.if2210_tb2_fck.model;

import org.example.if2210_tb2_fck.*;

public class DeckAktif extends GridMatrix<Kartu> {
    public DeckAktif(){
        super(1,6);
    }
    
    public void simpanAuto(Kartu kartu){
        for (int i = 0; i < getCol(); i++){
            if (retrieveKartu(0, i) == null){
                placeKartu(0, i, kartu);
                return;
            }
        }
    }

    public boolean isFull(){
        if(getNumOfElements() == 6){
            return true;
        }
        return false;
    }

}
