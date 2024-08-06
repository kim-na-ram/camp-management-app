package com.bootcamp.model;

import java.util.Arrays;

import static com.bootcamp.model.SubjectType.SUBJECT_TYPE_CHOICE;
import static com.bootcamp.model.SubjectType.SUBJECT_TYPE_MANDATORY;

public enum SubjectInfo {
    /* 필수 과목 */
    JAVA("SU1", "Java", SUBJECT_TYPE_MANDATORY, 1),
    OOP("SU2", "객체 지향", SUBJECT_TYPE_MANDATORY, 2),
    SPRING("SU3", "Spring", SUBJECT_TYPE_MANDATORY, 3),
    JPA("SU4", "JPA", SUBJECT_TYPE_MANDATORY, 4),
    MYSQL("SU5", "MySQL", SUBJECT_TYPE_MANDATORY, 5),
    /* 선택 과목 */
    DESIGN("SU6", "디자인 패턴", SUBJECT_TYPE_CHOICE, 1),
    SPRING_SECURITY("SU7", "Spring Security", SUBJECT_TYPE_CHOICE, 2),
    REDIS("SU8", "Redis", SUBJECT_TYPE_CHOICE, 3),
    MONGODB("SU9", "MongoDB", SUBJECT_TYPE_CHOICE, 4);

    private final String subjectId;
    private final String subjectName;
    private final SubjectType subjectType;

    private final int index;

    SubjectInfo(String subjectId, String subjectName, SubjectType subjectType, int index) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.index = index;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public SubjectType getSubjectType() { return subjectType; }

    public int getIndex() { return index; }

    public static SubjectInfo getMandatoryId(int index) {
        for(SubjectInfo id : values()) {
            if(SUBJECT_TYPE_MANDATORY.equals(id.getSubjectType()) && id.index == index){
                return id;
            }
        }
        return null;
    }

    public static SubjectInfo getChoice(int index) {
        for (SubjectInfo id : values()) {
            if (SUBJECT_TYPE_CHOICE.equals(id.getSubjectType()) && id.index == index){
                return id;
            }
        }
        return null;
    }

    public static boolean isExistSubjectId(String subjectId) {
        return Arrays.stream(values()).anyMatch(sbj -> sbj.subjectId.equals(subjectId));
    }

    public static void printSubjectInfo() {
        System.out.println("-------------------------------");
        for(SubjectInfo sbjInfo : values()) {
            System.out.println(sbjInfo.subjectName + " ( 고유번호 : " +sbjInfo.subjectId + " )");
        }
        System.out.println("-------------------------------");
    }

    public static SubjectType getSubjectType(String subjectId) {
        for (SubjectInfo sbjInfo : values()) {
            if (sbjInfo.subjectId.equals(subjectId)) {
                return sbjInfo.subjectType;
            }
        }
        throw new RuntimeException("존재하지 않는 과목입니다.");
    }

    public static int getSubjectIndex(String subjectId) {
        for (SubjectInfo sbjInfo : values()) {
            if (sbjInfo.subjectId.equals(subjectId)) {
                return sbjInfo.index;
            }
        }
        throw new RuntimeException("존재하지 않는 과목입니다.");
    }
}

