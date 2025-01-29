package com.example.ui;

import java.util.Scanner;

import com.example.model.Student;
import com.example.service.StudentService;

/**
 * @class StudentUI
 * @brief Handles user interactions with the Student Management System.
 */
public class StudentUI {
    private final StudentService studentService; ///< Student service instance.
    private final Scanner scanner; ///< Scanner for user input.

    /** @brief Constructor initializes student service and scanner. */
    public StudentUI() {
        this.studentService = new StudentService();
        this.scanner = new Scanner(System.in);
    }

    /** @brief Displays the main menu and processes user choices. */
    public void showMenu() {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Remove Student by ID");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addNewStudent();
                case 2 -> displayAllStudents();
                case 3 -> searchStudent();
                case 4 -> removeStudent();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    /** @brief Adds a new student after validating unique ID. */
    private void addNewStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Age: ");
        int age = scanner.nextInt();

        studentService.addStudent(new Student(id, name, age));
        System.out.println(studentService);
    }

    /** @brief Displays all students in a formatted table. */
    private void displayAllStudents() {
        System.out.println("\n--- Student List ---");
        for (Student student : studentService.getAllStudents()) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge());
        }
    }

    /** @brief Searches for a student by ID and displays details. */
    private void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();
        Student student = studentService.searchStudentById(id);

        if (student != null) {
            System.out.println("\nStudent Found: ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge());
        } else {
            System.out.println("Student not found.");
        }
    }

    /** @brief Removes a student by ID and displays a confirmation message. */
    private void removeStudent() {
        System.out.print("Enter Student ID to remove: ");
        int id = scanner.nextInt();

        if (studentService.removeStudentById(id)) {
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Error: Student not found.");
        }
    }
}