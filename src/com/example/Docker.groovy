#!/user/bin/env groovy
package com.example

class Docker implements Serializable {

    def script

    Docker (script) {
        this.script = script
    }

    buildDockerImage(String imageName) {
        script.echo "building the docker image..."
        script.sh "docker build -t $imageName ."
    }

    dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "echo ${script.PASS} | docker login -u ${script.USER} --password-stdin"
        }
    }

    dockerPush(String imageName) {
        script.sh "docker push $imageName"
    }
}
