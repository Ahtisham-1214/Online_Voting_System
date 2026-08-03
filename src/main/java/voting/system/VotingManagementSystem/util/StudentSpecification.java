package voting.system.VotingManagementSystem.util;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import voting.system.VotingManagementSystem.entity.Program;
import voting.system.VotingManagementSystem.entity.Season;
import voting.system.VotingManagementSystem.entity.Student;
import voting.system.VotingManagementSystem.entity.StudentStatus;

import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {
    public static Specification<Student> filterStudents(String email, Program program, StudentStatus status, Season season, Integer year)
    {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (program != null) {
                predicates.add(criteriaBuilder.equal(root.get("program"), program));
            }

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("studentStatus"), status));
            }

            if (season != null) {
                predicates.add(criteriaBuilder.equal(root.get("season"), season));
            }

            if (year != null) {
                predicates.add(criteriaBuilder.equal(root.get("year"), year));
            }

            // Combine all active predicates with logical AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
