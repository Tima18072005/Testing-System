package com.testing_system.tester.control_module.core.ports.first.impl;


import com.testing_system.tester.control_module.core.domain.Student;
import com.testing_system.tester.control_module.core.domain.StudentStatus;
import com.testing_system.tester.control_module.core.ports.first.GroupQueryUseCase;
import com.testing_system.tester.control_module.core.ports.first.StudentQueryUseCase;
import com.testing_system.tester.control_module.core.ports.first.StudentCommandUseCase;
import com.testing_system.tester.control_module.core.ports.second.GroupDrivenUseCase;
import com.testing_system.tester.control_module.core.ports.second.StudentDrivenUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.time.temporal.ChronoUnit.DAYS;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/*
 Сервис-оркестратор для изменения данных внутри учетной записи студента
 */
public class StudentCommandService implements StudentCommandUseCase {

    // Реализация использует первичные и вторичные порты
    private final StudentQueryUseCase firstPort;
    private final GroupQueryUseCase groupFirstPort;
    private final StudentDrivenUseCase secondPort;

    private final Logger logger = LoggerFactory.getLogger(StudentCommandService.class);



    public StudentCommandService(StudentQueryUseCase firstPort, GroupQueryUseCase groupFirstPort, StudentDrivenUseCase secondPort) {
        this.firstPort = firstPort;
        this.groupFirstPort = groupFirstPort;
        this.secondPort = secondPort;

    }



    // Вызывает метод первичного порта, который кидает NoStudentException()
    @Override
    public void changeGroup(Integer currentStudentId, String newGroup) {

        var student = firstPort.getStudentById(currentStudentId);

        if (!groupFirstPort.findGroup(newGroup))
            throw new IllegalArgumentException("Error! You can't change group to non-existent! Student ID: %s. Old group: %og. Non-existent group: %ng."
                    .formatted(currentStudentId, student.getStudentGroup(), newGroup));

        student.setStudentGroup(newGroup);
        secondPort.saveStudent(student);
        logger.info("Student with id {} changed group! New group: {}", currentStudentId, newGroup );

    }



    // Вызывает метод первичного порта, который кидает NoGroupException()
    @Override
    public void promoteStudents(String currentGroupNumber, List<Student> groupStudents){


        // 150 - самый худший случай
        if (groupStudents.size()>150)
            throw new IllegalArgumentException("Error! Maximal student's batch to promote is 150!");

        List<Student> toNothing = new ArrayList<>();
        List<Student> toPromote = new ArrayList<>();
        List<Student> toGraduate = new ArrayList<>();
        List<Integer> toDelete = new ArrayList<>();

        int maxCourse = groupFirstPort.getGroupByName(currentGroupNumber).getNumberOfCourses();

        for (Student student: groupStudents){

            if (DAYS.between(student.getStartLearningDate(), LocalDate.now()) < 365)
                toNothing.add(student);

            else if (DAYS.between(student.getStartLearningDate(), LocalDate.now())>=365*2)
                toDelete.add(student.getStudentId());

            else{

                var currentGroup = student.getStudentGroup();
                var currentName = currentGroup.substring(0,currentGroup.indexOf("-"));
                var currentNumber = Integer.parseInt(currentGroup.substring(currentGroup.lastIndexOf("-")+1));

                if(currentNumber/100 == maxCourse){

                    if (student.getStudStatus().equals(StudentStatus.ACTIVE)) student.setStudStatus(StudentStatus.SUCCESSFULLY_EXPELLED);

                    toGraduate.add(student);
                }
                else{
                    var newGroup = currentName + "-" + String.valueOf(currentNumber+100);
                    student.setStudentGroup(newGroup);
                    student.setStartLearningDate();
                    toPromote.add(student);
                }
            }
        }

        logger.info("{} students doesn't changed, because to early to promote or graduate!", toNothing.size());

        if (!toPromote.isEmpty()) {
            secondPort.saveStudent(toPromote);
            logger.info("{} students were promoted to next course!", toPromote.size());
        }

        if (!toGraduate.isEmpty()) {
            secondPort.saveStudent(toGraduate);
            logger.info("{} students were graduated!", toGraduate.size());
        }

        if (!toDelete.isEmpty()) {
            secondPort.deleteStudent(toDelete);
            logger.info("{} students were deleted!", toDelete.size());
        }
    }



    // Использует метод первичного порта, который кидает NoStudentException()
    @Override
    public void activeStudent(Integer currentStudentId) {
        var student = firstPort.getStudentById(currentStudentId);
        student.setStudStatus(StudentStatus.ACTIVE);
        secondPort.saveStudent(student);
        logger.info("Student with id {} is active!", currentStudentId);
    }



    // Использует метод первичного порта, который кидает NoStudentException()
    @Override
    public void graduatedStudent(Integer currentStudentId) {
        var student = firstPort.getStudentById(currentStudentId);
        student.setStudStatus(StudentStatus.SUCCESSFULLY_EXPELLED);
        secondPort.saveStudent(student);
        logger.info("Student with id {} was successfully expelled!", currentStudentId);
    }



    // Использует метод первичного порта, который кидает NoStudentException()
    @Override
    public void expelledStudent(Integer currentStudentId) {

        var student = firstPort.getStudentById(currentStudentId);
        student.setStudStatus(StudentStatus.EXPELLED);
        secondPort.saveStudent(student);
        logger.info("Student with id {} was expelled!", currentStudentId);
    }



    // Использует метод первичного порта, который кидает NoStudentException()
    @Override
    public void debtorStudent(Integer currentStudentId) {

        var student = firstPort.getStudentById(currentStudentId);
        student.setStudStatus(StudentStatus.DEBTOR);
        secondPort.saveStudent(student);
        logger.info("Student with id {} is debtor!", currentStudentId);
    }
}
