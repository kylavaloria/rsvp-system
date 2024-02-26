
package com.app.rsvp;

import com.attendance_system.DbConnection;
import java.util.Scanner;

public class Function extends DbConnection{
    public void logo(){
        String logo =
                "╔═══════════════════════════════════════════════════════════════╗ \n" +
                "║                   .o.           .       .                   .o8  oooooooooooo                                ║\n" +
                "║      .888.        .o8     .o8                               888  `888'     `8                                ║\n" +
                "║     .8''888.     .o888oo .o888oo  .ooooo.  ooo. .oo.    .oooo888   888          .oooo.    .oooo.   .ooooo.   ║\n" +
                "║    .8' `888.      888     888   d88' `88b `888PoY88b  d88' `888   888oooo8    `P  )88b  d88(   8 d88' `88b   ║\n" +
                "║   .88ooo8888.     888     888   888ooo888  888   888  888   888   888    o     .oP8888  ` Y88b.  888ooo888   ║\n" +
                "║  .8'     `888.    888 .   888 . 888    .o  888   888  888   888   888       o d8(  888  o.  )88b 888    .o   ║\n" +
                "║ o88o     o8888o   o888'   o888' `Y8bod8P' o888o o888o `Y8bod88P  o888ooooood8 `Y888\"8o 8\"\"888P' `Y8bod8P'    ║\n" +
                "╚═══════════════════════════════════════════════════════════════╝ \n";                                                                                   

        String[] lines = logo.split("\n");

        for (String line : lines) {
            System.out.println(line);
        }
    }
    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        try{
        System.out.println("");
        logo();
        //System.out.println("AttendEase: Optimized Attendance Confirmation System");
        System.out.println("");
        System.out.println("╔══███████══╗");
        System.out.println("║ Select an option  ║");
        System.out.println("║   1. Organizer    ║");
        System.out.println("║   2. Attendee     ║");
        System.out.println("║   3. Exit         ║");
        System.out.println("╚═══════════╝");
        System.out.println("\nPlease Input your choice:");
        
        int choice = scanner.nextInt();
            
            switch(choice){
                case 1: 
                    organizerUI();
                    break;
                case 2:
                    attendeeUI();
                    break;
                case 3:
                    System.out.println("Program Exited");
                    System.exit(0);
                default:
                    System.out.println("Invalid Input, try again.");
                    mainMenu();
            }
        }
        catch (Exception e) {
            System.out.println("There is exception intercepted: "+e);
            System.out.println("Try again.");
            mainMenu();
        }
        
    }
    public void organizerUI() {
        Scanner scanner = new Scanner(System.in);
        
        try{
            System.out.println("╔══████████══╗");
            System.out.println("║ Select an option    ║");
            System.out.println("║ 1. Add Event        ║");
            System.out.println("║ 2. Select Event     ║");
            System.out.println("║ 3. Update Event     ║");
            System.out.println("║ 4. Archive Event    ║");
            System.out.println("║ 5. Unarchive Event  ║");
            System.out.println("║ 6. Delete Event     ║");
            System.out.println("║ 7. Main Menu        ║");
            System.out.println("╚════════════╝");
            System.out.println("╔═════════════════════╗");
            System.out.print("█ Please Input your choice:\t");
            System.out.println("");
            System.out.println("╚═════════════════════╝");
           
           int choice = scanner.nextInt();
            
            switch(choice){
                case 1: 
                    addEvent();
                    break;
                case 2:
                    int selectedEvent = selectEvents();
                    afterSelecting(selectedEvent);
                    break;
                case 3:
                    printAllEvents();
                    updateEvent();
                    break;
                case 4:
                    archiveEvent();
                    break;
                case 5:
                    restoreEvent();
                    break;
                case 6:
                    selectedEvent = selectEvents();
                    deleteEvent(selectedEvent);
                    break;
                case 7:
                    mainMenu();
                    System.exit(0);
                default:
                    System.out.println("Invalid Input, try again.");
                    organizerUI();
            }
        }
        catch (Exception e) {
            System.out.println("There is exception intercepted: "+e);
            System.out.println("Try again.");
            organizerUI();
        }
        
    }

    public void attendeeUI() {
        Scanner scanner = new Scanner(System.in);
        
        try{
            System.out.println(" ____________________");
            System.out.println("| Select an option   |");
            System.out.println("| 1. Select Event    |");
            System.out.println("| 2. Update my info  |");
            System.out.println("| 3. Main Menu       |");
            System.out.println("|____________________|");
            System.out.println("\nPlease Input your choice:");
           
           int choice = scanner.nextInt();
            
            switch(choice){
                case 1: 
                    //selectEvent();
                    printAllAvailableEvents();
                    addAttendee();
                    break;
                case 2:
                    int selectedEvent = selectEventsForAttendee();
                    printAttendees(selectedEvent);
                    updateInfo(selectedEvent);
                    break;
                case 3:
                mainMenu();
                System.exit(0);
                default:
                    System.out.println("Invalid Input, try again.");
                    attendeeUI();
            }
        }
        catch (Exception e) {
            System.out.println("There is exception intercepted: "+e);
            System.out.println("Try again.");
            attendeeUI();
        }
        
    }

    public void addEvent() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(" __________________");
        System.out.println("|   CREATE EVENT   |");
        System.out.println("|------------------|");
        System.out.print("Name: ");
        String eventName = scanner.nextLine();
        System.out.print("Date: ");
        String eventDate  = scanner.nextLine();
        System.out.print("Time: ");
        String eventTime = scanner.nextLine();
        System.out.print("Location: ");
        String eventLocation = scanner.nextLine();
        System.out.print("Slots Available: ");
        int eventSlots = scanner.nextInt();
        
        String query = "INSERT INTO event (event_name, date, time, location, slots_available, status) VALUES (?, ?, ?, ?, ?, 1)";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setString(1, eventName);
            prep.setString(2, eventDate);
            prep.setString(3, eventTime);
            prep.setString(4, eventLocation);
            prep.setInt(5, eventSlots);

                int rowsAffected = prep.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Event added successfully!");
                } else {
                    System.out.println("Failed to add the event.");
                }
                con.close();
            }
        catch (Exception e) {
            e.printStackTrace();
        }
        organizerUI();
    }
    
    public void addAttendee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("NEW ATTENDEE");
        System.out.println(" ");
        System.out.println("Enter the event ID to register to:");
        int eventID = scanner.nextInt();
        slotChecker(eventID);

    }
    public void printAllEvents(){
       String selectEventsSql = "SELECT * FROM event WHERE status = 1";
    try {
        connect();
        state = con.createStatement();
        result = state.executeQuery(selectEventsSql);

        System.out.printf("\n\n%-10s%-20s%-20s%-20s%-20s%-10s%n", "Event ID", "Event Name", "Event Date", "Event Time", "Event Location", "Slots");

        while (result.next()) {
            int eventID = result.getInt("event_id");
            String eventName = result.getString("event_name");
            String eventDate = result.getString("date");
            String eventTime = result.getString("time");
            String eventLocation = result.getString("location");
            int slots = result.getInt("slots_available");
            
            System.out.printf("%-10d%-20s%-20s%-20s%-20s%-10d%n", eventID, eventName, eventDate, eventTime, eventLocation, slots);
        }
        con.close();
        }   
        catch (Exception e) {
        System.out.println(e);
        }
    }
    
    public void printAllArchivedEvents(){
       String selectEventsSql = "SELECT * FROM event WHERE status = 0";
    try {
        connect();
        state = con.createStatement();
        result = state.executeQuery(selectEventsSql);
      
        System.out.printf("\n\n%-10s%-20s%-20s%-20s%-20s%-10s%n", "Event ID", "Event Name", "Event Date", "Event Time", "Event Location", "Slots");

        while (result.next()) {
            int eventID = result.getInt("event_id");
            String eventName = result.getString("event_name");
            String eventDate = result.getString("date");
            String eventTime = result.getString("time");
            String eventLocation = result.getString("location");
            int slots = result.getInt("slots_available");
            
            System.out.printf("%-10d%-20s%-20s%-20s%-20s%-10d%n", eventID, eventName, eventDate, eventTime, eventLocation, slots);
        }
        con.close();
        }   
        catch (Exception e) {
        System.out.println(e);
        }
    }
    
    public int selectEvents() { //for organizer
        Scanner scanner = new Scanner (System.in);
        printAllEvents();
        
        System.out.println("Select an Event ID: ");
        int eventID = scanner.nextInt();

        int selectedEventID = getEventById(eventID);

        if (selectedEventID != -1) {
            System.out.println("Selected Event ID: " + selectedEventID);
        } else {
            System.out.println("Event not found. Please try again.");
            selectedEventID = selectEvents();
        }
        return selectedEventID;
    }
    
    public int selectEventsForAttendee() { //for attendee
        Scanner scanner = new Scanner (System.in);
        printAllEvents();
        
        System.out.println("Select the Event ID you are registered in: ");
        int eventID = scanner.nextInt();

        int selectedEventID = getEventById(eventID);

        if (selectedEventID != -1) {
            System.out.println("Selected Event ID: " + selectedEventID);
        } else {
            System.out.println("Event not found. Please try again.");
            selectedEventID = selectEvents();
        }
        return selectedEventID;
    }
    
    public int getEventById(int selectedEvent){
        String selectEventNameSql = "SELECT event_id FROM event WHERE event_id = ?";
        
        try {
            connect();
            prep = con.prepareStatement(selectEventNameSql);
            prep.setInt(1, selectedEvent);
            result = prep.executeQuery();
            
            return result.next() ? result.getInt("event_id") : -1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void afterSelecting(int selectedEvent) { //for organizer
        Scanner scanner = new Scanner(System.in);
        try{
        System.out.println(" _________________________");
        System.out.println("| Select an option        |");
        System.out.println("| 1. Add Attendee         |");
        System.out.println("| 2. Cancel Attendee      |");
        System.out.println("| 3. Search Attendee      |");
        System.out.println("| 4. Print All Attendees  |");
        System.out.println("| 5. Update Attendee Info |");
        System.out.println("| 6. Main Menu            |");
        System.out.println("|_________________________|");
        System.out.println("");
                     
        int choice2 = scanner.nextInt();
                     
        switch(choice2){
            case 1: 
                addingAttendee(selectedEvent);
                break;
            case 2:
                cancelAttendee(selectedEvent);
                break;
            case 3:
                searchAttendee(selectedEvent);
                break;
            case 4:
                printAttendees(selectedEvent);
                break;
            case 5:
                printAttendees(selectedEvent);
                updateAttendee(selectedEvent);
                break;
            case 6:
                mainMenu();
                break;
            default:
                System.out.println("Invalid Input, Try Again.");
                 afterSelecting(selectedEvent);
        }
       }
        catch (Exception e) {
            System.out.println("There is exception intercepted: "+e);
            System.out.println("Try again.");
            attendeeUI();
        }
    }

    public void deleteEvent(int selectedEvent) {
    String deleteWaitlistSql = "DELETE FROM waitlist WHERE event_name = ?";
    String deleteAttendeesSql = "DELETE FROM attendees WHERE event_id = ?";
    String deleteEventSql = "DELETE FROM event WHERE event_id = ?";
    
    try {
        connect();
        prep = con.prepareStatement(deleteWaitlistSql);
        prep.setInt(1, selectedEvent);
        int waitlistEntriesDeleted = prep.executeUpdate();
        
        prep = con.prepareStatement(deleteAttendeesSql);
        prep.setInt(1, selectedEvent);
        int attendeesDeleted = prep.executeUpdate();
        
        prep = con.prepareStatement(deleteEventSql);
        prep.setInt(1, selectedEvent);
        int rowsAffected = prep.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Event deleted successfully!");
        } else {
            System.out.println("Failed to delete event. Event ID not found.");
        }
        
    } catch (Exception e) {
        System.out.println(e);
    }
    organizerUI();
}
    
    public void archiveEvent (){
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Archive Event");
        System.out.println(" ");
        printAllEvents();
        System.out.println("Enter event ID: ");
        int choice = scanner.nextInt();
        
        String archiveEventQuery = "UPDATE event SET status = '0' WHERE event_id = ?";
        
        try {
        connect();
        state = con.createStatement();
        prep = con.prepareStatement(archiveEventQuery);
        prep.setInt(1, choice);
        int rowsAffected = prep.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Event archived successfully!");
        } else {
            System.out.println("Failed to archive event. Event ID not found.");
        }

        con.close();
        }   
        catch (Exception e) {
        System.out.println(e);
        }
        organizerUI();
    }
    
    public void restoreEvent (){
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Unarchive/Restore Event");
        System.out.println(" ");
        printAllArchivedEvents();
        System.out.println("Enter event ID: ");
        int choice = scanner.nextInt();
        
        String unarchiveEventQuery = "UPDATE event SET status = '1' WHERE event_id = ?";
        
        try {
        connect();
        state = con.createStatement();
        prep = con.prepareStatement(unarchiveEventQuery);
        prep.setInt(1, choice);
        int rowsAffected = prep.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Event restored successfully!");
        } else {
            System.out.println("Failed to restore event. Event ID not found.");
        }

        con.close();
        }   
        catch (Exception e) {
        System.out.println(e);
        }
        organizerUI();
    }

    public void addingAttendee(int selectedEvent) {
        int currentAttendees = getNumberOfAttendeesForEvent(selectedEvent);
        int availableSlots = getSlotsAvailableForEvent(selectedEvent);

        if (currentAttendees >= availableSlots) {
            System.out.println("Cannot add more attendees. Event is full.");
            afterSelecting(selectedEvent); 
        } else {
            addAttendee();
        }
    }

    public void cancelAttendee(int selectedEvent) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("CANCEL ATTENDEE");
        System.out.print("Enter Attendee Email Address: ");
        String emailAddress = scanner.nextLine();
        
        int attendeeID = getAttendeeId(selectedEvent, emailAddress);
        
        String deleteAttendeeSql = "DELETE FROM attendees WHERE attendee_id = ?";
        if (attendeeID != -1) {
            try {
                connect();
                prep = con.prepareStatement(deleteAttendeeSql);
                prep.setInt(1, attendeeID);
                
                int rowsAffected = prep.executeUpdate();
                
                if (rowsAffected > 0) {
                    System.out.println("Attendee canceled successfully!");
                } else {
                    System.out.println("Failed to cancel attendee. Attendee ID not found.");
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Attendee not found for the given event and email address.");
        }

        mainMenu();
    }

    public void printAttendees(int selectedEvent) {
    String selectAttendeesSql = "SELECT * FROM attendees WHERE event_id = ?";
    try {
        connect();
        prep = con.prepareStatement(selectAttendeesSql);
        prep.setInt(1, selectedEvent);
        result = prep.executeQuery();

        System.out.printf("%-15s%-20s%-20s%n", "Attendee ID", "First Name", "Last Name");

        while (result.next()) {
            
            int attendeeID = result.getInt("attendee_id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            
            System.out.printf("%-15d%-20s%-20s%n", attendeeID, firstName, lastName);
        }
    } 
    catch (Exception e) {
        }
    }

    public int getNumberOfAttendeesForEvent(int selectedEvent) {
        String countAttendeesSql = "SELECT COUNT(*) FROM attendees WHERE event_id = ?";
        
        try {
            connect();
            result = state.executeQuery(countAttendeesSql);
            prep.setInt(1, selectedEvent);
            result = prep.executeQuery();
            
            if (result.next()) {
                    return result.getInt(1);
                }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getSlotsAvailableForEvent(int selectedEvent) {
        String selectSlotsAvailableSql = "SELECT slots_available FROM event WHERE event_id = ?";
        try {
            connect();
            result = state.executeQuery(selectSlotsAvailableSql);
            prep.setInt(1, selectedEvent);
            result = prep.executeQuery();
            if (result.next()) {
                return result.getInt("slots_available");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void searchAttendee(int selectedEvent) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("SEARCH ATTENDEE");
    System.out.print("Enter Attendee Email Address to search: ");
    String emailAddress = scanner.nextLine();
    
    int attendeeID = getAttendeeIDByEmailAndEvent(emailAddress, selectedEvent);

    if (attendeeID != -1) {
        System.out.printf("%-15s%-20s%-20s%-15s%-20s%-15s%-25s%n", 
            "Attendee ID", "First Name", "Last Name", "Phone Number", "City/Municipality", "Province", "Organization");

        System.out.printf("%-15d%-20s%-20s%-15s%-20s%-15s%-25s%n", 
            attendeeID, 
            getFirstNameForAttendee(attendeeID), 
            getLastNameForAttendee(attendeeID), 
            getPhoneNumberForAttendee(attendeeID), 
            getCityOrMunicipalityForAttendee(attendeeID), 
            getProvinceForAttendee(attendeeID), 
            getOrganizationOrCompanyForAttendee(attendeeID));
    } else {

        System.out.println("Attendee not found for the provided Email Address and selected Event.");
    }
}
    
    public int getAttendeeIDByEmailAndEvent(String emailAddress, int selectedEvent) {
        String selectAttendeeIdSql = "SELECT attendee_id FROM attendees WHERE email_address = ? AND event_id = ?";
        
        try {
            connect();
            prep = con.prepareStatement(selectAttendeeIdSql);
            prep.setString(1, emailAddress);
            prep.setInt(2, selectedEvent);
            result = prep.executeQuery();
            return result.next() ? result.getInt("attendee_id") : -1;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getAttendeeIDByEmail(String emailAddress) {
        String selectAttendeeIdSql = "SELECT attendee_id FROM attendees WHERE email_address = ?";
        
        try {
            connect();
            prep = con.prepareStatement(selectAttendeeIdSql);
            prep.setString(1, emailAddress);
            result = prep.executeQuery();
            return result.next() ? result.getInt("attendee_id") : -1;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public String getFirstNameForAttendee(int attendeeID) {
        String selectFirstNameSql = "SELECT first_name FROM attendees WHERE attendee_id = ?";
        String firstName = "";
        try {
            connect();
            prep = con.prepareStatement(selectFirstNameSql);
            prep.setInt(1, attendeeID);
            result = prep.executeQuery();
            if (result.next()) {
                firstName = result.getString("first_name");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return firstName;
    }

    public String getLastNameForAttendee(int attendeeID) {
        String selectLastNameSql = "SELECT last_name FROM attendees WHERE attendee_id = ?";
        String lastName = "";
        try {
            prep = con.prepareStatement(selectLastNameSql);
            prep.setInt(1, attendeeID);
            result = prep.executeQuery();
            if (result.next()) {
                lastName = result.getString("last_name");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lastName;
    }

    public String getPhoneNumberForAttendee(int attendeeID) {
        String selectPhoneNumberSql = "SELECT phone_number FROM attendees WHERE attendee_id = ?";
        String phoneNumber = "";
        try {
            prep = con.prepareStatement(selectPhoneNumberSql);
            prep.setInt(1, attendeeID);
            result = prep.executeQuery();
            if (result.next()) {
                phoneNumber = result.getString("phone_number");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return phoneNumber;
    }

    public String getCityOrMunicipalityForAttendee(int attendeeID) {
        String selectCityOrMunicipalitySql = "SELECT city FROM attendees WHERE attendee_id = ?";
        String cityOrMunicipality = "";
        try {
            prep = con.prepareStatement(selectCityOrMunicipalitySql);
            prep.setInt(1, attendeeID);
            result = prep.executeQuery();
            if (result.next()) {
                cityOrMunicipality = result.getString("city");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return cityOrMunicipality;
    }

    public String getProvinceForAttendee(int attendeeID) {
        String selectProvinceSql = "SELECT province FROM attendees WHERE attendee_id = ?";
        String province = "";
        try {
            prep = con.prepareStatement(selectProvinceSql);
            prep.setInt(1, attendeeID);
            result = prep.executeQuery();
            if (result.next()) {
                province = result.getString("province");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return province;
    }

    public String getOrganizationOrCompanyForAttendee(int attendeeID) {
        String selectOrganizationSql = "SELECT organization FROM attendees WHERE attendee_id = ?";
        String organization = "";
        try {
            prep = con.prepareStatement(selectOrganizationSql);
            prep.setInt(1, attendeeID);
            result = prep.executeQuery();
            if (result.next()) {
                organization = result.getString("organization");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return organization;
    }

    public void printAllAvailableEvents() {
    String selectAvailableEventsSql = "SELECT * FROM event WHERE slots_available > 0 AND status = 1";
    try {
        connect();
        state = con.createStatement();
        result = state.executeQuery(selectAvailableEventsSql);
        System.out.println("Available Events:");

        System.out.printf("%-10s%-25s%-15s%-15s%-20s%-10s%n", 
            "Event ID", "Event Name", "Event Date", "Event Time", "Event Location", "Slots");

        while (result.next()) {
            int eventID = result.getInt("event_id");
            String eventName = result.getString("event_name");
            String eventDate = result.getString("date");
            String eventTime = result.getString("time");
            String eventLocation = result.getString("location");
            int slots = result.getInt("slots_available");

            System.out.printf("%-10d%-25s%-15s%-15s%-20s%-10d%n", 
                eventID, eventName, eventDate, eventTime, eventLocation, slots);
        }
    } catch (Exception e) {
        System.out.println(e);
    }
}

    public int getAttendeeId(int selectedEvent, String emailAddress) {
        String selectAttendeeIdSql = "SELECT attendee_id FROM attendees WHERE event_id = ? AND email_address = ?";
        try {
            connect();
            prep = con.prepareStatement(selectAttendeeIdSql);
            prep.setInt(1, selectedEvent);
            prep.setString(2, emailAddress);
            result = prep.executeQuery();
            return result.next() ? result.getInt("attendee_id") : -1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public void attendeeMain(int eventID){
        Scanner scanner = new Scanner(System.in);
                System.out.print("First Name: ");
                String firstName= scanner.nextLine();
                System.out.print("Last Name: ");
                String lastName = scanner.nextLine();
                System.out.print("Email Address: ");
                String emailAddress = scanner.nextLine();
                System.out.print("Phone Number: ");
                String phoneNumber = scanner.nextLine();
                System.out.print("City or Municipality: ");
                String city = scanner.nextLine();
                System.out.print("Province: ");
                String province = scanner.nextLine();
                System.out.print("Organization: ");
                String organization = scanner.nextLine();
                String insertAttendeeMain = "INSERT INTO attendees (event_id, first_name, last_name, email_address, phone_number, city, province, organization) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try {
                    connect();
                    prep = con.prepareStatement(insertAttendeeMain);
                    prep.setInt(1, eventID);
                    prep.setString(2, firstName);
                    prep.setString(3, lastName);
                    prep.setString(4, emailAddress);
                    prep.setString(5, phoneNumber);
                    prep.setString(6, city);
                    prep.setString(7, province);
                    prep.setString(8, organization);

                    int rowsAffected = prep.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Attendee added to the database!");
                        mainMenu();
                        } else{
                        System.out.println("Failed to add attendee");
                    }
                }
                    catch (Exception e) {
                            System.out.println(e);
                        } finally {

                }
    }
    public void attendeeWaitlist(int eventID){
        Scanner scanner = new Scanner(System.in);
        System.out.println("This event is full!");
        System.out.println("Do you want to be on the waitlist? [1 for Yes and 0 for No]");
        System.out.println("Disclaimer: Being added on the waitlist does not guarantee a slot for the event. Pahuli-huli ka kase!");
        try{
            byte choice = scanner.nextByte();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email Address: ");
                    String emailAddress = scanner.nextLine();
                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("City or Municipality: ");
                    String city = scanner.nextLine();
                    System.out.print("Province: ");
                    String province = scanner.nextLine();
                    System.out.print("Organization: ");
                    String organization = scanner.nextLine();
                    String insertAttendeeMain = "INSERT INTO waitlist (event_id, first_name, last_name, email_address, phone_number, city, province, organization) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    try {
                        connect();
                        prep = con.prepareStatement(insertAttendeeMain);
                        prep.setInt(1, eventID);
                        prep.setString(2, firstName);
                        prep.setString(3, lastName);
                        prep.setString(4, emailAddress);
                        prep.setString(5, phoneNumber);
                        prep.setString(6, city);
                        prep.setString(7, province);
                        prep.setString(8, organization);
                        
                        int rowsAffected = prep.executeUpdate();
                        
                        if (rowsAffected > 0) {
                            System.out.println("Attendee added to the database!");
                            mainMenu();
                        } else{
                            System.out.println("Failed to add attendee");
                        }
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 0:
                    attendeeUI();
                    break;
                default:
                    System.out.println("Invalid input!");
                    attendeeUI();
                    break;
            }
            }catch(Exception e){
                System.out.println(e);
            }
        
        }
    private void slotChecker(int eventID) {
        
        String checkRegistered = "SELECT COUNT(event_id) AS registered FROM attendees WHERE event_id = ? ";
        String checkAvailable = "SELECT slots_available FROM event WHERE event_id = ?";
        try {
            connect();
            prep = con.prepareStatement(checkRegistered);
            prep.setInt(1, eventID);
            result = prep.executeQuery();
            int registered = 0;
            if(result.next()){
                registered = result.getInt("registered");
            }else{
                System.out.println("Failed to check registered attendees.");
                
            }
            prep = con.prepareStatement(checkAvailable);
            prep.setInt(1, eventID);
            result = prep.executeQuery();
            int available = 0;
            if(result.next()){
                available = result.getInt("slots_available");
            }else{
                System.out.println("Failed to check registered attendees.");
            }
            
            int slot = available - registered;
            if(slot > 0){
                attendeeMain(eventID);
            }else{
                attendeeWaitlist(eventID);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public void updateEvent() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("UPDATE EVENT");

    System.out.print("Enter the Event ID you want to update: ");
    int selectedEvent = scanner.nextInt();
    scanner.nextLine();
    
    String selectEventSql = "SELECT * FROM event WHERE event_id = ?";
    boolean eventExists = false;
    
    try {
        connect();
        prep = con.prepareStatement(selectEventSql);
        prep.setInt(1, selectedEvent);
        result = prep.executeQuery();
        eventExists = result.next();
    } catch (Exception e) {
        System.out.println(e);
        return;
    }

    if (!eventExists) {
        System.out.println("Event not found. Returning to main menu...");
        mainMenu();
        return;
    }

    System.out.println("Select the information you want to update:");
    System.out.println("1. Event Name");
    System.out.println("2. Event Date");
    System.out.println("3. Event Time");
    System.out.println("4. Event Location");
    System.out.println("5. Slots Available");
    System.out.println("6. Cancel (Go back to main menu)");

    int choice = scanner.nextInt();
    scanner.nextLine();

    String columnName = "";
    String newValue = "";

    switch (choice) {
        case 1:
            columnName = "event_name";
            System.out.print("New Event Name: ");
            newValue = scanner.nextLine();
            break;
        case 2:
            columnName = "date";
            System.out.print("New Event Date: ");
            newValue = scanner.nextLine();
            break;
        case 3:
            columnName = "time";
            System.out.print("New Event Time: ");
            newValue = scanner.nextLine();
            break;
        case 4:
            columnName = "location";
            System.out.print("New Event Location: ");
            newValue = scanner.nextLine();
            break;
        case 5:
            columnName = "slots_available";
            System.out.print("New Slots Available: ");
            newValue = scanner.nextLine();
            break;
        case 6:
            System.out.println("Returning to main menu...");
            mainMenu();
            return;
        default:
            System.out.println("Invalid choice. Returning to main menu...");
            mainMenu();
            return;
    }

    String updateEventSql = "UPDATE event SET " + columnName + " = ? WHERE event_id = ?";

    try {
        connect();
        prep = con.prepareStatement(updateEventSql);
        prep.setString(1, newValue);
        prep.setInt(2, selectedEvent);

        int rowsAffected = prep.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Event updated successfully!");
        } else {
            System.out.println("Failed to update event. Event ID not found.");
        }
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
}
    
    public void updateAttendee(int selectedEvent) { //for organizer
    Scanner scanner = new Scanner(System.in);

    System.out.println("UPDATE ATTENDEE INFO");

    System.out.print("Enter the attendee_id you want to update: ");
    int selectedAttendee = scanner.nextInt();
    scanner.nextLine();
    
    String selectEventSql = "SELECT * FROM attendees WHERE attendee_id = ?";
    boolean attendeeExists = false;
    
    try {
        connect();
        prep = con.prepareStatement(selectEventSql);
        prep.setInt(1, selectedAttendee);
        result = prep.executeQuery();
        attendeeExists = result.next();
    } catch (Exception e) {
        System.out.println(e);
        return;
    }
    
    if (!attendeeExists) {
        System.out.println("Event not found. Returning to main menu...");
        mainMenu();
        return;
    }

    System.out.println("Select the information you want to update:");
    System.out.println("1. First Name");
    System.out.println("2. Last Name");
    System.out.println("3. Email Address");
    System.out.println("4. Phone Number");
    System.out.println("5. City");
    System.out.println("6. Province");
    System.out.println("7. Organization");
    System.out.println("8. Cancel (Go back to main menu)");

    int choice = scanner.nextInt();
    scanner.nextLine();

    String columnName = "";
    String newValue = "";

    switch (choice) {
        case 1:
            columnName = "first_name";
            System.out.print("New First Name: ");
            newValue = scanner.nextLine();
            break;
        case 2:
            columnName = "last_name";
            System.out.print("New Last Name: ");
            newValue = scanner.nextLine();
            break;
        case 3:
            columnName = "email_address";
            System.out.print("New Email: ");
            newValue = scanner.nextLine();
            break;
        case 4:
            columnName = "phone_number";
            System.out.print("New phone number: ");
            newValue = scanner.nextLine();
            break;
        case 5:
            columnName = "city";
            System.out.print("New city: ");
            newValue = scanner.nextLine();
            break;
        case 6:
            columnName = "province";
            System.out.print("New province: ");
            newValue = scanner.nextLine();
            break;
        case 7:
            columnName = "organization";
            System.out.print("New organization affiliated: ");
            newValue = scanner.nextLine();
            break;
        case 8:
            System.out.println("Returning to main menu...");
            mainMenu();
            return;
        default:
            System.out.println("Invalid choice. Returning to main menu...");
            mainMenu();
            return;
    }

    String updateEventSql = "UPDATE attendees SET " + columnName + " = ? WHERE attendee_id = ?";

    try {
        connect();
        prep = con.prepareStatement(updateEventSql);
        prep.setString(1, newValue);
        prep.setInt(2, selectedAttendee);

        int rowsAffected = prep.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Attendee updated successfully!");
        } else {
            System.out.println("Failed to update info. Attendee ID not found.");
        }
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
}
    
    public void updateInfo(int selectedEvent) { //for attendee
    Scanner scanner = new Scanner(System.in);

    System.out.println("UPDATE MY INFO");

    System.out.print("Enter your attendee_id: ");
    int selectedAttendee = scanner.nextInt();
    scanner.nextLine();
    
    String selectEventSql = "SELECT * FROM attendees WHERE attendee_id = ?";
    boolean attendeeExists = false;
    
    try {
        connect();
        prep = con.prepareStatement(selectEventSql);
        prep.setInt(1, selectedAttendee);
        result = prep.executeQuery();
        attendeeExists = result.next();
    } catch (Exception e) {
        System.out.println(e);
        return;
    }
    
    if (!attendeeExists) {
        System.out.println("Event not found. Returning to main menu...");
        mainMenu();
        return;
    }

    System.out.println("Select the information you want to update:");
    System.out.println("1. First Name");
    System.out.println("2. Last Name");
    System.out.println("3. Email Address");
    System.out.println("4. Phone Number");
    System.out.println("5. City");
    System.out.println("6. Province");
    System.out.println("7. Organization");
    System.out.println("8. Cancel (Go back to main menu)");

    int choice = scanner.nextInt();
    scanner.nextLine();

    String columnName = "";
    String newValue = "";

    switch (choice) {
        case 1:
            columnName = "first_name";
            System.out.print("New First Name: ");
            newValue = scanner.nextLine();
            break;
        case 2:
            columnName = "last_name";
            System.out.print("New Last Name: ");
            newValue = scanner.nextLine();
            break;
        case 3:
            columnName = "email_address";
            System.out.print("New Email: ");
            newValue = scanner.nextLine();
            break;
        case 4:
            columnName = "phone_number";
            System.out.print("New phone number: ");
            newValue = scanner.nextLine();
            break;
        case 5:
            columnName = "city";
            System.out.print("New city: ");
            newValue = scanner.nextLine();
            break;
        case 6:
            columnName = "province";
            System.out.print("New province: ");
            newValue = scanner.nextLine();
            break;
        case 7:
            columnName = "organization";
            System.out.print("New organization affiliated: ");
            newValue = scanner.nextLine();
            break;
        case 8:
            System.out.println("Returning to main menu...");
            mainMenu();
            return;
        default:
            System.out.println("Invalid choice. Returning to main menu...");
            mainMenu();
            return;
    }

    String updateEventSql = "UPDATE attendees SET " + columnName + " = ? WHERE attendee_id = ?";

    try {
        connect();
        prep = con.prepareStatement(updateEventSql);
        prep.setString(1, newValue);
        prep.setInt(2, selectedAttendee);

        int rowsAffected = prep.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Your information has been updated successfully!");
            attendeeUI();
        } else {
            System.out.println("Failed to update info. Attendee ID not found.");
        }
        con.close();
    } catch (Exception e) {
        System.out.println(" ");
    }
}

    public void waitlist(){
        
    }
}
