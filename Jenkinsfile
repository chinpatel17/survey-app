pipeline {
    agent any
    
    environment {
        EMAIL_RECIPIENTS = 'patel007ab17@gmail.com'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo '📦 Cloning repository...'
                git url: 'https://github.com/chinpatel17/survey-app.git', branch: 'master'
            }
        }
        
        stage('Build') {
            steps {
                echo '🔨 Building project...'
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo '🧪 Running tests...'
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo '📦 Packaging...'
                sh 'mvn package'
            }
        }
    }
    
    post {
        success {
            emailext(
                to: "${EMAIL_RECIPIENTS}",
                subject: "✅ Build SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build completed successfully!\n\nURL: ${env.BUILD_URL}\nBranch: ${env.GIT_BRANCH}"
            )
        }
        failure {
            emailext(
                to: "${EMAIL_RECIPIENTS}",
                subject: "❌ Build FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build failed!\n\nURL: ${env.BUILD_URL}console\nStage: ${env.STAGE_NAME}"
            )
        }
    }
}
