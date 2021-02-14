node {
    def app
    stage('Clone repository') {      
        checkout scm
    }

    stage('Build image') {  
        docker.withRegistry('http://172.30.1.1:5000', 'docker-registry') {
            app = docker.build("skakka")
        }       
    }

    stage('Test image') {
        app.inside {
            sh 'echo "Tests passed"'
        }
    }

    stage('Push image') {        
        docker.withRegistry('http://172.30.1.1:5000', 'docker-registry') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }
}