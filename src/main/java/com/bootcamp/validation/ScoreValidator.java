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

    public static boolean validateSubjectIdIsExistScoreList(String subjectId) {
        if (!SubjectInfo.isExistSubject(subjectId)) {
            System.out.println("\n존재하지 않는 과목입니다.");
            return false;
        }

        return true;
    }

    public static boolean validateRound(int round) {
        if (round <= 0 || round > 10) {
            System.out.println("회차는 1 ~ 10까지만 입력 가능합니다.");
            return false;
        }
        return true;
    }

    public static boolean validateScore(int score) {
        if (score <= 0 || score > 100) {
            System.out.println("\n점수는 0 ~ 100까지만 입력 가능합니다.");
            return false;
        }

        return true;
    }
}
