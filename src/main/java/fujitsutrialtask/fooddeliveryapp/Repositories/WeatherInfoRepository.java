package fujitsutrialtask.fooddeliveryapp.Repositories;

import fujitsutrialtask.fooddeliveryapp.Models.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to handle WeatherInfo related queries.
 */
@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {
}
