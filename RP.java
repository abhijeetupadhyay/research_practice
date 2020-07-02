import java.util.*;
public class RP {
	public static void loadBalancing(int n, int[] arr) {
		System.out.println("in Load Balancing!");
	}
	
	public static void featureExtraction(int n, int[] arr) {
		System.out.println("in feature Extraction!");
	}
	
	public static int serverSelection(int n, int[] arr) {
		int[] narr = new int[n];
		double[] farr = new double[n];
		double total = 0.0;
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < n; i++) {
			System.out.println("Enter no. of active connections on server " + (i+1));	
			narr[i] = sc.nextInt();
			farr[i] = 1.0 / (1.0 + narr[i]);
			total += farr[i];
		}
		
		double max = Double.MIN_VALUE;
		int ind = 0;
		for(int i = 0; i < n; i++) {
			double d = farr[i]/total;
			if(max < d) {
				max = d;
				ind = i;
			}
		}
		return (ind+1);
	}

	public static void printServerStatus(int[] arr) {
		for(int a : arr) {
			for(int i = 0; i < a; i++) {
				System.out.print("=");
			}
			System.out.println();
		}
	}

	public static void main(String []args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of servers : ");
		int n = sc.nextInt();
		int[] arr = new int[n];

		for(int i = 0; i < n; i++) {
			System.out.println("Enter load of server " + (i + 1) + " : ");
			arr[i] = sc.nextInt();
		}

		printServerStatus(arr);

		loadBalancing(n, arr);
		featureExtraction(n, arr);
		int res = serverSelection(n, arr);
		System.out.println("Request will be assigned to server "+res);
	}
}