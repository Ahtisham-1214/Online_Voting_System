package voting.system.VotingManagementSystem.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartyRequestDto {

    @NotNull(message = "Party name cannot be null")
    @NotBlank(message = "Party Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "No special character or number allowed")
    private String name;
    private String slogan;
}
