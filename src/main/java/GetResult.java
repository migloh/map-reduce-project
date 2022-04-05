package main.java;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GetResult extends Remote {
    public void getResult(String fileName) throws RemoteException;
}
