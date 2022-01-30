import { Set } from "typescript";
import { Team } from "./Team";

export interface Players {
    id : number;
    name : string;
    type : string;
    point : number;
    sportTeams : Team[];
}