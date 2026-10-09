
# Java Spring Boot Landing Page — Argo CD GitOps Demo

A responsive multi-page Java web application intended for a Kubernetes + Argo CD lab.

## Pages
- `/` — Home / landing page
- `/about` — About
- `/services` — Services
- `/contact` — Contact
- `/actuator/health` — Health check

## Requirements
- Java 17 and Maven (for local builds), or Docker (for container builds)
- Kubernetes cluster with Argo CD installed
- GitHub repository and Docker Hub account

## 1. Run locally
```bash
mvn spring-boot:run
```
Open http://localhost:8080

## 2. Build and test the JAR
```bash
mvn clean package
java -jar target/java-argocd-landing-app-1.0.0.jar
```

## 3. Build and push the container image
Replace `DOCKERHUB_USERNAME` with your Docker Hub username:
```bash
docker build -t DOCKERHUB_USERNAME/java-argocd-landing-app:1.0.0 .
docker login
docker push DOCKERHUB_USERNAME/java-argocd-landing-app:1.0.0
```
Edit `k8s/deployment.yaml` and replace `DOCKERHUB_USERNAME` with your real username.

## 4. Push the project to GitHub
Create an empty GitHub repository named `java-argocd-landing-app`, then from this project folder:
```bash
git init
git add .
git commit -m "Add Java landing page and Kubernetes manifests"
git branch -M main
git remote add origin https://github.com/YOUR_GITHUB_USERNAME/java-argocd-landing-app.git
git push -u origin main
```
Edit `argocd/application.yaml` and replace `YOUR_GITHUB_USERNAME` with your GitHub username before applying it.

## 5. Deploy through Argo CD
First ensure Argo CD is installed and accessible in your cluster. Apply the Application manifest:
```bash
kubectl apply -f argocd/application.yaml
kubectl get applications -n argocd
kubectl get pods -n java-landing
kubectl get svc -n java-landing
```
Argo CD should discover the `k8s/` directory and automatically synchronize it. Confirm the Application shows `Synced` and `Healthy`.

## 6. Open the website
This sample Service uses NodePort `30080`. If the cluster nodes are reachable from your computer and the node security group/firewall permits TCP 30080, open:
```text
http://<NODE_PUBLIC_IP>:30080
```
For a production or public AWS deployment, prefer an Ingress or a `LoadBalancer` Service and configure the matching AWS network/security rules. Do not expose the Kubernetes API or Argo CD admin endpoint publicly without appropriate access controls.

## Updating the site
Change the HTML/CSS or Java code, build and push a new image tag, update the image tag in `k8s/deployment.yaml`, and push the manifest change to Git. Argo CD will reconcile the cluster to the committed state.
=======

