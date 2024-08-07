package com.bootcamp.repository;

import com.bootcamp.model.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ScoreRepository {
    boolean isExistScoreByStudentIdAndSubjectId (String studentId, String subjectId);
    boolean isExistScoreByStudentIdAndSubjectIdAndRound(String studentId, String subjectId, int round);
    public List<Score> getScoreStore();
    public List<Score> getScoreStoreByStudentIdAndSubjectId(String studentId, String subjectId);
    public Optional<Score> getScoreByStudentIdAndSubjectIdAndRound(String studentId, String subjectId, int round);
    public void addNewScore(Score score);
}
