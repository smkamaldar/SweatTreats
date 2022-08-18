package org.example;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


// we told Lombok to generate these for all the fields of the class.

@Getter @Setter
public class Courier {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private int maxMileage;
    private boolean hasRefrigeratedBox;
    private double chargePerMile;
    private boolean isAvailable;

//    Use LocalTime.format(DateTimeFormatter) method to format a local time to the desired string representation
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

     public Courier(String name, String startTime, String endTime, int maxMileage, boolean hasRefrigeratedBox, double chargePerMile){
        this.name = name;
        setStartTime(startTime);
        setEndTime(endTime);
        this.maxMileage = maxMileage;
        this.hasRefrigeratedBox = hasRefrigeratedBox;
        this.chargePerMile = chargePerMile;
        isAvailable = true;

    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setStartTime(String startTime){
        try{
            this.startTime = LocalTime.parse(startTime,formatter);
        }catch(Exception e){
            System.out.println(e+"wrong format");
        }
    }
    public void setEndTime(String endTime){
        this.endTime = LocalTime.parse(endTime,formatter);
    }
    public LocalTime getStartTime(){
        return startTime;
    }
    public LocalTime getEndTime(){
        return endTime;
    }
    public void setAvailable(Boolean state){
        isAvailable = state;
    }
    public boolean getAvailable(){
        return isAvailable;
    }
    public boolean hasRefrigeratedBox(){
        return hasRefrigeratedBox;
    }
    public double getChargePerMile(){
         return chargePerMile;
    }


}
