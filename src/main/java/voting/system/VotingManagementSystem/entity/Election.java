package voting.system.VotingManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private ElectionStatus electionStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Column(nullable = false)
    private int maxPartyMembers;
}
