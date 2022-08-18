import org.example.Courier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SweatTreatsTest {

    private Courier courier;
    private  DateTimeFormatter formatter;

    @BeforeEach
    void setup(){
        courier = new Courier("eli","09:00","14:00",4,true,1.5);
        formatter = DateTimeFormatter.ofPattern("HH:mm");
    }
    @Test
    public void courierGetterTests (){

       Assertions.assertEquals( "eli",courier.getName());
       Assertions.assertEquals( LocalTime.parse("09:00",formatter), courier.getStartTime());
       Assertions.assertEquals( LocalTime.parse("14:00",formatter), courier.getEndTime());
       Assertions.assertEquals( 4,courier.getMaxMileage());
       Assertions.assertTrue(courier.hasRefrigeratedBox());
       Assertions.assertEquals( 1.5,courier.getChargePerMile());

    }
    @Test
    public void  setNameTest (){
        courier.setName("eli");
        Assertions.assertEquals( "eli",courier.getName());
    }
    @Test
    public void  setStartTimeTest (){
        courier.setStartTime("09:00");
        Assertions.assertEquals( LocalTime.parse("09:00",formatter),courier.getStartTime());
    }
    @Test
    public void setEndTimeTest(){
        courier.setEndTime("14:00");
        Assertions.assertEquals( LocalTime.parse("14:00",formatter), courier.getEndTime());
    }
    @Test
    public void maxMileageTest(){
        courier.setMaxMileage(5);
        Assertions.assertEquals(5,courier.getMaxMileage());
    }
    @Test
    public void hasRefrigeratedBoxTest(){
        courier.setHasRefrigeratedBox(false);
        Assertions.assertFalse(courier.hasRefrigeratedBox());
    }
    @Test
    public void chargePerMileageTest(){
        courier.setChargePerMile(2);
        Assertions.assertEquals(2,courier.getChargePerMile());
    }
    @Test
    public void isAvailableTest(){
        courier.setAvailable(true);
        Assertions.assertTrue(courier.getAvailable());
    }
}
