package voting.system.VotingManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import voting.system.VotingManagementSystem.entity.StudentStatus;


@Value
public class StudentRequestDto {

    @NotBlank( message = "Student Name cannot be empty")
    String name;

    @NotBlank( message = "Student SAP ID cannot be empty")
    String sapId;
    @Email(message = "Invalid email format")
    @NotBlank( message = "Student Email cannot be empty")
    String email;
    @NotNull( message = "Student Status cannot be empty")
    StudentStatus studentStatus;
}