
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

stage('Publish Artifact to Nexus') {
    steps {
        withCredentials([
            usernamePassword(
                credentialsId: 'nexus-credentials',
                usernameVariable: 'NEXUS_USER',
                passwordVariable: 'NEXUS_PASS'
            )
        ]) {
            sh '''
                set +x

                cat > nexus-settings.xml <<EOF
<settings>
  <servers>
    <server>
      <id>nexus-releases</id>
      <username>${NEXUS_USER}</username>
      <password>${NEXUS_PASS}</password>
    </server>
  </servers>
</settings>
EOF

                mvn -B deploy \
                  -DskipTests \
                  -DaltDeploymentRepository=nexus-releases::http://44.222.241.174:8081/repository/maven-releases/ \
                  -s nexus-settings.xml

                rm -f nexus-settings.xml
            '''
        }
    }
}

        
    }

post {
    success {
        echo 'SUCCESS: Build, tests, SonarQube analysis, and Nexus artifact upload completed.'
    }
    failure {
        echo 'FAILED: Check the failed stage in Jenkins Console Output.'
    }
    always {
        echo 'CI pipeline finished.'
    }
}

