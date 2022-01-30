import { Match } from "./Match";
import { User } from "./User";

export interface UserTeam {
    id : number;
    name : string;
    user : User;
    match : Match;
    points : number;
}