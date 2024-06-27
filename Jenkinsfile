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
            }
        }
    }
}



pipeline {
    agent any

    environment {
        GOOGLE_CHAT_WEBHOOK_URL = 'YOUR_WEBHOOK_URL_HERE'
    }

    stages {
        stage('Build') {
            steps {
                // Your build steps here
                echo 'Building...'
            }
        }
        stage('Test') {
            steps {
                // Your test steps here
                echo 'Testing...'
            }
        }
        stage('Deploy') {
            steps {
                // Your deploy steps here
                echo 'Deploying...'
            }
        }
    }

    post {
        always {
            steps {
                script {
                    def jsonPayload = """
                    {
                       "cardsV2":[
                          {
                             "cardId":"unique-card-id",
                             "card":{
                                "header":{
                                   "title":"${env.JOB_NAME}",
                                   "subtitle":"Build ${env.BUILD_ID}",
                                   "imageUrl":"https://developers.google.com/chat/images/quickstart-app-avatar.png",
                                   "imageType":"CIRCLE"
                                },
                                "sections":[
                                   {
                                      "header":"${currentBuild.currentResult}",
                                      "collapsible":true,
                                      "uncollapsibleWidgetsCount":1,
                                      "widgets":[
                                         {
                                            "textParagraph":{
                                               "text":"Click <a href=\\"${env.BUILD_URL}\\">here</a> for more info"
                                            }
                                         },
                                         {
                                            "divider":{}
                                         },
                                         {
                                            "decoratedText":{
                                               "icon":{
                                                  "knownIcon":"PERSON"
                                               },
                                               "topLabel":"Last commit",
                                               "text":"<i>${GIT_COMMIT}</i>",
                                               "bottomLabel":"Author: ${GIT_AUTHOR_NAME}"
                                            }
                                         }
                                      ]
                                   }
                                ]
                             }
                          }
                       ]
                    }
                    """

                    sh """
                    curl -X POST -H 'Content-Type: application/json' \
                    -d '${jsonPayload}' \
                    ${GOOGLE_CHAT_WEBHOOK_URL}
                    """
                }
            }
        }
    }
}
