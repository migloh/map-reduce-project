package main.java;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CallBackServer {
   public static void main(String[] var0) {
      try {
         LocateRegistry.createRegistry(8080);
         CallBackImpl stub = new CallBackImpl(3);
         Naming.rebind("rmi://localhost:8080/callback", stub);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
