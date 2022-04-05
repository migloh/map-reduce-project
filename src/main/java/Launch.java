package main.java;

import java.io.File;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

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
            DaemonThread dt = new DaemonThread(wc, rmiP, cb);
            dt.start();
         }
         cb.waitForAll();
         File f = new File("main/resources/res8081.txt");
         Scanner scannerFile = new Scanner(f);
         String textLine = scannerFile.nextLine();
         System.out.println("The res est: " + textLine);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
