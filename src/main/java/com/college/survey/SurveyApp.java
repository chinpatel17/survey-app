package com.college.survey;

import java.util.ArrayList;
import java.util.Scanner;

public class SurveyApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<SurveyResponse> responses = new ArrayList<>();
    
    static class SurveyResponse {
        String studentName;
        String department;
        int campusRating;
        int academicRating;
        String feedback;
    }
    
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("COLLEGE SURVEY SYSTEM");
        System.out.println("=".repeat(50));
        
        while (true) {
            System.out.println("\n1. Submit Survey");
            System.out.println("2. View All Surveys");
            System.out.println("3. View Statistics");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice == 1) submitSurvey();
            else if (choice == 2) viewSurveys();
            else if (choice == 3) showStats();
            else if (choice == 4) {
                System.out.println("Thank you! Goodbye!");
                break;
            }
            else System.out.println("Invalid choice!");
        }
        scanner.close();
    }
    
    static void submitSurvey() {
        System.out.println("\n--- STUDENT SURVEY ---");
        SurveyResponse sr = new SurveyResponse();
        System.out.print("Student Name: ");
        sr.studentName = scanner.nextLine();
        System.out.print("Department (CSE/ECE/ME/CIVIL): ");
        sr.department = scanner.nextLine().toUpperCase();
        System.out.print("Rate Campus Facilities (1-5): ");
        sr.campusRating = Integer.parseInt(scanner.nextLine());
        System.out.print("Rate Academic Quality (1-5): ");
        sr.academicRating = Integer.parseInt(scanner.nextLine());
        System.out.print("Feedback/Suggestions: ");
        sr.feedback = scanner.nextLine();
        responses.add(sr);
        System.out.println("\n✅ SURVEY SUBMITTED!");
    }
    
    static void viewSurveys() {
        if (responses.isEmpty()) {
            System.out.println("\nNo surveys yet!");
            return;
        }
        System.out.println("\n=== ALL SURVEYS ===");
        for (SurveyResponse sr : responses) {
            System.out.println("\nStudent: " + sr.studentName);
            System.out.println("Department: " + sr.department);
            System.out.println("Campus Rating: " + sr.campusRating + "/5");
            System.out.println("Academic Rating: " + sr.academicRating + "/5");
            System.out.println("Feedback: " + sr.feedback);
        }
    }
    
    static void showStats() {
        if (responses.isEmpty()) {
            System.out.println("\nNo data available!");
            return;
        }
        double totalCampus = 0, totalAcademic = 0;
        for (SurveyResponse sr : responses) {
            totalCampus += sr.campusRating;
            totalAcademic += sr.academicRating;
        }
        System.out.println("\n=== STATISTICS ===");
        System.out.println("Total Responses: " + responses.size());
        System.out.printf("Average Campus Rating: %.2f/5\n", totalCampus / responses.size());
        System.out.printf("Average Academic Rating: %.2f/5\n", totalAcademic / responses.size());
    }
}
