package com.example.ghc_java.service;

import com.example.ghc_java.entity.Course;
import com.example.ghc_java.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    public void testCreateCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Test Course");
        course.setDescription("Test Description");
        course.setCoverImage("Test Image");
        course.setContent("Test Content");
        course.setCreator("Test Creator");

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course createdCourse = courseService.createCourse(course);

        assertNotNull(createdCourse);
        assertEquals(1L, createdCourse.getId());
        assertEquals("Test Course", createdCourse.getName());
        assertEquals("Test Description", createdCourse.getDescription());
        assertEquals("Test Image", createdCourse.getCoverImage());
        assertEquals("Test Content", createdCourse.getContent());
        assertEquals("Test Creator", createdCourse.getCreator());
    }

    @Test
    public void testUpdateCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Updated Course");
        course.setDescription("Updated Description");
        course.setCoverImage("Updated Image");
        course.setContent("Updated Content");
        course.setCreator("Updated Creator");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course updatedCourse = courseService.updateCourse(1L, course);

        assertNotNull(updatedCourse);
        assertEquals(1L, updatedCourse.getId());
        assertEquals("Updated Course", updatedCourse.getName());
        assertEquals("Updated Description", updatedCourse.getDescription());
        assertEquals("Updated Image", updatedCourse.getCoverImage());
        assertEquals("Updated Content", updatedCourse.getContent());
        assertEquals("Updated Creator", updatedCourse.getCreator());
    }

    @Test
    public void testDeleteCourse() {
        doNothing().when(courseRepository).deleteById(1L);

        courseService.deleteCourse(1L);

        verify(courseRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllCourses() {
        Course course1 = new Course();
        course1.setId(1L);
        course1.setName("Course 1");
        course1.setDescription("Description 1");
        course1.setCoverImage("Image 1");
        course1.setContent("Content 1");
        course1.setCreator("Creator 1");

        Course course2 = new Course();
        course2.setId(2L);
        course2.setName("Course 2");
        course2.setDescription("Description 2");
        course2.setCoverImage("Image 2");
        course2.setContent("Content 2");
        course2.setCreator("Creator 2");

        List<Course> courses = Arrays.asList(course1, course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> allCourses = courseService.getAllCourses();

        assertNotNull(allCourses);
        assertEquals(2, allCourses.size());
        assertEquals("Course 1", allCourses.get(0).getName());
        assertEquals("Course 2", allCourses.get(1).getName());
    }

    @Test
    public void testGetCourseById() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Test Course");
        course.setDescription("Test Description");
        course.setCoverImage("Test Image");
        course.setContent("Test Content");
        course.setCreator("Test Creator");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course foundCourse = courseService.getCourseById(1L);

        assertNotNull(foundCourse);
        assertEquals(1L, foundCourse.getId());
        assertEquals("Test Course", foundCourse.getName());
        assertEquals("Test Description", foundCourse.getDescription());
        assertEquals("Test Image", foundCourse.getCoverImage());
        assertEquals("Test Content", foundCourse.getContent());
        assertEquals("Test Creator", foundCourse.getCreator());
    }
}
