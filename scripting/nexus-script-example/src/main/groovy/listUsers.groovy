import groovy.json.JsonOutput
import org.sonatype.nexus.security.user.User

users = security.getSecuritySystem().listUsers()
size = users.size()
log.info("User count: $size")

return JsonOutput.toJson(users)
