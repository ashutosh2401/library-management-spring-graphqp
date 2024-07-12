package com.bansira.assignment.repository;

import com.bansira.assignment.model.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testAddDepartment() {
        Department department = new Department();
        department.setName("Science");

        departmentRepository.save(department);

        assertThat(department.getId()).isNotNull();
    }

    @Test
    public void testFindDepartmentById() {
        Department department = new Department();
        department.setName("Science");

        departmentRepository.save(department);

        Department found = departmentRepository.findById(department.getId()).orElse(null);
        assertThat(found).isNotNull();
    }
}