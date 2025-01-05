package in.hefshine.quizsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.hefshine.quizsystem.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	Teacher findByEmail(String email);
}
