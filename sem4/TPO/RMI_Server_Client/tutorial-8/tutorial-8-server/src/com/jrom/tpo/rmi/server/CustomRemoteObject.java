package com.jrom.tpo.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.jrom.tpo.rmi.*;

public class CustomRemoteObject  extends UnicastRemoteObject implements IEcho,IAddition {

    private static final long serialVersionUID = 1853519261174411035L;

    protected CustomRemoteObject() throws RemoteException {
    }

    @Override
    public AddResponse add(AddRequest request) throws RemoteException {
        return new AddResponse(request.addend1,request.addend2);
    }

    @Override
    public EchoResponse echo(EchoRequest request) throws RemoteException {
        return new EchoResponse(request.message);
    }
}
