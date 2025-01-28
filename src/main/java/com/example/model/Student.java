package com.example.model;

/**
 * @class Student
 * @brief Represents a student entity with ID, name, and age.
 */
public class Student {
    private final int id;       ///< Unique student ID
    private final String name;  ///< Student name

    @SuppressWarnings("FieldMayBeFinal") ///< Age can change
    private int age;      ///< Student age

    /**
     * @brief Constructor for creating a student.
     * @param id Unique student ID.
     * @param name Name of the student.
     * @param age Age of the student.
     */
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /** @return Student ID */
    public int getId() {
        return id;
    }

    /** @return Student Name */
    public String getName() {
        return name;
    }

    /** @return Student Age */
    public int getAge() {
        return age;
    }
}