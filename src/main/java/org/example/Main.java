package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) throws Exception {

            Couriers couriers = new Couriers();
            couriers.addCourier( new Courier("bobby","09:00","13:00",5,true,1.75));
            couriers.addCourier( new Courier("Martin","09:00","17:00",3,false,1.50));
            couriers.addCourier( new Courier("Geoff","10:00","16:00",4,true,2));
//
//        System.out.println("avaliable courier " +  couriers.book("10:05",4, true) );
        while (true) {
            System.out.println("Sweat Treats Store");
            String isUser = getUserInput("Are you a user or admin?");
            if (isUser.equals("admin")) {
                String newCourier = getUserInput("Do you want to add a new courier? (Y/N)");
                while (newCourier.equals("Y")) {
                    System.out.println("Please provide us the new courier's details.");
                    String name = getUserInput("name: ");
                    String startTime = getUserInput("Start time (hh:mm): ");
                    String endTime = getUserInput("End time (hh:mm): ");
                    int maxMileage = Integer.parseInt(getUserInput("maximum mileage : "));
                    double chargePerMile = Double.parseDouble(getUserInput("charge per mile : "));
                    String hasRef = getUserInput("does it have refrigerator box? (Y/N)");
//
                    Courier courier = new Courier(name, startTime, endTime, maxMileage, hasRef.equals("Y"), chargePerMile);
                    couriers.addCourier(courier);
                    newCourier = getUserInput("Do you want to add a new courier? (Y/N)");
                }

            }else {
//                String time =getUserInput("what time do you expect you order? (HH:MM)");
               String time = getDateFromUser();
                int mileage = Integer.parseInt(getUserInput("How far is your delivery point?"));
                String hasRef = getUserInput("Does you item need a refrigerator box or not?(Y/N)");
                    couriers.book(time,mileage,hasRef.equals("Y"));
            }
        }
    }
    public static String getDateFromUser(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        boolean check = true;
        LocalTime requestedTime = null;

        while(check){
            String time = getUserInput("what time do you expect you order? (HH:MM)");
            try{
                 requestedTime = LocalTime.parse(time, formatter);
                    check = false;
                    return requestedTime.toString();
                }
            catch(Exception e) {
                System.out.println("invalid format !!! \n" + getUserInput("what time do you expect you order? (HH:MM)"));
            }
            }
   return requestedTime.toString();
    }

    public static String getUserInput(String message) {
        System.out.println();
        System.out.print(message);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}