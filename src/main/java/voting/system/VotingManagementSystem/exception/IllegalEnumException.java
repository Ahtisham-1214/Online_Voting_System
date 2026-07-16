package voting.system.VotingManagementSystem.exception;

import lombok.Getter;

@Getter
public class IllegalEnumException extends RuntimeException{
    private final String className;

    public IllegalEnumException(String message, String className) {
        super(message);
        this.className = className;
    }
}
