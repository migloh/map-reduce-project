package main.java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Node extends Thread {
    int nodePort;
    String receivedLine = "";
    public Node(int port, String file) {
        nodePort = port;
        receivedLine = file;
    }

    public void run() {
        try {
            String splitLine = "";
            ServerSocket daemonNode = new ServerSocket(nodePort);
            Socket nodeSocket = daemonNode.accept();

            DataInputStream inputStream = new DataInputStream(new BufferedInputStream(nodeSocket.getInputStream()));
            try {
                splitLine = inputStream.readUTF();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(receivedLine)));
                bw.write(splitLine);
                bw.newLine();
                bw.close();
                System.out.println("From origin: " + splitLine);
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream.close();
            nodeSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
