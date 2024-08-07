package com.bootcamp.repository;

import com.bootcamp.model.Student;
import com.bootcamp.model.SubjectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {
    private final static List<Student> studentStore = new ArrayList<>();

    @Override
    public boolean isExistStudentById(String studentId) {
        return studentStore.stream().anyMatch(student -> student.getStudentId().equals(studentId));
    }

    @Override
    public boolean isExistStudentByName(String studentName) {
        return studentStore.stream().anyMatch(student -> student.getStudentName().equals(studentName));
    }

    // 리스트 전체 조회
    @Override
    public List<Student> getStudentStore() {
        return studentStore;
    }

    // ID로 해당 객체 가져온다
    @Override
    public Optional<Student> getStudentById(String studentId) {
        return studentStore.stream().filter(st -> st.getStudentId().equals(studentId)).findFirst();
    }

    @Override
    public Optional<Student> getStudentByName(String studentName) {
        return studentStore.stream().filter(st -> st.getStudentName().equals(studentName)).findFirst();
    }

    // 객체 추가
    @Override
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

    public List<String> getSelectedSubjectByStudentIdAndSubjectType(String studentId, SubjectType subjectType) {
        return studentStore.stream().filter(st -> st.getStudentId().equals(studentId))
                .map(st -> subjectType == SubjectType.SUBJECT_TYPE_MANDATORY ? st.getCompulsory() : st.getElective())
                .findFirst()
                .get();
    }

    public boolean modifyStudentName(String studentId, String newName){
       Optional <Student> modifyStudent = studentStore.stream().filter(st -> st.getStudentId().equals(studentId)).findAny();
       if(modifyStudent.isPresent()) {
           Student student = modifyStudent.get();
           student.setStudentName(newName);
           return true;
       }
       return false;
    }
}

