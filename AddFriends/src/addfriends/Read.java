package addfriends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

public class Read {
    public static String Leer(){
    try {
            String nameNumberString;//Me gustan los pr oyectos de cawowina
            String name;
            long number;
            int index;

            File file = new File("C:\\Users\\Carolina\\Desktop\\files\\AddFriends.txt");

            if (file.exists() == false) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split(": ");

                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                System.out.println("Nombre: " + name + "\n" + "Número de contacto: " + number + "\n");
            }
            
            raf.close();

        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (NumberFormatException nef) {
            System.out.println(nef);
        }
        
        return "";
    }
}
