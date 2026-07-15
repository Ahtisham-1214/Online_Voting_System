package voting.system.VotingManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import voting.system.VotingManagementSystem.entity.Election;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
    @Query("update Election e set  where e.id = :id")
    @Modifying
    Election updateStatusById(Long id);
}
