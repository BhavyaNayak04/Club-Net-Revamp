# ClubNet

ClubNet is a comprehensive web application for managing and exploring various clubs in NMAMIT, offering robust features for authentication, club listing, and event tracking.

## Features

### User Authentication
- Secure authentication and session management using JWT

### Club Management
- Comprehensive club listings
- Detailed club and event views
- Club subscription functionality
- Real-time event notifications

## Technology Stack

### Backend
- **Framework**: Spring Boot
- **Security**: Spring Security
- **Authentication**: JWT (JSON Web Tokens)
- **Database**: MongoDB

### Frontend
- **Framework**: Next.js
- **Styling**: Tailwind CSS

### Build Tools
- **Backend**: Maven
- **Frontend**: npm

## Prerequisites

Before you begin, ensure you have the following installed:

- Java 17 or higher
- Maven 3.6 or higher
- Node.js 14 or higher
- npm or yarn

## Getting Started

### Backend Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/clubnet.git
   cd clubnet
   ```

2. Configure the database:
   Update `src/main/resources/application.properties` with your MongoDB configuration:
   ```properties
   spring.application.name=clubNet
   spring.data.mongodb.database=${env.MONGO_DATABASE}
   spring.data.mongodb.uri=mongodb+srv://${env.MONGO_USER}:${env.MONGO_PASSWORD}@${env.MONGO_CLUSTER}/?retryWrites=true&w=majority&appName=clubnet
   ```

3. Create a `.env` file in the project root:
   ```bash
   MONGO_DATABASE=your_database_name
   MONGO_USER=your_mongodb_username
   MONGO_PASSWORD=your_mongodb_password
   MONGO_CLUSTER=your_mongodb_cluster_address
   ```

4. Build the backend:
   ```bash
   mvn clean install
   ```

5. Run the backend:
   ```bash
   mvn spring-boot:run
   ```
   The backend will be accessible at `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Run the frontend development server:
   ```bash
   npm run dev
   ```
   The frontend will be accessible at `http://localhost:3000`

### Running the Full Application

1. Start the backend server:
   ```bash
   mvn spring-boot:run
   ```

2. In a separate terminal, start the frontend:
   ```bash
   cd frontend
   npm run dev
   ```

3. Open your web browser and visit `http://localhost:3000`
