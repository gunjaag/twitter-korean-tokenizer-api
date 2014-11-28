package com.tweetkorean.core;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class UIServer {
    public static void main(String[] args) {
        Server jettyServer = null;
        try {
            jettyServer = new Server();

            SocketConnector conn = new SocketConnector();
            conn.setPort(8081);
            jettyServer.setConnectors(new Connector[]{conn});

            WebAppContext context = new WebAppContext();
            context.setContextPath("/");
            context.setWar("src/main/webapp");

            jettyServer.setHandler(context);
            jettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
            if (null != jettyServer) {
                try {
                    jettyServer.stop();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
