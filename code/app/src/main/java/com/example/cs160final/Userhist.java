package com.example.cs160final;

import java.util.HashMap;
import java.util.List;

public class Userhist {
    private String fAcademics;
    private String fBudget;
    private String fEmail;
    private String fGrade;
    private String fHealth;
    private String fHobbies;
    private List<String> fLearning;
    private String fName;
    private String fSocial;
    private String fWeek;

    public Userhist() {}

    public Userhist(String fAcademics, String fBudget, String fEmail, String fGrade, String fHealth,
                    String fHobbies, List<String> fLearning, String fName, String fSocial, String fWeek) {
        this.fAcademics = fAcademics;
        this.fBudget = fBudget;
        this.fEmail = fEmail;
        this.fGrade = fGrade;
        this.fHealth = fHealth;
        this.fHobbies = fHobbies;
        this.fLearning = fLearning;
        this.fName = fName;
        this.fSocial = fSocial;
        this.fWeek = fWeek;
    }

    public List<String> getfLearning(){
        return fLearning;
    }
}
