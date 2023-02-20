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
                sh 'mvn sonar:sonar  -Dsonar.projectKey=jenkins  -Dsonar.host.url=http://localhost:9000    -Dsonar.login=228ccbd355b9274ed27e6e397ebb13953eecbe5a'
            }
        }
        stage('deploy to nexus3') {
            steps {
            script {
                nexusArtifactUploader artifacts:
                  [
                   [
                   artifactId: 'demo',
                    classifier: '',
                    file: 'target/demo-1.0.0.jar',
                     type: 'jar']],
                      credentialsId: 'bf65d7cd-e3b1-4e10-b35a-4b744d895583',
                       groupId: 'com.example',
                        nexusUrl: 'localhost:8082',
                         nexusVersion: 'nexus3',
                          protocol: 'http',
                           repository: 'my-nexus-repo',
                            version: '1.0.0'
              }
            }
        }
        	 stage('Build docker image'){
                      steps{
                              script{
                                  sh 'docker build -t mohamedamineboughrara/demo .'
                                                  }
                                                }
                                            }
                stage('Docker login') {

                                steps {
                                  sh 'echo "login Docker ...."'
                                  sh 'docker login -u mohamedamineboughrara -p azerty123'
                                             }  }
                stage('Docker push') {

                                steps {
                                    sh 'echo "Docker is pushing ...."'
                                	sh 'docker push mohamedamineboughrara/demo'
                                             }  }

                 stage('Sending Mail'){
                                  steps{
                                    mail ( body: 'Congratulations! your DevOps project pipeline was completed succesfully!', subject: 'Pipeline', to: 'boughhh1111@gmail.com')
                                                         }
                                                     }

      }
    }