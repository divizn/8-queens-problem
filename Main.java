public class Main {
	public static Character[][] testBoard = new Character[][] {
			{'Q', '.', '.', '.', '.', '.', '.', '.'},
			{'Q', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.', '.', '.', 'Q'},
			{'Q', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', '.', '.', '.', '.', 'Q', '.'},
			{'.', '.', '.', '.', 'Q', '.', '.', '.'},
			{'Q', '.', '.', '.', '.', '.', '.', '.'},
			{'.', '.', '.', 'Q', '.', '.', '.', '.'}
	};

	public static String binaryRepresentation = "011000100110000111000000";

	public static void main(String[] args) {
		Chess ch = new Chess(80);
		String solution = ch.getBestRRHC();
		System.out.println("solution: " + solution);
		System.out.println("fitness: " + Chess.getFitness(solution));

		double average = 0;
		int maxFitness = 0;
		for (int i = 0; i < 100; i++) {
			int fitness = Chess.getFitness(ch.getBestRRHC());
			average += fitness;
			if (fitness == 56) maxFitness++;
		}
		System.out.println("average (100 tests): " + average / 100);
		System.out.println("perfect fitness (100 tests): " + maxFitness);
	}
}