
// the repository manager is hosted behind a nging proxy server that redirects ports, 
// manages HTTPS and is configured with a DNS name
// so we need to set the base URL
core.baseUrl('https://repo.example.com')

// another proxy server in the data center is the gate to the internet and therefore 
// any remote proxy repositoriesthe repository manager therefore needs to connect to 
// it as HTTP/HTTPS proxy and authenticate to be able to retrieve remote content
core.httpProxyWithBasicAuth('webproxy', 9999, 'repomgr', 'letmethrough')

log.info('Script core completed successfully')