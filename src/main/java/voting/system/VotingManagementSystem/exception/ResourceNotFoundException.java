package voting.system.VotingManagementSystem.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String className;

    public ResourceNotFoundException(String message, String className) {
        super(message);
        this.className = className;
    }

}
