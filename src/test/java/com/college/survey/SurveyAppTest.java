package com.college.survey;

import org.junit.Test;
import static org.junit.Assert.*;

public class SurveyAppTest {
    
    @Test
    public void testSurveyCreation() {
        SurveyApp.SurveyResponse sr = new SurveyApp.SurveyResponse();
        sr.studentName = "John";
        sr.department = "CSE";
        sr.campusRating = 4;
        sr.academicRating = 5;
        
        assertEquals("John", sr.studentName);
        assertEquals("CSE", sr.department);
        assertEquals(4, sr.campusRating);
        assertEquals(5, sr.academicRating);
    }
    
    @Test
    public void testRatingRange() {
        int campusRating = 4;
        int academicRating = 5;
        
        assertTrue(campusRating >= 1 && campusRating <= 5);
        assertTrue(academicRating >= 1 && academicRating <= 5);
    }
}
