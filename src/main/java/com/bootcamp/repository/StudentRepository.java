package com.bootcamp.repository;

import com.bootcamp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudentRepository {
    private static List<Student> studentStore = new ArrayList<>();

    // 리스트 전체 조회
    public static List<Student> getStudentStore() {
        return studentStore;
    }
    // ID로 해당 객체 가져온다
    public Optional<Student> getStudentById(String studentId) {
        return studentStore.stream().filter(st -> st.getStudentId().equals(studentId)).findFirst();
    }
    // 객체 추가
    public void addStudent(Student student) {
        studentStore.add(student);
    }
    // ID로 객체 삭제
    public boolean removeStudentById(String studentId) {
        for(int i = 0; i < studentStore.size(); i++) {
            if(Objects.equals(studentId, studentStore.get(i).getStudentId())) {
                studentStore.remove(i);
                return true;
            }
        }

        return false;
    }
    // 객체 받아와서 해당하는 객체 삭제
    public boolean removeStudent(Student student) {
        if(studentStore.contains(student)) {
            studentStore.remove(student);
            return true;
        }

        return false;
    }
    // 객체 수정 인덱스 기준
    public void editStudent(int index, Student student) {
        studentStore.set(index, student);
    }
}
