export interface Task {
	id: number
  taskId?: string
	task: string
	priority: number
	dueDate: number
}

export interface SyncResult {
  insertCount: number
}
