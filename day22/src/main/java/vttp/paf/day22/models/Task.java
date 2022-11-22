package vttp.paf.day22.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    public enum Priority { LOW, MED, HIGH }

    private Integer taskId = -1;
    private String taskName;
    private Priority priority;
    private User assignTo;
    private Date completionDate;

    public Integer getTaskId() { return taskId; }
    public void setTaskId(Integer taskId) { this.taskId = taskId; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setPriority(String priority) {
        this.priority = Priority.valueOf(priority);
    }

    public User getAssignTo() { return assignTo; }
    public void setAssignTo(User assignTo) { this.assignTo = assignTo; }

    public Date getCompletionDate() { return completionDate; }
    public void setCompletionDate(Date completionDate) { this.completionDate = completionDate; }
    public void setCompletionDate(String completionDate) {
        try {
            this.completionDate = new SimpleDateFormat("yyyy-MM-dd").parse(completionDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", taskName=" + taskName + ", priority=" + priority + ", assignTo=" + assignTo
                + ", completionDate=" + completionDate + "]";
    }
}
