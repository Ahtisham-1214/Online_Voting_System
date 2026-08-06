package voting.system.VotingManagementSystem.util;

import voting.system.VotingManagementSystem.entity.Position;

public class HelperClass {

    public static boolean isExPosition(Position position){

        return (position == Position.EX_PRESIDENT || position == Position.EX_VICE_PRESIDENT ||
                position == Position.EX_GENERAL_SECRETARY || position == Position.EX_PUBLIC_RELATION_OFFICER ||
                position == Position.EX_TREASURER);
    }

}
