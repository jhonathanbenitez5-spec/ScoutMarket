import { useState } from 'react';
import { Player } from '../../models/Player';

interface Props {
    player: Player;
    onClose: () => void;
}

export const PlayerModal = ({ player, onClose }: Props) => {
    
    const [userOffer, setUserOffer] = useState(player.marketValue);
    const handleOfferChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const rawValue = e.target.value.replace(/[^0-9]/g, ''); 
        setUserOffer(Number(rawValue));
    };

    const customRoiRatio = userOffer > 0 ? player.estimatedValue / userOffer : 0;
    
    let customBadgeColor = '#94a3b8';
    let customBadgeText = 'High Risk / Overpriced';
    if (customRoiRatio > 1.5) { 
        customBadgeColor = '#22c55e'; 
        customBadgeText = 'Elite Bargain'; 
    } else if (customRoiRatio >= 0.9) { 
        customBadgeColor = '#3b82f6'; 
        customBadgeText = 'Fair Value'; 
    } else { 
        customBadgeColor = '#ef4444'; 
    }

    return (
        <div style={{
            position: 'fixed', top: 0, left: 0, right: 0, bottom: 0,
            backgroundColor: 'rgba(15, 23, 42, 0.85)',
            display: 'flex', justifyContent: 'center', alignItems: 'center',
            zIndex: 1000, padding: '1rem', fontFamily: 'system-ui, sans-serif'
        }} onClick={onClose}>
            
            <div style={{
                backgroundColor: '#f1f5f9', borderRadius: '8px', overflow: 'hidden',
                width: '100%', maxWidth: '900px', maxHeight: '90vh', overflowY: 'auto',
                boxShadow: '0 25px 50px -12px rgba(0, 0, 0, 0.5)'
            }} onClick={(e) => e.stopPropagation()}>

                {/* Header Section */}
                <div style={{ backgroundColor: '#0f172a', color: 'white', padding: '1.5rem', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                    <div>
                        <h1 style={{ margin: 0, fontSize: '2rem' }}>{player.name}</h1>
                        <p style={{ margin: '0.5rem 0 0 0', color: '#94a3b8', fontSize: '1rem' }}>
                            {player.team} • #{player.playerID}
                        </p>
                    </div>
                    <button onClick={onClose} style={{
                        background: 'none', border: 'none', color: 'white', fontSize: '2rem', cursor: 'pointer', transition: 'color 0.2s'
                    }}>×</button>
                </div>

                {/* Top Performance Banner */}
                <div style={{ backgroundColor: 'white', padding: '1.5rem', borderBottom: '2px solid #e2e8f0', display: 'flex', justifyContent: 'space-around', textAlign: 'center' }}>
                    <div><p style={{ margin: 0, color: '#64748b', fontSize: '0.9rem' }}>Appearances</p><h3 style={{ margin: '0.5rem 0 0 0', color: '#3b82f6' }}>{player.gamesPlayed || 0}</h3></div>
                    <div><p style={{ margin: 0, color: '#64748b', fontSize: '0.9rem' }}>Goals</p><h3 style={{ margin: '0.5rem 0 0 0', color: '#3b82f6' }}>{player.goals}</h3></div>
                    <div><p style={{ margin: 0, color: '#64748b', fontSize: '0.9rem' }}>Assists</p><h3 style={{ margin: '0.5rem 0 0 0', color: '#3b82f6' }}>{player.assists}</h3></div>
                    <div><p style={{ margin: 0, color: '#64748b', fontSize: '0.9rem' }}>Yellow Cards</p><h3 style={{ margin: '0.5rem 0 0 0', color: '#eab308' }}>{player.yellowCards || 0}</h3></div>
                    <div><p style={{ margin: 0, color: '#64748b', fontSize: '0.9rem' }}>Red Cards</p><h3 style={{ margin: '0.5rem 0 0 0', color: '#ef4444' }}>{player.redCards || 0}</h3></div>
                </div>

                {/* Main 2-Column Grid */}
                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '2px', backgroundColor: '#cbd5e1' }}>
                    
                    {/* LEFT COLUMN: Player Data List */}
                    <div style={{ backgroundColor: 'white', padding: '2rem' }}>
                        <h3 style={{ margin: '0 0 1.5rem 0', color: '#0f172a', borderBottom: '2px solid #0f172a', paddingBottom: '0.5rem' }}>PLAYER DATA</h3>
                        
                        <div style={{ display: 'grid', gridTemplateColumns: '150px 1fr', gap: '1rem', fontSize: '0.95rem' }}>
                            <span style={{ color: '#64748b' }}>Age:</span>
                            <span style={{ fontWeight: 'bold', color: '#0f172a' }}>{player.age || 'N/A'}</span>
                            
                            <span style={{ color: '#64748b' }}>Citizenship:</span>
                            <span style={{ fontWeight: 'bold', color: '#0f172a' }}>{player.nationality}</span>
                            
                            <span style={{ color: '#64748b' }}>Height:</span>
                            <span style={{ fontWeight: 'bold', color: '#0f172a' }}>{player.height || 'N/A'}</span>
                            
                            <span style={{ color: '#64748b' }}>Position:</span>
                            <span style={{ fontWeight: 'bold', color: '#0f172a' }}>{player.position}</span>
                            
                            <span style={{ color: '#64748b' }}>Current club:</span>
                            <span style={{ fontWeight: 'bold', color: '#0f172a' }}>{player.team}</span>
                        </div>
                    </div>

                    {/* RIGHT COLUMN: Visuals */}
                    <div style={{ display: 'flex', flexDirection: 'column', gap: '2px' }}>
                        
                        {/* Position Pitch */}
                        <div style={{ backgroundColor: 'white', padding: '2rem', flex: 1 }}>
                            <h4 style={{ margin: '0 0 1rem 0', color: '#475569' }}>Main position</h4>
                            <p style={{ fontWeight: 'bold', marginBottom: '1rem', color: '#0f172a' }}>{player.position}</p>
                            
                            <div style={{ 
                                width: '100%', height: '160px', backgroundColor: '#1e3a8a', 
                                border: '2px solid white', position: 'relative', borderRadius: '4px',
                                display: 'flex', justifyContent: 'center', alignItems: 'center'
                            }}>
                                <div style={{ width: '50px', height: '50px', border: '2px solid rgba(255,255,255,0.5)', borderRadius: '50%' }}></div>
                                <div style={{ position: 'absolute', top: 0, width: '80px', height: '25px', border: '2px solid rgba(255,255,255,0.5)', borderTop: 'none' }}></div>
                                <div style={{ position: 'absolute', bottom: 0, width: '80px', height: '25px', border: '2px solid rgba(255,255,255,0.5)', borderBottom: 'none' }}></div>
                                <div style={{ 
                                    position: 'absolute', width: '16px', height: '16px', 
                                    backgroundColor: '#ef4444', borderRadius: '50%', border: '2px solid white',
                                    boxShadow: '0 0 10px rgba(0,0,0,0.5)'
                                }}></div>
                            </div>
                        </div>

                        {/* Interactive Market Value Section */}
                        <div style={{ backgroundColor: 'white', padding: '2rem', flex: 1, display: 'flex', flexDirection: 'column' }}>
                            <h4 style={{ margin: '0 0 1rem 0', color: '#475569' }}>Valuation & Negotiation</h4>
                            
                            <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '1rem' }}>
                                <span style={{ color: '#64748b', fontSize: '0.9rem' }}>AI Estimated Value:</span>
                                <span style={{ color: '#16a34a', fontWeight: 'bold', fontSize: '1.2rem' }}>€{(player.estimatedValue / 1000000).toFixed(2)}m</span>
                            </div>

                            <div style={{ padding: '1.5rem', backgroundColor: '#f8fafc', borderRadius: '8px', border: '1px solid #e2e8f0' }}>
                                <label style={{ display: 'block', fontSize: '0.95rem', color: '#0f172a', fontWeight: 'bold', marginBottom: '0.5rem' }}>
                                    Your Custom Offer (€)
                                </label>
                                
                                {/* The Auto-Formatting Text Box */}
                                <input 
                                    type="text" 
                                    value={userOffer === 0 ? '' : userOffer.toLocaleString()} 
                                    onChange={handleOfferChange}
                                    style={{ 
                                        width: '100%', padding: '0.75rem', borderRadius: '6px', 
                                        border: '1px solid #cbd5e1', fontSize: '1.1rem', fontWeight: 'bold', 
                                        color: '#0f172a', marginBottom: '1rem', boxSizing: 'border-box'
                                    }}
                                />
                                
                                {/* The ROI Explanation Breakdown */}
                                <div style={{ borderTop: '1px solid #e2e8f0', paddingTop: '1rem', marginTop: '0.5rem' }}>
                                    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '0.75rem' }}>
                                        <span style={{ fontSize: '0.95rem', color: '#475569', fontWeight: 'bold' }}>ROI Score:</span>
                                        <span style={{ 
                                            backgroundColor: customBadgeColor, color: 'white', padding: '0.35rem 0.75rem', 
                                            borderRadius: '6px', fontSize: '0.9rem', fontWeight: 'bold' 
                                        }}>
                                            {customBadgeText} ({customRoiRatio.toFixed(2)})
                                        </span>
                                    </div>
                                    
                                    <p style={{ margin: 0, fontSize: '0.85rem', color: '#64748b', lineHeight: '1.5' }}>
                                        <strong>How it works:</strong> The AI divides its Estimated Value by your offer. A score above <strong>1.00</strong> means you are getting a bargain. A score below <strong>1.00</strong> means you are overpaying.
                                    </p>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    );
};