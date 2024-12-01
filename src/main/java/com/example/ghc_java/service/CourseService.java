package com.example.ghc_java.service;

import com.example.ghc_java.entity.Course;
import com.example.ghc_java.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setName(courseDetails.getName());
            course.setDescription(courseDetails.getDescription());
            course.setCoverImage(courseDetails.getCoverImage());
            course.setContent(courseDetails.getContent());
            course.setCreator(courseDetails.getCreator());
            return courseRepository.save(course);
        } else {
            throw new RuntimeException("Course not found with id " + id);
        }
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found with id " + id));
    }
}
