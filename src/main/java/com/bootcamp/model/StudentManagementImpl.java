package com.bootcamp.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentManagementImpl implements StudentManagement {

    /* 임시 (삭제 예정) */
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";
    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static List<Student> studentStore;
    private static List<String> compulsory;
    private static List<String> elective;
    private static Scanner sc;

    public StudentManagementImpl() {
        this.studentStore = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    //getter
    public List<String> getCompulsory() {
        return compulsory;
    }
    public List<String> getElective() {
        return elective;
    }

    @Override
    public void createStudent() {
        compulsory = new ArrayList<>();
        elective = new ArrayList<>();
        String studentName = createStudentName();
        createStudentMandatory();
        createStudentChoice();

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, getCompulsory(), getElective()); // 수강생 인스턴스 생성 예시 코드

        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
        System.out.println("현재 등록 하신 수강생 정보: ");
        System.out.println("ID: " + student.getStudentId());
        System.out.println("이름: " + student.getStudentName());
        System.out.println("필수 과목: " + student.getCompulsory());
        System.out.println("선택 과목: " + student.getElective());
    }

    //수강생 이름 등록
    public static String createStudentName() {
        sc.nextLine();
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.nextLine();
        return studentName;
    }
    // 수강생 필수 과목 등록
    public static void createStudentMandatory() {
        // 기능 구현 (필수 과목, 선택 과목)
        System.out.println("필수 과목에 해당하는 번호를 입력하세요. (1 ~ 5)");
        System.out.println("1. Java ||  2. 객체 지향    ||  3. Spring");
        System.out.println("4. JPA  ||  5. MySQL");
        System.out.println("0. 입력 종료");
        System.out.println("*최소 3과목 이상* (종료하려면 '0' 입력)...");
        System.out.println("필수 과목명: ");
        while (true) {
            try {
                int input = sc.nextInt();
                if (input == 0) {
                    if (compulsory.size() >= 3) {
                        break;
                    }
                    System.out.println("필수 과목은 최소 3과목 이상 입력해야 합니다.");
                } else {
                    SubjectInfo Subject = SubjectInfo.getMandatoryId(input);
                    if (Subject == null) {
                        System.out.println("해당 번호는 필수과목에 없습니다.");
                        System.out.println("다시 입력해주세요.");
                    } else {
                        if (!compulsory.contains(Subject.getSubjectName())) {
                            compulsory.add(Subject.getSubjectName());
                            System.out.println("현재 등록 된 필수 과목 : " + compulsory.toString());
                        } else {
                            System.out.println("중복 된 과목을 한번 더 선택 할 수 없습니다.");
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.nextLine();
            }
        }
    }
    //수강생 선택 과목 등록
    public static void createStudentChoice(){
        System.out.println("==================================");
        System.out.println("선택 과목에 해당하는 번호를 입력하세요. (1 ~ 4)");
        System.out.println("1. 디자인 패턴   ||  2. Spring Security");
        System.out.println("3. Redis       ||  4. MongoDB");
        System.out.println("0. 입력 종료");
        System.out.println("*최소 2과목 이상* (종료하려면 '0' 입력)...");
        System.out.println("선택 과목명: ");
        while (true) {
            try {
                int input = sc.nextInt();
                if (input == 0) {
                    if (elective.size() >= 2) {
                        break;
                    }
                    System.out.println("선택 과목은 최소 2과목 이상 입력해야 합니다.");
                } else {
                    SubjectInfo Subject = SubjectInfo.getChoice(input);
                    if (Subject == null) {
                        System.out.println("해당 번호는 선택과목에 없습니다.");
                        System.out.println("다시 입력해주세요.");
                    } else {
                        if (!elective.contains(Subject.getSubjectName())) {
                            elective.add(Subject.getSubjectName());
                            System.out.println("현재 등록 된 선택 과목 : " + elective.toString());
                        } else {
                            System.out.println("중복 된 과목을 한번 더 선택 할 수 없습니다.");
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.nextLine();
            }
        }
    }
}
