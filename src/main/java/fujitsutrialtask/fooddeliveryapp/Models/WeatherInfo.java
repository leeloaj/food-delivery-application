package fujitsutrialtask.fooddeliveryapp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String station;
    private String wmoCode;
    private double airTemperature;
    private double windSpeed;
    private String phenomenon;
    private LocalDate timestamp;
}
