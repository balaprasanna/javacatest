/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bala.cdi;

import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;




@ApplicationScoped
public class currentGames {
   private gameInstance gm;
   
   private List<gameInstance> gameList = new LinkedList<gameInstance>();
   
   /*
   Get the game instance from the current request....
   public void AddGameToList(gameInstance game){
        gameList.add(game);
    }
   */
    public void AddGameToList(gameInstance game){
        gameList.add(game);
    }

    public List<gameInstance> getGameList() {
        return gameList;
    }
    
    public boolean checkGameAvailable(gameInstance game){
        for(gameInstance gi : gameList ){
            if(gi.getGamename().equals(game.getGamename())){
                return  true;
            }
        }
        return false;
    }
}