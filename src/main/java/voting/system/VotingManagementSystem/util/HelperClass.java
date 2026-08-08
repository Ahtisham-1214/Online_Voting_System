package voting.system.VotingManagementSystem.util;

import voting.system.VotingManagementSystem.entity.Election;
import voting.system.VotingManagementSystem.entity.ElectionStatus;
import voting.system.VotingManagementSystem.entity.Position;

import java.time.LocalDateTime;

public class HelperClass {

    private HelperClass(){

    }

    public static boolean isExPosition(Position position){

        return (position == Position.EX_PRESIDENT || position == Position.EX_VICE_PRESIDENT ||
                position == Position.EX_GENERAL_SECRETARY || position == Position.EX_PUBLIC_RELATION_OFFICER ||
                position == Position.EX_TREASURER);
    }

    public static void validateTimes(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) return;
        if (endTime.isBefore(startTime) || endTime.isEqual(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
    }

    public static void autoDetermineStatus(Election election) {
        if (election.getStartTime() == null || election.getEndTime() == null) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(election.getStartTime())) {
            election.setElectionStatus(ElectionStatus.DRAFT);
        } else if (!now.isBefore(election.getStartTime()) && now.isBefore(election.getEndTime())) {
            election.setElectionStatus(ElectionStatus.VOTING);
        } else {
            election.setElectionStatus(ElectionStatus.CLOSED);
        }
    }
}
