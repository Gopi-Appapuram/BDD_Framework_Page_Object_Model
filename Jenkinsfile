pipeline {
    agent any

    tools {
        // Define the tools section to install Maven
        maven "Maven 3.6"
    }

    environment {
        // Define environment variables for email configuration
        EMAIL_RECIPIENTS = 'gopiappapuram022@gmail.com, appapuramgopi6@gmail.com'
        REPOSITORY_NAME = 'BDD_Framework_Page_Object_Model'
    }

    stages {
        stage('Build') {
            steps {
                // Checkout code from GitHub repository
                git 'https://github.com/Gopi-Appapuram/BDD_Framework_Page_Object_Model.git'

                // Run Maven build
                bat "mvn clean test -Dtest=amazontestrunner"
            }
        }
    }

    post {
        always {
            script {
                def buildStatus = currentBuild.currentResult ?: 'UNKNOWN'
                def buildDisplayName = "${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - ${buildStatus}"
                def currentTime = new Date().format("yyyy-MM-dd HH:mm:ss")
                def triggeredBy = env.BUILD_USER // Example: using BUILD_USER as an example of a Jenkins environment variable

                emailext(
                    subject: "${buildDisplayName}",
                    body: """<p>${buildDisplayName}</p>
                             <p>Repository: ${env.REPOSITORY_NAME}</p>
                             <p>Run Time: ${currentTime}</p>
                             <p>Triggered By: ${triggeredBy}</p>
                             <p>Check console output at <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                    to: "${env.EMAIL_RECIPIENTS}",
                    mimeType: 'text/html'
                )

                hangoutsNotify(
                    message: """
                    <b>Build Notification:</b><br/>
                    ${env.JOB_NAME} - Build #${env.BUILD_NUMBER} ${currentBuild.currentResult ?: 'UNKNOWN'}<br/>
                    Check console output at <a href="${env.BUILD_URL}">${env.BUILD_URL}</a>
                    """,
                    token: 'ukoXu6ZhL9QicFMD69uhXArG_',
                    threadByJob: false
                )


//                 hangoutsNotify(
//                     message: "This message is from a pipeline!",
//                     token: 'ukoXu6ZhL9QicFMD69uhXArG_',
//                     threadByJob: false
//                 )
            }
        }
    }
}
