// A bunch of security setup related tasks

//
// disable anonymous access 
//
security.setAnonymousAccess(false)


//
// Create new admin user
//
def adminRole = ["nx-admin"] 
security.addUser("jane.doe", "Jane", "Doe", "jane.doe@example.com", true, "changMe123", adminRole)


//
// Create a new role that allows a user same access as anonymous and adds healtchcheck access
//
def devPrivileges = new HashSet(["nx-healthcheck-read", "nx-healthcheck-summary-read"])
def anoRole = new HashSet(["nx-anonymous"])
// add roles that uses the built in nx-anonymous role as a basis and adds more privileges
security.addRole("developer", "Developer", "User with privileges to allow read access to repo content and healtcheck", devPrivileges, anoRole)
// use the new role to create a user 
def devRoles = ["developer"]
security.addUser("john.doe", "John", "Doe", "john.doe@example.com", true, "changMe456", devRoles)


//
// Create new role that allows deployment and create a user to be used on a CI server
//
// privileges with pattern * to allow any format, browse and read are already part of nx-anonymous
def depPrivileges = new HashSet(["nx-repository-view-*-*-add", "nx-repository-view-*-*-edit"])
def roles = new HashSet(["developer"])
// add roles that uses the developer role as a basis and adds more privileges
security.addRole("deployer", "Deployer", "User with privileges to allow deployment all repositories", depPrivileges, roles)
def depRoles = ["deployer"]
security.addUser("jenkins", "Leeroy", "Jenkins", "leeroy.jenkins@example.com", true, "changMe789", depRoles)