package Rubik;

public class EstadoCubo {

	private String[][][] cubo;
	private int Sides = 6;
	private int rows = 3;
	private int cols = 3;

	EstadoCubo() {

		cubo = new String[Sides][rows][cols];

		for (int i = 0; i < Sides; i++) {
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < cols; c++) {

					switch (i) {
					case 0:
						cubo[i][r][c] = "red";
					case 1:
						cubo[i][r][c] = "blue";
					case 2:
						cubo[i][r][c] = "yellow";
					case 3:
						cubo[i][r][c] = "green";
					case 4:
						cubo[i][r][c] = "orange";
					case 5:
						cubo[i][r][c] = "white";
					}
				}
			}
		}
	}
	
	
	
	
}
