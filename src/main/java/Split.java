package main.java;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Split {
    public static void main(String[] args) {
        try {
            ArrayList<Integer> portList = new ArrayList<Integer>();
            int initPort = 8080;
            String pathToData = args[0]; // main/resources/data.txt
            File myObj = new File(pathToData);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                Node daemon = new Node(initPort, "main/resources/request" + Integer.toString(initPort) + ".txt");
                daemon.start();
                Socket socketOrigin = new Socket("localhost", initPort);
                DataOutputStream output = new DataOutputStream(socketOrigin.getOutputStream());
                portList.add(initPort);
                String data = myReader.nextLine();
                output.writeUTF(data);
                initPort++;
                output.close();
                socketOrigin.close();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
