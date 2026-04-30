# ScoutMarket - Enterprise AI Player Valuation Engine

ScoutMarket is a full-stack, enterprise-grade application designed to evaluate global soccer talent. By ingesting live match data from API-Football, the system utilizes a custom mathematical algorithm to calculate real-time Estimated Market Values and ROI scores, providing rigorous risk assessment for player acquisitions.

## 🚀 Tech Stack & Architecture
* **Backend Engine:** Java 17, Spring Boot, RESTful APIs
* **Frontend UI:** React 18, TypeScript, Vite
* **Advanced Data Structures:** * Custom **AVL Trees** implemented from scratch for O(log n) memory indexing.
  * Custom **Max-Heaps** for dynamic, sub-millisecond leaderboard sorting based on ROI algorithms.
* **External Integrations:** API-Football (v3)

## 🧠 The Valuation Algorithm
The proprietary `ValuationService` calculates a player's worth based on a multi-factor production index:
1. **Production Base:** €10M base + (€5M per Goal) + (€3M per Assist).
2. **Age Premium/Depreciation:** Applies a 20% premium for prospects (≤ 23) and a 20% penalty for aging players (≥ 30).
3. **ROI Categorization:** Compares the AI Estimated Value against a custom user offer to classify the transaction as an *Elite Bargain*, *Fair Value*, or *Overpriced Risk*.

## ✨ Key Features
* **Zero-Typo Cascading Data Flow:** Ensures 100% API success rates by funneling users through database-driven League → Team → Player ID selections.
* **Robust Error Propagation:** Secure backend-to-frontend 404 handshake to instantly alert users of missing API data.
* **Interactive Negotiation Simulator:** Real-time UI updates allowing scouts to adjust financial offers and instantly view ROI metrics.

## 🛠️ Installation & Local Setup

### Backend (Java/Spring Boot)
1. Ensure Java 17 and Maven are installed.
2. Navigate to the root directory.
3. Add your API-Football key to `FootballApiService.java`.
4. Run the server:
   ```bash
   mvn spring-boot:run