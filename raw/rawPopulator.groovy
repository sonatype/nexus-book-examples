package groovyScripts

@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.2')
import groovyx.net.http.HTTPBuilder
@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.2')
import groovyx.net.http.HTTPBuilder

/**
 * Populate a raw repository will all content from a directory and all recursive subdirectories.
 */
import org.apache.http.HttpRequest
import org.apache.http.HttpRequestInterceptor
import org.apache.http.protocol.HttpContext

import static groovy.io.FileType.FILES
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.Method.PUT

CliBuilder cli = new CliBuilder(
    usage: 'groovy rawPopulator.groovy -u {user} -p {password} -d {path to content} -n {repoName} [-h {nx3Url}]')
cli.with {
  u longOpt: 'username', args: 1, required: true, 'User with permissions to deploy to the target repo'
  p longOpt: 'password', args: 1, required: true, 'Password for user'
  r longOpt: 'repository', args: 1, required: true, 'Name of raw repository to deploy to, strict content validation is suggested to be turned off.'
  d longOpt: 'directory', args: 1, required: true, 'Path of directory to recursively dploy'
  h longOpt: 'host', args: 1, 'Nexus Repository Manager 3 host url (including port if necessary). Defaults to http://localhost:8081'
}
def options = cli.parse(args)
if (!options) {
  return
}

File sourceFolder = new File(options.d)
assert sourceFolder.exists(): "${sourceFolder} does not exist"
def username = options.u
def password = options.password
def authInterceptor = new HttpRequestInterceptor() {
  void process(HttpRequest httpRequest, HttpContext httpContext) {
    httpRequest.addHeader('Authorization', 'Basic ' + "${username}:${password}".bytes.encodeBase64().toString())
  }
}

HTTPBuilder http = new HTTPBuilder(options.h ?: 'http://localhost:8081')
http.client.addRequestInterceptor(authInterceptor)
def resourcePath = "/repository/${options.r}/"

def files = []
sourceFolder.eachFileRecurse(FILES) { file ->
  if (file.name != '.DS_Store') {
    files << file
  }
}
println "Staging ${files.size()} files for publishing"

files.each { File file ->
  println "pushing $file"
  http.request(PUT) {
    uri.path = "$resourcePath${relativeize(sourceFolder, file)}"
    requestContentType = TEXT

    body = file.text
    response.success = { resp ->
      println "POST response status: ${resp.statusLine}"
      assert resp.statusLine.statusCode == 201
    }
  }
}

String relativeize(File parent, File child) {
  return parent.toURI().relativize(child.toURI()).getPath()
}