package fujitsutrialtask.fooddeliveryapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {

    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    /**
     * Station name.
     */
    @JacksonXmlProperty(localName = "name")
    private String station;

    /**
     * Station WMO Code.
     */
    @JacksonXmlProperty(localName = "wmocode")
    private String wmoCode;

    /**
     * Air temperature.
     */
    @JacksonXmlProperty(localName = "airtemperature")
    private double airTemperature;

    /**
     * Wind speed.
     */
    @JacksonXmlProperty(localName = "windspeed")
    private double windSpeed;

    /**
     * Phenomenon.
     */
    @JacksonXmlProperty(localName = "phenomenon")
    private String phenomenon;

    /**
     * Time which current weather was saved.
     */
    private LocalDateTime timestamp;
}
