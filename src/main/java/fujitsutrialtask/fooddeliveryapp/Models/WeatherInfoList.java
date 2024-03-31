package fujitsutrialtask.fooddeliveryapp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfoList {
    @JacksonXmlProperty(localName = "station")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<WeatherInfo> weatherInfos;

    public List<WeatherInfo> getWeatherInfos() {
        return weatherInfos;
    }

    public void setWeatherInfos(List<WeatherInfo> weatherInfos) {
        this.weatherInfos = weatherInfos;
    }
}
