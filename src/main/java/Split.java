package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Split {
    public static void main(String[] args) {
        try {
            String pathToData= args[0]; // main/resources/data.txt
            File myObj = new File(pathToData);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
