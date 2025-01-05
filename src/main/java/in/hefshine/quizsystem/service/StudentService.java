package in.hefshine.quizsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hefshine.quizsystem.entity.Student;
import in.hefshine.quizsystem.entity.Teacher;
import in.hefshine.quizsystem.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public String saveStudent(Student student) {
		if (studentRepository.findByEmail(student.getEmail()) != null) {
			throw new RuntimeException("Email is already registered.");
		}
		studentRepository.save(student);
		return "Student registered successfully!";
	}

	public Student authenticateStudent(String email, String password) {
		Student student = studentRepository.findByEmail(email);
		if (student != null && student.getPassword().equals(password)) {
			return student;
		}
		return null;
	}
}
