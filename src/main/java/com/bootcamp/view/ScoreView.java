package com.bootcamp.view;

import com.bootcamp.management.ScoreRecordManagement;
import com.bootcamp.management.ScoreRecordManagementImpl;

import java.util.Scanner;

public class ScoreView {
    private final ScoreRecordManagement scoreRecordManagement;

    public ScoreView() {
        scoreRecordManagement = new ScoreRecordManagementImpl();
    }

    public void displayScoreView(Scanner sc) {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> scoreRecordManagement.createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> scoreRecordManagement.updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> scoreRecordManagement.inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
}
