export interface Task {
  task: string
  priority: string,
  dueDate: string
}

export interface Todo {
  name: string
  email: string
  tasks: Task[]
}
