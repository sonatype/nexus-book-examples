// From https://help.sonatype.com/repomanager3/rest-and-integration-api/script-api/examples

import groovy.json.JsonSlurper
 
//expects json string with appropriate content to be passed in
def role = new JsonSlurper().parseText(args)
 
security.addRole(role.id, role.name, role.description, role.privilegeIds, role.roleIds)