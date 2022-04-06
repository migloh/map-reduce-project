package main.java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Node {
   public static void main(String[] args) {
      int portNo = Integer.parseInt(args[0]);
      String splitLine = "";
      ServerSocket serverSocket = null;
      Socket socketNode = null;

      try {
         serverSocket = new ServerSocket(portNo);
         LocateRegistry.createRegistry(portNo + 3);
         DaemonImpl daemonNode = new DaemonImpl();
         Naming.rebind("rmi://localHost:" + (portNo + 3) + "/daemon", daemonNode);
      } catch (IOException e) {
         e.printStackTrace();
      }

      try {
          while(true) {
              socketNode = serverSocket.accept();
              DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socketNode.getInputStream()));
              splitLine = inputStream.readUTF();
              BufferedWriter buffered = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
              buffered.write(splitLine);
              buffered.newLine();
              buffered.close();
              System.out.println("From origin: " + splitLine);
              inputStream.close();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
