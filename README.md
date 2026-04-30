#  ScoutMarket - Enterprise AI Player Valuation Engine

ScoutMarket is a full-stack application designed to evaluate global soccer talent. By using live match data from API-Football, the system utilizes a custom mathematical algorithm to calculate real-time Estimated Market Values and ROI scores, providing rigorous risk assessment for player acquisitions.

## 🚀 Tech Stack & Architecture
* **Backend Engine:** Java 17, Spring Boot, RESTful APIs
* **Frontend UI:** React 18, TypeScript, Vite
* **Advanced Data Structures:** * Custom **AVL Trees** implemented from scratch for O(log n) memory indexing.
  * Custom **Max-Heaps** for dynamic, sub-millisecond leaderboard sorting based on ROI algorithms.
* **External Integrations:** API-Football (v3)

## 🧠 The Valuation & Risk Arbitrage Engine
The proprietary `ValuationService` acts as an automated financial underwriter, calculating a player's true market worth in three sequential steps:

### 1. The Production Index (AI Estimated Value)
* **The Base Minimum:** Every professional player evaluated receives a guaranteed baseline valuation of **€10,000,000**.
* **The Production Premium:** The algorithm adds value based on live offensive output:
  * **Goals:** +€5,000,000 each.
  * **Assists:** +€3,000,000 each.
* **The Age Modifier (Upside vs. Depreciation):** * **Prospects (≤ 23):** 1.2x multiplier (+20% premium for future resale value).
  * **Prime Years (24-29):** 1.0x multiplier.
  * **Aging Penalty (≥ 30):** 0.8x multiplier (-20% penalty for physical depreciation).

### 2. ROI Categorization (Risk Assessment)
Once the AI Estimated Value is calculated, the system compares it against the user's Custom Offer (`ROI Score = AI Value ÷ User Offer`) to categorize the financial risk:
* 🟢 **Elite Bargain (ROI > 1.50):** The mathematical valuation is at least 50% higher than the offer. A highly efficient allocation of capital.
* 🔵 **Fair Value (ROI 0.90 - 1.50):** The asking price aligns perfectly with the player's statistical output. A mathematically sound transaction.
* 🔴 **Overpriced Risk (ROI < 0.90):** The user is paying a "hype tax." The player's data does not justify the financial investment.

## ✨ Key Features
* **Zero-Typo Cascading Data Flow:** Ensures 100% API success rates by funneling users through database-driven League → Team → Player ID selections.
* **Robust Error Propagation:** Secure backend-to-frontend 404 handshake to instantly alert users of missing API data.
* **Interactive Negotiation Simulator:** Real-time UI updates allowing scouts to adjust financial offers and instantly view ROI risk metrics.

## 🛠️ Installation & Local Setup

### Backend (Java/Spring Boot)
1. Ensure Java 17 and Maven are installed.
2. Navigate to the root directory.
3. Add your API-Football key to `FootballApiService.java`.
4. Run the server:
   ```bash
   mvn spring-boot:run