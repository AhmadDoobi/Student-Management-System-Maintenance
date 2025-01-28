package com.example.app;

import com.example.ui.StudentUI;

/**
 * @class Main
 * @brief Application entry point.
 */
public class Main {
    /** @brief Main method to launch the Student Management System. */
    public static void main(String[] args) {
        StudentUI ui = new StudentUI();
        ui.showMenu();
    }
}