package voting.system.VotingManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import voting.system.VotingManagementSystem.exception.IllegalEnumException;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.STRING, with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public enum ElectionStatus {
    RESULT_PUBLISHED,
    CLOSED,
    VOTING,
    DRAFT,
    TEST;

    @JsonCreator
    public static ElectionStatus fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return ElectionStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalEnumException(value + " is not accepted. Allowed: " +
                    Arrays.toString(ElectionStatus.values()), ElectionStatus.class.getSimpleName());
        }
    }
}
