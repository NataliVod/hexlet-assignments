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
    @GetMapping(path = "/search")
    public List<Map<String, String>> getCities(@RequestParam(required = false) String name) {

        List<City> filteredCities;

        if (name == null) {
            filteredCities = cityRepository.findAllByOrderByName();
        } else {
            filteredCities = cityRepository.findByNameStartingWithIgnoreCase(name);
        }

        List<Map<String, String>> citiesWithWeather = filteredCities.stream()
                .map(city -> {
                    Map<String, String> weather = weatherService.lookUp(city.getId());
                    return Map.of(
                            "name", city.getName(),
                            "temperature", weather.get("temperature")
                    );
                })
                .collect(Collectors.toList());

        return citiesWithWeather;
    }

    @GetMapping(path = "cities/{id}")
    public Map<String, String> getCity(@PathVariable long id) {

        Map<String, String> weather = weatherService.lookUp(id);

        return weather;
    }
    // END
}

