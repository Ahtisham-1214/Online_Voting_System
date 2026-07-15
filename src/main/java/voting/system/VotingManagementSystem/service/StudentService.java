package voting.system.VotingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voting.system.VotingManagementSystem.entity.Student;
import voting.system.VotingManagementSystem.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
       return studentRepository.save(student);

    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }




}
