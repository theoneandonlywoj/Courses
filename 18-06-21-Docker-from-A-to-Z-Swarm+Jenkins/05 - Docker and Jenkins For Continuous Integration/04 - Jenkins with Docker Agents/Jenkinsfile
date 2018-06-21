pipeline {
  agent none
  stages {
    stage('docker npm version') {
      agent {
          docker { image 'node:latest' }
      }
      steps {
        sh "node -v"
        sh "npm -v"
      }
    }
    stage('dockerfile npm version') {
      agent {
          dockerfile true
      }
      steps {
        sh "node -v"
        sh "npm -v"
        sh "curl --version"
      }
    }
    stage('docker container script'){
      steps{
        script{
          docker.image("node:latest").inside {
            sh 'node -v'
            sh 'npm -v'
          }
        }
      }
    }
  }
}
