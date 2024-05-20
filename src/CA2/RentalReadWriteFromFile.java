// THINZAR HNIN YU
// 2201014
// DIT/FT/1B/03
package CA2;

import java.io.*;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class RentalReadWriteFromFile {

    // read comics from comics.txt
    public Comic[] readComics() {
        // get the length of comics array by counting lines in comic.txt
        int count = 0;
        try {
            File file = new File("comics.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        Comic[] comics = new Comic[count];
        int index = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("comics.txt"));
            String s;

            while ((s = br.readLine()) != null) {
                String[] temp = s.split(";");
                // if Comic, create comic
                if ("Comic".equals(temp[4])) {
                    comics[index] = new Comic(temp[0], temp[1], Integer.parseInt(temp[2]), Double.parseDouble(temp[3]));
                } // if Manga, create manga
                else {
                    comics[index] = new Manga(temp[0], temp[1], Integer.parseInt(temp[2]), Double.parseDouble(temp[3]), temp[5]);
                }
                index++;
            }
            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return comics;
    }

    //read rentees from rentee.txt
    public Rentee[] readRentees() {
        // get the length of rentee array by counting lines in rentee.txt
        int count = 0;
        try {
            File file = new File("rentee.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        Rentee[] rentees = new Rentee[count];
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("rentee.txt"));
            String s;
            while ((s = br.readLine()) != null) {
                String[] temp = s.split(";");
                // get comics of rentee
                String[] temp2 = temp[2].split("#");
                rentees[i] = new Rentee(temp[0], temp[1], temp2);
                i++;
            }
            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rentees;
    }

    // read admins from admin.txt
    public Administrator[] readAdmins() {
        // get length of admin array by counting lines in admin.txt
        int count = 0;
        try {
            File file = new File("admin.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        Administrator[] admins = new Administrator[count];
        int index = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("admin.txt"));
            String s;

            while ((s = br.readLine()) != null) {
                String[] temp = s.split(";");
                admins[index] = new Administrator(temp[0], temp[1], temp[2]);
                index++;
            }
            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return admins;
    }

    // save rentee to rentee.dat
    public void saveRentee(Rentee[] rentee) {
        File f = new File("rentees.dat");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            for (int i = 0; i < rentee.length; i++) {
                os.writeObject(rentee);
            }
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // save comic to comics.txt after adding/deleting/editing by admin
    public void writeComic(Comic[] comicArray) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("comics.txt"));
            String text = "";
            for (int i = 0; i < comicArray.length; i++) {
                if (comicArray[i] instanceof Manga) {
                    if (comicArray[i].getLanguage().equals("This is a Manga translated to English")) {
                        text = comicArray[i].getIsbn() + ";" + comicArray[i].getTitle() + ";" + Integer.toString(comicArray[i].getPages()) + ";" + Double.toString(comicArray[i].getCost()) + ";" + "Manga" + ";" + "EN";

                    } else {
                        text = comicArray[i].getIsbn() + ";" + comicArray[i].getTitle() + ";" + Integer.toString(comicArray[i].getPages()) + ";" + Double.toString(comicArray[i].getCost()) + ";" + "Manga" + ";" + "JP";
                    }
                } else {
                    text = comicArray[i].getIsbn() + ";" + comicArray[i].getTitle() + ";" + Integer.toString(comicArray[i].getPages()) + ";" + Double.toString(comicArray[i].getCost()) + ";" + "Comic" + ";" + "EN";

                }
                pw.println(text);
            }
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in file writing...", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // save rentee to rentee.txt after adding/deleting/editing by admin
    public void writeRentee(Rentee[] renteeArray) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("rentee.txt"));
            String text = "";
            for (int i = 0; i < renteeArray.length; i++) {
                text = renteeArray[i].getMemberID() + ";" + renteeArray[i].getName() + ";";
                for (int j = 0; j < renteeArray[i].getComics().length; j++) {
                    text += renteeArray[i].getComics()[j] + "#";
                }
                text = text.substring(0, text.length() - 1);
                pw.println(text);
            }
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in file writing...", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // save admin to admin.txt after adding/deleting/editing by admin
    public void writeAdmin(Administrator[] administratorArray) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("admin.txt"));
            String text = "";
            for (int i = 0; i < administratorArray.length; i++) {
                text = administratorArray[i].getAdminID() + ";" + administratorArray[i].getName() + ";" + administratorArray[i].getPassword();
                pw.println(text);
            }
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in file writing...", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
 
}
