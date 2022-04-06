package main.java;

import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class DaemonResult {
    public static void main(String[] args) {
        int portNo = Integer.parseInt(args[0]);
        try {
            ServerSocket sv = new ServerSocket(portNo);
            Socket sk = sv.accept();
            File f = new File("main/resources/res"+ (portNo-6) +".txt");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                DataOutputStream dataOutputStream = new DataOutputStream(sk.getOutputStream());
                String line = scanner.nextLine();
                dataOutputStream.writeUTF(line);
            }
            sk.close();
            sv.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
