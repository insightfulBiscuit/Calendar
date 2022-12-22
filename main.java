/*
main class
Desc:
Daniel Kovalevskiy
Dec. 6, 2022 
*/

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class main{
    public static void main(String[] args) throws IOException{
        //variable declaration
        HashMap<Person, String> loginInfo = new HashMap<Person, String>();
        ArrayList<Event> eventList = new ArrayList<Event>(0);
        String username;
        String password;
        String option = "0";
        Person user = new Person();
        boolean authorized = false;
        boolean invalid = true;

        //constants for user input
        final String OPTION_LOGIN = "1";
        final String OPTION_CREATE_ASSESMENT = "1";
        final String OPTION_ADD_EVENT = "1";
        final String OPTION_CREATE_ACCOUNT = "2";   
        final String OPTION_REMOVE_EVENT = "2";
        final String OPTION_CREATE_EXTRACURRICULAR = "2";
        final String OPTION_CLOSE_PROGRAM = "3";
        final String OPTION_FIND_EVENT = "3";
        final String OPTION_CREATE_GENERAL = "3";
        final String OPTION_BACK_TO_MENU = "4";
        final String OPTION_LIST_EVENTS_AT_DATE = "4";
        final String OPTION_PRINT_CALENDAR = "5";
        final String OPTION_BACK_TO_LOGIN = "6";

        //reads file
        readFile("dataStore.txt", loginInfo);
        Scanner input = new Scanner(System.in);

        //while user didnt select to exit out of the program
        while (!option.equals(OPTION_CLOSE_PROGRAM)){
            do{
                //user asked to select one of the options listed
                System.out.println("1. Login\n2. Create Account\n3. Close Program");
                System.out.print("Please Select From Above: ");
                option = input.nextLine();

                //gets user login
                if (option.equals(OPTION_LOGIN)){
                    System.out.println("\n---------------------");
                    System.out.println("\tLogin");
                    System.out.println("---------------------\n");

                    System.out.print("Username: ");
                    username = input.nextLine();

                    System.out.print("Password: ");
                    password = input.nextLine();

                    //finds user in map and checks password
                    for (Person p: loginInfo.keySet()){
                        if (p.getName().equals(username) && loginInfo.get(p).equals(password)){
                            user = p;
                            authorized = true;
                        }
                    }

                    if (authorized){
                        System.out.println("\nLogin Successful!\n");
                        invalid = false;
                    }
                    else{
                        System.out.println("\nUsername or Password is Incorrect.\n");
                    }
                }

                //create account for user
                else if (option.equals(OPTION_CREATE_ACCOUNT)){
                    createUser(input, loginInfo);
                }

                //close program
                else if (option.equals(OPTION_CLOSE_PROGRAM)){
                    invalid = false;
                }

                //other inputs are not permitted
                else{
                    invalid = true;
                    System.out.println("\nInput Invalid. Please Try Again.\n");
                }
            } while(invalid);

            //while user didnt select to go back to login or exit out of program
            while (!option.equals(OPTION_BACK_TO_LOGIN) && !option.equals(OPTION_CLOSE_PROGRAM)){

                //user asked to select one of the options listed
                System.out.println("1. Add Event\n2. Remove Event\n3. Find Event\n4. List Events At Given Date\n5. List All Events\n6. Go Back");
                System.out.print("\nSelect Option From Above: ");
                option = input.nextLine();

                //user wants to add event
                if (option.equals(OPTION_ADD_EVENT)){
                    ArrayList<String> eventDetails = new ArrayList<String>(6);

                    do{
                        invalid = true;

                        //user asked what kind of event to create
                        System.out.println("\n\tCreate\n1. Assesment\n2. Extracurricular\n3. General Event\n4. Go Back");
                        System.out.print("\nSelect an option: ");
                        option = input.nextLine();

                        //valid options
                        if (option.equals(OPTION_CREATE_ASSESMENT) || option.equals(OPTION_CREATE_EXTRACURRICULAR) || option.equals(OPTION_CREATE_GENERAL) || option.equals(OPTION_BACK_TO_MENU)){
                            invalid = false;
                        }
                        //other inputs not permitted
                        else{
                            System.out.println("\nInvalid Input. Try Again.");
                        }
                    } while(invalid);

                    //user is asked to input details of event
                    if (!option.equals(OPTION_BACK_TO_MENU)){
                        invalid = true;

                        System.out.println("\nEnter the details bellow\n");

                        System.out.print("Name: ");
                        eventDetails.add(input.nextLine());

                        System.out.print("Date (YYYYMMDD): ");
                        eventDetails.add(input.nextLine());

                        System.out.print("Start Time (HHMM): ");
                        eventDetails.add(input.nextLine());

                        System.out.print("End Time (HHMM): ");
                        eventDetails.add(input.nextLine());

                        System.out.print("Location: ");
                        eventDetails.add(input.nextLine());

                        System.out.print("Description: ");
                        eventDetails.add(input.nextLine());

                        //additional inputs if event is an assesment
                        if (option.equals(OPTION_CREATE_ASSESMENT)){
                            System.out.print("Weight: ");
                            eventDetails.add(input.nextLine());

                            System.out.print("Subject: ");
                            eventDetails.add(input.nextLine());

                            System.out.print("Topics: ");
                            eventDetails.add(input.nextLine());
                        }

                        //additional inputs if event is an extracurricular
                        else if (option.equals(OPTION_CREATE_EXTRACURRICULAR)){
                            System.out.print("Organization: ");
                            eventDetails.add(input.nextLine());        
                        }

                        //will try creating the event
                        try{
                            final int START_TIME = 2;
                            final int END_TIME = 3;
                            if (Integer.valueOf(eventDetails.get(START_TIME)) >= 0 && Integer.valueOf(eventDetails.get(START_TIME)) < 2400 && Integer.valueOf(eventDetails.get(END_TIME)) >= 0 && Integer.valueOf(eventDetails.get(END_TIME)) < 2400 && Integer.valueOf(eventDetails.get(START_TIME)) < Integer.valueOf(eventDetails.get(END_TIME))){
                                user.getCalendar().addEvent(createEvent(eventDetails));
                                System.out.println("\nEvent Successfully Created!\n");
                            }
                            else{
                                System.out.println("\nSomething Doesn't Add Up! Event Not Created!\n");
                            }
                        } catch(NumberFormatException e){ //catches improper input format (i.e. input String when want int)
                            System.out.println("\nInvalid Format. Event Not Created.\n");
                            invalid = true;
                        }

                        option = "";
                    }
                    else{
                        System.out.println();
                    }
                }

                //user wants to remove event
                else if (option.equals(OPTION_REMOVE_EVENT)){
                    System.out.print("\nEnter the Name of Event: ");

                    //find all events containing that name
                    eventList = user.getCalendar().findEvent(input.nextLine());

                    //matching events are printed
                    if (eventList.isEmpty()){
                        System.out.println("\nNo Event(s) Found By That Name.\n");
                    }
                    else{
                        for (int i = 0; i < eventList.size(); i++){
                            System.out.println(i + ". " + eventList.get(i).toFile());
                        }
                        System.out.println();
                    }

                    invalid = true;

                    do{
                        //user asked which specific event they wish to remove
                        System.out.print("Enter The Index of Element To Delete: ");
                        option = input.nextLine();

                        //will try removing the event from tree
                        try{
                            if ((int)(Integer.valueOf(option)) >= 0 && (int)(Integer.valueOf(option)) < eventList.size()){
                                user.getCalendar().deleteEvent(eventList.get((int)(Integer.valueOf(option))));
                                System.out.println("\nEvent Deleted.\n");
                                invalid = false;
                            }
                            else{
                                System.out.println("\nIndex Out Of Range. Try Again.\n");
                            }
                        } catch (NumberFormatException e){ //catch improper input format
                            System.out.println("\nInvalid Input. Try again.\n");
                        }
                    } while(invalid);

                    option = "";
                }

                //user wishes to find event
                else if (option.equals(OPTION_FIND_EVENT)){
                    //user asked to input name of event
                    System.out.print("\nEnter the Name of Event: ");
                    eventList = user.getCalendar().findEvent(input.nextLine());

                    //print out all events matching the name
                    if (!eventList.isEmpty()){
                        System.out.println();
                        for (Event element: eventList){
                            System.out.println(element);
                        }
                        System.out.println("--------------------\n");
                    }
                    else{
                        System.out.println("\nNo Event Found.\n");
                    }
                    option = "";
                }

                //user wants the list of events on a given date
                else if (option.equals(OPTION_LIST_EVENTS_AT_DATE)){
                    invalid = true;
                    while (invalid){
                        //will get user input
                        try{
                            System.out.print("\nEnter the date (YYYYMMDD): ");
                            eventList = user.getCalendar().findEvent(input.nextInt());
                            invalid = false;
                        } catch(InputMismatchException e){ //catches inproper date format
                            System.out.println("\nInvalid Input. Try again.");
                        }
                    }

                    //print out all events on a given date
                    if (!eventList.isEmpty()){
                        System.out.println();
                        for (Event element: eventList){
                            System.out.println(element);
                        }
                        System.out.println("--------------------\n");
                    }
                    else{
                        System.out.println("\nNo Events at Given Date.\n");
                    }   

                    input.nextLine();
                    invalid = true;
                }

                //user wants to see the entire calendar
                else if (option.equals(OPTION_PRINT_CALENDAR)){
                    eventList = user.getCalendar().listEvents();
                    System.out.println();

                    //all events in calendar are printed
                    if (!eventList.isEmpty()){
                        for (Event element: eventList){
                            System.out.println(element);
                        }
                        System.out.println("--------------------\n");
                    }
                    else{
                        System.out.println("\nYour Calendar is Empty.");
                    }
                }
                
                else{
                    //user wishes to go back to login page
                    if (option.equals(OPTION_BACK_TO_LOGIN)){
                        System.out.println("\nBack To Main Menu...\n");
                    }

                    //other inputs not permitted
                    else{
                        System.out.println("\nInvalid Input. Try again.\n");
                    }
                }
            } 
        }

        //one the user closes the program, all data is stored in file
        writeFile("dataStore.txt", loginInfo);
    } 

    //create user from program
    public static void createUser(Scanner input, HashMap<Person, String> loginInfo){
        String username;
        String age;
        String gender;
        String school;
        String studentNumber;
        String password;
        boolean invalid = false;

        //name input
        do{
            System.out.print("\nEnter Username: ");
            username = input.nextLine();

            invalid = false;

            //existing names not allowed
            for (Person p: loginInfo.keySet()){
                if (p.getName().equals(username)){
                    System.out.println("\nUsername Already Exists. Please Try Again.");
                    invalid = true;
                }
            }
        } while(invalid);

        invalid = true;

        //age input
        do{
            System.out.print("Enter Age: ");
            age = input.nextLine();

            //check if input is int
            try{
                if ((int)(Integer.valueOf(age)) < 0){
                    System.out.println("\nInput Invalid. Please Try Again.\n");
                }
                else{
                    invalid = false;
                }
            } catch (NumberFormatException e){ //catch other inputs
                System.out.println("\nInvalid Input. Try again.\n");
            }
        } while(invalid);

        //gender input
        do{
            final String OPTION_GENDER_MALE = "M";
            final String OPTION_GENDER_FEMALE = "F";

            System.out.print("Enter Gender (M/F): ");
            gender = input.nextLine();

            if (gender.equals(OPTION_GENDER_MALE) || gender.equals(OPTION_GENDER_FEMALE)){
                invalid = false;
            }

            //other inputs not permitted
            else {
                invalid = true;
                System.out.println("\nWrong Format. Try again.\n");
            }
        } while(invalid);

        //school input
        System.out.print("Enter School Name: ");
        school = input.nextLine();

        invalid = true;

        do{
            //student number input
            System.out.print("Enter Student Number: ");
            studentNumber = input.nextLine();

            //student number needs to be above 0 and only be numbers
            try{
                if ((int)(Integer.valueOf(studentNumber)) < 0){
                    System.out.println("\nInput Invalid. Please Try Again.\n");
                }
                else{
                    invalid = false;
                }
            } catch (NumberFormatException e){ //catch inproper input format
                System.out.println("\nInvalid Input. Try again.\n");
            }
        } while(invalid);

        //password input
        System.out.print("Enter Password: ");
        password = input.nextLine();

        //new user is created
        Person newUser = new Student(username, Integer.valueOf(age), gender, school, Integer.valueOf(studentNumber));
        loginInfo.put(newUser, password);
        System.out.println("\nAccount Successfully Created!\n");
    }

    //create user from file
    public static Person createUser(String[] detail, String password, HashMap<Person, String> loginInfo){
        Person newUser = new Student(detail[0], Integer.valueOf(detail[1]), detail[2], detail[3], Integer.valueOf(detail[4]));
        loginInfo.put(newUser, password);

        return newUser;
    }

    //create event in program
    public static Event createEvent(ArrayList<String> eventDetails){
        Event event = new Event();

        //general event
        if (eventDetails.size() == 6){
            event = new Event(eventDetails.get(0), Integer.valueOf(eventDetails.get(1)), Integer.valueOf(eventDetails.get(2)), Integer.valueOf(eventDetails.get(3)), eventDetails.get(4), eventDetails.get(5));
        }

        //assesment event
        else if (eventDetails.size() == 9){
            event = new Assesment(eventDetails.get(0), Integer.valueOf(eventDetails.get(1)), Integer.valueOf(eventDetails.get(2)), Integer.valueOf(eventDetails.get(3)), eventDetails.get(4), eventDetails.get(5), Integer.valueOf(eventDetails.get(6)), eventDetails.get(7), eventDetails.get(8));
        }

        //extracurricular event
        else if (eventDetails.size() == 7){
            event = new Extracurricular(eventDetails.get(0), Integer.valueOf(eventDetails.get(1)), Integer.valueOf(eventDetails.get(2)), Integer.valueOf(eventDetails.get(3)), eventDetails.get(4), eventDetails.get(5), eventDetails.get(6));
        }

        return event;
    }

    //create event from file
    public static Event createEvent(String[] eventDetails){
        Event event = new Event();

        //general event
        if (eventDetails.length == 6){
            event = new Event(eventDetails[0], Integer.valueOf(eventDetails[1]), Integer.valueOf(eventDetails[2]), Integer.valueOf(eventDetails[3]), eventDetails[4], eventDetails[5]);
        }

        //assesment event
        else if (eventDetails.length == 9){
            event = new Assesment(eventDetails[0], Integer.valueOf(eventDetails[1]), Integer.valueOf(eventDetails[2]), Integer.valueOf(eventDetails[3]), eventDetails[4], eventDetails[5], Integer.valueOf(eventDetails[6]), eventDetails[7], eventDetails[8]);
        }

        //extracurricular event
        else if (eventDetails.length == 7){
            event = new Extracurricular(eventDetails[0], Integer.valueOf(eventDetails[1]), Integer.valueOf(eventDetails[2]), Integer.valueOf(eventDetails[3]), eventDetails[4], eventDetails[5], eventDetails[6]);
        }

        return event;
    }

    //write program data to file
    public static void writeFile(String fileName, HashMap<Person, String> loginInfo) throws IOException{
        File file = new File(fileName);
        FileWriter output = new FileWriter(file, false);
        ArrayList<Event> eventList = new ArrayList<Event>(0);

        //gets users from map
        for (Person user: loginInfo.keySet()){
            output.write("-" + "\n");

            //writes user info and password
            output.write(((Student)user).toString() + "\n");
            output.write("" + loginInfo.get(user) + "\n");

            //writes all events pertaining to user
            eventList = user.getCalendar().listEvents();
            for (Event element: eventList){
                output.write(element.toFile() + "\n");
            }
        }

        output.close();
    }

    //read program data from file
    public static void readFile(String fileName, HashMap<Person, String> loginInfo) throws IOException{
        File file = new File(fileName);
        Person tempUser = new Student();
        Scanner input = new Scanner(file);
        String line;
        String[] detail = new String[9];
        int i = 0;

        while (input.hasNext()){
            line = input.nextLine();

            //read user info - create user
            if (line.equals("-")){
                i++;
                tempUser = createUser(input.nextLine().split(", "), input.nextLine(), loginInfo);
            }

            //read event info - create event
            else {
                detail = line.split(", ");
                tempUser.getCalendar().addEvent(createEvent(detail));
            }
        }

        input.close();
    }
}