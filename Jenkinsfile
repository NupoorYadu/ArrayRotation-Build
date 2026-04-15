// Jenkinsfile for Array Rotation - Selenium Automation Testing
// Pipeline as Code - Declarative approach

pipeline {
    agent any

    environment {
        PROJECT_NAME = 'array-rotation'
        BUILD_ARTIFACTS = 'target'
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_LOGIN = 'admin'
        GIT_COMMIT_LOG = 'git log --oneline -10'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 30, unit: 'MINUTES')
        timestamps()
    }

    triggers {
        pollSCM('H/15 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                echo '========== Checking out source code =========='
                checkout scm
                bat 'git log --oneline -10'
            }
        }

        stage('Build') {
            steps {
                echo '========== Building Maven project =========='
                bat 'mvn clean compile'
            }
        }

        stage('Automated Unit Tests') {
            steps {
                echo '========== Running automated unit tests (30+ tests) =========='
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                echo '========== Running JaCoCo code coverage analysis =========='
                bat 'mvn jacoco:report'
            }
        }

        stage('Code Quality Analysis') {
            steps {
                echo '========== SonarQube Code Quality Analysis (Optional) =========='
                // Uncomment to enable SonarQube integration
                // bat 'mvn sonar:sonar -Dsonar.host.url=%SONAR_HOST_URL% -Dsonar.login=%SONAR_LOGIN%'
                echo 'SonarQube analysis skipped. To enable: uncomment sonar:sonar goal'
            }
        }

        stage('Package') {
            steps {
                echo '========== Packaging JAR artifact =========='
                bat 'mvn package -DskipTests'
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo '========== Archiving build artifacts =========='
                archiveArtifacts artifacts: 'target/array-rotation-1.0.0.jar,target/site/jacoco/**',
                                 allowEmptyArchive: true,
                                 fingerprint: true
                echo 'Artifacts archived successfully'
            }
        }

        stage('Build Summary') {
            steps {
                echo '========== Build Summary =========='
                echo "Project: ${PROJECT_NAME}"
                echo "Build Number: ${BUILD_NUMBER}"
                echo "Build Status: SUCCESS"
                echo "Artifacts: JAR, JaCoCo Coverage Report"
                echo "Tests: 30+ automated test cases"
                echo "Git Commit: ${bat(script: 'git rev-parse HEAD', returnStdout: true).trim()}"
                echo '========== Pipeline Complete =========='
            }
        }
    }

    post {
        always {
            echo '========== Post-Build Actions =========='
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
        success {
            echo '✓ Build completed successfully'
        }
        failure {
            echo '✗ Build failed. Check console output for details.'
        }
    }
}
