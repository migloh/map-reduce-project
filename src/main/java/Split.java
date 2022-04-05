package main.java;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Split {
   public static void main(String[] var0) {
      try {
         ArrayList<Integer> portList = new ArrayList<Integer>();
         int initPort = 8081;
         String dataFile = "main/resources/data.txt";
         File f = new File(dataFile);
         Scanner scannerFile = new Scanner(f);

         while(scannerFile.hasNextLine()) {
            Socket socketNode = new Socket("localhost", initPort);
            DataOutputStream splitFile = new DataOutputStream(socketNode.getOutputStream());
            portList.add(initPort);
            String textLine = scannerFile.nextLine();
            splitFile.writeUTF(textLine);
            ++initPort;
            splitFile.close();
            socketNode.close();
         }
         scannerFile.close();
      } catch (FileNotFoundException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }
}
