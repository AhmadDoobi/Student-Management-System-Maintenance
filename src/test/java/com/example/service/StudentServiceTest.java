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
 * @brief Unit tests for the StudentService class.
 * 
 * @details This test suite was created to validate the core functionalities of 
 *          the StudentService class, including adding, retrieving, searching, 
 *          and removing students.
 */
class StudentServiceTest {
    private StudentService studentService;

    /** 
     * @brief Initializes a new StudentService instance before each test. 
     * 
     * @note Ensures each test runs independently with a fresh StudentService instance.
     */
    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    /** 
     * @brief Tests if a student is added successfully. 
     */
    @Test
    void testAddNewStudentSuccess() {
        Student student = new Student(1, "Alice", 20);
        assertTrue(studentService.addNewStudent(student), "Student should be added successfully.");
    }

    /** 
     * @brief Tests that duplicate student IDs are not allowed. 
     */
    @Test
    void testAddNewStudentDuplicateId() {
        studentService.addNewStudent(new Student(1, "Alice", 20));
        assertFalse(studentService.addNewStudent(new Student(1, "Bob", 22)), 
            "Duplicate ID should not be allowed.");
    }

    /** 
     * @brief Tests validation for student names. 
     */
    @Test
    void testAddNewStudentInvalidName() {
        assertFalse(studentService.addNewStudent(new Student(1, "Alice123", 20)), 
            "Student name with numbers should not be allowed.");
        assertFalse(studentService.addNewStudent(new Student(2, "Bob9", 22)), 
            "Student name with numbers should not be allowed.");
        assertTrue(studentService.addNewStudent(new Student(3, "Charlie", 25)), 
            "Valid student name should be allowed.");
    }

    /** 
     * @brief Tests validation for student age. 
     */
    @Test
    void testAddNewStudentInvalidAge() {
        assertFalse(studentService.addNewStudent(new Student(1, "Alice", 0)), 
            "Student with age 0 should not be allowed.");
        assertFalse(studentService.addNewStudent(new Student(2, "Bob", -5)), 
            "Student with negative age should not be allowed.");
        assertTrue(studentService.addNewStudent(new Student(3, "Charlie", 1)), 
            "Student with valid age should be allowed.");
    }

    /** 
     * @brief Tests retrieving all students. 
     */
    @Test
    void testGetAllStudents() {
        studentService.addNewStudent(new Student(1, "Alice", 20));
        studentService.addNewStudent(new Student(2, "Bob", 22));
        List<Student> students = studentService.getAllStudents();
        assertEquals(2, students.size(), "There should be 2 students in the list.");
    }

    /** 
     * @brief Tests searching for a student by ID. 
     */
    @Test
    void testSearchStudentByIdFound() {
        Student student = new Student(1, "Alice", 20);
        studentService.addNewStudent(student);
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
        studentService.addNewStudent(new Student(1, "Alice", 20));
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