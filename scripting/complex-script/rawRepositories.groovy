// create a new blob store dedicated to usage with raw repositories
def rawStore = blobStore.createFileBlobStore('raw', 'raw')

// and create a first raw hosted repository for documentation using the new blob store
repository.createRawHosted('documentation', rawStore.name)

log.info('Script rawRepositories completed successfully')