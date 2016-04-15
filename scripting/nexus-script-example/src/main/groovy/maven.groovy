// simple example showing simple method and equivalent method with all default parameters expanded.

import org.sonatype.nexus.blobstore.api.BlobStoreManager
import org.sonatype.nexus.repository.storage.WritePolicy

repository.createMavenHosted('private')

repository.createMavenHosted('private-again', BlobStoreManager.DEFAULT_BLOBSTORE_NAME, VersionPolicy.RELEASE,
        WritePolicy.ALLOW_ONCE, LayoutPolicy.STRICT)
