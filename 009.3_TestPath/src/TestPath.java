/*
 * Practice 009.3_Test path
 * Date 20170810
 */

import java.util.Scanner;
import java.util.Arrays;

public class TestPath {

	static int status;
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Path path = new Path();
		
		status = 0;
		
		do {
			switch (status) {
			case 0:
				doOption();
				break;
				
			case 1:
				path.append(scanner.nextInt(), scanner.nextInt());
				status = 0;
				break;
				
			case 2:
				if (path.getCount() == 0) {
					System.out.println("There is no point!");
					status = 0;
					break;
				}
				
				//Print all points.
				int[] temp = new int[2];
				for (int i = 0; i < path.getCount(); i++) {
					temp = path.getPoint(i);
					System.out.printf("(%d,%d) ", temp[0], temp[1]);
				}
				System.out.printf("\nCount of edges: %d\n", path.getEdge());
				System.out.printf("Euclidean length: %.2f\n", path.length());
				
				if (path.isSimple() == true)
					System.out.println("This is a simple path!");
				else
					System.out.println("This is not a simple path!");
				
				status = 0;
				break;
			}
		} while (status != -1);
		
		System.out.print("Program terminate.");

	}
	
	//Prompt the user to input option and return next status.
	public static void doOption() {
		int input;
		System.out.print("Options: 1) Append, 2)Output, -1)Quit:");
		input = scanner.nextInt();
		if (input == 1) 
			status = 1;
		else if (input == 2)
			status = 2;
		else if (input == -1)
			status = -1;
		else {
			System.out.print("Input error!\n");
			status = 0;
		}
	}
	

}

class Path{
	private int [][] data;
	private int count;
	
	//Constructor.
	public Path() {
		data = new int[5][2];
		count = 0;
	}
	
	//Add new input point into array data.
	public void append(int x, int y) {
		//If array is not enough to store input data, resize.
		if (count > data.length) {
			data = Arrays.copyOf(data, count * 2);
		}
		data[count][0] = x;
		data[count][1] = y;
		count++;
	}
	
	
	public int[] getPoint(int inx) {
		int[] a = data[inx];
		return a;
	}
	
	public int getCount() {
		return count;
	}
	
	public int getEdge() {
		if (count == 0) 
			return 0;
		return (count - 1);
	}
	
	//Return true if the path is simple path, else return false. 
	public boolean isSimple() {
		
		for (int i = 0; i < count - 1; i++) {
			for (int j = i + 1; j < count; j++) {
				if (data[i][0] == data[j][0] && data[i][1] == data[j][1])
					return false;
			}
		}
		return true;
	}
	
	//Calculate sum of all Euclidean lengths between points.
	public double length(){
		double totalLength = 0, disXSq, disYSq;
		for (int i = 0; i < count - 1; i++) {
			disXSq = Math.pow((data[i][0] - data[i + 1][0]), 2);
			disYSq = Math.pow((data[i][1] - data[i + 1][1]), 2);
			totalLength += Math.sqrt(disXSq + disYSq);
		}
		return totalLength;
	}
	
}