package voting.system.VotingManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import voting.system.VotingManagementSystem.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    Student findBySapId(String studentId);



}
