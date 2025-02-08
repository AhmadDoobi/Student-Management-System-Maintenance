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

    /**
     * @brief Constructor initializes student service and scanner.
     */
    public StudentUI() {
        this.studentService = new StudentService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * @brief Displays the main menu and processes user choices.
     * 
     * @changes 
     * - Added option 4 to remove students.
     * - Improved input handling to avoid scanner issues.
     */
    public void showMenu() {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Remove Student by ID"); // New Feature
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            // Validate menu choice input
            int choice = getValidIntInput("Choose a valid option (1-5):");

            switch (choice) {
                case 1 -> addNewStudent();
                case 2 -> displayAllStudents();
                case 3 -> searchStudent();
                case 4 -> removeStudent(); // New Feature
                case 5 -> {
                    System.out.println("Exiting program. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * @brief Adds a new student after validating unique ID.
     * 
     * @changes 
     * - Now validates student input before adding.
     * - Handles invalid input gracefully.
     */
    private void addNewStudent() {
        System.out.print("Enter Student ID: ");
        int id = getValidIntInput("Enter a valid integer for Student ID:");

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine(); // Allow free-form text for name.

        System.out.print("Enter Student Age: ");
        int age = getValidIntInput("Enter a valid integer for Student Age:");

        studentService.addNewStudent(new Student(id, name, age));
    }

    /**
     * @brief Displays all students in a formatted table.
     * 
     * @note No major changes.
     */
    private void displayAllStudents() {
        System.out.println("\n--- Student List ---");
        for (Student student : studentService.getAllStudents()) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge());
        }
    }

    /**
     * @brief Searches for a student by ID and displays details.
     * 
     * @note No major changes.
     */
    private void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = getValidIntInput("Enter a valid integer for Student ID:");
        Student student = studentService.searchStudentById(id);

        if (student != null) {
            System.out.println("\nStudent Found: ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge());
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * @brief Removes a student by ID and displays a confirmation message.
     * 
     * @changes 
     * - Added confirmation message for student removal.
     * - Improved input validation.
     */
    private void removeStudent() {
        System.out.print("Enter Student ID to remove: ");
        int id = getValidIntInput("Enter a valid integer for Student ID:");
        studentService.removeStudentById(id);
    }

    /**
     * @brief Utility method to get valid integer input from the user.
     * 
     * @details Repeatedly prompts the user until a valid integer is entered. 
     * Displays an error message for invalid input.
     * 
     * @param errorMessage The error message to display for invalid input.
     * @return A valid integer.
     */
    private int getValidIntInput(String errorMessage) {
        while (true) {
            try {
                String input = scanner.nextLine().trim(); // Read input as string
                return Integer.parseInt(input); // Attempt to parse input as integer
            } catch (NumberFormatException e) {
                System.out.println(errorMessage); // Display error message for invalid input
            }
        }
    }
}