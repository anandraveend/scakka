node {
    def app    
    def DOCKER_PROJECT="${env.BRANCH_NAME}/scala-http"

    // Skip run if main branch
    if (branch ==~ 'main'){
        echo "Skipping build for ${env.BRANCH_NAME}"
        currentBuild.result = 'SUCCESS'
        return
    }    
    echo "Running build for ${env.BRANCH_NAME}"
    echo "Setting DOCKER_PROJECT = " + DOCKER_PROJECT
    stage('GIT CHECKOUT') {      
        checkout scm
    }

    stage('SBT BUILD') {
        echo "SBT Build"
        sh "sbt clean compile docker:stage"
    }

    stage('DOCKER BUILD') {  
        echo "Docker Build"
        docker.withRegistry('http://172.30.1.1:5000', 'docker-registry') {
            app = docker.build(DOCKER_PROJECT)
        }       
    }

    stage('DOCKER TAG AND PUSH TO OPENSHIFT') {   
        echo "Docker publish to " + DOCKER_PROJECT    
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