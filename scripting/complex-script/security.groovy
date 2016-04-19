import groovy.json.JsonOutput

// A bunch of security setup related tasks

//
// disable anonymous access 
//
security.setAnonymousAccess(false)
log.info('Anonymous access disabled')

//
// Create new admin user
//
def adminRole = ['nx-admin']
def janeDoe = security.addUser('jane.doe', 'Jane', 'Doe', 'jane.doe@example.com', true, 'changMe123', adminRole)
log.info('User jane.doe created')

//
// Create a new role that allows a user same access as anonymous and adds healtchcheck access
//
def devPrivileges = ['nx-healthcheck-read', 'nx-healthcheck-summary-read']
def anoRole = ['nx-anonymous']
// add roles that uses the built in nx-anonymous role as a basis and adds more privileges
security.addRole('developer', 'Developer', 'User with privileges to allow read access to repo content and healtcheck', devPrivileges, anoRole)
log.info('Role developer created')
// use the new role to create a user 
def devRoles = ['developer']
def johnDoe = security.addUser('john.doe', 'John', 'Doe', 'john.doe@example.com', true, 'changMe456', devRoles)
log.info('User john.doe created')

//
// Create new role that allows deployment and create a user to be used on a CI server
//
// privileges with pattern * to allow any format, browse and read are already part of nx-anonymous
def depPrivileges = ['nx-repository-view-*-*-add', 'nx-repository-view-*-*-edit']
def roles = ['developer']
// add roles that uses the developer role as a basis and adds more privileges
security.addRole('deployer', 'Deployer', 'User with privileges to allow deployment all repositories', depPrivileges, roles)
log.info('Role deployer created')
def depRoles = ['deployer']
def lJenkins = security.addUser('jenkins', 'Leeroy', 'Jenkins', 'leeroy.jenkins@example.com', true, 'changMe789', depRoles)
log.info('User jenkins created')


log.info('Script security completed successfully')

//Return a JSON response containing our new Users for confirmation
return JsonOutput.toJson([janeDoe, johnDoe, lJenkins])

// output will be like:

//[
//    {
//      "emailAddress": "jane.doe@example.com",
//      "firstName": "Jane",
//      "lastName": "Doe",
//      "name": "Jane Doe",
//      "readOnly": false,
//      "roles": [
//        {
//          "roleId": "nx-admin",
//          "source": "default"
//        }
//    ],
//      "source": "default",
//      "status": "active",
//      "userId": "jane.doe",
//      "version": null
//    },
//    {
//      "emailAddress": "john.doe@example.com",
//      "firstName": "John",
//      "lastName": "Doe",
//      "name": "John Doe",
//      "readOnly": false,
//      "roles": [
//        {
//          "roleId": "developer",
//          "source": "default"
//        }
//    ],
//      "source": "default",
//      "status": "active",
//      "userId": "john.doe",
//      "version": null
//    },
//    {
//      "emailAddress": "leeroy.jenkins@example.com",
//      "firstName": "Leeroy",
//      "lastName": "Jenkins",
//      "name": "Leeroy Jenkins",
//      "readOnly": false,
//      "roles": [
//        {
//          "roleId": "deployer",
//          "source": "default"
//        }
//    ],
//      "source": "default",
//      "status": "active",
//      "userId": "jenkins",
//      "version": null
//    }
//]

