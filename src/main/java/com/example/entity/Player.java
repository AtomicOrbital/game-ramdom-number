package com.example.entity;

public class Player {
    private String name;
    private int guesses;
    
    public Player() {
    	
    }
    
    public Player(String name, int guesses) {
    	this.name = name;
    	this.guesses = guesses;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGuesses() {
		return guesses;
	}
	public void setGuesses(int guesses) {
		this.guesses = guesses;
	}

    
}
