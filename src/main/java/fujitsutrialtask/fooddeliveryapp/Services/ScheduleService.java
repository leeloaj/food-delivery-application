package fujitsutrialtask.fooddeliveryapp.Services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ScheduleService {
    @Scheduled(cron = "${cron.job}")
    private void scheduleWithFixedRate() {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
