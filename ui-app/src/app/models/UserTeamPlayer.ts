import { Match } from "./Match";
import { Players } from "./Players";
import { UserTeam } from "./UserTeam";

export interface UserTeamPlayer {
    id : number;
    name : string;
    match : Match;
    player : Players;
    userTeam : UserTeam;
    isCaptain : boolean;
    isViceCaptain : boolean;
}