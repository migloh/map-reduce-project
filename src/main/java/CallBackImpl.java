package main.java;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallBackImpl extends UnicastRemoteObject implements CallBack {
    int nbnode;

    public CallBackImpl(int var1) throws RemoteException {
        this.nbnode = var1;
    }

    public synchronized void completed() throws RemoteException {
        this.notify();
    }

    public synchronized void waitForAll() throws InterruptedException, RemoteException {
        try{
            this.wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}