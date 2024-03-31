package fujitsutrialtask.fooddeliveryapp.Controllers;

import fujitsutrialtask.fooddeliveryapp.Exception.DeliveryException;
import fujitsutrialtask.fooddeliveryapp.Models.WeatherInfo;
import fujitsutrialtask.fooddeliveryapp.Repositories.WeatherInfoRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculationsControllerTest {
    @Autowired
    private CalculationsController controller;

    @Resource
    private WeatherInfoRepository weatherInfoRepository;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @BeforeEach
    public void setUp() {
        List<WeatherInfo> infos = Arrays.asList(
                new WeatherInfo(1L, "Tallinn-Harku", "26038", 10.0, 10.0, "Snow", LocalDateTime.now().plusYears(1)),
                new WeatherInfo(2L, "Tartu-Tõravere", "26242", 15.5, 2.7, "Rain", LocalDateTime.now().plusYears(1)),
                new WeatherInfo(3L, "Pärnu", "41803", -11, 21, "", LocalDateTime.now().plusYears(1))
        );
        weatherInfoRepository.saveAll(infos);
    }


    @Test
    public void testParnuScooter() {
        double price = controller.getPrice("Pärnu", "Scooter");

        assertEquals(3.5, price);
    }

    @Test
    public void testParnuBike() {
        DeliveryException exception = assertThrows(DeliveryException.class, () -> {
            controller.getPrice("Pärnu", "Bike");
        });
        assertEquals(DeliveryException.Reason.UNSUITABLE_CONDITIONS_FOR_THIS_VEHICLE_TYPE, exception.getReason());
    }
    @Test
    public void testTartuCar() {
        double price = controller.getPrice("Tartu", "Car");

        assertEquals(3.5, price);
    }

    @Test
    public void testTartuBike() {
        double price = controller.getPrice("Tartu", "Bike");

        assertEquals(3.0, price);
    }

    @Test
    public void testTallinnScooterSnow() {
        double price = controller.getPrice("Tallinn", "Scooter");

        assertEquals(4.5, price);
    }

    @Test
    public void testTallinnCarSnow() {
        double price = controller.getPrice("Tallinn", "Car");

        assertEquals(4, price);
    }
}