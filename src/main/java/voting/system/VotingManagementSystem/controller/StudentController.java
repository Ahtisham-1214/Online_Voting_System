package voting.system.VotingManagementSystem.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voting.system.VotingManagementSystem.dto.StudentDto;
import voting.system.VotingManagementSystem.entity.Student;
import voting.system.VotingManagementSystem.service.StudentService;

@RestController
@RequestMapping("/admin")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @Autowired
    private final ModelMapper modelMapper;

    public StudentController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/student")
   public ResponseEntity<StudentDto> getStudentByEmail(@RequestParam String email) {
        Student student = studentService.getStudentByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(student, StudentDto.class));
    }

    @PostMapping("/student")
    public ResponseEntity<StudentDto> addStudent (@RequestBody StudentDto studentDto) {

        Student student = studentService.addStudent(modelMapper.map(studentDto, Student.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(student, StudentDto.class));
    }
}
