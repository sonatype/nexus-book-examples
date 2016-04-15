import groovy.xml.MarkupBuilder
def newRepositories = []
newRepositories << repository.createNpmProxy('npmjs-org2', 'https://registry.npmjs.org')

newRepositories << repository.createNpmHosted('npm-internal2')

def npmMembers = ['npmjs-org', 'npm-internal' ]
newRepositories << repository.createNpmGroup('npm-all2', npmMembers)

newRepositories << repository.createBowerProxy('bower-io2', 'http://bower.herokuapp.com')

newRepositories << repository.createBowerHosted('bower-internal2')

def bowerMembers = ['bower-io', 'bower-internal']
newRepositories << repository.createBowerGroup('bower-all2', bowerMembers)

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
//  <repository name='npmjs-org2' url='http://localhost:8081/repository/npmjs-org2' />
//  <repository name='npm-internal2' url='http://localhost:8081/repository/npm-internal2' />
//  <repository name='npm-all2' url='http://localhost:8081/repository/npm-all2' />
//  <repository name='bower-io2' url='http://localhost:8081/repository/bower-io2' />
//  <repository name='bower-internal2' url='http://localhost:8081/repository/bower-internal2' />
//  <repository name='bower-all2' url='http://localhost:8081/repository/bower-all2' />
//</repositories>
