package in.hefshine.quizsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.hefshine.quizsystem.entity.Student;
import in.hefshine.quizsystem.entity.Teacher;
import in.hefshine.quizsystem.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

	@Autowired
	private StudentService studentService;

	
	@PostMapping("/register")
	public ResponseEntity<String> registerStudent(@RequestBody Student student) {
	    try {
	        String result = studentService.saveStudent(student);
	        return new ResponseEntity<>(result, HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}

	
	@PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody Student studentData) {
		Student student = studentService.authenticateStudent(studentData.getEmail(), studentData.getPassword());
        if (student != null) {
            return new ResponseEntity<>("Login successful!", HttpStatus.OK); 
        } else {
            return new ResponseEntity<>("Invalid email or password.", HttpStatus.UNAUTHORIZED);
        }
    }
	
}
