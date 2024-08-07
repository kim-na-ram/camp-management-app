package com.bootcamp;

import com.bootcamp.view.ScoreView;
import com.bootcamp.view.StudentView;

import java.util.*;

public class CampManagementApplication {
    // view
    private static StudentView studentView;
    private static ScoreView scoreView;

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        studentView = new StudentView();
        scoreView = new ScoreView();

        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
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
                case 1 -> studentView.displayStudentView(sc); // 수강생 관리
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
}
