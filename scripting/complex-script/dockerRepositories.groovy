import org.sonatype.nexus.blobstore.api.BlobStoreManager

repository.createDockerHosted("docker-internal", 1, 18444) // change httpPort to null once that works

repository.createDockerProxy("docker-hub",              // name
                             "https://registry-1.docker.io", // remoteUrl
                             "HUB",                          // indexType
                             null,                           // indexUrl
                             2,                           // httpPort TBD -- change to null once that works
                             3,                           // httpsPort TBD -- change to null once that works
                             BlobStoreManager.DEFAULT_BLOBSTORE_NAME, // blobStoreName 
                             true, // strictContentTypeValidation
                             true  // v1Enabled
                             )

repository.createDockerGroup("docker-all", 4, 18443, true, "docker-internal", "docker-hub") // change httpPort to null once that works


