def call(String tokenString){
    pipeline {
        agent{label "docker"}
        triggers {
            GenericTrigger{
                genericVariables: [
                    [key: 'ref', value: "$"]
                ],
                causeString: 'Triggered on',
                token: tokenString,
                printContributedVariables: true,
                printPostContent: true,
                silentResponse: false,
            }
        }
       stages {
        stage('Demo Stage') {
            steps{
                script{
                    def jsonObj = readJson text: ref
                    echo "Webhook Payload is"
                    echo "$ref.eventKey"
                }
            }
        }
       } 
    }
}