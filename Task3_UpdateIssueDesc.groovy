import com.atlassian.jira.event.issue.IssueEvent
import com.atlassian.jira.event.type.EventType
import com.atlassian.jira.event.type.EventDispatchOption
import com.atlassian.jira.component.ComponentAccessor

def issueManager = ComponentAccessor.issueManager
def issue = event.issue
def commentManager = ComponentAccessor.getCommentManager()


//// Check if the event is of type "Issue Commented"
if(event.eventTypeId == EventType.ISSUE_COMMENTED_ID) {
    def lastComment = commentManager.getLastComment(issue)

    /// Get the updated version of the issue object
    def updatedIssue = issueManager.getIssueObject(issue.id)

    //// Update the description of the issue with the body of the last comment
    updatedIssue.setDescription(lastComment.body)

    //make the changes to the issue in the database
    issueManager.updateIssue(ComponentAccessor.jiraAuthenticationContext.loggedInUser, updatedIssue, com.atlassian.jira.event.type.EventDispatchOption.ISSUE_UPDATED, false)
}
