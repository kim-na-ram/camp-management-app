package com.bootcamp.repository;

import com.bootcamp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudentRepository {
    private static List<Student> studentStore = new ArrayList<>();

    public List<Student> getStudentStore() {
        return studentStore;
    }

    public Optional<Student> getStudentById(String studentId) {
        return studentStore.stream().filter(st -> st.getStudentId().equals(studentId)).findFirst();
    }

    public void addStudent(Student student) {
        studentStore.add(student);
    }

    public boolean removeStudentById(String studentId) {
        for(int i = 0; i < studentStore.size(); i++) {
            if(Objects.equals(studentId, studentStore.get(i).getStudentId())) {
                studentStore.remove(i);
                return true;
            }
        }

        return false;
    }

    public boolean removeStudent(Student student) {
        if(studentStore.contains(student)) {
            studentStore.remove(student);
            return true;
        }

        return false;
    }

    public void editStudent(int index, Student student) {
        studentStore.set(index, student);
    }
}
