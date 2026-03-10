def call(Map config = [:]) {
    // Set default values if not provided
    def credentialsId = config.credentialsId ?: 'dockerHubCred'
    def imageName = config.imageName ?: 'notes-app'
    def imageTag = config.imageTag ?: 'latest'

    echo "Preparing to push ${imageName}:${imageTag} to Docker Hub..."

    withCredentials([usernamePassword(
        credentialsId: credentialsId,
        passwordVariable: 'dockerHubPass',
        usernameVariable: 'dockerHubUser'
    )]) {
        // Securely login using stdin on Windows
        bat "echo %dockerHubPass% | docker login -u %dockerHubUser% --password-stdin"
        
        // Tag and push using the credentials provided by Jenkins
        bat "docker image tag ${imageName}:${imageTag} %dockerHubUser%/${imageName}:${imageTag}"
        bat "docker push %dockerHubUser%/${imageName}:${imageTag}"
        
        echo "Successfully pushed %dockerHubUser%/${imageName}:${imageTag}"
    }
}
