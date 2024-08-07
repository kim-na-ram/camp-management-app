package com.bootcamp.management;

import com.bootcamp.model.Student;
import com.bootcamp.model.StudentStatus;
import com.bootcamp.repository.StudentRepository;
import com.bootcamp.repository.StudentRepositoryImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StudentStatusManagementImpl implements StudentStatusManagement {
    private final StudentRepository studentRepository;

    private final Scanner sc = new Scanner(System.in);

    private String studentId;

    public StudentStatusManagementImpl() {
        studentRepository = new StudentRepositoryImpl();
    }

    public Student getStudent() {
        System.out.println("수강생의 ID를 입력하세요.");
        studentId = sc.next();
        return studentRepository.getStudentById(studentId).orElse(null);
    }

    @Override
    public void createStatus() {
        System.out.println("수강생 상태를 등록합니다.");
        boolean found = true;
        while (true) {
            Student id = getStudent();
            if(id == null){
                System.out.println("ID를 찾을 수 없습니다.");
                found = false;
            }
            while (found) {
                System.out.println("수강생 상태를 선택하세요.");
                System.out.println("1. Green");
                System.out.println("2. Yellow");
                System.out.println("3. Red");
                try {
                    int statusNum = sc.nextInt();
                    StudentStatus status = StudentStatus.getStatus(statusNum);
                    if (statusNum < 4) {
                        id.setStatus(status.getStatusName());
                        System.out.println("ID: " + id.getStudentId());
                        System.out.println("이름: " + id.getStudentName());
                        System.out.println("완료!" + id.getStatus());
                        return;
                    } else if(status == null) {
                        System.out.println("알맞는 번호를 입력하세요.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("번호를 입력하세요.");
                    sc.nextLine();
                }
            }
        }
    }

    @Override
    public void updateStatus() {
        System.out.println("수강생 상태를 수정합니다.");
        boolean found = true;
        while (true) {
            Student id = getStudent();
            if(id == null){
                System.out.println("ID를 찾을 수 없습니다.");
                found = false;
            }
            if (id.getStatus() == null) {
                System.out.println("상태를 먼저 등록 해주세요.");
                return;
            }
            while (found) {
                System.out.println("수강생 상태를 선택하세요.");
                System.out.println("1. Green");
                System.out.println("2. Yellow");
                System.out.println("3. Red");
                try {
                    int statusNum = sc.nextInt();
                    StudentStatus status = StudentStatus.getStatus(statusNum);

                    if (statusNum < 4) {
                        id.setStatus(status.getStatusName());
                        System.out.println("ID: " + id.getStudentId());
                        System.out.println("이름: " + id.getStudentName());
                        System.out.println("완료!" + id.getStatus());
                        return;
                    } else if (status == null) {
                        System.out.println("알맞는 번호를 입력하세요.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("번호를 입력하세요.");
                    sc.nextLine();
                }
            }
        }
    }
}
