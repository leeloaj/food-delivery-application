package fujitsutrialtask.fooddeliveryapp.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fujitsutrialtask.fooddeliveryapp.Models.WeatherInfo;
import fujitsutrialtask.fooddeliveryapp.Models.WeatherInfoList;
import fujitsutrialtask.fooddeliveryapp.Repositories.WeatherInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ScheduleService {

    private final WeatherInfoRepository weatherInfoRepository;

    public ScheduleService(WeatherInfoRepository weatherInfoRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
    }

    @PostConstruct
    public void onStartup() {
        getWeatherInfo();
    }

    @Scheduled(cron = "${cron.job}")
    private void scheduleWeatherInfo() {
        getWeatherInfo();
    }

    private void getWeatherInfo() {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String xml = response.body();
            ObjectMapper xmlMapper = new XmlMapper();
            WeatherInfoList weatherInfos = xmlMapper.readValue(xml, WeatherInfoList.class);

            List<String> allowed = Arrays.asList("Tallinn-Harku", "Tartu-Tõravere", "Pärnu");
            List<WeatherInfo> allowedStations = weatherInfos
                    .getWeatherInfos()
                    .stream()
                    .filter(weatherInfo -> allowed.contains(weatherInfo.getStation()))
                    .toList();

            LocalDateTime now = LocalDateTime.now();
            for (WeatherInfo weatherInfo : allowedStations) {
                weatherInfo.setTimestamp(now);
            }
            weatherInfoRepository.saveAll(allowedStations);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
