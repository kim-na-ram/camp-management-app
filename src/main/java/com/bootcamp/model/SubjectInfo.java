package com.bootcamp.model;

public enum SubjectInfo {
    JAVA(1, "Java"),
    OOP(2 , "객체 지향"),
    SPRING(3, "Spring"),
    JPA(4, "JPA"),
    MYSQL(5, "MySQL"),
    DESIGN(6, "디자인 패턴"),
    SPRING_SECURITY(7, "Spring Security"),
    REDIS(8, "Redis"),
    MONGODB(9, "MongoDB");

    private final int subjectId;
    private final String subjectName;

    SubjectInfo(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public static SubjectInfo getMandatoryId(int subjectId) {
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
}

