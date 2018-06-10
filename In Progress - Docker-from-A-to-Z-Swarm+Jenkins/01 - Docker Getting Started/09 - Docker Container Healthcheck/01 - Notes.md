# Healthcheck: Web Server Container Example
## Accessing http on the localhost with curl
### Example for full content port 8080:
curl "localhost:8080"

### Fetching only the HTTP-header:
curl --head "localhost:8080"

### Forcing curl to exit in proper fail state if the request fails:
curl --head -f "localhost:8080" || exit 1

## Example of a healthcheck 
HEALTHCHECK --interval=15s --retries=5 --timeout=30s --start-period=30s CMD curl --head -f "localhost:8080" || exit 1