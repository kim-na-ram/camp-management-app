package com.bootcamp.model;

import com.bootcamp.utils.SubjectType;

import java.util.Arrays;

import static com.bootcamp.utils.SubjectType.SUBJECT_TYPE_CHOICE;
import static com.bootcamp.utils.SubjectType.SUBJECT_TYPE_MANDATORY;

public enum SubjectInfo {
    /* 필수 과목 */
    JAVA(1, "Java", SUBJECT_TYPE_MANDATORY),
    OOP(2 , "객체 지향", SUBJECT_TYPE_MANDATORY),
    SPRING(3, "Spring", SUBJECT_TYPE_MANDATORY),
    JPA(4, "JPA", SUBJECT_TYPE_MANDATORY),
    MYSQL(5, "MySQL", SUBJECT_TYPE_MANDATORY),
    /* 선택 과목 */
    DESIGN(1, "디자인 패턴", SUBJECT_TYPE_CHOICE),
    SPRING_SECURITY(2, "Spring Security", SUBJECT_TYPE_CHOICE),
    REDIS(3, "Redis", SUBJECT_TYPE_CHOICE),
    MONGODB(4, "MongoDB", SUBJECT_TYPE_CHOICE);

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

    public int getSubjectId() {
        return subjectId;
    }

    public SubjectType getSubjectType() { return subjectType; }

    public static SubjectInfo getMandatoryId(int subjectId) {
        for(SubjectInfo id : values()) {
            if(SUBJECT_TYPE_MANDATORY.equals(id.getSubjectType()) && id.getSubjectId() == subjectId){
                return id;
            }
        }
        return null;
    }

    public static SubjectInfo getChoice(int subjectId) {
        for (SubjectInfo id : values()) {
            if (SUBJECT_TYPE_CHOICE.equals(id.getSubjectType()) && id.getSubjectId() == subjectId){
                return id;
            }
        }
        return null;
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

