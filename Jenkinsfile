pipeline {
    agent any
    
    tools {
        maven 'Maven-3'
        jdk 'JDK-21'
    }
    
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
        
        stage('Run Survey Demo') {
            steps {
                echo '🎓 Running survey demo...'
                sh 'mvn exec:java -Dexec.mainClass="com.college.survey.SurveyApp" << EOF\n1\nTest Student\nCSE\n4\n5\nGreat!\n4\nEOF'
            }
        }
    }
    
    post {
        success {
            emailext (
                subject: "✅ SUCCESS: Survey Build #${env.BUILD_NUMBER}",
                body: """
                    <h2 style="color:green">Build Successful!</h2>
                    <p><b>Project:</b> Simple College Survey</p>
                    <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                    <p><b>Status:</b> ✅ PASSED</p>
                    <p><b>Build URL:</b> <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></p>
                    <p><b>Branch:</b> ${env.GIT_BRANCH}</p>
                    <hr>
                    <h3>Build Summary:</h3>
                    <ul>
                        <li>✓ Code compiled successfully</li>
                        <li>✓ All tests passed</li>
                        <li>✓ Package created</li>
                        <li>✓ Survey application is ready</li>
                    </ul>
                    <hr>
                    <p><i>Jenkins Pipeline | College Survey System</i></p>
                """,
                to: "${EMAIL_RECIPIENTS}"
            )
            echo "📧 Success email sent!"
        }
        
        failure {
            emailext (
                subject: "❌ FAILED: Survey Build #${env.BUILD_NUMBER}",
                body: """
                    <h2 style="color:red">Build Failed!</h2>
                    <p><b>Project:</b> Simple College Survey</p>
                    <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                    <p><b>Status:</b> ❌ FAILED</p>
                    <p><b>Build URL:</b> <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></p>
                    <p><b>Failed Stage:</b> ${env.STAGE_NAME}</p>
                    <hr>
                    <h3>Actions Required:</h3>
                    <ol>
                        <li>Check console output: <a href='${env.BUILD_URL}console'>Console Output</a></li>
                        <li>Review recent code changes</li>
                        <li>Fix compilation or test errors</li>
                    </ol>
                    <hr>
                    <p><i>Investigate immediately - Pipeline broken!</i></p>
                """,
                to: "${EMAIL_RECIPIENTS}"
            )
            echo "📧 Failure email sent!"
        }
        
        unstable {
            emailext (
                subject: "⚠️ UNSTABLE: Survey Build #${env.BUILD_NUMBER}",
                body: """
                    <h2 style="color:orange">Build Unstable</h2>
                    <p><b>Project:</b> Simple College Survey</p>
                    <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                    <p><b>Some tests may have failed.</b></p>
                    <p><b>Build URL:</b> <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></p>
                    <p>Check test reports for details.</p>
                """,
                to: "${EMAIL_RECIPIENTS}"
            )
        }
        
        always {
            echo '🏁 Pipeline completed!'
            cleanWs()
        }
    }
}
