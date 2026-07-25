package voting.system.VotingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import voting.system.VotingManagementSystem.entity.Program;
import voting.system.VotingManagementSystem.entity.Season;
import voting.system.VotingManagementSystem.entity.Student;
import voting.system.VotingManagementSystem.entity.StudentStatus;
import voting.system.VotingManagementSystem.exception.ResourceNotFoundException;
import voting.system.VotingManagementSystem.repository.StudentRepository;
import voting.system.VotingManagementSystem.util.CSVHelper;
import voting.system.VotingManagementSystem.util.StudentSpecification;

import java.io.IOException;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
       return studentRepository.save(student);

    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Student not found with email " + email, Student.class.getSimpleName()));
    }

    public Student getStudentBySapId(String sapId) {
        return studentRepository.findBySapId(sapId).orElseThrow(()-> new ResourceNotFoundException("Student not found with sap id " +sapId, Student.class.getSimpleName()));
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with id " + id, Student.class.getSimpleName()));
    }

    public List<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getFilteredStudents(
            String email,
            Program program,
            StudentStatus status,
            Season season,
            Integer year,
            Pageable pageable
    ) {
        Specification<Student> spec = StudentSpecification.filterStudents(email, program, status, season, year);
        return studentRepository.findAll(spec, pageable).getContent();
    }

    public void saveStudentsFromCsv(MultipartFile file) {
        try {
            List<Student> students = CSVHelper.csvToStudents(file.getInputStream());
            studentRepository.saveAll(students);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store CSV data: " + e.getMessage());
        }
    }
}
