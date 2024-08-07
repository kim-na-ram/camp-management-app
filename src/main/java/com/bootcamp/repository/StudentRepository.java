package com.bootcamp.repository;

import com.bootcamp.model.Student;
import com.bootcamp.model.SubjectType;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    boolean isExistStudentById(String studentId);
    List<Student> getStudentStore();
    Optional<Student> getStudentById(String studentId);
    Optional<Student> getStudentByName(String studentName);
    void addStudent(Student student);
    boolean removeStudentById(String studentId);
    boolean removeStudent(Student student);
    void editStudent(int index, Student student);
    List<String> getSelectedSubjectByStudentIdAndSubjectType(String studentId, SubjectType subjectType);
}