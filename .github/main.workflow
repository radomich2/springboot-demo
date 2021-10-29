workflow "Test my code" {
  on = "push"
  resolves = ["test"]
}

action "test" {
  uses = "docker://node:alpine"
  runs = "env"
  secrets = [
    "MACHINEUSER_VAULT_KEY"
  ]
}
