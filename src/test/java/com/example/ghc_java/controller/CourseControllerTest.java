package com.example.ghc_java.controller;

import com.example.ghc_java.entity.Course;
import com.example.ghc_java.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testCreateCourse() throws Exception {
        Course course = new Course();
        course.setId(1L);
        course.setName("Test Course");
        course.setDescription("Test Description");
        course.setCoverImage("Test Image");
        course.setContent("Test Content");
        course.setCreator("Test Creator");

        when(courseService.createCourse(any(Course.class))).thenReturn(course);

        mockMvc.perform(post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Course\",\"description\":\"Test Description\",\"coverImage\":\"Test Image\",\"content\":\"Test Content\",\"creator\":\"Test Creator\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Course"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.coverImage").value("Test Image"))
                .andExpect(jsonPath("$.content").value("Test Content"))
                .andExpect(jsonPath("$.creator").value("Test Creator"));
    }

    @Test
    public void testUpdateCourse() throws Exception {
        Course course = new Course();
        course.setId(1L);
        course.setName("Updated Course");
        course.setDescription("Updated Description");
        course.setCoverImage("Updated Image");
        course.setContent("Updated Content");
        course.setCreator("Updated Creator");

        when(courseService.updateCourse(eq(1L), any(Course.class))).thenReturn(course);

        mockMvc.perform(put("/courses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Course\",\"description\":\"Updated Description\",\"coverImage\":\"Updated Image\",\"content\":\"Updated Content\",\"creator\":\"Updated Creator\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Course"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.coverImage").value("Updated Image"))
                .andExpect(jsonPath("$.content").value("Updated Content"))
                .andExpect(jsonPath("$.creator").value("Updated Creator"));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        doNothing().when(courseService).deleteCourse(1L);

        mockMvc.perform(delete("/courses/1"))
                .andExpect(status().isOk());

        verify(courseService, times(1)).deleteCourse(1L);
    }

    @Test
    public void testGetAllCourses() throws Exception {
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

        when(courseService.getAllCourses()).thenReturn(courses);

        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Course 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].coverImage").value("Image 1"))
                .andExpect(jsonPath("$[0].content").value("Content 1"))
                .andExpect(jsonPath("$[0].creator").value("Creator 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Course 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].coverImage").value("Image 2"))
                .andExpect(jsonPath("$[1].content").value("Content 2"))
                .andExpect(jsonPath("$[1].creator").value("Creator 2"));
    }

    @Test
    public void testGetCourseById() throws Exception {
        Course course = new Course();
        course.setId(1L);
        course.setName("Test Course");
        course.setDescription("Test Description");
        course.setCoverImage("Test Image");
        course.setContent("Test Content");
        course.setCreator("Test Creator");

        when(courseService.getCourseById(1L)).thenReturn(course);

        mockMvc.perform(get("/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Course"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.coverImage").value("Test Image"))
                .andExpect(jsonPath("$.content").value("Test Content"))
                .andExpect(jsonPath("$.creator").value("Test Creator"));
    }
}
