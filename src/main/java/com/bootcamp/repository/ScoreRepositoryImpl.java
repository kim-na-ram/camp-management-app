package com.bootcamp.repository;

import com.bootcamp.model.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreRepositoryImpl implements ScoreRepository {
    private final List<Score> scoreStore = new ArrayList<>();

    @Override
    public boolean isExistScoreByStudentIdAndSubjectId (String studentId, String subjectId) {
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
}