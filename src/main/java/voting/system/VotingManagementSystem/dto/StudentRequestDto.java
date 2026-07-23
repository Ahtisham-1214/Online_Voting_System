package voting.system.VotingManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import voting.system.VotingManagementSystem.entity.Program;
import voting.system.VotingManagementSystem.entity.Role;
import voting.system.VotingManagementSystem.entity.Season;
import voting.system.VotingManagementSystem.entity.StudentStatus;

import java.time.Year;


@Setter
@Getter
@NoArgsConstructor
public class StudentRequestDto {

    @NotBlank( message = "Student Name cannot be empty")
    String name;

    @NotBlank( message = "Student SAP ID cannot be empty")
    @Pattern(regexp = "^\\d+$", message = "Invalid Sap Id")
    String sapId;
    @Email(message = "Invalid email format")
    @NotBlank( message = "Student Email cannot be empty")
    String email;
    @NotNull( message = "Student Status cannot be empty")
    StudentStatus studentStatus;

    @NotNull( message = "Student Program cannot be empty")
    Program program;

    @NotNull( message = "Student Season cannot be empty")
    Season season;

    @NotNull( message = "Student Year cannot be empty")
    Year year;

    @NotNull( message = "Student Role cannot be empty")
    Role role;
}