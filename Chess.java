public class Chess {
	public int iterations;
	public static final int RRHC_RESTARTS = 300;

	public Chess(int iterations) {
		this.iterations = iterations;
	}

	public static boolean checkCharacter(Character i) {
		return i == 'Q' || i == '.';
	}

	public static boolean isBoardValid(Character[][] board) {
		if (board == null || board.length != 8)  return false;
		int queens = 0;
		for (Character[] row : board) {
			if (row.length != 8) return false;
			for (char i : row) {
				if (!checkCharacter(i)) return false;
				if (i == 'Q') queens++;
			}
		}
		return queens == 8;
	}

	public static String generateBinaryString(Character[][] board) {
		StringBuilder sb = new StringBuilder();
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j <= 7; j++) {
				sb.append(board[i][j] == 'Q' ?
						String
								.format("%3s", Integer.toBinaryString(j))
								.replace(' ', '0')
						: "");
			}
		}
		return sb.toString();
	}

	public static String randomBinaryString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 24; i++) {
			sb.append(Math.random() > 0.5 ? "1" : "0");
		}
		return sb.toString();
	}

	public static String changeBinaryString(String str) {
		StringBuilder sb = new StringBuilder(str);
		int random = (int) (Math.random() * 24);
		sb.setCharAt(random, sb.charAt(random) == '1' ? '0' : '1');
		return sb.toString();
	}

	public static int getFitness(String str) {
		int fitness = 56;
		int[] queens = new int[8];
		for (int i = 0; i < 8; i++) {
			queens[i] = Integer.parseInt(str.substring(i * 3, i * 3 + 3), 2);
		}
		int clashes = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = i+1; j < 8; j++) {
				int rowDiff = Math.abs(queens[i] - queens[j]);
				int colDiff = Math.abs(i - j);
				if (queens[i] == queens[j] || colDiff == rowDiff) {
					clashes++;
					clashes++;
				}
			}
		}
		fitness -= clashes;
		return fitness;
	}

	public String getBestRRHC() {
		int fitness = 0;
		String str = "";
		for (int i = 0; i < RRHC_RESTARTS; i++) {
			String newStr = getBestRMHC(iterations);
			int newFitness = getFitness(newStr);
			if (newFitness > fitness) {
				fitness = newFitness;
				str = newStr;
			}
			if (fitness == 56) return str;
		}
		return str;
	}

	public static String getBestRMHC(int iterations) {
		String str = randomBinaryString();
		int fitness = getFitness(str);
		for (int j = 0; j < iterations; j++) {
			String newStr = changeBinaryString(str);
			int newFitness = getFitness(newStr);
			if (newFitness > fitness) {
				fitness = newFitness;
				str = newStr;
			}
		}
		return str;
	}
}
