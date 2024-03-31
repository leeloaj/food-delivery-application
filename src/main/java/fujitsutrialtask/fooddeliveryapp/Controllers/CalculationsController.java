package fujitsutrialtask.fooddeliveryapp.Controllers;

import fujitsutrialtask.fooddeliveryapp.Enums.City;
import fujitsutrialtask.fooddeliveryapp.Enums.Vehicle;
import fujitsutrialtask.fooddeliveryapp.Models.WeatherInfo;
import fujitsutrialtask.fooddeliveryapp.Services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class CalculationsController {

    private final PriceService priceService;

    @Autowired
    public CalculationsController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping(path = "price")
    public double getPrice(
            @RequestParam(value = "city", required = false, defaultValue = "Tallinn") City city,
            @RequestParam(value = "vehicle", required = false, defaultValue = "Car") Vehicle vehicle) {
        return priceService.calculatePrice(city, vehicle);
    }

    @GetMapping(path = "weather")
    public List<WeatherInfo> getWeatherInfo() {
        return priceService.GetWeatherInfo();
    }
}
