// value object
export interface Task {
  task: string
  priority: string
}
export interface Todo {
  name: string
  email: string
  comments?: string // this is an optional field/property
  tasks: Task[]
}
