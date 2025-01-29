package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Student;

/**
 * @class StudentService
 * @brief Handles student-related operations such as adding, searching, and removing students.
 */
public class StudentService {
    private final List<Student> students; ///< List of students.

    /** @brief Constructor initializes the student list. */
    public StudentService() {
        this.students = new ArrayList<>();
    }

    /**
     * @brief Adds a new student to the system after validating unique ID.
     * @param student The student object to add.
     * @return True if added successfully, false if ID already exists or age is less than 1.
     */
    public boolean  addNewStudent(Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                System.out.println("Error: student ID is used before");
                return false; // Error duplicate ID found
            }
        }
        if (student.getName().matches(".*\\d.*")) { 
            System.out.println("Error: name cant contain numbers");
            return false; // Name can't contain numbers
        }
        if (student.getAge() <= 0) {
            System.out.println("Error: age cant be less than 1"); 
            return false; // Age can't be less than 1
        } else {
            students.add(student);
            System.out.println("student added successfully"); 
            return true; // Student added successfully
        }
    }

    /**
     * @brief Retrieves the list of all students.
     * @return List of students.
     */
    public List<Student> getAllStudents() {
        return students;
    }

    /**
     * @brief Searches for a student by ID.
     * @param id The student ID to search.
     * @return The student if found, null otherwise.
     */
    public Student searchStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    /**
     * @brief Removes a student by ID.
     * @param id The student ID to remove.
     * @return True if removed successfully, false if student not found.
     */
    public boolean removeStudentById(int id) {
        return students.removeIf(student -> student.getId() == id);
    }
}