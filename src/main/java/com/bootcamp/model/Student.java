package com.bootcamp.model;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<String> compulsory;
    private List<String> elective;

    public Student(String seq, String studentName, List<String> compulsory, List<String> elective) {
        this.studentId = seq;
        this.studentName = studentName;
        this.compulsory = compulsory;
        this.elective = elective;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<String> getCompulsory() {
        return compulsory;
    }

    public List<String> getElective() {
        return elective;
    }

}
