package voting.system.VotingManagementSystem.entity;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private String sapId;

    private String email;

    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;

}
