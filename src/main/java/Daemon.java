package main.java;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Daemon extends Remote {
   void call(MapReduce m, String blockin, String blockout, CallBack cb) throws RemoteException;
}
