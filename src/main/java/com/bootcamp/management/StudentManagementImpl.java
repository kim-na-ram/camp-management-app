package com.bootcamp.management;

import com.bootcamp.model.Student;
import com.bootcamp.model.SubjectInfo;
import com.bootcamp.model.SubjectType;

import java.util.*;

import static com.bootcamp.utils.Utils.INDEX_TYPE_STUDENT;
import static com.bootcamp.utils.Utils.sequence;

public class StudentManagementImpl implements StudentManagement {

    private static List<Student> studentStore;
    private static List<String> compulsory;
    private static List<String> elective;

    private final static Scanner sc = new Scanner(System.in);

    public StudentManagementImpl() {
        this.studentStore = new ArrayList<>();
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

    @Override
    public void inquireStudent() {                 //예외처리 생각해보기
        System.out.println("1. 수강생 전체 조회");
        System.out.println("2. 수강생 ID 조회");
        System.out.println("3. 수강생 이름 조회");

        // 기능 구현
        int select = sc.nextInt();

        if (select == 1) {
            System.out.println("\n수강생 목록을 조회합니다...");
            if (studentStore.isEmpty()) {
                System.out.println("등록된 수강생이 존재하지 않습니다");
            } else {
                // 수강생 목록을 sort 를 사용하여 정렬하고
                // comparator 를 사용해 기준으로 오름차순 정렬
                studentStore.sort(Comparator.comparing(Student::getStudentName));

                // 정렬된 수강생 목록 출력
                for (Student student : studentStore) {
                    System.out.println("이름: " + student.getStudentName());
                    System.out.println("ID: " + student.getStudentId());
                    System.out.println(); // 수강생 정보 구분을 위해 빈 줄 추가
                }

                System.out.println("수강생 목록 조회 성공!");
            }
        } else if (select == 2) {
            System.out.println("조회할 수강생의 ID를 입력해 주세요: ");
            String studentId = sc.next();

            for (Student student : studentStore) {
                if (student.getStudentId().equalsIgnoreCase(studentId)) {
                    System.out.println();
                    System.out.println("이름: " + student.getStudentName());
                    System.out.println("ID: " + student.getStudentId());

                } else if (studentId.isEmpty()) {
                    System.out.println("등록된 수강생이 존재하지 않습니다");
                }
            }
        } else if (select == 3) {
            System.out.println("조회할 수강생의 이름을 입력해 주세요: ");
            String studentName = sc.next();
            for (Student student : studentStore) {
                if (student.getStudentName().equalsIgnoreCase(studentName)) {
                    System.out.println();
                    System.out.println("이름: " + student.getStudentName());
                    System.out.println("ID: " + student.getStudentId());
                } else if (studentName.isEmpty()) {
                    System.out.println("등록된 수강생이 존재하지 않습니다");
                }
            }
        }
    }

    @Override
    public boolean isExistStudent(String studentId) {
        return studentStore.stream().anyMatch(student -> student.getStudentId().equals(studentId));
    }

    @Override
    public List<String> getSelectedSubjectList(String studentId, SubjectType subjectType) {
        return studentStore.stream().filter(st -> st.getStudentId().equals(studentId))
                .map(st -> subjectType == SubjectType.SUBJECT_TYPE_MANDATORY ? st.getCompulsory() : st.getElective())
                .findFirst()
                .get();
    }
}