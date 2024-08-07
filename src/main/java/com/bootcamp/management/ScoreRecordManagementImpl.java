package com.bootcamp.management;

import com.bootcamp.exception.ManagementException;
import com.bootcamp.model.*;
import com.bootcamp.repository.ScoreRepository;
import com.bootcamp.repository.ScoreRepositoryImpl;
import com.bootcamp.repository.StudentRepository;
import com.bootcamp.utils.Utils;
import com.bootcamp.validation.ScoreValidator;

import java.util.*;

public class ScoreRecordManagementImpl implements ScoreRecordManagement {
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;

    // 스캐너
    private final Scanner sc = new Scanner(System.in);

    public ScoreRecordManagementImpl() {
        this.studentRepository = new StudentRepository();
        this.scoreRepository = new ScoreRepositoryImpl();
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

            if (studentRepository.isExistStudentById(studentId)) {
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
                List<String> selectedSubjectList = studentRepository.getSelectedSubjectByStudentIdAndSubjectType(studentId, subjectType);

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

                boolean isExistRound = scoreRepository.isExistScoreByStudentIdAndSubjectIdAndRound(studentId, subjectId, round);

                if (ScoreValidator.validateRound(round)
                       && !isExistRound) {
                    newScore.setRound(round);
                    break;
                }

                if (isExistRound) System.out.println("이미 등록되어 있는 회차는 등록할 수 없습니다.");
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
        scoreRepository.addNewScore(newScore);
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    @Override
    public void updateRoundScoreBySubject() {
        String studentId, subjectId;
        int round, index;
        Score findScore;

        while (true) {
            System.out.println("관리할 수강생의 고유번호를 입력하시오.");
            studentId = sc.next();

            if (studentRepository.isExistStudentById(studentId)) break;

            System.out.println("\n해당 학생 ID를 찾을 수 없습니다.");
            if (Utils.printEscapeCondition(sc)) return;
        }

        while (true) {
            SubjectInfo.printSubjectInfo();
            System.out.println("수정할 과목의 고유번호를 입력하시오.");
            subjectId = sc.next();

            boolean isExistSubjectId = ScoreValidator.validateSubjectIdIsExistScoreList(subjectId);
            boolean isExistScore = scoreRepository.isExistScoreByStudentIdAndSubjectId(studentId, subjectId);

            if (isExistSubjectId && isExistScore) break;
            if (isExistSubjectId) System.out.println("\n해당 과목의 성적은 존재하지 않습니다.");

            if (Utils.printEscapeCondition(sc)) return;
        }

        while (true) {
            try {
                System.out.println("수정할 회차를 입력하시오.");
                round = sc.nextInt();

                boolean isValidateRound = ScoreValidator.validateRound(round);
                boolean isExistScore = scoreRepository.isExistScoreByStudentIdAndSubjectIdAndRound(studentId, subjectId, round);

                if (isValidateRound && isExistScore) {
                    findScore = scoreRepository.getScoreByStudentIdAndSubjectIdAndRound(studentId, subjectId, round).get();
                    break;
                }
                if (isValidateRound) System.out.println("\n해당 회차의 점수는 존재하지 않습니다.");
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

        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    @Override
    public void inquireRoundGradeBySubject() {
        String studentId, subjectId;

        while (true) {
            System.out.println("관리할 수강생의 고유번호를 입력하시오.");
            studentId = sc.next();

            if (studentRepository.isExistStudentById(studentId)) break;

            System.out.println("\n해당 학생 ID를 찾을 수 없습니다.");
            if (Utils.printEscapeCondition(sc)) return;
        }

        while (true) {
            SubjectInfo.printSubjectInfo();
            System.out.println("등급을 확인할 과목의 고유번호를 입력하시오.");
            subjectId = sc.next();

            boolean isExistSubjectId = ScoreValidator.validateSubjectIdIsExistScoreList(subjectId);
            boolean isExistScore = scoreRepository.isExistScoreByStudentIdAndSubjectId(studentId, subjectId);

            if (isExistSubjectId && isExistScore) break;
            if (isExistSubjectId) System.out.println("\n해당 과목의 성적은 존재하지 않습니다.");
            if (Utils.printEscapeCondition(sc)) return;
        }

        System.out.println("회차별 등급을 조회합니다...");

        scoreRepository.getScoreStoreByStudentIdAndSubjectId(studentId, subjectId)
                .forEach(score -> System.out.println(score.getRound() + "회차 등급 : " + score.getGrade()));

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
