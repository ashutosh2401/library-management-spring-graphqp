package com.bansira.assignment;

import com.bansira.assignment.model.Department;
import com.bansira.assignment.repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssignmentApplication implements CommandLineRunner {

	@Autowired
	private DepartmentRepository departmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!departmentRepository.existsById(1L)) {
			Department fictionDepartment = new Department();
			fictionDepartment.setName("Fiction");
			fictionDepartment.setId(1L);
			departmentRepository.save(fictionDepartment);
			System.out.println("Department 'Fiction' created with ID 1");
		}
	}
}
