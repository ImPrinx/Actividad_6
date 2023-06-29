package addfriends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

public class Create {
    public static String Crear(String newName, long newNumber){
        try {
            String nameNumberString;
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

                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    break;
                }
            }

            if (found == false) {
                nameNumberString = newName + ": " + String.valueOf(newNumber);
                raf.writeBytes(nameNumberString);
                raf.writeBytes(System.lineSeparator());
                System.out.println("Amigo añadido con éxito.");
                raf.close();
            } else {
                raf.close();
                System.out.println("El nombre ingresado ya existe.");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (NumberFormatException nef) {
            System.out.println(nef);
        }
        return "";
    }
}
    
    

    

