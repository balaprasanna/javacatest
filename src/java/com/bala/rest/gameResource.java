/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bala.rest;


import com.bala.cdi.currentGames;
import com.bala.cdi.gameInstance;
import com.bala.runnables.MyTask;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

@Path("/gameService")
@RequestScoped
public class gameResource implements Serializable{
    private static final Long version =1L;
  @Inject currentGames allGames;
  gameInstance gmInstance;
    @Resource(mappedName = "concurrent/MyManageScheduleExecutorService") ManagedScheduledExecutorService src;
    
    @GET
    @Path("/{game}")
    @Produces("text/event-stream")
    public Response getALink(@PathParam("game") String gamename)
    {
       gmInstance= new gameInstance(gamename);
      
         EventOutput outputLink = new EventOutput();
         MyTask c = new MyTask(outputLink,gamename);
         ScheduledFuture<?> future = src.scheduleAtFixedRate(c, 1, 1, TimeUnit.SECONDS);
         
         c.setFuture(future);
         return Response.ok(outputLink).build();
    }
    
}
