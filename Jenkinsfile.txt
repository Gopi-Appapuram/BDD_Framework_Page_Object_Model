pipeline {
    agent any

    tools {
        // Install the Maven version configured as "Maven 3.6" and add it to the path.
        maven "Maven 3.6"
    }

    environment {
        // Define environment variables for email configuration
        EMAIL_RECIPIENTS = 'team@example.com'
        // Define repository name
        REPOSITORY_NAME = 'BDD_Framework_Page_Object_Model'
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/Gopi-Appapuram/BDD_Framework_Page_Object_Model.git'

                // To run Maven on a Windows agent, use
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }

    post {
        success {
            // If Maven was able to run the tests, even if some of the tests failed, record the test results and archive the jar file.
            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts 'target/*.jar'
        }
        always {
            script {
                def currentTime = new Date().format("yyyy-MM-dd HH:mm:ss")
                def buildUser = env.BUILD_USER ?: 'Unknown' // Default to 'Unknown' if not available
                emailext(
                    subject: "Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' - ${currentBuild.currentResult}",
                    body: """<p>Build ${currentBuild.currentResult}!</p>
                             <p>Job: ${env.JOB_NAME}</p>
                             <p>Build Number: ${env.BUILD_NUMBER}</p>
                             <p>Repository: ${env.REPOSITORY_NAME}</p>
                             <p>Run Time: ${currentTime}</p>
                             <p>Triggered By: ${buildUser}</p>
                             <p>Check console output at ${env.BUILD_URL}</p>""",
                    to: "${env.EMAIL_RECIPIENTS}"
                )
            }
        }
    }
}
