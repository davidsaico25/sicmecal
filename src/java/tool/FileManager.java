package tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author davisonsp
 */
public class FileManager {
    
    public static void ticketReservaCitaMedica() throws IOException {
        String path = "C:\\Users\\davisonsp\\Documents\\2016-II\\prueba.txt";
        File file = new File(path);
        BufferedWriter bufferedWriter;
        if (!file.exists()) {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("Hola Mundo\r\nComo estas?");
            bufferedWriter.close();
        }
    }
    
    public static void main(String args[]) {
        try {
            ticketReservaCitaMedica();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
