package org.example.if2210_tb2_fck.model;

import java.util.ArrayList;
import java.util.List;

public class GridMatrix<T> {
    private int row;            //ukuran baris
    private int col;            //ukuran kolom
    private int numofelements;  //jumlah elemen di dalam grid
    private ArrayList<ArrayList<T>> matrix;

    // inisialisasi template grid sebesar row x col yang kosong
    public GridMatrix(int row, int col){
        this.row = row;
        this.col = col;
        this.numofelements = 0;
        matrix = new ArrayList<ArrayList<T>>();
        for (int i = 0; i < row; i++) {
            matrix.add(new ArrayList<>(col)); // Initialize each row
            for (int j = 0; j < col; j++) {
                matrix.get(i).add(null); // Initialize each element to null
            }
        }
    }

    public int getNumOfElements(){
        return numofelements;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }
    //remove saja dan tidak return apapun
    public void removeKartu(int idrow, int idcol){
        matrix.get(idrow).set(idcol,null);
        numofelements--;
    }

    //ambil kartu di spot idrow, idcol
    public T retrieveKartu(int idrow, int idcol){
        return matrix.get(idrow).get(idcol);
    }

    //taruh kartu di spot idrow, idcol
    public void placeKartu(int idrow, int idcol, T kartu){
        matrix.get(idrow).set(idcol, kartu);
        numofelements++;
    }

    // cek apakah ada kartu di idrow dan idcol
    public boolean isPresent(int idrow, int idcol){
        return retrieveKartu(idrow, idcol)==null;
    }

    public void printGrid() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                T element = matrix.get(i).get(j);
                if (element != null) {
                    System.out.print(element + "\t");
                } else {
                    System.out.print("null\t");
                }
            }
            System.out.println();
        }
    }

    // return list semua kartu
    public ArrayList<T> getAllCards(){
        ArrayList<T> allKartu = new ArrayList<T>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                T element = matrix.get(i).get(j);
                if (element != null) {
                    allKartu.add(element);
                }
            }
        }
        return allKartu;
    }
    
}
