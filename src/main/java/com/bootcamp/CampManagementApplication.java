package com.bootcamp;

import com.bootcamp.management.StudentManagement;
import com.bootcamp.management.StudentManagementImpl;
import com.bootcamp.management.StudentStatusManagement;
import com.bootcamp.management.StudentStatusManagementImpl;
import com.bootcamp.model.*;
import com.bootcamp.model.Grade;
import com.bootcamp.model.SubjectType;

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

    // 데이터 저장소
    private static List<Student> studentStore;
    private static ArrayList<Score> scoreStore;

    // index 관리 필드
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            // TODO 삭제예정 : 에러 확인용
            e.printStackTrace();
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentManagement = new StudentManagementImpl();

        // TODO 삭제예정
        studentStore = new ArrayList<>();
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
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
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
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


    private static void displayScoreView() {
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
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        SubjectType subjectType = null;
        int round;

        Score newscore = new Score(sequence(INDEX_TYPE_SCORE));
        while (true) {
            System.out.println("관리할 수강생의 번호를 입력하시오...");
            String studentID = sc.next();
            boolean found = false;
            for (int i = 0; i < studentStore.size(); i++) {
                if (studentStore.get(i).getStudentId().equals(studentID)) {
                    newscore.setStudentId(studentID);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("해당 학생 ID를 찾을 수 없습니다.");
                if (printEscapeCondition()) return;
            } else break;
        }

        while (true) {
            SubjectInfo.printSubjectInfo();
            System.out.println("관리할 과목의 번호를 입력하시오...");
            String subjectID = sc.next();
            if (SubjectInfo.isExistSubjectId(subjectID)) {
                newscore.setSubjectId(subjectID);
                subjectType = SubjectInfo.getSubjectType(subjectID);
                break;
            }

            System.out.println("해당 과목 ID를 찾을 수 없습니다.");
            if (printEscapeCondition()) return;
        }

        while (true) {
            System.out.println("등록할 회차를 입력하시오...");
            round = sc.nextInt();
            if (round <= 0 || round > 10) {
                System.out.println("회차범위: 1~10까지 입력하시오...");
                continue;
            }

            boolean roundOver = false;
            for (Score round2 : scoreStore) {
                if (round2.getRound() == round) {
                    roundOver = true;
                    break;
                }
            }
            if (roundOver) {
                System.out.println("이미 등록되어 있는 회차는 등록할 수 없습니다.");
            } else {
                newscore.setRound(round);
                break;
            }
        }

        while (true) {
            System.out.println("등록할 점수를 입력하시오...");
            int score = sc.nextInt();
            if (score <= 0 || score > 100) {
                System.out.println("점수범위: 0~100까지 입력하시오...");
            } else {
                newscore.setScore(score);
                newscore.setGrade(scoreInToGrade(score, subjectType));
                break;
            }
        }

        System.out.println("시험 점수를 등록합니다...");
        scoreStore.add(newscore);
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        String subjectId;
        SubjectType subjectType;
        int round, subjectScore, scoreIdx = 0;

        Score findScore = null;

        while (true) {
            if (!isExistStudentId(studentId)) {
                System.out.println("해당 학생 ID를 찾을 수 없습니다.");
                if (printEscapeCondition()) return;
                else studentId = getStudentId();
            } else {
                break;
            }
        }

        while (true) {
            SubjectInfo.printSubjectInfo();
            System.out.println("수정할 과목의 번호를 입력하시오");
            subjectId = sc.next();

            // capturing lambda 를 위한 effectively final 변수 선언
            String finalSubjectId = subjectId;
            if (scoreStore.stream().noneMatch(sc -> sc.getSubjectId().equals(finalSubjectId))) {
                System.out.println("해당 과목의 점수는 존재하지 않습니다.");
                if (printEscapeCondition()) return;
            } else {
                subjectType = SubjectInfo.getSubjectType(subjectId);
                break;
            }
        }

        while (true) {
            System.out.println("수정할 회차를 입력하시오");
            round = sc.nextInt();

            if (round <= 0 || round > 10) {
                System.out.println("회차는 1 ~ 10까지만 입력 가능합니다.");
            } else {
                for (int i = 0; i < scoreStore.size(); i++) {
                    Score score = scoreStore.get(i);
                    if (score.getStudentId().equals(studentId)
                            && score.getSubjectId() == subjectId
                            && score.getRound() == round) {
                        scoreIdx = i;
                        findScore = score;
                    }
                }

                if (findScore == null) {
                    System.out.println("해당 회차는 존재하지 않습니다.");
                    if (printEscapeCondition()) return;
                } else break;
            }
        }

        while (true) {
            System.out.println("수정할 점수를 입력하시오");
            subjectScore = sc.nextInt();

            if (subjectScore <= 0 || subjectScore > 100) {
                System.out.println("점수는 1 ~ 100까지만 입력 가능합니다.");
                if (printEscapeCondition()) return;
            } else break;
        }

        System.out.println("시험 점수를 수정합니다...");

        findScore.setScore(subjectScore);
        findScore.setGrade(scoreInToGrade(subjectScore, subjectType));

        scoreStore.set(scoreIdx, findScore);

        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        String subjectId;

        while (true) {
            if (!isExistStudentId(studentId)) {
                System.out.println("해당 학생 ID를 찾을 수 없습니다.");
                if (printEscapeCondition()) return;
                else studentId = getStudentId();
            } else {
                break;
            }
        }

        while (true) {
            SubjectInfo.printSubjectInfo();
            System.out.println("등급을 확인할 과목의 번호 입력하시오");
            subjectId = sc.next();

            // capturing lambda 를 위한 effectively final 변수 선언
            String finalSubjectId = subjectId;
            if (scoreStore.stream().noneMatch(sc -> sc.getSubjectId().equals(finalSubjectId))) {
                System.out.println("해당 과목의 점수는 존재하지 않습니다.");
                if (printEscapeCondition()) return;
            } else break;
        }

        System.out.println("회차별 등급을 조회합니다...");

        // capturing lambda 로 인한 effectively final 변수 선언
        String finalStudentId = studentId;
        String finalSubjectId = subjectId;

        List<Score> roundList = scoreStore.stream().filter(sc -> sc.getStudentId().equals(finalStudentId)
                && sc.getSubjectId().equals(finalSubjectId)).toList();

        roundList.forEach(score -> System.out.println(score.getRound() + "회차 등급 : " + score.getGrade()));

        System.out.println("\n등급 조회 성공!");
    }

    private static boolean isExistStudentId(String studentId) {
        Optional<Student> student = studentStore.stream()
                .filter(std -> std.getStudentId().equals(studentId))
                .findFirst();

        return student.isPresent();
    }

    private static boolean printEscapeCondition() {
        System.out.println("계속하려면 'y'를 입력하세요. 종료하려면 다른 키를 입력하세요");
        String continueInput = sc.next();
        return !continueInput.equalsIgnoreCase("y");
    }

    // score 와 type 을 통해 등급을 반환
    private static Grade scoreInToGrade(int score, SubjectType type) {
        if (SubjectType.SUBJECT_TYPE_MANDATORY == type) {
            return Grade.mandatorySubjectGrade(score);
        } else {
            return Grade.choiceSubjectGrade(score);
        }
    }
}
