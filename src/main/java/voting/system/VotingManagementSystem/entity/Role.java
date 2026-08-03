package voting.system.VotingManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import voting.system.VotingManagementSystem.exception.IllegalEnumException;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.STRING, with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)

public enum Role {
    ADMIN,
    STUDENT;

    @JsonCreator
    public static Role fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return Role.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalEnumException(value + " is not accepted. Allowed: " +
                    Arrays.toString(Role.values()), Role.class.getSimpleName());
        }
    }
}
