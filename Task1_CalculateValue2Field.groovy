import com.atlassian.jira.component.ComponentAccessor

def customFieldManager = ComponentAccessor.customFieldManager

// Get custom fields by their field id
// Replace with the actual field id of the first & second number field
def field1 = customFieldManager.getCustomFieldObject(10000) 
def field2 = customFieldManager.getCustomFieldObject(10001) 

// Get values of the custom fields
def val1 = issue.getCustomFieldValue(field1)
def val2 = issue.getCustomFieldValue(field2)

// Perform the calculation and return the result
return val1 + val2
