package com.bootcamp.management;

import com.bootcamp.model.SubjectType;

import java.util.List;

public interface StudentManagement {
    void createStudent();
    void inquireStudent();
    boolean isExistStudent(String studentId);
    List<String> getSelectedSubjectList(String studentId, SubjectType subjectType);
}