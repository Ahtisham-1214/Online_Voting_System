package voting.system.VotingManagementSystem.controller;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import voting.system.VotingManagementSystem.dto.StudentRequestDto;
import voting.system.VotingManagementSystem.dto.StudentResponseDto;
import voting.system.VotingManagementSystem.entity.Program;
import voting.system.VotingManagementSystem.entity.Season;
import voting.system.VotingManagementSystem.entity.Student;
import voting.system.VotingManagementSystem.entity.StudentStatus;
import voting.system.VotingManagementSystem.mapper.MyMapper;
import voting.system.VotingManagementSystem.service.StudentService;
import voting.system.VotingManagementSystem.util.CSVHelper;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Validated
public class StudentController {

    private final StudentService studentService;

    private final MyMapper myMapper;

    @Autowired
    public StudentController(StudentService studentService, MyMapper myMapper) {
        this.studentService = studentService;
        this.myMapper = myMapper;
    }


    @GetMapping("/students/{sapId}")
    public ResponseEntity<StudentResponseDto> getStudentBySapId(@PathVariable @Pattern(regexp = "^\\d+$", message = "Invalid Sap Id") String sapId) {
        Student student = studentService.getStudentBySapId(sapId);
        return ResponseEntity.status(HttpStatus.OK).body(myMapper.toStudentResponseDto(student));
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponseDto> addStudent (@RequestBody StudentRequestDto studentRequestDto) {

        Student student = studentService.addStudent(myMapper.toStudent(studentRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(myMapper.toStudentResponseDto(student));
    }

//    @GetMapping("/students")
//    public ResponseEntity<List<StudentResponseDto>> getAllStudents(
//            @RequestParam(required = false) @Email(message = "Invalid Email") String email,
//            @RequestParam(value = "pageNo", defaultValue = "1") @Min(value = 1, message = "Page Number must be greater or equal to 1") int pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
//            @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder
//
//    ) {
//
//        if (email != null && !email.isBlank()) {
//            Student student = studentService.getStudentByEmail(email);
//            return ResponseEntity.status(HttpStatus.OK).body(List.of(myMapper.toStudentResponseDto(student)));
//        }
//
//        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
//
//        List<StudentResponseDto> patients = studentService.getAllStudents(PageRequest.of(pageNumber - 1, pageSize, sort))
//                .stream().map(myMapper::toStudentResponseDto).toList();
//
//        return ResponseEntity.ok(patients);
//    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(
            @RequestParam(required = false) @Email(message = "Invalid Email") String email,
            @RequestParam(required = false) Program program,
            @RequestParam(required = false) StudentStatus status,
            @RequestParam(required = false) Season season,
            @RequestParam(required = false) Integer year,
            @RequestParam(value = "pageNo", defaultValue = "1") @Min(value = 1, message = "Page Number must be greater or equal to 1") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3") @Min(value = 1, message = "Page size must be at least 1") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder
    ) {
        if (email != null && !email.isBlank()) {
            Student student = studentService.getStudentByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(List.of(myMapper.toStudentResponseDto(student)));
        }

        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        List<StudentResponseDto> students = studentService.getFilteredStudents(email, program, status, season, year, PageRequest.of(pageNumber - 1, pageSize, sort))
                .stream()
                .map(myMapper::toStudentResponseDto)
                .toList();

        return ResponseEntity.ok(students);
    }

    @PostMapping("/students/upload")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {

        // 1. Validate if file is uploaded
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a CSV file to upload.");
        }

        // 2. Validate file format
        if (!CSVHelper.hasCSVFormat(file)) {
            return ResponseEntity.badRequest().body("Invalid file type. Please upload a .csv file.");
        }
            studentService.saveStudentsFromCsv(file);
            return ResponseEntity.ok("CSV file processed and students imported successfully!");

    }
}
