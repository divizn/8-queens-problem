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

	public static String binaryRepresentation = "011000100110100111000101";

	public static void main(String[] args) {
		int iters = 80;
		String solution = Chess.getBestRRHC(iters);
		System.out.println("solution: " + solution);
		System.out.println("fitness: " + Chess.getFitness(solution));
		System.out.println("string: " + Chess.getFitness(binaryRepresentation)); // should be 42

		double average = 0;
		int maxFitness = 0;
		for (int i = 0; i < 100; i++) {
			int fitness = Chess.getFitness(Chess.getBestRRHC(iters));
			average += fitness;
			if (fitness == 56) maxFitness++;
		}
		System.out.println("average (100 tests): " + average / 100);
		System.out.println("perfect fitness (100 tests): " + maxFitness);
	}
}