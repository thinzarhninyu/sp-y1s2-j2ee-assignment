// THINZAR HNIN YU
// 2201014
// DIT/FT/1B/03
package CA2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class RentalSystem extends JFrame {

    RentalReadWriteFromFile rw = new RentalReadWriteFromFile();
    Comic[] comicArray = rw.readComics();
    Rentee[] renteeArray = rw.readRentees();
    double[] ratingArray = new double[comicArray.length];
    Administrator[] administratorArray = rw.readAdmins();

    // get all comics
    public Comic[] getComics() {
        return comicArray;
    }

    // get all rentees
    public Rentee[] getRentees() {
        return renteeArray;
    }

    // search comic
    public int searchComic(String isbn) {
        Boolean comicFound = false;
        int comicIndex = 0;

        // Looping through the comicArray to find the matching ISBN of the comic
        for (int i = 0; i < comicArray.length; i++) {
            if (isbn.equals(comicArray[i].getIsbn())) {
                comicFound = true;
                comicIndex = i;
            }
        }
        // Conditional statements to display output to the user
        if (comicFound) {
            return comicIndex;
        } else {
            return -1;
        }
    }

    // search rentee
    public int searchMember(String memberID) {
        // Getting user input
        String outputText = "";
        int memberIndex = 0;
        Boolean memberFound = false;
        String comicList = "";
        double totalRentalPerDay = 0;
        // Looping through the renteeArray to find the matching memberID of the user
        for (int i = 0; i < renteeArray.length; i++) {
            if (memberID.equalsIgnoreCase(renteeArray[i].getMemberID())) {
                // Looping through the array of comics inside the index of renteeArray to calculate totalRentalPerDay of the rentee
                memberFound = true;
                memberIndex = i;
            }
        }
        // Conditional statements to display output to the user
        if (memberFound) {
            return memberIndex;
        } else {
            return -1;
        }
    }

    // get earning statistics
    public String printStatistics() {
        double totalRentalPerDay = 0;
        // Looping through the renteeArray to access the list of comics inside each rentee index
        for (int i = 0; i < renteeArray.length; i++) {
            // Looping through the list of comics inside the index of renteeArray to calculate totalRentalPerDay
            for (int j = 0; j < renteeArray[i].getComics().length; j++) {
                for (int k = 0; k < comicArray.length; k++) {
                    if (renteeArray[i].getComics()[j].equals(comicArray[k].getIsbn())) {
                        totalRentalPerDay += (comicArray[k].calculateDayPrice());
                    }
                }
            }
        }
        // Calculating the averageRentalPerDay based on the totalRentalPerDay and the number of rentees inside the renteeArray
        double averageRentalPerDay = totalRentalPerDay / renteeArray.length;
        String message = "Earning Per Day:\n------------------\n\nThere are " + (renteeArray.length) + " Rentees in total.\n\nAverage earning per day based on numbers of rentees is $" + String.format("%.2f", averageRentalPerDay) + "\n\nTotal earning per day is $" + String.format("%.2f", totalRentalPerDay) + ".\n";
        return message;
    }

    // print earning statistics
    public void printStatistics(String message) {
        int printOrNot = 0;
        // Additional feature - Ask the user whether to print the output in a .txt file or not
        printOrNot = JOptionPane.showConfirmDialog(null, "Do you want to print the earning statistics?");
        // Conditional statement to print the output
        if (printOrNot == JOptionPane.YES_OPTION) {
            try {
                PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
                writer.println(message);
                writer.close();
                JOptionPane.showMessageDialog(null, "Successfully Printed as output.txt!");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Invalid!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (UnsupportedEncodingException u) {
                JOptionPane.showMessageDialog(null, "Invalid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // admin log in
    public boolean LogInAsAdmin(String adminID, String password) {
        boolean loggedIn = false;
        boolean adminIDFound = false;
        // Asking for adminID user input to check if the adminID exists in the system
        for (int i = 0; i < administratorArray.length; i++) {
            if (adminID.equals(administratorArray[i].getAdminID())) {
                adminIDFound = true;
                // Authentication
                if (password.equals(administratorArray[i].getPassword())) {
                    loggedIn = true;
                }
            }
        }
        return loggedIn;
    }

    // add comic
    public String addComic(String title, String isbn, int pages, double cost) {
        boolean comicExists = false;
        // Checking if the comic already exists in the system
        for (int i = 0; i < comicArray.length; i++) {
            if (isbn.equals(comicArray[i].getIsbn())) {
                comicExists = true;
            }
        }
        if (comicExists) {
            return "Comic ISBN-13 already exists!";
        } // check if any details are missing
        else if (title.equals("") || isbn.equals("")) {
            return "Please input all fields!";
        } else if (pages <= 0 || cost <= 0) {
            return "Values cannot be less than or equal to 0!";
        } else {
            // Adding the new comic into the comicArray
            Comic[] newComicArray = new Comic[comicArray.length + 1];
            for (int i = 0; i < comicArray.length; i++) {
                newComicArray[i] = comicArray[i];
            }
            comicArray = newComicArray;
            newComicArray[newComicArray.length - 1] = new Comic(isbn, title, pages, cost);

            // Additional Feature - Adding a new slot for rating
            double[] newRatingArray = new double[ratingArray.length + 1];
            for (int i = 0; i < ratingArray.length; i++) {
                newRatingArray[i] = ratingArray[i];
            }
            ratingArray = newRatingArray;
            rw.writeComic(newComicArray);
            // Successful output display to the user
            return "Comic successfully added!";
        }
    }

    // add manga
    public String addManga(String title, String isbn, int pages, double cost, String language) {
        boolean comicExists = false;
        // Checking if the comic already exists in the system
        for (int i = 0; i < comicArray.length; i++) {
            if (isbn.equals(comicArray[i].getIsbn())) {
                comicExists = true;
            }
        }
        if (comicExists) {
            return "Comic ISBN-13 already exists!";
        } else if (title.equals("") || isbn.equals("")) {
            return "Please input all fields!";
        } else if (pages <= 0 || cost <= 0) {
            return "Values cannot be less than or equal to 0!";
        } else {
            // Adding the new comic into the comicArray
            Comic[] newComicArray = new Comic[comicArray.length + 1];
            for (int i = 0; i < comicArray.length; i++) {
                newComicArray[i] = comicArray[i];
            }
            comicArray = newComicArray;
            newComicArray[newComicArray.length - 1] = new Manga(isbn, title, pages, cost, language);

            // Additional Feature - Adding a new slot for rating
            double[] newRatingArray = new double[ratingArray.length + 1];
            for (int i = 0; i < ratingArray.length; i++) {
                newRatingArray[i] = ratingArray[i];
            }
            ratingArray = newRatingArray;
            rw.writeComic(newComicArray);
            // Successful output display to the user
            return "Comic successfully added!";
        }
    }

    // add rentee
    public String addRentee(String memberID, String name, String[] comics) {
        boolean memberExists = false;
        for (int i = 0; i < renteeArray.length; i++) {
            if (memberID.equalsIgnoreCase(renteeArray[i].getMemberID())) {
                memberExists = true;
            }
        }
        // check if the member ID already exists
        if (memberExists) {
            return "Member ID already exists!";
        } // check if any details are missing
        else if (name.equals("") || memberID.equals("") || comics.length == 0) {
            return "Please input all fields!";
        } else {
            // Adding the new rentee into the renteeArray
            Rentee[] newRenteeArray = new Rentee[renteeArray.length + 1];
            for (int i = 0; i < renteeArray.length; i++) {
                newRenteeArray[i] = renteeArray[i];
            }
            newRenteeArray[newRenteeArray.length - 1] = new Rentee(memberID, name, comics);
            renteeArray = newRenteeArray;
            rw.writeRentee(newRenteeArray);
            // Successful output display to the user
            return "Rentee successfully added!";
        }
    }

    // add admin
    public String addAdmin(String adminNo, String username, String password) {
        boolean adminExists = false;
        // Checking if the comic already exists in the system
        for (int i = 0; i < administratorArray.length; i++) {
            if (adminNo.equals(administratorArray[i].getAdminID())) {
                adminExists = true;
            }
        }
        if (adminExists) {
            return "Admin ID already exists!";
        } // check if any details are missing
        else if (username.equals("") || password.equals("")) {
            return "Please input all fields!";
        } else {
            // Adding the new comic into the comicArray
            Administrator[] newAdminArray = new Administrator[administratorArray.length + 1];
            for (int i = 0; i < administratorArray.length; i++) {
                newAdminArray[i] = administratorArray[i];
            }
            administratorArray = newAdminArray;
            newAdminArray[newAdminArray.length - 1] = new Administrator(adminNo, username, password);

            rw.writeAdmin(newAdminArray);
            // Successful output display to the user
            return "Admin successfully added!";
        }
    }

    // edit comic
    public String editComic(String comicEditChoice, String newTitle, int newPages, double newCost) {
        Boolean comicFound = false;
        int comicIndex = 0;
        String message = "";
        // Looping through the comicArray to find the matching ISBN of the comic
        for (int i = 0; i < comicArray.length; i++) {
            if (comicEditChoice.equals(comicArray[i].getIsbn())) {
                comicFound = true;
                comicIndex = i;
            }
        }
        // Conditional statements to decide what to do if the comic is found or not
        if (comicFound) {
            // if inputs are not provided/correclty, set to previous information
            if (newTitle.equals("")) {
                newTitle = comicArray[comicIndex].getTitle();
            }
            if (newPages == 0) {
                newPages = comicArray[comicIndex].getPages();
            }
            if (newCost == 0) {
                newCost = comicArray[comicIndex].getCost();
            }
            if (newPages < 0 || newCost < 0) {
                message = "Values cannot be less than or equal to 0!";
            } else {
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure to edit this?");
                // Confirmation to edit the details of the comic
                if (confirmation == JOptionPane.YES_OPTION) {
                    comicArray[comicIndex].setTitle(newTitle);
                    comicArray[comicIndex].setPages(newPages);
                    comicArray[comicIndex].setCost(newCost);
                    rw.writeComic(comicArray);
                    message = "Changes saved!";
                } else {
                    message = "No changes saved!";
                }
            }
        } // if comic is not found
        else {
            message = "Cannot find the Comic \"" + comicEditChoice + "\"!!";
        }
        return message;
    }

    // edit rentee
    public String editRenteeInfo(String memberEditChoice, String newName) {
        Boolean memberFound = false;
        int renteeIndex = 0;
        String message = "";
        // Looping through the renteeArray to find the matching memberID of the rentee
        for (int i = 0; i < renteeArray.length; i++) {
            if (memberEditChoice.equalsIgnoreCase(renteeArray[i].getMemberID())) {
                memberFound = true;
                renteeIndex = i;
            }
        }
        // if inputs are not provided/correclty, set to previous information
        if (newName.equals("")) {
            newName = renteeArray[renteeIndex].getName();
        }
        // Editing the rentee's name
        if (memberFound) {
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure to edit this?");
            // Confirmation to edit the name of the rentee
            if (confirmation == JOptionPane.YES_OPTION) {
                renteeArray[renteeIndex].setName(newName);
                rw.writeRentee(renteeArray);
                message = "Changes saved!";
            } else {
                message = "No changes saved!";
            }
        } // if Member ID is not found
        else {
            message = "Cannot find the Member \"" + memberEditChoice + "\"!!";
        }
        return message;
    }

    // delete comic
    public int deleteComic(String comicDeleteChoice) {
        Boolean comicFound = false;
        int comicIndex = 0, deleteComic = 0;
        // Looping through the comicArray to find the matching ISBN of the comic 
        for (int j = 0; j < comicArray.length; j++) {
            if (comicDeleteChoice.equalsIgnoreCase(comicArray[j].getIsbn())) {
                // Asking confirmation to delete the comic
                deleteComic = JOptionPane.showConfirmDialog(null, "Are you sure to delete?\n" + comicArray[j].getTitle());
                comicFound = true;
                comicIndex = j;
            }
        }
        // Removing the comic from the comicArray if conditions match
        if (comicFound && deleteComic == JOptionPane.YES_OPTION) {
            List<Comic> comicArrayList = new ArrayList<Comic>();
            Collections.addAll(comicArrayList, comicArray);
            comicArrayList.remove(comicIndex);
            comicArray = comicArrayList.toArray(new Comic[comicArrayList.size()]);
            rw.writeComic(comicArray);
            return comicIndex;
        } // if comic is not found
        else if (!comicFound) {
            return -1;
        } // if deletion is cancelled
        else {
            return -2;
        }
    }

    // delete rentee
    public int deleteRentee(String renteeDeleteChoice) {
        Boolean renteeFound = false;
        int renteeIndex = 0, deleteRentee = 0;
        // Looping through the comicArray to find the matching member ID of the rentee 
        for (int j = 0; j < renteeArray.length; j++) {
            if (renteeDeleteChoice.equalsIgnoreCase(renteeArray[j].getMemberID())) {
                // Asking confirmation to delete the rentee
                deleteRentee = JOptionPane.showConfirmDialog(null, "Are you sure to delete?\n" + renteeArray[j].getName());
                renteeFound = true;
                renteeIndex = j;
            }
        }
        // Removing the comic from the renteeArray if conditions match
        if (renteeFound && deleteRentee == JOptionPane.YES_OPTION) {
            List<Rentee> renteeArrayList = new ArrayList<Rentee>();
            Collections.addAll(renteeArrayList, renteeArray);
            renteeArrayList.remove(renteeIndex);
            renteeArray = renteeArrayList.toArray(new Rentee[renteeArrayList.size()]);
            rw.writeRentee(renteeArray);
            return renteeIndex;
        } // if rentee is not found
        else if (!renteeFound) {
            return -1;
        } // if deletion is cancelled
        else {
            return -2;
        }
    }

    // rate comics
    public String rate(String comicSearchChoice, int rating) {
        String outputText = "";
        Boolean comicFound = false;
        int comicIndex = 0;
        // Looping through the comicArray to find the matching ISBN of the comic
        for (int i = 0; i < comicArray.length; i++) {
            if (comicSearchChoice.equals(comicArray[i].getTitle())) {
                outputText += comicArray[i].getTitle() + "\n\nStock purchased at $" + comicArray[i].getCost() + ".\nCost $" + String.format("%.2f", comicArray[i].calculateDayPrice()) + " per day to rent.\nRequire deposit of $" + String.format("%.2f", comicArray[i].calculateDepositFee()) + ".";
                comicFound = true;
                comicIndex = i;
            }
        }
        // Conditional statements to display output to the user and\or allow the user to rate
        ratingArray[comicIndex] = rating;
        System.out.println("rated!" + comicSearchChoice + ": " + rating);
        return "Thank you for rating!";
    }

    // get ratings
    public double[] getRatings() {
        return ratingArray;
    }

    // recommend a randomized comic
    public String recommend() {
        Comic[] comicRecommendationArray = new Comic[comicArray.length + 1];
        for (int i = 0; i < comicArray.length; i++) {
            comicRecommendationArray[i] = comicArray[i];
        }
        // Convert the array to a list to shuffle, and then convert the list back to an array
        List<Comic> comicRecommendationList = new ArrayList<Comic>();
        Collections.addAll(comicRecommendationList, comicArray);
        Collections.shuffle(comicRecommendationList);
        comicRecommendationList.toArray(comicRecommendationArray);
        return comicRecommendationArray[0].getIsbn();
    }

    // thank you message
    public void thankYou() {
        JOptionPane.showMessageDialog(null, "Saving data...");
        JOptionPane.showMessageDialog(null, "Thank you for using Comic Rental.\nWe look forward to serve you in the near future.");
    }
}
