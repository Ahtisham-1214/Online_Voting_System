package voting.system.VotingManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import voting.system.VotingManagementSystem.exception.IllegalEnumException;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.STRING, with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)

public enum Position {
    PRESIDENT,
    VICE_PRESIDENT,
    TREASURER,
    GENERAL_SECRETARY,
    PUBLIC_RELATION_OFFICER,
    EX_PRESIDENT,
    EX_VICE_PRESIDENT,
    EX_TREASURER,
    EX_GENERAL_SECRETARY,
    EX_PUBLIC_RELATION_OFFICER;




    @JsonCreator
    public static Position fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return Position.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalEnumException(value + " is not accepted. Allowed: " +
                    Arrays.toString(Position.values()), Position.class.getSimpleName());
        }
    }

    }
