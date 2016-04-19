// The four main API providers are blobStore, security, repository and core.

blobStore.createFileBlobStore('npm', 'npm')

security.addRole('blue','blue', 'Blue Role', [], [])

repository.createBowerHosted('bower-internal')

core.baseUrl('http://repo.example.com')
