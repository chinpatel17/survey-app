package com.college.survey.model;

import jakarta.persistence.*;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String department;
    private String campusFeedback;
    private String academicFeedback;

    public Survey() {
    }

    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCampusFeedback() {
        return campusFeedback;
    }

    public void setCampusFeedback(String campusFeedback) {
        this.campusFeedback = campusFeedback;
    }

    public String getAcademicFeedback() {
        return academicFeedback;
    }

    public void setAcademicFeedback(String academicFeedback) {
        this.academicFeedback = academicFeedback;
    }
}
