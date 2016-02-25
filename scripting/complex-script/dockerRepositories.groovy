import org.sonatype.nexus.blobstore.api.BlobStoreManager

// create hosted repo and expose via https to allow deployments
repository.createDockerHosted('docker-internal', null, 18444) 

// create proxy repo of Docker Hub and enable v1 to get search to work
// no ports since access is only indirectly via group
repository.createDockerProxy('docker-hub',                   // name
                             'https://registry-1.docker.io', // remoteUrl
                             'HUB',                          // indexType
                             null,                           // indexUrl
                             null,                           // httpPort
                             null,                           // httpsPort
                             BlobStoreManager.DEFAULT_BLOBSTORE_NAME, // blobStoreName 
                             true, // strictContentTypeValidation
                             true  // v1Enabled
                             )

// create group and allow access via https
def groupMembers = ['docker-hub', 'docker-internal']
repository.createDockerGroup('docker-all', null, 18443, groupMembers, true)


log.info('Script dockerRepositories completed successfully')