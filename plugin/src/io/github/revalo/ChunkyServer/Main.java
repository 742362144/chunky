package io.github.revalo.ChunkyServer;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.*;

public class Main extends JavaPlugin {
    public Socket clientSocket;

    public PrintWriter out;
    public BufferedReader in;

    private final String REMOTE = "localhost";
    private final int PORT = 4445;

    @Override
    public void onEnable(){
        try {
            clientSocket = new Socket(REMOTE, PORT);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (Exception e) {
            System.out.println("Chunky died connecting to socket: " + e.toString());
        }

        System.out.println("Chunky is here!");

        this.getCommand("swap").setExecutor(new SwapCommand(out));
        this.getCommand("state").setExecutor(new StateCommand());
    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }
}