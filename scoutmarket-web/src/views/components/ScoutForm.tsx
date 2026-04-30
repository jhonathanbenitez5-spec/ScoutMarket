import { useState, useEffect } from 'react';
import { PlayerController } from '../../controllers/PlayerController';
import { Player } from '../../models/Player';

interface Props {
    onScoutSuccess: () => void;
}

export const ScoutForm = ({ onScoutSuccess }: Props) => {
    const [leagueId, setLeagueId] = useState(39); 
    const [teams, setTeams] = useState<any[]>([]);
    const [selectedTeamId, setSelectedTeamId] = useState('');
    
    const [squad, setSquad] = useState<any[]>([]);
    const [selectedPlayerId, setSelectedPlayerId] = useState('');
    
    const [marketValue, setMarketValue] = useState(50000000);
    const [loading, setLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);

    useEffect(() => {
        const fetchTeams = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/players/teams/${leagueId}`);
                if (!response.ok) throw new Error(`Server returned ${response.status}`);
                const data = await response.json();
                
                if (data.errors && Object.keys(data.errors).length > 0) {
                    setErrorMessage("API Error: " + JSON.stringify(data.errors));
                }

                setTeams(data.response || []);
                setSelectedTeamId(''); 
                setSquad([]); 
            } catch (err: any) {
                console.error("Failed to load teams.", err);
                setErrorMessage("Could not connect to Java Server.");
            }
        };
        fetchTeams();
    }, [leagueId]);

    useEffect(() => {
        if (!selectedTeamId) return;
        const fetchSquad = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/players/squad/${selectedTeamId}`);
                if (!response.ok) throw new Error(`Server returned ${response.status}`);
                const data = await response.json();
                setSquad(data.response[0]?.players || []);
                setSelectedPlayerId(''); 
            } catch (err: any) {
                console.error("Failed to load squad.", err);
            }
        };
        fetchSquad();
    }, [selectedTeamId]);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setErrorMessage(null);

        const teamName = teams.find(t => t.team.id === Number(selectedTeamId))?.team.name || "Unknown Team";
        const selectedPlayer = squad.find(p => p.id === Number(selectedPlayerId));

        if (!selectedPlayer) {
            setErrorMessage("Please select a valid player.");
            setLoading(false);
            return;
        }

        const newPlayer: Partial<Player> = {
            playerID: selectedPlayer.id, 
            name: selectedPlayer.name,
            team: teamName,
            leagueId: leagueId,
            season: 2023,
            marketValue: marketValue,
            age: 25, nationality: "Unknown", gamesPlayed: 0, minutesPlayed: 0, position: "Attacker",
            estimatedValue: 0, roiCategory: "Pending"
        };

        try {
            await PlayerController.scoutPlayer(newPlayer);
            setSelectedPlayerId(''); 
            onScoutSuccess();
        } catch (error: any) {
            setErrorMessage(error.message);
        }
        setLoading(false);
    };

    const inputStyle = { 
        padding: '0.75rem', 
        borderRadius: '6px', 
        border: '1px solid #cbd5e1', 
        color: '#0f172a', // Enforces dark text
        backgroundColor: '#ffffff' // Enforces white background
    };

    return (
        <form onSubmit={handleSubmit} style={{ 
            backgroundColor: '#ffffff', padding: '1.5rem', borderRadius: '12px', 
            border: '1px solid #e2e8f0'
        }}>
            <h3 style={{ margin: '0 0 1rem 0', color: '#0f172a' }}>Scout Management Console</h3>
            
            {errorMessage && (
                <div style={{ backgroundColor: '#fee2e2', borderLeft: '4px solid #ef4444', color: '#991b1b', padding: '0.75rem 1rem', borderRadius: '4px', marginBottom: '1rem' }}>
                    ⚠️ {errorMessage}
                </div>
            )}
            
            <div style={{ display: 'grid', gap: '1rem', gridTemplateColumns: '1fr 1fr' }}>
                
                <select value={leagueId} onChange={(e) => setLeagueId(Number(e.target.value))} style={inputStyle}>
                    <option value={39}>Premier League</option>
                    <option value={140}>La Liga</option>
                    <option value={253}>MLS</option>
                </select>

                <select value={selectedTeamId} onChange={(e) => setSelectedTeamId(e.target.value)} required style={inputStyle}>
                    <option value="">-- Select Team --</option>
                    {teams.map(t => (
                        <option key={t.team.id} value={t.team.id}>{t.team.name}</option>
                    ))}
                </select>

                <select value={selectedPlayerId} onChange={(e) => setSelectedPlayerId(e.target.value)} disabled={!selectedTeamId} required 
                    style={{ ...inputStyle, backgroundColor: !selectedTeamId ? '#f1f5f9' : '#ffffff' }}>
                    <option value="">-- Select Player --</option>
                    {squad.map(p => (
                        <option key={p.id} value={String(p.id)}>{p.name} ({p.position})</option>
                    ))}
                </select>

                <input type="number" placeholder="Evaluation (€)" required value={marketValue} onChange={(e) => setMarketValue(Number(e.target.value))} style={inputStyle} />
                
                <button disabled={loading || !selectedPlayerId} style={{ gridColumn: '1 / -1', padding: '0.75rem', borderRadius: '6px', backgroundColor: (loading || !selectedPlayerId) ? '#94a3b8' : '#2563eb', color: 'white', border: 'none', fontWeight: 'bold', cursor: 'pointer', transition: 'background-color 0.2s' }}>
                    {loading ? 'Analyzing Official Stats...' : 'Deploy API Scout'}
                </button>
            </div>
        </form>
    );
};