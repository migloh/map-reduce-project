package main.java;

import java.rmi.Naming;

public class DaemonThread extends Thread {
   WordCount wc;
   int nodePort;
   CallBack cb;

   public DaemonThread(WordCount wc, int port, CallBack cb) {
      this.wc = wc;
      this.nodePort = port;
      this.cb = cb;
   }

   public void run() {
      try {
         Daemon daemonNode = (Daemon)Naming.lookup("rmi://localHost:" + this.nodePort + "/daemon");
         daemonNode.call(wc, "main/resources/split" + (this.nodePort - 3) + ".txt", "main/resources/res" + (this.nodePort - 3) + ".txt", cb);
         cb.completed();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
