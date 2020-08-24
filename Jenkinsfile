pipeline {
    agent any

    environment {
        DOCKER_PASS = credentials('DOCKER_PASS')
    }

    stages {
        stage('Git') {
            steps {
                // Pulling latest repository from git
                git 'https://github.com/jbirtharia/todo-web-application-mysql-docker.git'
            }

        }
        stage('Build'){
            steps{
                // Building jar file
                sh '''
                    echo Maven Build Start...
                    mvn package -Dmaven.test.skip=true
                 '''
            }
             post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    archiveArtifacts '**/target/*.war'
                    archiveArtifacts '**/target/*.war.original'
                }
             }
        }
        stage('Pushing'){
            steps{
                // Pushing image into docker hub
                sh '''
                    echo "*****Logging In**********"
                    docker login -u jbirtharia -p ${DOCKER_PASS}
                    echo "*****Pushing To Dockerhub****"

                    DOCKER_TAG=$(docker images jbirtharia/todo-web-application-mysql| tail -n +2 | awk '{print $2}'| head -1)

                    docker push jbirtharia/todo-web-application-mysql:$DOCKER_TAG
                    
                 '''
            }
        }
        stage('Cleaning'){
            steps{
                // Cleaning target folder
                sh "chmod +x ./clean.sh"
                sh "./clean.sh"
            }
        }
        stage ('Deploy') {
            steps{
                //Deploying on uat server by pulling image from dockerhub
                script {
                             def runDeployScript = "./deploy.sh"
                             sshagent(credentials : ['app']) {
                                 sh "ssh -o StrictHostKeyChecking=no ubuntu@18.218.84.178 ${runDeployScript}"
                             }
                        }
                }
        }
    }
}
