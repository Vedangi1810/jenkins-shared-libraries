def call(String ProjectName, String ImageTag, String DockerHubUser){
    bat 'docker build -t ${DockerHubUser}/${ProjectName}:${ImageTag}'
}
