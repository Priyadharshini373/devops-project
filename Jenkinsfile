pipeline {
agent any

environment {
    DOCKER_IMAGE = "priyadharshini37/devops-demo:latest"
}

tools {
    maven 'Maven'
}

stages {

    stage('Build') {
        steps {
            sh 'mvn clean package'
        }
    }

    stage('SonarQube Analysis') {
        steps {
            withSonarQubeEnv('sonarqube') {
                sh 'mvn sonar:sonar'
            }
        }
    }

    stage('Build Docker Image') {
        steps {
            sh 'docker build -t $DOCKER_IMAGE .'
        }
    }

    stage('Push Docker Image') {
        steps {
            withCredentials([usernamePassword(
                credentialsId: 'dockerhub',
                usernameVariable: 'USERNAME',
                passwordVariable: 'PASSWORD'
            )]) {
                sh '''
                echo $PASSWORD | docker login -u $USERNAME --password-stdin
                docker push $DOCKER_IMAGE
                '''
            }
        }
    }

    stage('Deploy to Kubernetes') {
        steps {
            sh 'kubectl apply -f k8s/deployment.yaml'
        }
    }
}


}

