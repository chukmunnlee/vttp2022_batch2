import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import Dexie from "dexie";
import { firstValueFrom, Subject } from "rxjs";
import { SyncResult, Task } from "./models";

@Injectable()
export class TaskRepository extends Dexie {

  todo!: Dexie.Table<Task, number>

  onTodo = new Subject<void>()

  constructor(private http: HttpClient) {
    super('taskdb')
    this.version(1).stores({
      todo: 'id++,dueDate'
    })
    this.todo = this.table('todo')
  }

  addTodo(todo: Task): Promise<number> {
    return this.todo.add(todo)
      .then(v => {
        this.onTodo.next()
        return v
      })
  }

  getTodos(): Promise<Task[]> {
    return this.todo.orderBy('dueDate').toArray()
  }

  deleteAll(): Promise<void> {
    return this.getTodos()
      .then(result => result.map(v => v.id))
      .then(result => {
        console.info('>>> result: ', result)
        return result
      })
      .then(result => this.todo.bulkDelete(result))
      .then(() => {
        this.onTodo.next()
      })
  }

  synaAsUrlForm(endpoint: string, task: Task) {
    const params = new HttpParams()
      .set('task', task.task)
      .set('dueDate', task.dueDate)
      .set('priority', task.priority)

    const headers = new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded')

    return firstValueFrom(
      // POST /api/tasks
      // Content-Type: application/x-www-form-urlencoded
      this.http.post<any>(endpoint, params.toString(), { headers })
    )
  }

  sync(endpoint: string): Promise<void> {
    return this.getTodos()
      .then(result => firstValueFrom(this.http.post<SyncResult>(endpoint, result)))
      .then(result => {
        console.info('>>> after sync: ', result)
      })
      .then(() => this.deleteAll())
  }
}
