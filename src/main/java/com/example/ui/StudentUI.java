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
     * - Added validation to handle invalid menu choices.
     */
    public void showMenu() {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Remove Student by ID");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            // Prompt for a valid menu option
            int choice = getValidIntInput("Choose a valid option (1-5):");
            switch (choice) {
                case 1 -> addNewStudent();
                case 2 -> displayAllStudents();
                case 3 -> searchStudent();
                case 4 -> removeStudent();
                case 5 -> {
                    System.out.println("Exiting program. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * @brief Adds a new student after validating unique ID, name, and age.
     * 
     * @changes
     * - Added input validation for name (no numbers allowed).
     * - Added input validation for age (must be greater than 0).
     */
    private void addNewStudent() {
        // Prompt for Student ID
        System.out.print("Enter Student ID: ");
        int id = getValidIntInput("Enter a valid integer for Student ID:");

        // Prompt for Student Name and validate (no numbers allowed)
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine(); // Allow free-form text for name.
        while (name.matches(".*\\d.*")) { // Check if name contains any numbers
            System.out.println("Enter a valid name without numbers:");
            name = scanner.nextLine();
        }

        // Prompt for Student Age and validate (must be > 0)
        System.out.print("Enter Student Age: ");
        int age = getValidIntInput("Enter a valid integer for Student Age:");
        while (age <= 0) { // Check if age is less than or equal to 0
            System.out.println("Error: Age can't be less than 1");
            System.out.print("Enter Student Age: ");
            age = getValidIntInput("Enter a valid integer for Student Age:");
        }

        // Add the student to the system
        studentService.addNewStudent(new Student(id, name, age));
    }

    /**
     * @brief Displays all students in a formatted list.
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
     * @brief Searches for a student by ID and displays their details.
     * 
     * @note No major changes.
     */
    private void searchStudent() {
        // Prompt for Student ID to search
        System.out.print("Enter Student ID to search: ");
        int id = getValidIntInput("Enter a valid integer for Student ID:");

        // Search for the student and display details if found
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
     * - Added validation for Student ID input.
     */
    private void removeStudent() {
        // Prompt for Student ID to remove
        System.out.print("Enter Student ID to remove: ");
        int id = getValidIntInput("Enter a valid integer for Student ID:");

        // Remove the student from the system
        studentService.removeStudentById(id);
    }

    /**
     * @brief Utility method to get valid integer input from the user.
     * 
     * @details Repeatedly prompts the user until a valid integer is entered. 
     *          Displays an error message for invalid input (e.g., letters, decimals).
     * 
     * @param errorMessage The error message to display for invalid input.
     * @return A valid integer.
     * 
     * @example
     * int age = getValidIntInput("Enter a valid age:");
     */
    private int getValidIntInput(String errorMessage) {
        while (true) {
            try {
                // Read input as a string and attempt to parse as integer
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input); // Convert input to integer
            } catch (NumberFormatException e) {
                // Display error message for invalid input
                System.out.println(errorMessage);
            }
        }
    }
}