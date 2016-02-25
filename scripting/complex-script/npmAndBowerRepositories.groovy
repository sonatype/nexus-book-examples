repository.createNpmProxy('npmjs-org', 'https://registry.npmjs.org')

repository.createNpmHosted('npm-internal')

repository.createNpmGroup('npm-all', 'npm-interal', 'npmjs-org')

repository.createBowerProxy('bower-io', 'http://bower.herokuapp.com')

repository.createBowerHosted('bower-internal')

repository.createBowerGroup('bower-all', 'bower-io', 'bower-internal')

log.info('Script npmAndBowerRepositories completed successfully')