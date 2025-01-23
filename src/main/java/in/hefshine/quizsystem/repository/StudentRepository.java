package in.hefshine.quizsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.hefshine.quizsystem.entity.Student;
import in.hefshine.quizsystem.entity.Teacher;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findByEmail(String email);
}
