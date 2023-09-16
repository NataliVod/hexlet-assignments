package exercise.controller;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> weatherInCity(@PathVariable Long id) {
      City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("not found"));
      return weatherService.getWeather(city.getName());
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> AllCities(@RequestParam (value = "name", required = false) String name) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        Iterable<City> cities;

        if (name == null) {
        cities = cityRepository.findAllByOrderByNameAsc();
        }
        else cities = cityRepository.findAllByNameIgnoreCaseStartingWith(name);

        for (var city : cities) {
            String cityName = city.getName();
            Map<String, String> weather = weatherService.getWeather(cityName);
            Map<String, String> temperatureInCities = new HashMap<>();

            temperatureInCities.put("name", cityName);
            temperatureInCities.put("temperature", weather.get("temperature"));

            result.add(temperatureInCities);
        }
        return result;
    }

    // END
}

