package main.java;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallBackImpl extends UnicastRemoteObject implements CallBack {
    int nbnode;

    public CallBackImpl(int n) throws RemoteException {
        nbnode = n;
    }

    @Override
    public synchronized void completed() throws RemoteException {
        notify();
    }

    public synchronized void waitForAll() throws InterruptedException {
        for(int i = 0; i < nbnode; i++)
            wait();
    }
}
