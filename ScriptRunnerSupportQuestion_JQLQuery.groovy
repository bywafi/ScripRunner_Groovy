import com.atlassian.jira.bc.issue.search.SearchService
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.search.SearchException
import com.atlassian.jira.web.bean.PagerFilter
import org.apache.log4j.Level

// Set log level to INFO
log.setLevel(Level.INFO)

// The JQL query you want to search with
final jqlSearch = "project = 'My Project' and status = 'Open'"

// Some components
def user = ComponentAccessor.jiraAuthenticationContext.loggedInUser
def searchService = ComponentAccessor.getComponentOfType(SearchService)

// Parse the query
def parseResult = searchService.parseQuery(user, jqlSearch)
if (!parseResult.valid) {
    log.error('Invalid query')
    return null
}

try {
    // Perform the query to get the issues
    def results = searchService.search(user, parseResult.query, PagerFilter.unlimitedFilter)
    def issues = results.results
    issues.each {
        log.info(it.key)
    }

    return results.results
} catch (SearchException e) {
    e.printStackTrace()
    null
}
