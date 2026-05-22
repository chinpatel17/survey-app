package com.college.survey;

import java.util.*;
import java.io.*;

public class SurveyApp {
    
    private static Scanner scanner = new Scanner(System.in);
    private static List<SurveyResponse> responses = new ArrayList<>();
    
    static class SurveyResponse {
        String studentName;
        String department;
        int campusRating;     // 1-5
        int academicRating;   // 1-5
        String feedback;
        
        public void display() {
            System.out.println("\n📊 Student: " + studentName);
            System.out.println("   Department: " + department);
            System.out.println("   Campus Rating: " + campusRating + "/5");
            System.out.println("   Academic Rating: " + academicRating + "/5");
            System.out.println("   Feedback: " + feedback);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("🎓 COLLEGE SURVEY SYSTEM");
        System.out.println("=".repeat(50));
        
        while (true) {
            System.out.println("\n1. Submit Survey");
            System.out.println("2. View All Surveys");
            System.out.println("3. View Statistics");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1: submitSurvey(); break;
                case 2: viewSurveys(); break;
                case 3: showStats(); break;
                case 4: 
                    System.out.println("Thank you! Goodbye!");
                    System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
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
        
        System.out.print("Your Feedback/Suggestions: ");
        sr.feedback = scanner.nextLine();
        
        responses.add(sr);
        
        System.out.println("\n✅ Survey Submitted Successfully!");
        
        // Save to file
        saveToFile(sr);
    }
    
    static void viewSurveys() {
        if (responses.isEmpty()) {
            System.out.println("\n📭 No surveys yet!");
            return;
        }
        
        System.out.println("\n=== ALL SURVEYS ===");
        for (SurveyResponse sr : responses) {
            sr.display();
        }
        System.out.println("Total: " + responses.size() + " responses");
    }
    
    static void showStats() {
        if (responses.isEmpty()) {
            System.out.println("\nNo data available!");
            return;
        }
        
        double avgCampus = 0;
        double avgAcademic = 0;
        
        for (SurveyResponse sr : responses) {
            avgCampus += sr.campusRating;
            avgAcademic += sr.academicRating;
        }
        
        avgCampus /= responses.size();
        avgAcademic /= responses.size();
        
        System.out.println("\n=== STATISTICS ===");
        System.out.printf("📊 Total Responses: %d\n", responses.size());
        System.out.printf("🏫 Average Campus Rating: %.2f/5\n", avgCampus);
        System.out.printf("📚 Average Academic Rating: %.2f/5\n", avgAcademic);
        
        // Rating interpretation
        if (avgCampus >= 4.0) System.out.println("✅ Campus facilities: Excellent!");
        else if (avgCampus >= 3.0) System.out.println("👍 Campus facilities: Good");
        else System.out.println("⚠️ Campus facilities: Needs improvement");
        
        if (avgAcademic >= 4.0) System.out.println("✅ Academic quality: Excellent!");
        else if (avgAcademic >= 3.0) System.out.println("👍 Academic quality: Good");
        else System.out.println("⚠️ Academic quality: Needs improvement");
    }
    
    static void saveToFile(SurveyResponse sr) {
        try (FileWriter fw = new FileWriter("surveys.txt", true)) {
            fw.write(sr.studentName + "|" + sr.department + "|" + 
                     sr.campusRating + "|" + sr.academicRating + "|" + 
                     sr.feedback + "\n");
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }
}
