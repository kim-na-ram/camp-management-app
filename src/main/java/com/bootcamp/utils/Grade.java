package com.bootcamp.utils;

public enum Grade {
    A,
    B,
    C,
    D,
    F,
    N;

    public static Grade mandatorySubjectGrade(int score) {
        if(score >= 95) return Grade.A;
        else if(score >= 90) return Grade.B;
        else if(score >= 80) return Grade.C;
        else if(score >= 70) return Grade.D;
        else if(score >= 60) return Grade.F;
        else return Grade.N;
    }

    public static Grade choiceSubjectGrade(int score) {
        if(score >= 90) return Grade.A;
        else if(score >= 80) return Grade.B;
        else if(score >= 70) return Grade.C;
        else if(score >= 60) return Grade.D;
        else if(score >= 50) return Grade.F;
        else return Grade.N;
    }
}