package com.bootcamp.model;

import com.bootcamp.utils.SubjectType;

import java.util.Arrays;

import static com.bootcamp.utils.SubjectType.SUBJECT_TYPE_CHOICE;
import static com.bootcamp.utils.SubjectType.SUBJECT_TYPE_MANDATORY;

public enum SubjectInfo {
    JAVA(1, "Java", SUBJECT_TYPE_MANDATORY),
    OOP(2 , "객체 지향", SUBJECT_TYPE_MANDATORY),
    SPRING(3, "Spring", SUBJECT_TYPE_MANDATORY),
    JPA(4, "JPA", SUBJECT_TYPE_MANDATORY),
    MYSQL(5, "MySQL", SUBJECT_TYPE_MANDATORY),
    DESIGN(6, "디자인 패턴", SUBJECT_TYPE_CHOICE),
    SPRING_SECURITY(7, "Spring Security", SUBJECT_TYPE_CHOICE),
    REDIS(8, "Redis", SUBJECT_TYPE_CHOICE),
    MONGODB(9, "MongoDB", SUBJECT_TYPE_CHOICE);

    private final int subjectId;
    private final String subjectName;
    private final SubjectType subjectType;

    SubjectInfo(int subjectId, String subjectName, SubjectType subjectType) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public SubjectType getSubjectType() { return subjectType; }

    public static SubjectInfo getMandatoryId(int subjectId) throws RuntimeException{
        for(SubjectInfo id : values()) {
            if(id.subjectId == subjectId && subjectId < 6){
                return id;
            }
        }
        throw new RuntimeException("맞지 않는 번호 입니다.");
    }

    public static SubjectInfo getChoice(int subjectId) {
        for (SubjectInfo id : values()) {
            if (id.subjectId == subjectId + 5 && subjectId < 5){
                return id;
            }
        }
        throw new RuntimeException("맞지 않는 번호 입니다.");
    }

    public static boolean isExistSubjectId(int subjectId) {
        return Arrays.stream(values()).anyMatch(sbj -> sbj.subjectId == subjectId);
    }

    public static void printSubjectInfo() {
        System.out.println("-------------------------------");
        for(SubjectInfo sbjInfo : values()) {
            System.out.println(sbjInfo.subjectId + "번 " + sbjInfo.subjectName);
        }
        System.out.println("-------------------------------");
    }

    public static SubjectType getSubjectType(int subjectId) {
        for (SubjectInfo sbjInfo : values()) {
            if (sbjInfo.subjectId == subjectId) {
                return sbjInfo.subjectType;
            }
        }
        throw new RuntimeException("존재하지 않는 과목입니다.");
    }
}

