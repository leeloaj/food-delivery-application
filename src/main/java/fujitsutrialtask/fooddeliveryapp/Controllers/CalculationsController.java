package fujitsutrialtask.fooddeliveryapp.Controllers;

import fujitsutrialtask.fooddeliveryapp.Models.WeatherInfo;
import fujitsutrialtask.fooddeliveryapp.Services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public double GetPrice() {
        return priceService.CalculatePrice();
    }

    @GetMapping(path = "weather")
    public List<WeatherInfo> GetWeatherInfo() {
        return priceService.GetWeatherInfo();
    }
}
