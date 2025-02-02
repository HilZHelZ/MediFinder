package com.example.hospitalfindertest;

public class Developer {
    private String name;
    private String studentId;
    private int imageResourceId;

    public Developer(String name, String studentId, int imageResourceId) {
        this.name = name;
        this.studentId = studentId;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
