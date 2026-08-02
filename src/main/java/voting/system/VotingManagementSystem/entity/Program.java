package voting.system.VotingManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import voting.system.VotingManagementSystem.exception.IllegalEnumException;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.STRING, with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public enum Program {
    ARTIFICIAL_INTELLIGENCE,
    CYBER_SECURITY,
    MULTIMEDIA_GAMING,
    CIVIL_ENGINEERING,
    ARCHITECTURE,
    ENVIRONMENTAL_SCIENCE,
    CLIMATE_CHANGE_AND_SUSTAINABLE_DEVELOPMENT,
    VISUAL_ARTS,
    DIGITAL_ARTS,
    TEXTILE_DESIGN,
    FASHION_DESIGN,
    CERAMIC_DESIGN,
    HISTORY,
    PAKISTAN_STUDIES,
    ARCHAEOLOGY,
    TOURISM_AND_HOSPITALITY;

    @JsonCreator
    public static Program fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return Program.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalEnumException(value + " is not accepted. Allowed: " +
                    Arrays.toString(Program.values()), Program.class.getSimpleName());
        }
    }



}
