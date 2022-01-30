
import { Contest } from "./Contest";
import { UserTeam } from "./UserTeam";

export interface ContestTeam {
    id : number;
    contest : Contest;
    userTeam : UserTeam;
    rank : number;
}