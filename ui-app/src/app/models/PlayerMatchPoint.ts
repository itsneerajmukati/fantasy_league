import { Match } from "./Match";
import { Players } from "./Players";

export interface PlayerMatchPoint {
    player : Players;
    match : Match;
    points : number;
}