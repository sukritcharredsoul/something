package MQPlanner;

import java.io.File;

public class Check {
    public static void main(String[] args) {
        File dir = new File("src/data");
        if (!dir.exists()) {
            System.out.println("Doesn't exist Folder");
        }
    }
}
