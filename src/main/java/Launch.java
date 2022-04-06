package main.java;

import java.io.*;
import java.net.Socket;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

public class Launch {
   public static void main(String[] args) {
      WordCount wc = new WordCount();

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
//         for(int i:socketPorts) {
//            File f = new File("main/resources/res"+ i +".txt");
//            Scanner scannerFile = new Scanner(f);
//            System.out.println("Fichier main/resources/res"+ i +".txt");
//            while (scannerFile.hasNextLine()){
//               String textLine = scannerFile.nextLine();
//               System.out.println(textLine);
//            }
//            scannerFile.close();
//         }
         System.out.println("Ready");
         for (int porte : socketPorts) {
            Socket sk = new Socket("localhost", porte+6);
//            System.out.println("Got Socketation");
            DataInputStream inputStream = new DataInputStream(new BufferedInputStream(sk.getInputStream()));
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("main/resources/res" + porte + "received.txt")));
            String lmeo;
            for (int i=0; i<3; i++) {
               if (porte == 8083 && i == 1) {
                  break;
               }
               lmeo = inputStream.readUTF();
               bw.write(lmeo);
               bw.newLine();
            }
            bw.flush();
            bw.close();
            inputStream.close();
            sk.close();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
