package com.bootcamp.view;

import com.bootcamp.management.StudentManagement;
import com.bootcamp.management.StudentManagementImpl;
import com.bootcamp.management.StudentStatusManagement;
import com.bootcamp.management.StudentStatusManagementImpl;

import java.util.Scanner;

public class StudentView {
    private final StudentManagement studentManagement;
    private final StudentStatusManagement studentStatusManagement;

    public StudentView() {
        studentManagement = new StudentManagementImpl();
        studentStatusManagement = new StudentStatusManagementImpl();
    }

    public void displayStudentView(Scanner sc) {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 상태 관리");
            System.out.println("3. 수강생 목록 조회");
            System.out.println("4. 등록 수강생 이름 수정");
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> studentManagement.createStudent(); // 수강생 등록
                case 2 -> displayStatusView(sc); // 수강생 상태 조회
                case 3 -> studentManagement.inquireStudent(); // 수강생 목록 조회
                case 4 -> studentManagement.modifyStudentName(); // 수강생 이름 수정
                case 5 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private void displayStatusView(Scanner sc) {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 상태 등록");
            System.out.println("2. 수강생 상태 수정");
            System.out.println("3. 수강생 관리 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> studentStatusManagement.createStatus(); // 상태 등록
                case 2 -> studentStatusManagement.updateStatus(); // 상태 수정
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
}
