package main.java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//public class Node extends Thread {
public class Node {
//    int nodePort;
//    String receivedLine = "";
//    public Node(int port, String file) {
//        nodePort = port;
//        receivedLine = file;
//    }

    public static void main(String[] args) {
        String splitLine = "";
        ServerSocket daemonNode = null;
        Socket nodeSocket = null;

        try {
            daemonNode = new ServerSocket(Integer.parseInt(args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (true) {
                nodeSocket = daemonNode.accept();
                DataInputStream inputStream = new DataInputStream(new BufferedInputStream(nodeSocket.getInputStream()));
                splitLine = inputStream.readUTF();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
                bw.write(splitLine);
                bw.newLine();
                bw.close();
                System.out.println("From origin: " + splitLine);
                inputStream.close();
//                nodeSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {}
}
