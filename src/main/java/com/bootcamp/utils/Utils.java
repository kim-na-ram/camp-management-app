package com.bootcamp.utils;

import java.util.Scanner;

public class Utils {
    // index 관리 필드
    public static int studentIndex = 1;
    public static final String INDEX_TYPE_STUDENT = "ST";
    public static int scoreIndex = 1;
    public static final String INDEX_TYPE_SCORE = "SC";

    // index 자동 증가
    public static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                return INDEX_TYPE_STUDENT + studentIndex++;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex++;
            }
        }
    }

    public static boolean printEscapeCondition(Scanner scanner) {
        System.out.println("계속하려면 'y'를 입력하세요. 종료하려면 다른 키를 입력하세요");
        String continueInput = scanner.next();
        return !continueInput.equalsIgnoreCase("y");
    }
}