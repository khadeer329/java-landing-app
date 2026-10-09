
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
                withCredentials([
                    string(
                        credentialsId: 'sonarube token',
                        variable: 'SONAR_TOKEN'
                    )
                ]) {
                    sh '''
                        set +x
                        mvn -B sonar:sonar \
                          -Dsonar.host.url="$SONAR_URL" \
                          -Dsonar.projectKey="$SONAR_PROJECT_KEY" \
                          -Dsonar.token="$SONAR_TOKEN"
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

