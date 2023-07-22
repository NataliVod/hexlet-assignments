package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous/")
    public Iterable<Course> getPreviousCourses(@PathVariable long id) {
        String path = courseRepository.findById(id).getPath();
        if (path != null && !("").equals(path)) {
            List<Long> ids = Arrays.stream(path.split("\\."))
                    .map(x -> (Long.parseLong(x)))
                    .collect(Collectors.toList());
            return courseRepository.findAllById(ids);
        }
        return new ArrayList<>();
    }

    // END

}
