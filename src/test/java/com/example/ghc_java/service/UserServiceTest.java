package com.example.ghc_java.service;

import com.example.ghc_java.entity.User;
import com.example.ghc_java.repository.UserRepository;
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
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");
        user.setRole("USER");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(1L, createdUser.getId());
        assertEquals("testuser", createdUser.getUsername());
        assertEquals("password", createdUser.getPassword());
        assertEquals("testuser@example.com", createdUser.getEmail());
        assertEquals("USER", createdUser.getRole());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("updateduser");
        user.setPassword("newpassword");
        user.setEmail("updateduser@example.com");
        user.setRole("ADMIN");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(1L, user);

        assertNotNull(updatedUser);
        assertEquals(1L, updatedUser.getId());
        assertEquals("updateduser", updatedUser.getUsername());
        assertEquals("newpassword", updatedUser.getPassword());
        assertEquals("updateduser@example.com", updatedUser.getEmail());
        assertEquals("ADMIN", updatedUser.getRole());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setEmail("user1@example.com");
        user1.setRole("USER");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setEmail("user2@example.com");
        user2.setRole("ADMIN");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();

        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
        assertEquals("user1", allUsers.get(0).getUsername());
        assertEquals("user2", allUsers.get(1).getUsername());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");
        user.setRole("USER");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("password", foundUser.getPassword());
        assertEquals("testuser@example.com", foundUser.getEmail());
        assertEquals("USER", foundUser.getRole());
    }
}
