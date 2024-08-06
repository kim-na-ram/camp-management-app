package com.bootcamp;

import com.bootcamp.exception.ManagementException;
import com.bootcamp.model.*;
import com.bootcamp.utils.Grade;
import com.bootcamp.utils.SubjectType;

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
    // 데이터 저장소
    private static List<Student> studentStore;
    private static ArrayList<Score> scoreStore;

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
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
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
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
        StudentManagementImpl smi = new StudentManagementImpl();
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
                case 1 -> smi.createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static void inquireStudent() {                 //예외처리 생각해보기
        System.out.println("1. 수강생 전체 조회");
        System.out.println("2. 수강생 ID 조회");
        System.out.println("3. 수강생 이름 조회");

        // 기능 구현
        int select = sc.nextInt();

        if (select == 1) {
            System.out.println("\n수강생 목록을 조회합니다...");
            if (studentStore.isEmpty()) {
                System.out.println("등록된 수강생이 존재하지 않습니다");
            } else {
                // 수강생 목록을 sort 를 사용하여 정렬하고
                // comparator 를 사용해 기준으로 오름차순 정렬
                studentStore.sort(Comparator.comparing(Student::getStudentName));

                // 정렬된 수강생 목록 출력
                for (Student student : studentStore) {
                    System.out.println("이름: " + student.getStudentName());
                    System.out.println("ID: " + student.getStudentId());
                    System.out.println(); // 수강생 정보 구분을 위해 빈 줄 추가
                }

                System.out.println("수강생 목록 조회 성공!");
            }
        } else if (select == 2) {
            System.out.println("조회할 수강생의 ID를 입력해 주세요: ");
            String studentId = sc.next();

            for (Student student : studentStore) {
                if (student.getStudentId().equalsIgnoreCase(studentId)) {
                    System.out.println();
                    System.out.println("이름: " + student.getStudentName());
                    System.out.println("ID: " + student.getStudentId());

                } else if (studentId.isEmpty()) {
                    System.out.println("등록된 수강생이 존재하지 않습니다");
                }
            }
        } else if (select == 3) {
            System.out.println("조회할 수강생의 이름을 입력해 주세요: ");
            String studentName = sc.next();
            boolean flag = false;
            for (Student student : studentStore) {
                if (student.getStudentName().equalsIgnoreCase(studentName)) {
                    System.out.println();
                    System.out.println("이름: " + student.getStudentName());
                    System.out.println("ID: " + student.getStudentId());
                } else if (studentName.isEmpty()) {
                    System.out.println("등록된 수강생이 존재하지 않습니다");
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

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        SubjectType subjectType;
        String studentId;
        int round;

        Score newscore = new Score(sequence(INDEX_TYPE_SCORE));
        while (true) {
            System.out.println("관리할 수강생의 번호를 입력하시오...");
            studentId = sc.next();
            boolean found = false;
            for (int i = 0; i < studentStore.size(); i++) {
                if (studentStore.get(i).getStudentId().equals(studentId)) {
                    newscore.setStudentId(studentId);
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
            try {
                SubjectInfo.printSubjectInfo();
                System.out.println("관리할 과목의 고유번호를 입력하시오...");
                Optional<String> student = validateSubjectId(studentId);

                if (student.isPresent()) {
                    newscore.setSubjectId(student.get());
                    subjectType = SubjectInfo.getSubjectType(student.get());
                    break;
                }

                System.out.println("해당 과목은 선택되지 않아 등록할 수 없습니다.");
                if (printEscapeCondition()) return;
            } catch (ManagementException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("등록할 회차를 입력하시오...");
            round = sc.nextInt();
            if (round <= 0 || round > 10) {
                System.out.println("회차범위: 1~10까지 입력하시오...");
                continue;
            }

            boolean roundExists = false;
            for (Score existingScore : scoreStore) {
                if (existingScore.getStudentId().equals(newscore.getStudentId()) &&
                        existingScore.getSubjectId().equals(newscore.getSubjectId()) &&
                        existingScore.getRound() == round) {
                    roundExists = true;
                    break;
                }
            }
            if (roundExists) {
                System.out.println("이미 등록되어 있는 회차는 등록할 수 없습니다.");
            } else {
                newscore.setRound(round);
                break;
            }
        }

        while (true) {
            System.out.println("등록할 점수를 입력하시오");
            Optional<Integer> score = validateScore();

            if(score.isPresent()) {
                newscore.setScore(score.get());
                newscore.setGrade(scoreInToGrade(score.get(), subjectType));
                break;
            }

            if(printEscapeCondition()) return;
        }

        System.out.println("시험 점수를 등록합니다...");
        scoreStore.add(newscore);
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId, subjectId;
        int round, scoreIdx = 0;

        Score findScore = null;

        while (true) {
            System.out.println("관리할 수강생의 고유번호를 입력하시오.");
            Optional<String> student = validateStudentId();

            if (student.isPresent()) {
                studentId = student.get();
                break;
            }

            if (printEscapeCondition()) return;
        }

        while (true) {
            SubjectInfo.printSubjectInfo();
            System.out.println("수정할 과목의 고유번호를 입력하시오.");
            Optional<String> subject = validateSubjectIdIsExistScoreList(studentId);

            if(subject.isPresent()) {
                subjectId = subject.get();
                break;
            } else {
            if (printEscapeCondition()) return;
            }
        }

        while (true) {
            try {
                System.out.println("수정할 회차를 입력하시오.");
                round = sc.nextInt();

                if (round <= 0 || round > 10) {
                    System.out.println("\n회차는 1 ~ 10까지만 입력 가능합니다.");
                } else {
                    for (int i = 0; i < scoreStore.size(); i++) {
                        Score score = scoreStore.get(i);
                        if (score.getStudentId().equals(studentId)
                                && score.getSubjectId().equals(subjectId)
                                && score.getRound() == round) {
                            scoreIdx = i;
                            findScore = score;
                        }
                    }

                    if (findScore == null) {
                        System.out.println("\n해당 회차의 점수는 존재하지 않습니다.");
                        if (printEscapeCondition()) return;
                    } else break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n회차는 숫자만 입력할 수 있습니다.");
                sc.nextLine();
            }
        }

        int score;
        while (true) {
            System.out.println("수정할 점수를 입력하시오.");
            Optional<Integer> opScore = validateScore();

            if(opScore.isPresent()) {
                score = opScore.get();
                break;
            }
            if(printEscapeCondition()) return;
        }

        System.out.println("시험 점수를 수정합니다...");

        findScore.setScore(score);
        findScore.setGrade(scoreInToGrade(score, SubjectInfo.getSubjectType(subjectId)));

        scoreStore.set(scoreIdx, findScore);

        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId, subjectId;

        while (true) {
            System.out.println("관리할 수강생의 고유번호를 입력하시오.");
            Optional<String> student = validateStudentId();

            if (student.isPresent()) {
                studentId = student.get();
                break;
            }

            if (printEscapeCondition()) return;
        }

        while (true) {
            SubjectInfo.printSubjectInfo();
            System.out.println("등급을 확인할 과목의 고유번호를 입력하시오.");
            Optional<String> subject = validateSubjectIdIsExistScoreList(studentId);

            if(subject.isPresent()) {
                subjectId = subject.get();
                break;
            } else {
                if (printEscapeCondition()) return;
            }
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

    private static boolean printEscapeCondition() {
        System.out.println("계속하려면 'y'를 입력하세요. 종료하려면 다른 키를 입력하세요");
        String continueInput = sc.next();
        return !continueInput.equalsIgnoreCase("y");
    }

    private static Grade scoreInToGrade(int score, SubjectType type) {
        if (SubjectType.SUBJECT_TYPE_MANDATORY == type) {
            return Grade.mandatorySubjectGrade(score);
        } else {
            return Grade.choiceSubjectGrade(score);
        }
    }

    private static Optional<String> validateStudentId() {
        String studentId = sc.next();
        Optional<Student> student = studentStore.stream()
                .filter(std -> std.getStudentId().equals(studentId))
                .findFirst();

        if (student.isEmpty()) {
            System.out.println("\n해당 학생 ID를 찾을 수 없습니다.");
            return Optional.empty();
        }

        return Optional.of(studentId);
    }

    private static Optional<String> validateSubjectId(String studentId) {
        String subjectId = sc.next();
        SubjectType subjectType = SubjectInfo.getSubjectType(subjectId);
        int index = SubjectInfo.getSubjectIndex(subjectId);

        List<String> selectedSubjectList = studentStore.stream().filter(st -> st.getStudentId().equals(studentId))
                .map(st -> subjectType == SubjectType.SUBJECT_TYPE_MANDATORY ? st.getCompulsory() : st.getElective())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("\n해당 학생의 ID가 존재하지 않습니다."));

        return selectedSubjectList.contains(String.valueOf(index)) ? Optional.of(subjectId) : Optional.empty();
    }

    private static Optional<String> validateSubjectIdIsExistScoreList(String studentId) {
        String subjectId = sc.next();
        if (scoreStore.stream().filter(sc -> sc.getStudentId().equals(studentId)).noneMatch(sc -> sc.getSubjectId().equals(subjectId))) {
            System.out.println("\n해당 과목의 점수는 존재하지 않습니다.");
            return Optional.empty();
        }

        return Optional.of(subjectId);
    }

    private static Optional<Integer> validateScore() {
        int score;
        try {
            score = sc.nextInt();

            if (score <= 0 || score > 100) {
                System.out.println("\n점수는 0 ~ 100까지만 입력 가능합니다.");
            } else return Optional.of(score);
        } catch (InputMismatchException e) {
            System.out.println("\n점수는 숫자만 입력할 수 있습니다.");
            sc.nextLine();
        }

        return Optional.empty();
    }
}
