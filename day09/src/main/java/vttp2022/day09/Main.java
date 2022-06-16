package vttp2022.day09;

public class Main {
    public static void main( String[] args ) throws Exception {
        FileParser fp = new FileParser(args[0]);
		int count = 0;
		int gens = 5;
		if (args.length >= 2)
			gens = Integer.parseInt(args[1]);

        char[][] grid = fp.parse();
		Conway conway = new Conway(grid);
		conway.generate(gens);
		for (char[][] g: conway.getGenerations()) {
			System.out.printf("Generation: %d\n", count);
			printGrid(g);
			System.out.println("---------------------------------------\n");
			count++;
		}
    }

    public static void printGrid(char[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if ('*' != grid[y][x])
                    System.out.print('.');
                else
                    System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }
}
