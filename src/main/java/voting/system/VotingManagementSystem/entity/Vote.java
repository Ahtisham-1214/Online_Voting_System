package voting.system.VotingManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "vote",
        uniqueConstraints = {
                // Enforces at the database level that 1 student can only vote ONCE per election!
                @UniqueConstraint(name = "aror_student_election", columnNames = {"student_id", "election_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "election_id")
    Election election;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    Student student;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    Party party;


    @CreationTimestamp
    @Column(name = "voted_at", nullable = false, updatable = false)
    private LocalDateTime votedAt;
}
