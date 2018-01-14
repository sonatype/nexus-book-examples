users = security.getSecuritySystem().listUsers()

size = users.size()

log.info("User count: $size")

for (user in users)
{
    log.info("User: $user")
}