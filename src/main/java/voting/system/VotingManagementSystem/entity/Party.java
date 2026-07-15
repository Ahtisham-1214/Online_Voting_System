package voting.system.VotingManagementSystem.entity;

import jakarta.persistence.*;

@Entity
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private String slogan;

}
