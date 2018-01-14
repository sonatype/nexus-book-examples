import groovy.json.JsonOutput
import groovy.json.JsonSlurper

// Only LDAP Users & associated Roles
//users = security.getSecuritySystem().getUserManager("LDAP").listUsers()


//All Users & Roles
users = security.getSecuritySystem().listUsers()

//expects json string with appropriate content to be passed in
def p_role = null
if (args?.trim()) {
    p_role = new JsonSlurper().parseText(args)
}

Map<String, Set<String> > role_to_user = new HashMap<String, Set<String> >();

for (user in users)
{
    //log.info("User: $user")

    for (role in user.getRoles())
    {
        //log.info("    ----> $role")

        if (p_role == null || role.roleId.equals(p_role.id)) {
            if (role_to_user.containsKey(role.roleId) == false) {
                role_to_user.put(role.roleId, new TreeSet<String>())
            }

            Set<String> userList = role_to_user.get(role.roleId)
            userList.add(user.userId)
        }
    }

}

return JsonOutput.toJson(role_to_user)