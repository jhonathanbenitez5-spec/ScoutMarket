import { Player } from '../../models/Player';

interface Props {
    player: Player;
    rank: number;
    onClick: () => void; 
}

export const PlayerCard = ({ player, rank, onClick }: Props) => {
    const totalContributions = player.goals + player.assists;
    const costPerContribution = totalContributions > 0 ? player.marketValue / totalContributions : player.marketValue;

    let badgeColor = '#94a3b8';
    if (player.roiCategory === 'Elite Bargain') badgeColor = '#22c55e';
    else if (player.roiCategory === 'Fair Value') badgeColor = '#3b82f6';
    else if (player.roiCategory === 'High Risk / Overpriced') badgeColor = '#ef4444';

    return (
        <div 
            onClick={onClick}
            style={{ 
                border: '1px solid #e2e8f0', padding: '1.5rem', margin: '1rem 0', 
                borderRadius: '12px', backgroundColor: '#ffffff',
                boxShadow: '0 4px 6px -1px rgba(0, 0, 0, 0.1)',
                cursor: 'pointer', transition: 'transform 0.2s'
            }}
            onMouseOver={(e) => e.currentTarget.style.transform = 'translateY(-2px)'}
            onMouseOut={(e) => e.currentTarget.style.transform = 'translateY(0)'}
        >
            {/* TOP ROW: Header & Badge */}
            <div style={{ display: 'flex', justifyContent: 'space-between', borderBottom: '1px solid #f1f5f9', paddingBottom: '1rem', marginBottom: '1rem' }}>
                <div>
                    <h2 style={{ margin: '0', color: '#0f172a', display: 'flex', alignItems: 'center', gap: '10px' }}>
                        <span style={{ color: '#64748b', fontSize: '1.2rem' }}>#{rank}</span> 
                        {player.name}
                    </h2>
                    <p style={{ margin: '0.25rem 0 0 0', color: '#64748b', fontSize: '0.9rem' }}>
                        {player.team} • {player.position}
                    </p>
                </div>
                <div style={{ textAlign: 'right' }}>
                    <span style={{ 
                        backgroundColor: badgeColor, color: 'white', padding: '0.25rem 0.75rem', 
                        borderRadius: '999px', fontSize: '0.8rem', fontWeight: 'bold' 
                    }}>
                        {player.roiCategory}
                    </span>
                    <h3 style={{ margin: '0.5rem 0 0 0', color: '#0f172a' }}>€{player.marketValue.toLocaleString()}</h3>
                </div>
            </div>

            {/* MIDDLE ROW: Stats Grid */}
            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gap: '1rem', textAlign: 'center' }}>
                <div style={{ backgroundColor: '#f8fafc', padding: '0.75rem', borderRadius: '8px' }}>
                    <p style={{ margin: '0', fontSize: '0.8rem', color: '#64748b' }}>Age</p>
                    <p style={{ margin: '0', fontWeight: 'bold', color: '#0f172a' }}>{player.age || 'N/A'}</p>
                </div>
                <div style={{ backgroundColor: '#f8fafc', padding: '0.75rem', borderRadius: '8px' }}>
                    <p style={{ margin: '0', fontSize: '0.8rem', color: '#64748b' }}>Matches</p>
                    <p style={{ margin: '0', fontWeight: 'bold', color: '#0f172a' }}>{player.gamesPlayed || 'N/A'}</p>
                </div>
                <div style={{ backgroundColor: '#f8fafc', padding: '0.75rem', borderRadius: '8px' }}>
                    <p style={{ margin: '0', fontSize: '0.8rem', color: '#64748b' }}>Goals</p>
                    <p style={{ margin: '0', fontWeight: 'bold', color: '#16a34a' }}>{player.goals}</p>
                </div>
                <div style={{ backgroundColor: '#f8fafc', padding: '0.75rem', borderRadius: '8px' }}>
                    <p style={{ margin: '0', fontSize: '0.8rem', color: '#64748b' }}>Assists</p>
                    <p style={{ margin: '0', fontWeight: 'bold', color: '#16a34a' }}>{player.assists}</p>
                </div>
            </div>

            {/* BOTTOM ROW: Deep ROI Analysis */}
            <div style={{ marginTop: '1rem', padding: '1rem', backgroundColor: '#f1f5f9', borderRadius: '8px', display: 'flex', justifyContent: 'space-between' }}>
                <div>
                    <p style={{ margin: '0', fontSize: '0.8rem', color: '#475569' }}>Algorithm ROI Score</p>
                    <p style={{ margin: '0', fontWeight: 'bold', fontSize: '1.2rem', color: '#0f172a' }}>
                        {player.performanceScore.toFixed(2)}
                    </p>
                </div>
                <div style={{ textAlign: 'right' }}>
                    <p style={{ margin: '0', fontSize: '0.8rem', color: '#475569' }}>Cost per Goal Contribution</p>
                    <p style={{ margin: '0', fontWeight: 'bold', fontSize: '1.1rem', color: '#0f172a' }}>
                        €{Math.round(costPerContribution).toLocaleString()}
                    </p>
                </div>
            </div>
        </div>
    );
};