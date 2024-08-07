package com.bootcamp;

import com.bootcamp.management.StudentManagement;
import com.bootcamp.management.StudentManagementImpl;
import com.bootcamp.management.StudentStatusManagement;
import com.bootcamp.management.StudentStatusManagementImpl;
import com.bootcamp.view.ScoreView;

import java.util.*;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    private static StudentManagement studentManagement;

    // view
    private static ScoreView scoreView;

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        scoreView = new ScoreView();

        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            // TODO 삭제예정 : 에러 확인용
            e.printStackTrace();
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    private static void setInitData() {
        studentManagement = new StudentManagementImpl();
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> scoreView.displayScoreView(sc); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
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
                case 2 -> displayStatusView(); // 수강생 상태 조회
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

    private static void displayStatusView() {
        StudentStatusManagement smi = new StudentStatusManagementImpl(studentManagement);
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
                case 1 -> smi.createStatus(); // 상태 등록
                case 2 -> smi.updateStatus(); // 상태 수정
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
}
