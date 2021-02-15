node {
    def app
    stage('GIT CHECKOUT') {      
        checkout scm
    }

    stage('SBT BUILD') {
        echo "${env.WORKSPACE}"
        sh "sbt clean compile docker:stage"
    }

    stage('DOCKER BUILD') {  
        docker.withRegistry('http://172.30.1.1:5000', 'docker-registry') {
            app = docker.build("myproject/skakka")
        }       
    }

    // stage('Test docker image') {
    //     app.inside {
    //         sh 'echo "Tests passed"'
    //     }
    // }

    stage('DOCKER TAG AND PUSH TO OPENSHIFT') {        
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