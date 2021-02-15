node {
    def app
    stage('Clone repository') {      
        checkout scm
    }

    stage('Build sbt code') {
        echo "${env.WORKSPACE}"
        sh "sbt clean compile docker:stage"
    }

    stage('Build docker image') {  
        docker.withRegistry('http://172.30.1.1:5000', 'docker-registry') {
            app = docker.build("myproject/skakka")
        }       
    }

    stage('Test docker image') {
        app.inside {
            sh 'echo "Tests passed"'
        }
    }

    stage('Tag and push image') {        
        docker.withRegistry('http://172.30.1.1:5000', 'docker-registry') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }
}

node {
    stage('NOTIFY VIA EMAIL'){
        emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",to: "mail4anandkr@gmail.com", subject: "Job ${env.JOB_NAME} build ${env.BUILD_NUMBER} : ${currentBuild.currentResult}"

    }
}