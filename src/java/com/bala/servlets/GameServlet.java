/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bala.servlets;

import com.bala.cdi.currentGames;
import com.bala.cdi.gameInstance;
import com.bala.runnables.CreatePlayer_R;
import com.bala.runnables.MyTask;
import java.io.IOException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/gameServer")
public class GameServlet extends HttpServlet{
    @Resource(mappedName = "concurrent/MyManageScheduleExecutorService") ManagedScheduledExecutorService src;
    @Inject gameInstance gameReq;
  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String gameName = req.getParameter("gameName");
      if(gameName == ""){
          resp.setStatus(resp.SC_BAD_REQUEST);
       }else{
       /*check weather the game has already been started or not
         if started...don not start a new one..
          else start a new one and keep the connection open...
          This job is going to be done by 
          CreatePlayer_R Runnnable thread...
       */
       gameReq.setGamename(gameName);
       CreatePlayer_R task = new CreatePlayer_R(gameReq);
       src.submit(task);
          
      }/*
        MyTask taskRunnable = new MyTask(gameName);
        src.scheduleAtFixedRate(taskRunnable, 0, 1, TimeUnit.SECONDS);
       */
    }

}
