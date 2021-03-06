try {

    stage("🛬 Checkout") {
        node('linux') {
            checkout scm
        }
    }

    stage("👷 Build") {
        node('linux') {
            withEnv(["JAVA_HOME=${tool 'jdk1.8'}", "PATH+MAVEN=${tool 'maven'}/bin:${env.JAVA_HOME}/bin"]) {
                sh "mvn clean compile -DskipTests"
            }
        }
    }

    stage("☔ Unit Tests") {
        node('linux') {
            withEnv(["JAVA_HOME=${tool 'jdk1.8'}", "PATH+MAVEN=${tool 'maven'}/bin:${env.JAVA_HOME}/bin"]) {
                sh "mvn clean test -Pdev"
                junit allowEmptyResults: true, testResults: '**/target/surefire-reports/TEST-*.xml,**/target/test-reports/*.xml'
            }
        }
    }

    stage("☔ Integration Tests") {
        node('linux') {
            withEnv(["JAVA_HOME=${tool 'jdk1.8'}", "PATH+MAVEN=${tool 'maven'}/bin:${env.JAVA_HOME}/bin"]) {
                sh "mvn clean integration-test -DskipTests -Pdev,int"
                junit allowEmptyResults: true, testResults: '**/target/failsafe-reports/TEST-*.xml,**/target/test-reports/*.xml'
            }
        }
    }

    stage("🔍 Sonar") {
        node('linux') {
            withEnv(["JAVA_HOME=${tool 'jdk1.8'}", "PATH+MAVEN=${tool 'maven'}/bin:${env.JAVA_HOME}/bin"]) {
                withSonarQubeEnv {
                    sh "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Pdev -Dmaven.test.failure.ignore=true sonar:sonar"
                }
            }
        }
    }

    stage("🛫 Deploy") {
        node('linux') {
            withEnv(["JAVA_HOME=${tool 'jdk1.8'}", "PATH+MAVEN=${tool 'maven'}/bin:${env.JAVA_HOME}/bin"]) {
                sh "mvn clean deploy -DskipTests"

            }
        }
    }
} catch (e) {
    sendMail("FAILED")
    throw e
}

sendMail("SUCCESS")

def sendMail(status) {
    mail(to: 'am.guezzar@gmail.com',
         subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
         body: """${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
                   Check console output at ${env.BUILD_URL}"""
        )
}





