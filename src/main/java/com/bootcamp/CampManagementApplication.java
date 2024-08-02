package com.bootcamp;


import com.bootcamp.model.Score;
import com.bootcamp.model.Student;
import com.bootcamp.model.Subject;
import com.bootcamp.utils.Grade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static ArrayList<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
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
        studentStore = new ArrayList<>();
        // TODO 삭제예정 : 테스트용
        studentStore.add(new Student(sequence(INDEX_TYPE_STUDENT), "테스터"));
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
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
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드
        // 기능 구현
        System.out.println("수강생 등록 성공!\n");
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("\n수강생 목록 조회 성공!");
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
                System.out.println("계속하려면 'y'를 입력하세요. 종료하려면 다른 키를 입력하세요");
                String continueInput = sc.next();
                if (!continueInput.equalsIgnoreCase("y")) {
                    break;
                }
            } else break;
        }

        while (true) {
            System.out.println("관리할 과목의 번호를 입력하시오...");
            String subjectID = sc.next();
            boolean flag = false;
            for (int i = 0; i < subjectStore.size(); i++) {
                if (subjectStore.get(i).getSubjectId().equals(subjectID)) {
                    newscore.setSubjectId(subjectID);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("해당 과목 ID를 찾을 수 없습니다.");
                System.out.println("계속 하려면 'y'를 입력하세요. 종료하려면 다른 키를 입력하세요.");
                String continueInput = sc.next();
                if (!continueInput.equalsIgnoreCase("y")) {
                    break;
                }
            } else break;
        }

        while (true) {
            System.out.println("등록할 회차를 입력하시오...");
            round = sc.nextInt();
            if (round <= 0 || round > 10) {
                System.out.println("회차범위: 1~10까지 입력하시오...");
            } else {
                // TODO 성현님 : 입력받은 회차랑 일치하는 회차가 존재하는지 확인
                // 있다면 warning : 이미 있는 회차는 등록할 수 없다~
                // 없다면 아래 코드 실행
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
                // TODO 성현님 : 점수 -> 등급으로 변경
                // 등급도 Score 객체에 저장
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
        String subjectId, subjectType;
        int round, subjectScore;
        int scoreIdx = 0;

        Score findScore = null;

        while (true) {
            System.out.println("수정할 과목을 입력하시오");
            String subjectName = sc.next();

            Optional<Subject> subject = subjectStore.stream()
                    .filter(sbj -> sbj.getSubjectName().equals(subjectName))
                    .findFirst();

            if (subject.isEmpty()) {
                System.out.println("해당 과목은 존재하지 않습니다.");
            } else {
                subjectId = subject.get().getSubjectId();
                subjectType = subject.get().getSubjectType();
                break;
            }
        }

        while (true) {
            System.out.println("수정할 회차를 입력하시오");
            round = sc.nextInt();

            if (round <= 0 || round > 10) {
                System.out.println("회차는 1 ~ 10까지만 입력 가능합니다.");
            } else {
                for(int i = 0; i < scoreStore.size(); i++) {
                    Score score = scoreStore.get(i);
                    if (score.getStudentId().equals(studentId)
                    && score.getSubjectId().equals(subjectId)
                    && score.getRound() == round) {
                        scoreIdx = i;
                        findScore = score;
                    }
                }

                if(findScore == null) System.out.println("해당 회차는 존재하지 않습니다.");
                else break;
            }
        }

        while (true) {
            System.out.println("수정할 점수를 입력하시오");
            subjectScore = sc.nextInt();

            if (subjectScore <= 0 || subjectScore > 100) {
                System.out.println("점수는 1 ~ 100까지만 입력 가능합니다.");
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
            System.out.println("등급을 확인할 과목을 입력하시오");
            String subjectName = sc.next();

            Optional<Subject> subject = subjectStore.stream()
                    .filter(sbj -> sbj.getSubjectName().equals(subjectName))
                    .findFirst();

            if (subject.isEmpty()) {
                System.out.println("해당 과목은 존재하지 않습니다.");
            } else {
                subjectId = subject.get().getSubjectId();
                break;
            }
        }

        System.out.println("회차별 등급을 조회합니다...");

        List<Score> roundList = scoreStore.stream().filter(sc -> sc.getStudentId().equals(studentId)
                && sc.getSubjectId().equals(subjectId)).toList();

        roundList.forEach(score -> System.out.println(score.getRound() + "회차 등급 : " + score.getGrade()));

        System.out.println("\n등급 조회 성공!");
    }

    // score 와 type 을 통해 등급을 반환
    private static Grade scoreInToGrade(int score, String type) {
        if (type.equals(SUBJECT_TYPE_MANDATORY)) {
            return Grade.mandatorySubjectGrade(score);
        } else {
            return Grade.choiceSubjectGrade(score);
        }
    }
}
