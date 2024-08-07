package com.bootcamp.management;

import com.bootcamp.model.Student;
import com.bootcamp.model.SubjectInfo;
import com.bootcamp.model.SubjectType;
import com.bootcamp.repository.ScoreRepository;
import com.bootcamp.repository.ScoreRepositoryImpl;
import com.bootcamp.repository.StudentRepository;
import com.bootcamp.repository.StudentRepositoryImpl;
import com.bootcamp.utils.Utils;

import java.util.*;

import static com.bootcamp.utils.Utils.INDEX_TYPE_STUDENT;
import static com.bootcamp.utils.Utils.sequence;

public class StudentManagementImpl implements StudentManagement {
    private StudentRepository studentRepository;
    private ScoreRepository scoreRepository;

    private final static Scanner sc = new Scanner(System.in);

    public StudentManagementImpl() {
        studentRepository = new StudentRepositoryImpl();
        scoreRepository = new ScoreRepositoryImpl();
    }

    @Override
    public void createStudent() {
        List<String> compulsory = new ArrayList<>();
        List<String> elective = new ArrayList<>();
        String studentName = createStudentName();
        createStudentMandatory(compulsory);
        createStudentChoice(elective);

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, compulsory, elective); // 수강생 인스턴스 생성 예시 코드

        studentRepository.addStudent(student);
        System.out.println("수강생 등록 성공!\n");
        System.out.println("현재 등록 하신 수강생 정보: ");
        System.out.println("ID: " + student.getStudentId());
        System.out.println("이름: " + student.getStudentName());
        System.out.println("필수 과목: " + student.getCompulsory());
        System.out.println("선택 과목: " + student.getElective());
    }

    //수강생 이름 등록
    public String createStudentName() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        return sc.next();
    }

    // 수강생 필수 과목 등록
    public void createStudentMandatory(List<String> compulsory) {
        // 기능 구현 (필수 과목, 선택 과목)
        System.out.println("==================================");
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
    public void createStudentChoice(List<String> elective) {
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

    @Override      // 수강생 조회
    public void inquireStudent() {
        System.out.println("1. 수강생 전체 조회");
        System.out.println("2. 수강생 ID 조회");
        System.out.println("3. 수강생 이름 조회");
        int select = sc.nextInt();
        boolean inquiry = false;

        List<Student> studentStore = studentRepository.getStudentStore();
        if (select == 1) {
            System.out.println("\n수강생 목록을 조회합니다...");
            if (studentStore.isEmpty()) {
                System.out.println("등록된 수강생이 존재하지 않습니다");
            } else {
                studentStore.sort(Comparator.comparing(Student::getStudentName));
                System.out.println("전체 수강생 조회를 완료했습니다.");
                for (Student student : studentStore) {
                    System.out.println();
                    System.out.println("이름: " + student.getStudentName());
                    System.out.println("ID: " + student.getStudentId());
                    System.out.println("필수 과목: " + student.getCompulsory());
                    System.out.println("선택 과목: " + student.getElective());
                    System.out.println("상태 : " + student.getStatus() );
                }
            }
        } else if (select == 2) {
            System.out.println("조회할 수강생의 ID를 입력해 주세요: ");
            String studentId = sc.next();

            inquiry = studentRepository.isExistStudentById(studentId);
            if(inquiry) {
                Student student = studentRepository.getStudentById(studentId).get();
                System.out.println();
                System.out.println("수강생 조회를 완료했습니다.");
                System.out.println("이름: " + student.getStudentName());
                System.out.println("ID: " + student.getStudentId());
                System.out.println("필수 과목: " + student.getCompulsory());
                System.out.println("선택 과목: " + student.getElective());
                System.out.println("상태 : " + student.getStatus() );
            } else {
                System.out.println("입력한 ID가 존재하지 않습니다");
            }
        } else if (select == 3) {
            // TODO 이 부분은 중복 이름이 있을 수도 있으니 List 로 받는 것이 맞을듯함
            System.out.println("조회할 수강생의 이름을 입력해 주세요: ");
            String studentName = sc.next();
            for (Student student : studentStore) {
                if (student.getStudentName().equalsIgnoreCase(studentName)) {
                    inquiry = true;
                    System.out.println();
                    System.out.println("수강생 조회를 완료했습니다.");
                    System.out.println("이름: " + student.getStudentName());
                    System.out.println("ID: " + student.getStudentId());
                    System.out.println("필수 과목: " + student.getCompulsory());
                    System.out.println("선택 과목: " + student.getElective());
                    System.out.println("상태 : " + student.getStatus() );
                }
            }if (!inquiry) {
                System.out.println("등록된 수강생이 존재하지 않습니다");
            }
        }
    }

    // TODO repository 메서드를 사용하도록 수정
    // 수강생 이름 수정
    public void modifyStudentName(){
        System.out.println("수정하실 수강생의 이름을 입력해 주세요: ");
        String studentName = sc.next();
        boolean named = false;

        List<Student> studentStore = studentRepository.getStudentStore();
        for (Student student : studentStore) {
            if (student.getStudentName().equalsIgnoreCase(studentName)) {
                named = true;
                System.out.println();
                System.out.println("현재 이름: " + student.getStudentName());
                System.out.println("새로운 이름을 입력해주세요: ");
                String newName = sc.next();
                student.setStudentName(newName);
                System.out.println("수정된 이름: " +student.getStudentName());
                System.out.println("이름 수정이 정상적으로 완료 되었습니다.");
            }
        }if (!named){
            System.out.println("등록된 수강생이 존재하지 않습니다");
        }
    }

    // 수강생 삭제
    @Override
    public void deleteStudent() {
        while (true) {
            System.out.println("삭제할 수강생의 ID를 입력하시오 (취소하려면 'exit' 입력)...");
            String studentId = sc.next();

            if ("exit".equalsIgnoreCase(studentId)) {
                System.out.println("삭제를 취소합니다.");
                return;
            }

            // 학생 ID로 학생 찾기
            if (studentRepository.isExistStudentById(studentId)) {
                if (studentRepository.removeStudentById(studentId)) {
                    // 점수 저장소에서 해당 수강생의 점수도 삭제
                    if (scoreRepository.removeScoresByStudentId(studentId)) {
                        System.out.println("수강생과 관련된 모든 점수를 삭제했습니다.");
                    }
                    System.out.println("수강생 삭제 성공!");
                    break;
                }
            } else {
                System.out.println("해당 수강생 ID를 찾을 수 없습니다.");
                if (Utils.printEscapeCondition(sc)) return;
            }
        }
    }
}
