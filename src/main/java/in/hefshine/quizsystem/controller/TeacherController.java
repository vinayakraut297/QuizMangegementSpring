package in.hefshine.quizsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.hefshine.quizsystem.entity.Teacher;
import in.hefshine.quizsystem.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/register")
    public ResponseEntity<String> registerTeacher(@RequestBody Teacher teacher) {
        try {
            return new ResponseEntity<>(teacherService.saveTeacher(teacher), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering teacher: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }	 

    @PostMapping("/login")
    public ResponseEntity<String> loginTeacher(@RequestBody Teacher teacherData) {
        Teacher teacher = teacherService.authenticateTeacher(teacherData.getEmail(), teacherData.getPassword());
        if (teacher != null) {
            return new ResponseEntity<>("Login successful!", HttpStatus.OK); 
        } else {
            return new ResponseEntity<>("Invalid email or password.", HttpStatus.UNAUTHORIZED);
        }
    }
}
