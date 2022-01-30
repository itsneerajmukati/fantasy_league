import { Players } from "./Players";

export interface Team {
    id : number;
    name : string;
    code : string;
    players : Players[];
}