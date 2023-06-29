package addfriends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

public class Update {
    public static String Actualizar(String newName, long newNumber) {
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

            if (found) {
                File tmpFile = new File("C:\\Users\\Carolina\\Desktop\\files\\temp.txt");
                RandomAccessFile tmpRaf = new RandomAccessFile(tmpFile, "rw");

                raf.seek(0);

                while (raf.getFilePointer() < raf.length()) {
                    nameNumberString = raf.readLine();
                    index = nameNumberString.indexOf(": ");
                    name = nameNumberString.substring(0, index);

                    if (name.equals(newName)) {
                        nameNumberString = newName + ": " + String.valueOf(newNumber);
                    }

                    tmpRaf.writeBytes(nameNumberString);
                    tmpRaf.writeBytes(System.lineSeparator());
                }

                raf.seek(0);
                tmpRaf.seek(0);

                while (tmpRaf.getFilePointer() < tmpRaf.length()) {
                    raf.writeBytes(tmpRaf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                raf.setLength(tmpRaf.length());

                tmpRaf.close();
                raf.close();

                tmpFile.delete();

                System.out.println("Amigo actualizado con éxito.");
            } else {
                raf.close();
                System.out.println("El nombre o el número ingresado no existe.");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (NumberFormatException nef) {
            System.out.println(nef);
        }
        return "";
    }
}
