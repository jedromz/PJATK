package com.jrom.tpo.rmi.server;

import com.jrom.tpo.rmi.Utils;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try{
            LocateRegistry.createRegistry(Utils.RMI_PORT);
            CustomRemoteObject remoteObject = new CustomRemoteObject();
            Naming.bind(Utils.ECHO_NAME,remoteObject);
            Naming.bind(Utils.ADDITION_NAME,remoteObject);
            System.out.println("SERVER STARTED...");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
