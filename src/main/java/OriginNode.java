package main.java;

public class OriginNode {
    public static void main(String[] args) {
        Split splitter = new Split();
        Launch launcher = new Launch();
        splitter.run();
        launcher.run();
    }
}
