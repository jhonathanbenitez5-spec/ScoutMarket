export interface Player {
    playerID: number;
    name: string;
    team: string;
    leagueId: number;
    season: number;
    goals: number;
    assists: number;
    marketValue: number;
    performanceScore: number;
    age: number;
    nationality: string;
    gamesPlayed: number;
    minutesPlayed: number;
    position: string;
    height?: string;        
    yellowCards?: number;
    redCards?: number;
    estimatedValue: number;
    roiCategory: string;
}