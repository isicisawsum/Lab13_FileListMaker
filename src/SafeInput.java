import java.util.Scanner; //importing Scanner
public class SafeInput
{


    //Part testing
    public static String getNonZeroLengthString(Scanner pipe, String prompt)
    {
        String nonZeroLength = ""; //defining variable

        do{
            System.out.print("\n" + prompt + ": "); //prompting user
            nonZeroLength = pipe.nextLine(); //setting variable to what they entered

            if(nonZeroLength.length() == 0)
            {
                System.out.println("\nYou must enter some characters!"); //outputs that user needs to enter some characters
            }

        }while (nonZeroLength.length() == 0 ); //loops runs while num of characters is 0

        return nonZeroLength; //returns the string
    }

    public static int getInt(Scanner pipe, String prompt)
    {
        //defining variables
        int retValue = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) { //testing if has an int
                retValue = pipe.nextInt();
                pipe.nextLine(); // clear key buffer
                done = true; //ends loop
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not: " + trash); //user did not enter an int number
            }
        }while (!done); //loops while done = false

        return retValue; //returning intvalue
    }
    public static double getDouble(Scanner pipe, String prompt) {
        //defining variables
        double Doub = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.println("\n" + prompt + ": "); //prompting user for a double
            if (pipe.hasNextDouble()) { //testing if var is a Doub
                Doub = pipe.nextDouble(); //getting var Doub
                done = true; //ends loop
            }
            else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double number, not: " + trash); //user did not enter a correct double value and program informs them of their mistake
            }
        }while (!done);

        return Doub; //returning double value
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high){
        //defining variables
        int retValue = 0;
        String trash = "";
        boolean done = false;//looping variable

        do {
            System.out.print("\n" + prompt + "[" + low + " - " + high + "]: "); //asking user to enter a ranged int number
            if (pipe.hasNextInt()) { //testing if has an int number
                retValue = pipe.nextInt();
                pipe.nextLine(); // clear key buffer
                if(retValue >= low && retValue <= high) //testing if var meets criteria
                    done = true; //end loop
                else
                    System.out.println("\nNumber is out of range [" + low + " - " + high + "] not: " + retValue); //num is out of range
            } else {
                //user did not make a valid number
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not: " + trash);
            }
        }while (!done);

        return retValue; //returning the value
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high){
        //defining variables
        double retValue = 0;
        String trash = "";
        boolean done = false; //looping variable

        do {
            System.out.print("\n" + prompt + "[" + low + " - " + high + "]: "); //asking user to enter a ranged double number
            if (pipe.hasNextDouble()) { //testing if has a double number
                retValue = pipe.nextDouble();
                pipe.nextLine(); // clear key buffer
                if(retValue >= low && retValue <= high) //testing if var meets criteria
                    done = true; //ending loop
                else //var did not meet criteria
                    System.out.println("\nNumber is out of range [" + low + " - " + high + "] not: " + retValue); //outputting number is out of range
            } else { //user did not enter a double number
                //informing user of mistake
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double not: " + trash);
            }
        }while (!done);

        return retValue; //returning the value
    }

    public static String getYNConfirm(Scanner pipe, String prompt){
        //defining variables
        String YorN = "";
        boolean done = false; //looping var

        do { //loop
            System.out.println("\n" + prompt + "[Y or N]"); //prompting user for Y or N
            YorN = pipe.nextLine(); //setting YorN to what user entered
            YorN = YorN.toUpperCase(); //setting their choice to uppercase
            if (YorN.equals("Y")){
                done = true; //ending loop
            }
            else if(YorN.equals("N")){
                done = true; //ending loop
            }
            else{
                System.out.println("You must enter Y or N, not " + YorN); //user entered something wrong
            }
        }while (!done);

        return YorN; //returning answer Y or N
    }

    public static String getRegExString(Scanner pipe, String prompt, String regExPattern){
        //defining variables
        String value = "";
        boolean gotAValue = false;

        do { //looping
            System.out.print("\n" + prompt + ":"); //printing out prompt
            value = pipe.nextLine(); //inputting for var value

            //test to see if it is correct
            if (value.matches(regExPattern)){
                gotAValue = true; //end loop
            }
            else {
                System.out.println("\n" +value +" is an invalid input. Please enter one of the following:" + regExPattern);
                System.out.println("Try again"); //user entered something wrong
            }
        } while(!gotAValue);

        return value; //returning var value to be used
    }

    public static void prettyHeader(String msg){
        final int CHAR_COUNT = 60;  //60 characters on each line
        int totalSpaces = CHAR_COUNT - 6 - msg.length();  //how much space is left after the message
        int leftSpaces, rightSpaces;
        if(totalSpaces % 2 == 0)  // testing for even spaces using modulo to make it centered
        {
            leftSpaces = rightSpaces = totalSpaces / 2;  //side spaces
        }
        else // the user entered an odd num of characters, which will be a little bit off
        {
            rightSpaces = totalSpaces / 2;  //division by two for space
            leftSpaces = rightSpaces + 1;  //move left one space for simulated center
        }
        System.out.println("************************************************************"); //printing a line of *'s
        // print left spaces
        for(int ch = 0; ch < leftSpaces; ch++)
            System.out.print(" "); //printing spaces
        // print msg that was fed to the method as a parameter
        System.out.print(msg); //prints the user's message
        // print right spaces,
        for(int ch = 0; ch < rightSpaces; ch++)
            System.out.print(" "); //printing spaces
        System.out.println("\n************************************************************"); //printing a line of *'s
    }


}
