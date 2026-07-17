package voting.system.VotingManagementSystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import voting.system.VotingManagementSystem.entity.ElectionStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ElectionRequestDto {

    @NotBlank(message = "Election Title cannot be empty")
    String title;
    ElectionStatus electionStatus;
    LocalDateTime startTime;
    LocalDateTime endTime;
    @Min(value = 0, message = "Max Party Members cannot be negative")
    int maxPartyMembers;
}
