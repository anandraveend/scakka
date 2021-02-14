pipeline {
    agent any
    tools {sbt "sbtPipeline"}

    stages {
        stage('Build'){
            steps{
                sbt clean compile
            }
        }
        stage ('Docker publish'){
            steps{
                sbt docker:publishLocal
            }
        }
        

    }
}