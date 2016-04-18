import groovy.xml.MarkupBuilder
def newRepositories = []
newRepositories << repository.createNpmProxy('npmjs-org', 'https://registry.npmjs.org')

newRepositories << repository.createNpmHosted('npm-internal')

def npmMembers = ['npmjs-org', 'npm-internal' ]
newRepositories << repository.createNpmGroup('npm-all', npmMembers)

newRepositories << repository.createBowerProxy('bower-io', 'http://bower.herokuapp.com')

newRepositories << repository.createBowerHosted('bower-internal')

def bowerMembers = ['bower-io', 'bower-internal']
newRepositories << repository.createBowerGroup('bower-all', bowerMembers)

log.info('Script npmAndBowerRepositories completed successfully')

// build up an XML response containing the urls for newly created repositories
def writer = new StringWriter()
def xml = new MarkupBuilder(writer)
xml.repositories() {
  newRepositories.each { repo ->
    repository(name: repo.name, url: repo.url)    
  }  
}
return writer.toString()

// output will be like:

//<repositories>
//  <repository name='npmjs-org' url='http://localhost:8081/repository/npmjs-org' />
//  <repository name='npm-internal' url='http://localhost:8081/repository/npm-internal' />
//  <repository name='npm-all' url='http://localhost:8081/repository/npm-all' />
//  <repository name='bower-io' url='http://localhost:8081/repository/bower-io' />
//  <repository name='bower-internal' url='http://localhost:8081/repository/bower-internal' />
//  <repository name='bower-all' url='http://localhost:8081/repository/bower-all' />
//</repositories>
