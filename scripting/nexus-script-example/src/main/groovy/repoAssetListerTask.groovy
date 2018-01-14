import org.sonatype.nexus.repository.storage.Asset
import org.sonatype.nexus.repository.storage.Query
import org.sonatype.nexus.repository.storage.StorageFacet

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

def assetListFile = new File('/tmp/assetListFile.txt')
def request = new JsonSlurper().parseText("{\"repoName\":\"maven-releases\",\"startDate\":\"2016-01-01\"}")

assert request.repoName: 'repoName parameter is required'
assert request.startDate: 'startDate parameter is required, format: yyyy-mm-dd'

log.info("Gathering Asset list for repository: ${request.repoName} as of startDate: ${request.startDate}")

def repo = repository.repositoryManager.get(request.repoName)
StorageFacet storageFacet = repo.facet(StorageFacet)
def tx = storageFacet.txSupplier().get()

try {
    tx.begin()
    Iterable<Asset> assets = tx.
            findAssets(Query.builder().where('last_updated > ').param(request.startDate).build(), [repo])

    assets.each {Asset asset ->
        assetListFile << asset.name() + '\n'
    }
}
finally {
    tx.close()
}
