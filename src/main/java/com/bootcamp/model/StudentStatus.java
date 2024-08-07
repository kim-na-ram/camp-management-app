package com.bootcamp.model;

public enum StudentStatus {
    GREEN(1, "Green"),
    YELLOW(2, "Yellow"),
    RED(3, "Red");


    private final int statusNum;
    private final String statusName;

    StudentStatus(int statusNum, String statusName) {
        this.statusNum = statusNum;
        this.statusName = statusName;
    }

    public static StudentStatus getStatus(int statusNum) {
        for(StudentStatus num : values()){
            if (num.statusNum == statusNum) {
                return num;
            }
        }
        return null;
    }

    /* getter */
    public int getStatusNum() {
        return statusNum;
    }
    public String getStatusName() {
        return statusName;
    }
}
