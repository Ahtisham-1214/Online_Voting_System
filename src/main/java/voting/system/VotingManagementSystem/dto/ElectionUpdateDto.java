package voting.system.VotingManagementSystem.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import voting.system.VotingManagementSystem.entity.ElectionStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ElectionUpdateDto {
    String title;
    ElectionStatus electionStatus;
    LocalDateTime startTime;
    LocalDateTime endTime;
    @Min(value = 5, message = "At least Have 5 members in a party")
    Integer maxPartyMembers;
}
