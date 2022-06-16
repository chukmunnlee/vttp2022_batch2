package vttp2022.day09;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Conway {

	private List<char[][]> generations = new LinkedList<>();

	public Conway(char[][] genesis) {
		generations.add(genesis);
	}

	public List<char[][]> getGenerations() {
		return generations;
	}

	public void generate(int count) {
		for (int i = 0; i < count; i++) {
			System.out.printf("Generating %d\n", i);
			char[][] nextGen = evaluate();
			generations.add(nextGen);
		}
	}

	private char[][] evaluate() {

		char[][] lastGeneration = generations.get(generations.size() - 1);
		char[][] nextGen = createGrid(lastGeneration);

		for (int y = 0; y < lastGeneration.length; y++) 
			for (int x = 0; x < lastGeneration[y].length; x++) {
				int n = countNeighbours(x, y, lastGeneration);
					if (isPopulated(x, y, lastGeneration)) {
						// Die by loneliness or overpopulation
						if ((n <=1) || (n >= 4))
							nextGen[y][x] = ' ';
						else if ((n >= 2) && (n <= 3))
							nextGen[y][x] = '*';
					} else if (3 == n)
						nextGen[y][x] = '*';
			}

		return nextGen;
	}

	private boolean isPopulated(int xPos, int yPos, char[][] grid) {
		return '*' == grid[yPos][xPos];
	}

	private int countNeighboursWithStream(int xPos, int yPos, char[][] grid) {
		int count = 0;

		int[] row = IntStream.range(yPos - 1, yPos + 2)
				.filter(y -> (y >= 0) && (y < grid.length))
				.toArray();
		int[] col = IntStream.range(xPos - 1, xPos + 2)
				.filter(x -> (x >= 0) && (x < grid[0].length))
				.toArray();

		for (int y: row)
			for (int x: col)
				if ('*' == grid[y][x])
					count++;

		return (isPopulated(xPos, yPos, grid))? count - 1: count;
	}

	private int countNeighbours(int xPos, int yPos, char[][] grid) {
		int startX = xPos - 1;
		int startY = yPos - 1;
		int count = 0;
		
		for (int y = startY; y < (yPos + 2); y++) {
			if ((y < 0) || (y >= grid.length))
				continue;
			for (int x = startX; x < (xPos + 2); x++) {
				if ((x < 0) || (x >= grid[y].length))
					continue;
				if ('*' == grid[y][x])
					count++;
			}
		}

		// tenary/Elvis operator 
		return (isPopulated(xPos, yPos, grid))? count - 1: count;
	}

	private char[][] createGrid(char[][] original) {
		// Assume grid is a rectangle
		return new char[original.length][original[0].length];
	}
	
}
