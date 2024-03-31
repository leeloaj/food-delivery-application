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
    public double CalculatePrice() {
        List<WeatherInfo> weatherInfo = weatherInfoRepository.findAll();
        return 111.11111;
    }

    public List<WeatherInfo> GetWeatherInfo() {
        return weatherInfoRepository.findAll();
    }
}
