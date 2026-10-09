
pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/khadeer329/java-landing-app.git'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn -B clean verify'
            }
        }

stage('SonarQube Analysis') {
    steps {
        withSonarQubeEnv('Sonarqube') {
            sh '''
                mvn -B \
                  org.sonarsource.scanner.maven:sonar-maven-plugin:5.2.0.4988:sonar \
                  -Dsonar.projectKey=java-landing-app
            '''
        }
    }
}
    }

    post {
        success {
            echo 'SUCCESS: Tests passed and SonarQube analysis completed.'
        }
        failure {
            echo 'FAILED: Check the failed stage in Jenkins Console Output.'
        }
        always {
            echo 'CI pipeline finished.'
        }
    }
}

