import org.sonatype.nexus.repository.Repository
import org.sonatype.nexus.repository.storage.Query
import org.sonatype.nexus.repository.storage.StorageFacet

import groovy.json.JsonOutput

def result = [:]

def totalComponents = 0
def totalAssets = 0

repository.repositoryManager.browse().each { Repository repo ->
  def tx = repo.facet(StorageFacet).txSupplier().get()
  def components = 0
  def assets = 0
  try {
    tx.begin()
    components = tx.countComponents(Query.builder().where('1').eq(1).build(), [repo])
    assets = tx.countAssets(Query.builder().where('1').eq(1).build(), [repo])
    tx.commit()
  } catch (Exception e) {
    log.warn("Transaction failed {}", e.toString())
    tx.rollback()
  } finally {
    tx.close()
  }
  totalComponents += components
  totalAssets += assets
  result[repo.name] = [components: components, assets: assets]
}

result["_totals"] = [components : totalComponents, assets : totalAssets]

def json = JsonOutput.toJson(result)
log.info json
return json
