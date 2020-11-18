package com.semicode.pocimonapp.data.model;

import java.util.ArrayList;

public class PokemonRespone {

    private int count;
    private String next, previous;
    private ArrayList<Pokemon> results;

    public PokemonRespone(int count, String next, String previous, ArrayList<Pokemon> result) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> result) {
        this.results = result;
    }


}
