package exercise.repository;

import exercise.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    // BEGIN
    public List<City> findAllByNameIgnoreCaseStartingWith(String name);
    public List<City> findAllByOrderByNameAsc();
    // END
}
