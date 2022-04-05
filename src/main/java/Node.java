package main.java;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Node {
   public static void main(String[] var0) {
      boolean var1 = false;
      int var2 = Integer.parseInt(var0[0]);
      String var3 = "";
      ServerSocket var4 = null;
      Socket var5 = null;

      try {
         var4 = new ServerSocket(var2);
         LocateRegistry.createRegistry(var2 + 3);
         DaemonImpl var6 = new DaemonImpl();
         Naming.rebind("rmi://localHost:" + (var2 + 3) + "/daemon", var6);
      } catch (IOException var11) {
         var11.printStackTrace();
      }

      try {
         while(true) {
            File var8;
            do {
               var5 = var4.accept();
               DataInputStream var13 = new DataInputStream(new BufferedInputStream(var5.getInputStream()));
               var3 = var13.readUTF();
               BufferedWriter var7 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(var0[1])));
               var7.write(var3);
               var7.newLine();
               var7.close();
               System.out.println("From origin: " + var3);
               var13.close();
               var8 = new File("main/resources/res" + var2 + ".txt");
            } while(var1);

            if (var8.exists() && var8.isDirectory()) {
               BufferedReader var9 = new BufferedReader(new InputStreamReader(new FileInputStream("main/resources/res" + var2 + ".txt")));
               DataOutputStream var10 = new DataOutputStream(var5.getOutputStream());
               var10.writeUTF(var9.readLine());
               var9.close();
               var10.close();
               var1 = true;
            }
         }
      } catch (IOException var12) {
         var12.printStackTrace();
      }
   }

   public void run() {
   }
}
