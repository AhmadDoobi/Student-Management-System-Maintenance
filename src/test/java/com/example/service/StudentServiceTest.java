package com.example.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.model.Student;

/**
 * @class StudentServiceTest
 * @brief Unit tests for StudentService class.
 */
class StudentServiceTest {
    private StudentService studentService;

    /**
     * @brief Initializes a new StudentService before each test.
     */
    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    /**
     * @brief Tests adding a student successfully.
     */
    @Test
    void testAddStudentSuccess() {
        Student student = new Student(1, "Alice", 20);
        assertTrue(studentService.addStudent(student), "Student should be added successfully.");
    }

    /**
     * @brief Tests that duplicate student IDs are not allowed.
     */
    @Test
    void testAddStudentDuplicateId() {
        studentService.addStudent(new Student(1, "Alice", 20));
        assertFalse(studentService.addStudent(new Student(1, "Bob", 22)), "Duplicate ID should not be allowed.");
    }

    /**
     * @brief Tests retrieving all students.
     */
    @Test
    void testGetAllStudents() {
        studentService.addStudent(new Student(1, "Alice", 20));
        studentService.addStudent(new Student(2, "Bob", 22));
        List<Student> students = studentService.getAllStudents();
        assertEquals(2, students.size(), "There should be 2 students in the list.");
    }

    /**
     * @brief Tests searching for a student by ID.
     */
    @Test
    void testSearchStudentByIdFound() {
        Student student = new Student(1, "Alice", 20);
        studentService.addStudent(student);
        assertEquals(student, studentService.searchStudentById(1), "Student with ID 1 should be found.");
    }

    /**
     * @brief Tests searching for a student that does not exist.
     */
    @Test
    void testSearchStudentByIdNotFound() {
        assertNull(studentService.searchStudentById(999), "Student with non-existent ID should return null.");
    }

    /**
     * @brief Tests removing a student successfully.
     */
    @Test
    void testRemoveStudentByIdSuccess() {
        studentService.addStudent(new Student(1, "Alice", 20));
        assertTrue(studentService.removeStudentById(1), "Student should be removed successfully.");
        assertNull(studentService.searchStudentById(1), "Removed student should not be found.");
    }

    /**
     * @brief Tests removing a non-existent student.
     */
    @Test
    void testRemoveStudentByIdNotFound() {
        assertFalse(studentService.removeStudentById(999), "Removing non-existent student should return false.");
    }
}