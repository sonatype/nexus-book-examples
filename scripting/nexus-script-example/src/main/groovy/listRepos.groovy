import groovy.json.JsonOutput
import org.sonatype.nexus.repository.Repository

List<String> urls = []

repository.repositoryManager.browse().each { Repository repo ->
    log.info("Repository: $repo")
    urls.add(repo.name)

}



return JsonOutput.toJson(urls)