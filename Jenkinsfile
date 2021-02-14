pipeline {
    agent any
    stages {
        stage('Build'){
            steps{
                sh "sbt clean compile"
            }
        }
        stage ('Docker publish'){
            steps{
                sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASSWORD} ${DOCKER_REPO}"
                sh "sbt docker:publishLocal"
            }
        }       

    }
}