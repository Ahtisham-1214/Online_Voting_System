package voting.system.VotingManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import voting.system.VotingManagementSystem.entity.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    boolean existsByName(String name);

    @Query("select (count(p) > 0) from Party p where p.slogan = :slogan")
    boolean existBySlogan(@Param("slogan") String slogan);
}
