package fujitsutrialtask.fooddeliveryapp.Services;

import fujitsutrialtask.fooddeliveryapp.Models.WeatherInfo;
import fujitsutrialtask.fooddeliveryapp.Repositories.WeatherInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    private final WeatherInfoRepository weatherInfoRepository;

    @Autowired
    public PriceService(WeatherInfoRepository weatherInfoRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
    }
    public double CalculatePrice(String city, String vehicle) {
        List<WeatherInfo> weatherInfo = weatherInfoRepository.findAll();

        System.out.println(city);
        System.out.println(vehicle);


        return 11.11;


    }

    public List<WeatherInfo> GetWeatherInfo() {
        return weatherInfoRepository.findAll();
    }
}
