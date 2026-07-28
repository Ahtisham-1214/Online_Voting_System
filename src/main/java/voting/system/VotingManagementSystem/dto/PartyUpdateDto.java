package voting.system.VotingManagementSystem.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartyUpdateDto {
    @Pattern(regexp = "^[A-Za-z ]+$", message = "No special character or number allowed")
    private String name;
    private String slogan;
}
