package com.testing_system.tester.control_module.infrastructure.second_adapters;

import com.testing_system.tester.control_module.core.domain.Employee;
import com.testing_system.tester.control_module.core.ports.second.EmployeeDrivenUseCase;
import com.testing_system.tester.control_module.infrastructure.dto.db.EmployeeEntity;
import com.testing_system.tester.control_module.infrastructure.mappers.EmployeeMapper;
import com.testing_system.tester.control_module.infrastructure.repos.EmployeeRepoInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
Вторичный адаптер для доменной модели сотрудника
 */
@Service
public class EmployeeSecondAdapter implements EmployeeDrivenUseCase {

    // Использует JPA-repository и EmployeeMapper
    private final EmployeeRepoInterface interfaceRepository;
    private final EmployeeMapper mapper;

    public EmployeeSecondAdapter(EmployeeRepoInterface interfaceRepository, EmployeeMapper mapper) {
        this.interfaceRepository = interfaceRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Employee> getAllEmployee() {

        List<EmployeeEntity> allEntity = interfaceRepository.findAll();

        if (allEntity.isEmpty()) return List.of();

        return allEntity.stream().map(mapper::mapEntityToDomain).toList();
    }

    @Override
    public Optional<Employee> getEmployee(Integer currentId) {

        var entityEmployee = interfaceRepository.findById(currentId);

        if (entityEmployee.isEmpty()) return Optional.empty();

        Employee domainEmployee = mapper.mapEntityToDomain(entityEmployee.get());
        return Optional.of(domainEmployee);
    }

    @Override
    public Optional<Employee> getAdmin() {

        var adminEntity = interfaceRepository.getAdmin();

        return adminEntity.map(mapper::mapEntityToDomain);

    }

    @Override
    public void saveEmployee(Employee currentEmployee) {
        interfaceRepository.save(mapper.mapDomainToEntity(currentEmployee));
    }

    @Override
    public void deleteEmployee(Integer currentId) {
        interfaceRepository.deleteById(currentId);
    }
}
