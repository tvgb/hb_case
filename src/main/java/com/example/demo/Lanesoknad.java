package com.example.demo;

import java.util.ArrayList;

public class Lanesoknad {
    public ArrayList<Lanetaker> lanetakere;
    public double lanebelop;
    public String behov;

    public String toString() {
        String s = "";

        for (Lanetaker lanetaker : lanetakere) {
            s += "Lanetaker: ";
            s += lanetaker.toString() + "\n";
        }

        s += "Lanebelop: " + lanebelop + " kr\n";
        s += "Behov: " + behov + "\n";
        
        return s;
    }
}
