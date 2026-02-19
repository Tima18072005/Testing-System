package com.testing_system.tester.control_module.infrastructure.mappers;

import com.testing_system.tester.control_module.core.domain.Student;
import com.testing_system.tester.control_module.infrastructure.dto.db.StudentEntity;
import com.testing_system.tester.control_module.infrastructure.dto.response.StudentFullDTO;
import com.testing_system.tester.control_module.infrastructure.dto.response.StudentSaveDTO;
import org.springframework.stereotype.Component;

/*
Маппер для доменной модели студента
Использует конструкторы Student, в которые передаются дата начала обучения и статус студента
 */
@Component
public class StudentMapper {

    public StudentEntity domainToEntity(Student currentStudent){

        if (currentStudent.getPatronymic()==null) return new StudentEntity(
                currentStudent.getStudentId(),
                currentStudent.getFirstName(),
                currentStudent.getLastName(),
                currentStudent.getStudentGroup(),
                currentStudent.getStudStatus(),
                currentStudent.getStartLearningDate()
        );

        return new StudentEntity(currentStudent.getStudentId(),
                currentStudent.getFirstName(),
                currentStudent.getLastName(),
                currentStudent.getPatronymic(),
                currentStudent.getStudentGroup(),
                currentStudent.getStudStatus(),
                currentStudent.getStartLearningDate());
    }

    public Student entityToDomain(StudentEntity currentStudentEntity){

        if (currentStudentEntity.getPatronymic()==null){
            return new Student(
                    currentStudentEntity.getStudentId(),
                    currentStudentEntity.getFirstName(),
                    currentStudentEntity.getLastName(),
                    currentStudentEntity.getStudentGroup(),
                    currentStudentEntity.getStudStatus(),
                    currentStudentEntity.getStartLearningDate()
            );
        }
        return new Student(
                currentStudentEntity.getStudentId(),
                currentStudentEntity.getFirstName(),
                currentStudentEntity.getLastName(),
                currentStudentEntity.getPatronymic(),
                currentStudentEntity.getStudentGroup(),
                currentStudentEntity.getStudStatus(),
                currentStudentEntity.getStartLearningDate()
        );


    }

    // Маппинг в DTO и обратно

    public Student saveDTOToDomain(StudentSaveDTO currentDTO){

        if (currentDTO.getPatronymic()==null) return new Student(
                currentDTO.getStudentId(),
                currentDTO.getFirstName(),
                currentDTO.getLastName(),
                currentDTO.getStudentGroup()
        );

        return new Student(
                currentDTO.getStudentId(),
                currentDTO.getFirstName(),
                currentDTO.getLastName(),
                currentDTO.getPatronymic(),
                currentDTO.getStudentGroup()
        );
    }

    public StudentFullDTO domainToFullDTO(Student currentStudent){
        if (currentStudent.getPatronymic()==null) return new StudentFullDTO(
                currentStudent.getStudentId(),
                currentStudent.getFirstName(),
                currentStudent.getLastName(),
                currentStudent.getStudentGroup(),
                currentStudent.getStudStatus(),
                currentStudent.getStartLearningDate()
        );

        return new StudentFullDTO(currentStudent.getStudentId(),
                currentStudent.getFirstName(),
                currentStudent.getLastName(),
                currentStudent.getPatronymic(),
                currentStudent.getStudentGroup(),
                currentStudent.getStudStatus(),
                currentStudent.getStartLearningDate());
    }

    // Возможно DTO в доменную так-же нужно

    public Student fullDTOToDomain(StudentFullDTO currentStudent){

        if (currentStudent.getPatronymic()==null) return new Student(
                currentStudent.getStudentId(),
                currentStudent.getFirstName(),
                currentStudent.getLastName(),
                currentStudent.getStudentGroup(),
                currentStudent.getStudStatus(),
                currentStudent.getStartLearningDate()
        );

        return new Student(currentStudent.getStudentId(),
                currentStudent.getFirstName(),
                currentStudent.getLastName(),
                currentStudent.getPatronymic(),
                currentStudent.getStudentGroup(),
                currentStudent.getStudStatus(),
                currentStudent.getStartLearningDate());
    }

}
