import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

/**
 *
 * @author wulft
 *
 * Use the thread safe NIO IO library to read a file
 */
public class Main
{
    static ArrayList<String> Rray = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Scanner pipe = new Scanner(System.in);
        JFileChooser chooser = new JFileChooser();
        File selectedFile = null;
        String rec = "";
        final String menu = "A - Add\nD - Delete\nV - View (The list)\nC - Clear all elements from list\nO - Open a list file from disk\nS - Save current list file to disk\nQ - Quit"; //A variable to be displayed to the user
        String cmd = "";
        boolean done = false;

        do{

            cmd = SafeInput.getRegExString(pipe, menu, "[AaDdVvQqOoCcSs]"); //using regEx method to get a certain pattern
            cmd = cmd.toUpperCase(); //making input uppercase

            switch(cmd){ //accounting for all options
                case "A": //user types A
                    String add = addToMenu(pipe); //using add method
                    Rray.add(add); //adding what user typed to the table
                    break; //end case
                case "D": //user types D
                    int del = delete(pipe); //using delete method
                    Rray.remove(del); //removes a number that user types
                    break; //end case
                case "V": //user types P
                    displayMenu(); //displays menu
                    break; //end case
                case "Q": //user types Q
                    quit(pipe); //using quit method
                    break; //end case
                case "O": //user types O
                    open(chooser, selectedFile, rec, Rray); //using open method
                    break; //end case
                case "C": //user types C
                    clear(Rray);
                    break;
                case "S":
                    save(Rray);
                    break;
            }
        }while(!done); //loop runs while variable done is false



    }
    private static void displayMenu() {
        System.out.println("************"); //prints nice border
        for (int i = 0; i < Rray.size(); i++){
            System.out.println(i+1 + ":" + Rray.get(i)); //prints a number, then what is in the table for that number for the length of the table
        }
        System.out.println("************"); //prints nice border
    }
    private static String addToMenu(Scanner pipe){
        System.out.println("What would you like to add to the list?"); //asks what user would like to add to the list
        String add = pipe.nextLine(); //variable add is what user responds
        return add; //returns what user types
    }

    private static int delete(Scanner pipe){
        int pos; //defining a variable that user will select
        displayMenu(); //displays menu so that user can decide which part they want to remove
        do {
            System.out.println("Enter the number:"); //prompting for pos
            while (!pipe.hasNextInt()) { //small loop if user makes a mistake
                System.out.println("That's not a valid number!"); //telling user their mistake
                pipe.next(); //new line
            }
            pos = pipe.nextInt() - 1; //moving what user typed down 1
        } while (pos < 0 || pos >= Rray.size()); //makes sure user can't type number less than one or greater than the table size
        pipe.nextLine(); //makes a new line
        return pos; //returns the position where they want to remove
    }

    private static void quit(Scanner pipe){
        boolean quit = false; //looping variable
        do{
            String yn = SafeInput.getYNConfirm(pipe, "Are you sure you want to quit?"); //prompting user if they want to quit
            yn = yn.toUpperCase(); //moving their answer to upper case
            if (yn.equals("N")){ //user typed N
                quit = true; //stops the loop
                break; //exit the if/then
            }
            else if (yn.equals("Y")){ //user typed Y
                System.exit(0); //exits program
                break;
            }
            else{
                System.out.println("That is not a Y or a N"); //tells user they made mistake
            }
        } while (!quit); //loop runs until user enters Y or N

    }

    private static void open(JFileChooser chooser, File selectedFile, String rec, ArrayList Rray){
        clear(Rray);
        try {
            Path workingDirectory = new File(System.getProperty("user.dir")).toPath();
            workingDirectory = workingDirectory.resolve("src");
            chooser.setCurrentDirectory(workingDirectory.toFile());


            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                int line = 0; //counts the num of lines
                int i; //counts the number of characters
                while (reader.ready()) {
                    rec = reader.readLine();
                    Rray.add(rec);
                    line++;

                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");



                System.out.println("The name of the selected file is :" + selectedFile.getName());
                System.out.println("The number of lines in the selected file is " + line);


            } else  // User closed the chooser without selecting a file
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e) //file not found
        {
            System.out.println("File not found!!!");
            e.printStackTrace(); //the file user entered was not found
        }
        catch (IOException e) //other exception
        {
            e.printStackTrace();
        }
    }

    private static void clear(ArrayList Rray){
        Rray.clear(); //clear the Rray list
        System.out.println("List cleared"); //telling user the list is cleared
    }

    private static void save(ArrayList Rray){
        try {
            JFileChooser chooser = new JFileChooser(); //making new file chooser
            int result = chooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile(); //gets the selected file
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(Rray); //writes what is in the array table to a file

                oos.close();
                fos.close(); //closes FileOutputStream and ObjectObputStream

                System.out.println("ArrayList saved to file successfully."); //telling user file sucessfully save
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
