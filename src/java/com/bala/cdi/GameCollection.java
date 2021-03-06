/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bala.cdi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GameCollection {
private HashMap<String,Game> listOfGames = new HashMap<String,Game>();

    public HashMap<String, Game> getListOfGames() {
        return listOfGames;
    }
    
    public Game getAGame(String name){
        return ((listOfGames.get(name))!=null? listOfGames.get(name):null);
    }
    
    public void addGame(String name,Game g){
       listOfGames.put(name, g);
    }
    
    public  void RemoveGame(String name){
        listOfGames.remove(name);
    }
 
    public boolean hasGame(String name){
        return (listOfGames.containsKey(name))?true:false;
    }
 
}
