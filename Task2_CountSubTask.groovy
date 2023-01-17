import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue

// Get all sub-tasks of the current issue
def subTasks = issue.subTaskObjects

// Return the number of sub-tasks
return subTasks.size()