package com.bootcamp.model;

import com.bootcamp.utils.Grade;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectId;
    private int score;
    private int round;
    private Enum<Grade> grade;

    public Score(String seq) {
        this.scoreId = seq;
    }

    public Score(String scoreId, String studentId, String subjectId, int score, int round, Enum<Grade> grade) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score = score;
        this.round = round;
        this.grade = grade;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Enum<Grade> getGrade() {
        return grade;
    }

    public void setGrade(Enum<Grade> grade) {
        this.grade = grade;
    }
}