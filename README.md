# **FURIDA v2.0.0**

FURIDA is a Spring Boot application. Follow the instructions below to set up, run, and use the application.

---

## **Running the Server**

### **Run Locally**
- Start the server by running the `ServerApplication` class in your IDE or using the command:
  ```bash
  ./mvnw spring-boot:run
  ```
- To run the server in development mode, use the CLI argument:
  ```bash
  --spring.profiles.active=dev
  ```
- Ensure all required environment variables specified in `application.yml` are provided.

---

## **Docker Deployment**

### **Pull the Docker Image**
- Pull the latest Docker image using the command:
  ```bash
  docker pull amankr08/furida-server:latest
  ```

### **Setup and Run with Docker Compose**
1. Create a `.env` file in the root directory to store the required environment variables (e.g., database credentials, API keys, etc.).
   - Example `.env`:
     ```env
     DB_USERNAME=root
     DB_PASSWORD=password
     DB_NAME=furida
     JWT_SECRET=your_secret_key
     CLOUDINARY_NAME=your_cloudinary_name
     CLOUDINARY_KEY=your_cloudinary_key
     CLOUDINARY_SECRET=your_cloudinary_secret
     ```
2. Start the application using Docker Compose:
   ```bash
   docker-compose up
   ```

---

## **Users and Login**

- A default **admin user** is created upon initialization:
  - **Username**: `root`
  - **Password**: `1`
- Access the **Admin Console** at:
  ```url
  http://<your-host>:8080/login
  ```
  Replace `<your-host>` with your server's IP or domain.

---

## **Environment Variables**

Below is a list of required environment variables to configure the application:

| Variable Name        | Description                         | Example Value         |
|----------------------|-------------------------------------|-----------------------|
| `DB_USERNAME`        | Database username                   | `root`                |
| `DB_PASSWORD`        | Database password                   | `password`            |
| `DB_NAME`            | Name of the PostgreSQL database     | `furida`              |
| `JWT_SECRET`         | Secret key for JWT authentication   | `your_secret_key`     |
| `CLOUDINARY_NAME`    | Cloudinary account name             | `my-cloudinary-name`  |
| `CLOUDINARY_KEY`     | Cloudinary API key                  | `my-cloudinary-key`   |
| `CLOUDINARY_SECRET`  | Cloudinary API secret               | `my-cloudinary-secret`|

---

## **GitHub Actions for Docker Deployment**

This repository uses GitHub Actions to build and push Docker images to Docker Hub. Below is the workflow configuration:

```yaml
name: Docker Image CI

on:
  push:
    branches: [ "master" ]
  pull_request:
    branches: [ "master" ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      # Step 1: Checkout the repository
      - uses: actions/checkout@v4
      
      # Step 2: Log in to Docker Hub
      - name: Log in to Docker Hub
        uses: docker/login-action@v2
        with:
          username: ${{ secrets.DOCKER_USERNAME }}
          password: ${{ secrets.DOCKER_PASSWORD }}
      
      # Step 3: Build the Docker image
      - name: Build the Docker image
        run: |
          docker build . \
            --file Dockerfile \
            --tag ${{ secrets.DOCKER_USERNAME }}/furida-server:latest \
            --tag ${{ secrets.DOCKER_USERNAME }}/furida-server:${{ github.sha }}
      
      # Step 4: Push the Docker image to Docker Hub
      - name: Push the Docker image
        run: |
          docker push ${{ secrets.DOCKER_USERNAME }}/furida-server:latest
          docker push ${{ secrets.DOCKER_USERNAME }}/furida-server:${{ github.sha }}
```

---

## **Contributing**

Contributions are welcome! Please create a new branch for your feature or bugfix and submit a pull request with clear documentation.

---

## **License**

Include licensing details here (e.g., MIT License) if applicable.

