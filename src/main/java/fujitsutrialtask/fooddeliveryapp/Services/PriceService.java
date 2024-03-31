package fujitsutrialtask.fooddeliveryapp.Services;

import fujitsutrialtask.fooddeliveryapp.Exception.DeliveryException;
import fujitsutrialtask.fooddeliveryapp.Models.WeatherInfo;
import fujitsutrialtask.fooddeliveryapp.Repositories.WeatherInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PriceService {

    private final WeatherInfoRepository weatherInfoRepository;

    @Autowired
    public PriceService(WeatherInfoRepository weatherInfoRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
    }
    public double CalculatePrice(String city, String vehicle) {
        List<WeatherInfo> weatherInfos = weatherInfoRepository.findAll();
        WeatherInfo weatherInfo = weatherInfos
                .stream()
                .filter(info -> info.getStation().contains(city))
                .sorted(Comparator.comparing(WeatherInfo::getTimestamp).reversed())
                .toList()
                .get(0);

        System.out.println(weatherInfo);

        double totalPrice = 0.0;
        switch (city) {
            case "Tallinn" -> {
                switch (vehicle) {
                    case "Car" -> totalPrice += 4;
                    case "Scooter" -> totalPrice += 3.5 + getATEF(weatherInfo.getAirTemperature())
                            + getWPEF(weatherInfo.getPhenomenon());
                    case "Bike" -> totalPrice += 3 + getATEF(weatherInfo.getAirTemperature())
                            + getWSEF(weatherInfo.getWindSpeed())
                            + getWPEF(weatherInfo.getPhenomenon());
                }
            }
            case "Tartu" -> {
                switch (vehicle) {
                    case "Car" -> totalPrice += 3.5;
                    case "Scooter" -> totalPrice += 3 + getATEF(weatherInfo.getAirTemperature())
                            + getWPEF(weatherInfo.getPhenomenon());
                    case "Bike" -> totalPrice += 2.5 + getATEF(weatherInfo.getAirTemperature())
                            + getWSEF(weatherInfo.getWindSpeed())
                            + getWPEF(weatherInfo.getPhenomenon());
                }
            }
            case "Pärnu" -> {
                switch (vehicle) {
                    case "Car" -> totalPrice += 3;
                    case "Scooter" -> totalPrice += 2.5 + getATEF(weatherInfo.getAirTemperature())
                            + getWPEF(weatherInfo.getPhenomenon());
                    case "Bike" -> totalPrice += 2 + getATEF(weatherInfo.getAirTemperature())
                            + getWSEF(weatherInfo.getWindSpeed())
                            + getWPEF(weatherInfo.getPhenomenon());
                }
            }
        }


        return totalPrice;
    }

    private double getATEF(double airTemperature) {
        double atef = 0.0;
        if (airTemperature < -10) {
            atef += 1;
        } else if (airTemperature >= -10 && airTemperature <= 0) {
            atef += 0.5;
        }
        return atef;
    }

    private double getWSEF(double windSpeed) {
        double wsef = 0.0;
        if (windSpeed >= 10 && windSpeed <= 20) {
            wsef += 0.5;
        } else if (windSpeed > 20) {
            throw new DeliveryException("Usage of selected vehicle type is forbidden",
                    DeliveryException.Reason.UNSUITABLE_CONDITIONS_FOR_THIS_VEHICLE_TYPE);
        }
        return wsef;
    }

    private double getWPEF(String  phenomenon) {
        double wpef = 0.0;
        if (phenomenon.toLowerCase().matches(".*(snow|sleet).*")) {
            wpef += 1;
        } else if (phenomenon.toLowerCase().matches(".*(rain).*")) {
            wpef += 0.5;
        } else if (phenomenon.toLowerCase().matches(".*(hail|glaze|thunder).*")) {
            throw new DeliveryException("“Usage of selected vehicle type is forbidden",
                    DeliveryException.Reason.UNSUITABLE_CONDITIONS_FOR_THIS_VEHICLE_TYPE);
        }
        return wpef;
    }

    public List<WeatherInfo> GetWeatherInfo() {
        return weatherInfoRepository.findAll();
    }
}
