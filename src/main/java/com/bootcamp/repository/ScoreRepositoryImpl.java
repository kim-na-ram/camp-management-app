package com.bootcamp.repository;

import com.bootcamp.model.Grade;
import com.bootcamp.model.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bootcamp.utils.Utils.INDEX_TYPE_SCORE;
import static com.bootcamp.utils.Utils.sequence;

public class ScoreRepositoryImpl implements ScoreRepository {
    private final List<Score> scoreStore = new ArrayList<>();

    @Override
    public boolean isExistScoreByStudentIdAndSubjectId(String studentId, String subjectId) {
        return scoreStore.stream().filter(sc -> sc.getStudentId().equals(studentId)).anyMatch(sc -> sc.getSubjectId().equals(subjectId));
    }

    @Override
    public boolean isExistScoreByStudentIdAndSubjectIdAndRound(String studentId, String subjectId, int round) {
        return scoreStore.stream().anyMatch(sc -> sc.getStudentId().equals(studentId)
                && sc.getSubjectId().equals(subjectId)
                && sc.getRound() == round);
    }

    @Override
    public List<Score> getScoreStore() {
        return scoreStore;
    }

    @Override
    public List<Score> getScoreStoreByStudentIdAndSubjectId(String studentId, String subjectId) {
        return scoreStore.stream().filter(sc -> sc.getStudentId().equals(studentId)
                && sc.getSubjectId().equals(subjectId)).toList();
    }

    @Override
    public Optional<Score> getScoreByStudentIdAndSubjectIdAndRound(String studentId, String subjectId, int round) {
        return scoreStore.stream()
                .filter(sc -> sc.getStudentId().equals(studentId)
                        && sc.getSubjectId().equals(subjectId)
                        && sc.getRound() == round)
                .findFirst();
    }

    @Override
    public void addNewScore(Score score) {
        scoreStore.add(score);
    }

    // 테스트 데이터 세팅, dev 에는 올라가지 않을 예정
    @Override
    public void addTestData() {
        scoreStore.addAll(
                List.of(
                        new Score(
                                sequence(INDEX_TYPE_SCORE),
                                "ST1",
                                "SU1",
                                64,
                                1,
                                Grade.F
                        ),
                        new Score(
                                sequence(INDEX_TYPE_SCORE),
                                "ST2",
                                "SU1",
                                95,
                                1,
                                Grade.A
                        ),
                        new Score(
                                sequence(INDEX_TYPE_SCORE),
                                "ST1",
                                "SU1",
                                64,
                                2,
                                Grade.F
                        ),
                        new Score(
                                sequence(INDEX_TYPE_SCORE),
                                "ST1",
                                "SU8",
                                96,
                                3,
                                Grade.A
                        ),
                        new Score(
                                sequence(INDEX_TYPE_SCORE),
                                "ST1",
                                "SU1",
                                64,
                                3,
                                Grade.F
                        ),
                        new Score(
                                sequence(INDEX_TYPE_SCORE),
                                "ST2",
                                "SU2",
                                75,
                                1,
                                Grade.D
                        ),
                        new Score(
                                sequence(INDEX_TYPE_SCORE),
                                "ST2",
                                "SU8",
                                80,
                                1,
                                Grade.B
                        )
                )
        );
    }
}