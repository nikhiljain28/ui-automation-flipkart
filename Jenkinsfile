pipeline {
    agent any

    tools {
        maven 'Maven'   
    }

    stages {

        stage('Clone Repository') {
            steps {
                echo 'Cloning repository...'
                git branch: 'main',
                    url: 'https://github.com/your-username/your-repo.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building project...'
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Publish Report') {
            steps {
                echo 'Publishing Extent Report...'
                publishHTML(target: [
                    allowMissing         : false,
                    alwaysLinkToLastBuild: true,
                    keepAll              : true,
                    reportDir            : 'reports',
                    reportFiles          : 'ExtentReport.html',
                    reportName           : 'Extent Test Report'
                ])
            }
        }
    }

    post {
        success {
            echo 'Build Successful — Report emailed!'
        }
        failure {
            echo 'Build Failed — Check Console Output!'
        }
        always {
            echo 'Build Finished!'
        }
    }
}
