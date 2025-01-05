package in.hefshine.quizsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hefshine.quizsystem.entity.Teacher;
import in.hefshine.quizsystem.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	public String saveTeacher(Teacher teacher) {
		if (teacherRepository.findByEmail(teacher.getEmail()) != null) {
			throw new RuntimeException("Email is already registered.");
		}
		try {
			teacherRepository.save(teacher);
			return "Teacher registered successfully!";
		} catch (Exception e) {
			throw new RuntimeException("Failed to register teacher", e);
		}
	}

	public Teacher authenticateTeacher(String email, String password) {
		Teacher teacher = teacherRepository.findByEmail(email);

		if (teacher != null && teacher.getPassword().equals(password)) {
			return teacher; // Return the teacher object on successful login
		}
		return null; // Return null if login fails
	}
}