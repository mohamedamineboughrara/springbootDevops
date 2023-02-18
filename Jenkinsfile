pipeline {
    agent any
    stages {
        stage('Checkout GIT') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: 'main']],
                    userRemoteConfigs: [[url: 'https://github.com/mohamedamineboughrara/springbootDevops.git']]
                ])
            }
        }

        stage('MVN CLEAN') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('MVN COMPILE') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('MVN PACKAGE') {
            steps {
                sh 'mvn package'
            }
        }

        stage('MVN TEST') {
            steps {
                sh 'mvn test'
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar \
                    -Dsonar.projectKey=jenkins \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.login=228ccbd355b9274ed27e6e397ebb13953eecbe5a'
            }
        }
    }
}
