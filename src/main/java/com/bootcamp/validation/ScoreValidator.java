package com.bootcamp.validation;

import com.bootcamp.exception.ManagementException;
import com.bootcamp.model.Score;
import com.bootcamp.model.Student;
import com.bootcamp.model.SubjectInfo;
import com.bootcamp.model.SubjectType;

import java.util.List;
import java.util.Optional;

public class ScoreValidator {
    public static boolean validateSubjectId(List<String> selectedSubjectList, String subjectId) {
        String subjectName = SubjectInfo.getSubjectName(subjectId);
        return selectedSubjectList.contains(subjectName);
    }

    public static boolean validateSubjectIdIsExistScoreList(List<Score> scoreStore, String studentId, String subjectId) {
        if (!SubjectInfo.isExistSubject(subjectId)) {
            System.out.println("\n존재하지 않는 과목입니다.");
            return false;
        }
        if (scoreStore.stream().filter(sc -> sc.getStudentId().equals(studentId)).noneMatch(sc -> sc.getSubjectId().equals(subjectId))) {
            System.out.println("\n해당 과목의 점수는 존재하지 않습니다.");
            return false;
        }

        return true;
    }

    public static boolean validateRound(List<Score> scoreStore, String studentId, String subjectId, int round) {
        if (round <= 0 || round > 10) {
            throw new ManagementException("회차는 1 ~ 10까지만 입력 가능합니다.");
        } else {
            return scoreStore.stream().anyMatch(sc -> sc.getStudentId().equals(studentId)
                    && sc.getSubjectId().equals(subjectId)
                    && sc.getRound() == round);
        }
    }

    public static boolean validateScore(int score) {
        if (score <= 0 || score > 100) {
            System.out.println("\n점수는 0 ~ 100까지만 입력 가능합니다.");
            return false;
        }

        return true;
    }
}
