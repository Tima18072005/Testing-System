package com.testing_system.tester.control_module.infrastructure.second_adapters;

import com.testing_system.tester.control_module.core.domain.Student;
import com.testing_system.tester.control_module.core.ports.second.StudentDrivenUseCase;
import com.testing_system.tester.control_module.infrastructure.dto.db.StudentEntity;
import com.testing_system.tester.control_module.infrastructure.mappers.StudentMapper;
import com.testing_system.tester.control_module.infrastructure.repos.StudentRepoInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
Вторичный адаптер для вторичной сущности студента
 */
@Service
public class StudentSecondAdapter implements StudentDrivenUseCase {


    private final StudentRepoInterface interfaceRepository;
    private final StudentMapper mapper;

    public StudentSecondAdapter(StudentRepoInterface interfaceRepository, StudentMapper mapper) {
        this.interfaceRepository = interfaceRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Student> getAllStudents() {

        List<StudentEntity> listOfEntity = interfaceRepository.findAll();

        if (listOfEntity.isEmpty()) return List.of();

        return listOfEntity.stream().map(mapper::entityToDomain).toList();
    }

    @Override
    public Optional<Student> getStudent(Integer currentId) {

        Optional<StudentEntity> currentEntity = interfaceRepository.findById(currentId);

        return currentEntity.map(mapper::entityToDomain);
    }

    @Override
    public void saveStudent(Student currentStudent) {

        interfaceRepository.save(mapper.domainToEntity(currentStudent));

    }

    @Override
    public void saveStudent(List<Student> currentStudents) {

        interfaceRepository.saveAll(currentStudents.stream().map(mapper::domainToEntity).toList());

    }

    @Override
    public void deleteStudent(Integer currentId) {
        interfaceRepository.deleteById(currentId);
    }

    @Override
    public void deleteStudent(List<Integer> currentIds) {
        interfaceRepository.deleteAllById(currentIds);
    }
}
