package vttp2022.csf.day38.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.csf.day38.server.models.Task;
import vttp2022.csf.day38.server.respositories.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepo;

	public void addTasks(List<Task> tasks) {
		todoRepo.add(tasks);
	}
}
