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
def devRole = security.addRole("developer", "Developer", "User with privileges to allow read access to repo content and healthcheck", devPrivileges, anoRole)
// use the new role to create a user 
security.addUser("john.doe", "John", "Doe", "john.doe@example.com", true, "changMe456", devRole)


//
// Create new role that allows deployment and create a user to be used on a CI server
//
// privileges with pattern * to allow any format, browse and read are already part of nx-anonymous
def depPrivileges = new HashSet(["nx-repository-view-*-*-add", "nx-repository-view-*-*-edit"])
def devRole = new HashSet(["developer"]) 
// add roles that uses the developer role as a basis and adds more privileges
def depRole = security.addRole("deployer", "Deployer", "User with privileges to allow deployment all repositories", depPrivileges, devRole)
security.addUser("jenkins", "Leeroy", "Jenkins", "leeroy.jenkins@example.com", true, "changMe789", depRole)