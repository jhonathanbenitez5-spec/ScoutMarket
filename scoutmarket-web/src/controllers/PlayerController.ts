import { Player } from '../models/Player';

const BASE_URL = 'http://localhost:8080/api/players';

export const PlayerController = {
    getLeaderboard: async (): Promise<Player[]> => {
        const response = await fetch(`${BASE_URL}/leaderboard`);
        return await response.json();
    },

    scoutPlayer: async (playerData: Partial<Player>): Promise<Player> => {
        const response = await fetch(`${BASE_URL}/scout`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(playerData)
        });

        if (!response.ok) {
            const errorData = await response.json().catch(() => null);
            if (errorData && errorData.error) {
                throw new Error(errorData.error);
            } else {
                throw new Error("Failed to scout player. Check Java terminal.");
            }
        }

        return await response.json();
    }
};