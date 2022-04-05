package main.java;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Iterator;

public class Launch {
   public static void main(String[] args) {
      WordCount wc = new WordCount();
//      new ArrayList();

      try {
         ArrayList<Integer> socketPorts = new ArrayList<>();
         ArrayList<Integer> rmiPort = new ArrayList<>();
         socketPorts.add(8081);
         socketPorts.add(8082);
         socketPorts.add(8083);
         rmiPort.add(8084);
         rmiPort.add(8085);
         rmiPort.add(8086);
         CallBack cb = (CallBack)Naming.lookup("rmi://localhost:8080/callback");

         for(int rmiP:rmiPort) {
            new DaemonThread(wc, rmiP, cb);
         }

      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }
}
