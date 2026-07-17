package voting.system.VotingManagementSystem.dto;

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
    int maxPartyMembers;
}
