def call(){
  bat "docker rm -f db_cont || true"
  bat "docker compose down && docker compose up -d"
}
