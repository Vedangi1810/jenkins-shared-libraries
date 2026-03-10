def call(String ProjectName, String ImageTag){
    bat 'docker build -t ${ProjectName}:${ImageTag} .'
}
