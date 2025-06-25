# 📊 Coding Analytics Dashboard

A full-stack web application that analyzes and visualizes competitive programming performance across **LeetCode**, **Codeforces**, and **GeeksforGeeks**. Designed for coders to track strengths, weaknesses, and progress with actionable insights.

---

## 🚀 Features

- 🔗 Aggregates contest and submission data from LeetCode (GraphQL), Codeforces, and GFG.
- 🔐 Secure OAuth 2.0 login (Google) with JWT-based session management.
- 📈 Personalized visual analytics: 
  - Rating trends
  - Tag-wise mastery
  - Contest time graphs
  - Submission heatmaps
  - Difficulty-wise and platform-wise breakdown
- 🔄 Data refresh every 24 hours via scheduled backend tasks.
- 🧠 Focus on meaningful insights — beyond native platform stats.

---

## 🛠️ Tech Stack

### Backend
- **Spring Boot** – RESTful backend
- **Spring Security** – OAuth 2.0 + JWT auth
- **Spring Data JPA** – ORM & database access
- **WebClient** – Data fetching (REST + GraphQL)
- **MySQL** – Relational data persistence
- **Scheduled Tasks** – Periodic data updates

### Frontend
- **React.js** – SPA architecture
- **Tailwind CSS** – Utility-first styling
- **Recharts** – Data visualization (bar, line, pie, heatmaps)
- **React Router** – Routing and protected routes
- **React Toastify** – Toast notifications
- **Context API** – Theme toggle and auth state
- **Custom Hooks** – Platform data fetch with 24h localStorage cache

---

## 📦 Setup Instructions

### Prerequisites
- Java 17+
- Node.js 18+
- MySQL Server
- Maven

---

### 🔧 Backend Setup

```bash
# Navigate to backend directory
cd backend

# Configure application.properties (DB credentials, OAuth secrets)

# Run the server
./mvnw spring-boot:run
```

### Frontend Setup
```
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install

# Start the development server
npm start
```

### 🔐 Authentication Flow
Users authenticate via Google OAuth 2.0.
JWT token is generated and stored in localStorage.
Token is used to access protected routes and API endpoints.
Frontend automatically redirects unauthenticated users to login.



### 📊 Visual Analytics
Codeforces
Submission pie charts (AC, TLE, WA, etc.)
Difficulty distribution
Rating-wise problem solving
Tag-wise performance (Graphs, DP, Trees, etc.)

LeetCode
Rating change heatmap (green/red)
Trends graph (uptrend/downtrend)
Time per contest line graph
Problems solved per contest
Tag mastery bar charts

GFG
Question distribution by difficulty
Overall attempt stats and ratings

### 🧱 Architecture
Backend: MVC pattern with DTOs for data normalization.
Frontend: Modular component structure with platform-based layout.
Caching: Data cached in frontend using localStorage for 24h.
Scheduling: Spring @Scheduled task for periodic backend data updates.

### 📄 License
This project is licensed under the MIT License.

### 🤝 Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you'd like to change.


