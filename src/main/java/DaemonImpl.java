package main.java;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DaemonImpl extends UnicastRemoteObject implements Daemon {
   public DaemonImpl() throws RemoteException {
   }

   public void call(MapReduce m, String blockin, String blockout, CallBack cb) throws RemoteException {
      m.executeMap(blockin, blockout);
      cb.completed();
   }
}
