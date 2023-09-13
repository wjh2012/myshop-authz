pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub_accessToken')
        MYSHOP_AUTHZ_CREDENTIALS = credentials('myshop_authz')
    }

    stages {
        stage('Github') {
            steps {
                git branch: 'develop', url: 'https://github.com/wjh2012/myshop-authz.git'
            }
        }

        stage('Build jar') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew bootJar'
            }
        }

        stage('Build docker image') {
            steps{
                sh 'docker build -t ggomg/myshop_authz:latest .'
            }
        }

        stage('Login DockerHub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push docker image') {
            steps {
                sh 'docker push ggomg/myshop_authz:latest'
            }
        }

        stage('Deploy') {
            steps {
                sshagent(['myshop_authz']) {
                    sh '''
                        ssh -o "StrictHostKeyChecking=accept-new" ec2-user@ec2-13-209-4-220.ap-northeast-2.compute.amazonaws.com "echo 'hello world'"
                        scp docker-compose.yml ec2-user@ec2-13-209-4-220.ap-northeast-2.compute.amazonaws.com:/home/ec2-user
                        ssh ec2-user@ec2-13-209-4-220.ap-northeast-2.compute.amazonaws.com 'docker compose up -d'
                        ssh ec2-user@ec2-13-209-4-220.ap-northeast-2.compute.amazonaws.com 'docker ps -a'
                        ssh ec2-user@ec2-13-209-4-220.ap-northeast-2.compute.amazonaws.com 'docker system prune -f'
                    '''
                }
            }
        }

        // 위와 같음
        // stage('Deploy') {
        //     steps {
        //         withCredentials([sshUserPrivateKey(credentialsId: 'myshop_authz', keyFileVariable: 'SSH_KEY', passphraseVariable: 'SSH_PASSPHRASE')]) {
        //             sh '''
        //                 ssh -o "StrictHostKeyChecking=accept-new" -i ${SSH_KEY} ec2-user@ec2-13-209-4-220.ap-northeast-2.compute.amazonaws.com "echo 'hello world'"
        //             '''
        //         }
        //     }
        // }

        stage('Prune Docker') {
            steps{
                sh 'docker system prune -a --volumes -f'
            }
        }
    }

    post {
        always{
            sh 'docker logout'
        }
    }
}