/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bala.runnables;

import com.bala.cdi.currentGames;
import com.bala.cdi.gameInstance;
import javax.inject.Inject;


public class CreatePlayer_R implements 
        Runnable{
    
  @Inject currentGames allGames;
  private gameInstance gI;
    
    public CreatePlayer_R(gameInstance name) {
    this.gI = name;
    }
    
    
    public void run(){
    if(allGames.checkGameAvailable(gI)){
        System.out.println("Game allready existed"+gI.getGamename());        
    }
    else{
        System.out.println("Game is not available"+gI.getGamename());        
        allGames.AddGameToList(gI);
        
    }
    
    }
}
