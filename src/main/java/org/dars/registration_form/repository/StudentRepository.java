package org.dars.registration_form.repository;

import org.dars.registration_form.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	boolean existsByEmail(String email);

}
