repository.createNpmProxy('npmjs-org', 'https://registry.npmjs.org')

repository.createNpmHosted('npm-internal')

def npmMembers = ['npmjs-org', 'npm-internal' ]
repository.createNpmGroup('npm-all', npmMembers)

repository.createBowerProxy('bower-io', 'http://bower.herokuapp.com')

repository.createBowerHosted('bower-internal')

def bowerMembers = ['bower-io', 'bower-internal']
repository.createBowerGroup('bower-all', bowerMembers)

log.info('Script npmAndBowerRepositories completed successfully')