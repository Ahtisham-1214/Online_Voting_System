package voting.system.VotingManagementSystem.exception;

import lombok.Getter;

@Getter
public class CSVFileException extends RuntimeException{
    private String className;

    public CSVFileException (String errorMessage, String className){
        this(errorMessage);
        this.className = className;
    }

    public CSVFileException(String errorMessage){
        super(errorMessage);
    }

}
