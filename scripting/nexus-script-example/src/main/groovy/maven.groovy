// simple example showing simple method and equivalent method with all default parameters expanded.

import org.sonatype.nexus.blobstore.api.BlobStoreManager
import org.sonatype.nexus.repository.storage.WritePolicy
import org.sonatype.nexus.repository.maven.VersionPolicy
import org.sonatype.nexus.repository.maven.LayoutPolicy

repository.createMavenHosted('private')

repository.createMavenHosted('private-again', BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true, VersionPolicy.RELEASE,
        WritePolicy.ALLOW_ONCE, LayoutPolicy.STRICT)
