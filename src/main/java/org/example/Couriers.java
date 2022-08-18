package org.example;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class Couriers {
    private static final Logger LOGGER = Logger.getLogger(Couriers.class.getName());

    static{
        FileHandler fileHandler = null;
        try {
             fileHandler = new FileHandler(Couriers.class.getSimpleName() + ".log");
             fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.addHandler(fileHandler);
    }

    private Courier courier;
    private List<Courier> couriers = new ArrayList<Courier>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");


    public void addCourier(Courier courier) {
        couriers.add(courier);
    }

    public int countOfCouriers() {
        return couriers.size();
    }

    public List<Courier> availableCouriers() {

        return couriers.stream().filter(courier1 -> courier1.getAvailable()).collect(Collectors.toList());

    }

    public Courier book(String time, int mileage, boolean needRefrigrator) throws Exception {
        Courier cheapest = null;
        LocalTime requestedTime = LocalTime.parse(time, formatter);

        var filter = availableCouriers().stream()
                        .filter(courier -> courier.getEndTime().isAfter(requestedTime)
                        && courier.getStartTime().isBefore(requestedTime)
                        && courier.getMaxMileage() >= mileage
                        && courier.hasRefrigeratedBox() == needRefrigrator)
                .collect(Collectors.toList());

        if (!filter.isEmpty()) {
//            given the courier and compare their chargepermile
//            static function
//            comparator contains  a list of objects
            Comparator<Courier> comparator = Comparator.comparing(Courier::getChargePerMile);
            cheapest= filter.stream().min(comparator).get();
            cheapest.setAvailable(false);
            LOGGER.log(Level.INFO,"the cheapest courier "
                    + cheapest.getName() + " with amount of "
                    + cheapest.getChargePerMile() + " has been selected");
            System.out.println(cheapest.getName()+ " has been booked for you!");
        }else{
            LOGGER.log(Level.WARNING,"\n\t no one available for you at "+ time);
            System.out.println("no one is available at the moment");
        }
        return cheapest ;
    }
}
