import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import Dexie from "dexie";
import { firstValueFrom } from "rxjs";
import { SyncResult, Task } from "./models";

@Injectable()
export class TaskRepository extends Dexie {

  todo!: Dexie.Table<Task, number>

  constructor(private http: HttpClient) {
    super('taskdb')
    this.version(1).stores({
      todo: 'id++,dueDate'
    })
    this.todo = this.table('todo')
  }

  addTodo(todo: Task): Promise<number> {
    return this.todo.add(todo)
  }

  getTodos(): Promise<Task[]> {
    return this.todo.orderBy('dueDate').toArray()
  }

  deleteAll(): Promise<void> {
    return this.getTodos()
      .then(result => result.map(v => v.id))
      .then(result => this.todo.bulkDelete(result))
  }

  sync(endpoint: string): Promise<void> {
    return this.getTodos()
      .then(result => firstValueFrom(this.http.post<SyncResult>(endpoint, result)))
      .then(result => this.deleteAll())
  }
}
