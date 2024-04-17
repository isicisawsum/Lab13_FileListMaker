import java.util.ArrayList;
import java.util.Scanner;
//importing scanner and array list
public class ListMaker {
    static ArrayList<String> Rray = new ArrayList<>(); //creating arraylist Rray
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); //creating Scanner

        final String menu = "A - Add\nD - Delete\nP - Print (The list)\nQ - Quit\n"; //A variable to be displayed to the user
        boolean done = false; //looping variable
        String cmd = "";//defining variables

        do{

            cmd = SafeInput.getRegExString(in, menu, "[AaDdPpQq]"); //using regEx method to get a certain pattern
            cmd = cmd.toUpperCase(); //making input uppercase

            switch(cmd){ //accounting for all options
                case "A": //user types A
                    String add = addToMenu(in); //using add method
                    Rray.add(add); //adding what user typed to the table
                    break; //end case
                case "D": //user types D
                    int del = delete(in); //using delete method
                    Rray.remove(del); //removes a number that user types
                    break; //end case
                case "P": //user types P
                    displayMenu(); //displays menu
                    break; //end case
                case "Q": //user types Q
                    quit(in); //using quit method
                    break; //end case
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

}