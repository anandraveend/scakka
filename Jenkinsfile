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
                sh "sbt docker:publishLocal"
            }
        }       

    }
}