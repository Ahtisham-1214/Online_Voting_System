package voting.system.VotingManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequestDto {

    @NotBlank(message = "SAP ID is required")
    private String sapId;       // e.g., "5000000159" (from logged-in user session)

    @NotNull(message = "Election ID is required")
    private Long electionId;   // e.g., 5 (from active election endpoint)

    @NotNull(message = "Party ID is required")
    private Long partyId;      // e.g., 1 (selected on UI)
}