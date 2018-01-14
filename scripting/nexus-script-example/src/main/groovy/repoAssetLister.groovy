import org.sonatype.nexus.repository.storage.Asset
import org.sonatype.nexus.repository.storage.Query
import org.sonatype.nexus.repository.storage.StorageFacet

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

def request = new JsonSlurper().parseText(args)
assert request.repoName: 'repoName parameter is required'
assert request.startDate: 'startDate parameter is required, format: yyyy-mm-dd'

log.info("Gathering Asset list for repository: ${request.repoName} as of startDate: ${request.startDate}")

def repo = repository.repositoryManager.get(request.repoName)
StorageFacet storageFacet = repo.facet(StorageFacet)
def tx = storageFacet.txSupplier().get()
def urls = []
try {
    tx.begin()
    Iterable<Asset> assets = tx.
            findAssets(Query.builder().where('last_updated > ').param(request.startDate).build(), [repo])
    urls = assets.collect { "/repository/${repo.name}/${it.name()}" }
}
finally {
    tx.close()
}

def result = JsonOutput.toJson([
        assets  : urls,
        since   : request.startDate,
        repoName: request.repoName
])
return result

