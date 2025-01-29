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

    /** 
     * @brief Constructor initializes the student list.
     * 
     * @note No changes here, but service now has improved validation in addNewStudent.
     */
    public StudentService() {
        this.students = new ArrayList<>();
    }

    /**
     * @brief Adds a new student to the system after validating unique ID, name, and age.
     * @param student The student object to add.
     * @return True if added successfully, false if ID already exists or invalid data.
     * 
     * @changes 
     * - Added validation: student name cannot contain numbers.
     * - Added validation: student age must be greater than 0.
     * - Error messages are now printed for incorrect inputs.
     */
    public boolean addNewStudent(Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                System.out.println("Error: student ID is used before");
                return false;
            }
        }
        if (student.getName().matches(".*\\d.*")) { 
            System.out.println("Error: name can't contain numbers");
            return false;
        }
        if (student.getAge() <= 0) {
            System.out.println("Error: age can't be less than 1"); 
            return false;
        } 
        
        students.add(student);
        System.out.println("Student added successfully"); 
        return true;
    }

    /** 
     * @brief Retrieves the list of all students.
     * @return List of students.
     * 
     * @note No major changes.
     */
    public List<Student> getAllStudents() {
        return students;
    }

    /**
     * @brief Searches for a student by ID.
     * @param id The student ID to search.
     * @return The student if found, null otherwise.
     * 
     * @note No major changes.
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
     * 
     * @changes 
     * - Changed logic to return confirmation message when student is removed.
     */
    public boolean removeStudentById(int id) {
        boolean removed = students.removeIf(student -> student.getId() == id);
        if (removed) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Error: Student not found.");
        }
        return removed;
    }
}