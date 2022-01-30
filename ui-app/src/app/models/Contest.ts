import { Match } from "./Match";

export interface Contest {
    id : number;
    match : Match;
    name : string;
    fees : number;
}