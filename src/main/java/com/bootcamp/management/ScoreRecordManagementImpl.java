package com.bootcamp.management;

import com.bootcamp.exception.ManagementException;
import com.bootcamp.model.*;
import com.bootcamp.utils.Utils;
import com.bootcamp.validation.ScoreValidator;

import java.util.*;

public class ScoreRecordManagementImpl implements ScoreRecordManagement {
    private final StudentManagement studentManagement;

    // 점수 저장 리스트
    private final List<Score> scoreStore;

    // 스캐너
    private final Scanner sc = new Scanner(System.in);

    public ScoreRecordManagementImpl(StudentManagement studentManagement) {
        this.scoreStore = new ArrayList<>();
        this.studentManagement = studentManagement;
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    @Override
    public void createScore() {
        SubjectType subjectType;
        String studentId, subjectId;
        int round;

        Score newScore = new Score(Utils.sequence(Utils.INDEX_TYPE_SCORE));
        while (true) {
            System.out.println("관리할 수강생의 번호를 입력하시오...");
            studentId = sc.next();

            if (studentManagement.isExistStudent(studentId)) {
                newScore.setStudentId(studentId);
                break;
            }

            System.out.println("해당 학생 ID를 찾을 수 없습니다.");
            if (Utils.printEscapeCondition(sc)) return;
        }

        while (true) {
            try {
                SubjectInfo.printSubjectInfo();
                System.out.println("관리할 과목의 고유번호를 입력하시오...");
                subjectId = sc.next();

                subjectType = SubjectInfo.getSubjectType(subjectId);
                List<String> selectedSubjectList = studentManagement.getSelectedSubjectList(studentId, subjectType);

                if (ScoreValidator.validateSubjectId(selectedSubjectList, subjectId)) {
                    newScore.setSubjectId(subjectId);
                    break;
                }

                System.out.println("해당 과목은 선택되지 않아 등록할 수 없습니다.");
                if (Utils.printEscapeCondition(sc)) return;
            } catch (ManagementException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("등록할 회차를 입력하시오...");
                round = sc.nextInt();

                if (!ScoreValidator.validateRound(scoreStore, studentId, subjectId, round)) {
                    newScore.setRound(round);
                    break;
                }

                System.out.println("이미 등록되어 있는 회차는 등록할 수 없습니다.");
                if (Utils.printEscapeCondition(sc)) return;
            } catch (InputMismatchException e) {
                System.out.println("\n회차는 숫자만 입력할 수 있습니다.");
                sc.nextLine();
            } catch (ManagementException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

        while (true) {
            System.out.println("등록할 점수를 입력하시오");
            int score;
            try {
                score = sc.nextInt();
                if (ScoreValidator.validateScore(score)) {
                    newScore.setScore(score);
                    newScore.setGrade(scoreToGrade(score, subjectType));
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n점수는 숫자만 입력할 수 있습니다.");
                sc.nextLine();
            }

            if (Utils.printEscapeCondition(sc)) return;
        }

        System.out.println("시험 점수를 등록합니다...");
        scoreStore.add(newScore);
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    @Override
    public void updateRoundScoreBySubject() {
        String studentId, subjectId;
        int round, scoreIdx;
        Score findScore;

        while (true) {
            System.out.println("관리할 수강생의 고유번호를 입력하시오.");
            studentId = sc.next();

            if (studentManagement.isExistStudent(studentId)) break;

            System.out.println("\n해당 학생 ID를 찾을 수 없습니다.");
            if (Utils.printEscapeCondition(sc)) return;
        }

        while (true) {
            SubjectInfo.printSubjectInfo();
            System.out.println("수정할 과목의 고유번호를 입력하시오.");
            subjectId = sc.next();

            if (ScoreValidator.validateSubjectIdIsExistScoreList(scoreStore, studentId, subjectId)) break;
            if (Utils.printEscapeCondition(sc)) return;
        }

        while (true) {
            try {
                System.out.println("수정할 회차를 입력하시오.");
                round = sc.nextInt();

                // capturing lambda 로 인한 effectively final 변수 선언
                String finalStudentId = studentId;
                String finalSubjectId = subjectId;
                int finalRound = round;
                if (ScoreValidator.validateRound(scoreStore, studentId, subjectId, round)) {
                    findScore = scoreStore.stream()
                            .filter(sc -> sc.getStudentId().equals(finalStudentId)
                                    && sc.getSubjectId().equals(finalSubjectId)
                                    && sc.getRound() == finalRound)
                            .findFirst()
                            .get();
                    scoreIdx = scoreStore.indexOf(findScore);
                    break;
                }

                System.out.println("\n해당 회차의 점수는 존재하지 않습니다.");
                if (Utils.printEscapeCondition(sc)) return;
            } catch (InputMismatchException e) {
                System.out.println("\n회차는 숫자만 입력할 수 있습니다.");
                sc.nextLine();
            } catch (ManagementException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

        int score;
        while (true) {
            System.out.println("수정할 점수를 입력하시오.");
            try {
                score = sc.nextInt();
                if (ScoreValidator.validateScore(score)) break;
            } catch (InputMismatchException e) {
                System.out.println("\n점수는 숫자만 입력할 수 있습니다.");
                sc.nextLine();
            }

            if (Utils.printEscapeCondition(sc)) return;
        }

        System.out.println("시험 점수를 수정합니다...");

        findScore.setScore(score);
        findScore.setGrade(scoreToGrade(score, SubjectInfo.getSubjectType(subjectId)));

        scoreStore.set(scoreIdx, findScore);

        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    @Override
    public void inquireRoundGradeBySubject() {
        String studentId, subjectId;

        while (true) {
            System.out.println("관리할 수강생의 고유번호를 입력하시오.");
            studentId = sc.next();

            if (studentManagement.isExistStudent(studentId)) break;

            System.out.println("\n해당 학생 ID를 찾을 수 없습니다.");
            if (Utils.printEscapeCondition(sc)) return;
        }

        while (true) {
            SubjectInfo.printSubjectInfo();
            System.out.println("등급을 확인할 과목의 고유번호를 입력하시오.");
            subjectId = sc.next();

            if (ScoreValidator.validateSubjectIdIsExistScoreList(scoreStore, studentId, subjectId)) break;
            if (Utils.printEscapeCondition(sc)) return;
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

    private Grade scoreToGrade(int score, SubjectType type) {
        if (SubjectType.SUBJECT_TYPE_MANDATORY == type) {
            return Grade.mandatorySubjectGrade(score);
        } else {
            return Grade.choiceSubjectGrade(score);
        }
    }
}
