import { Team } from "./Team";

export interface Match {
    id : number;
    team1 : Team;
    team2 : Team;
    venue : string;
}