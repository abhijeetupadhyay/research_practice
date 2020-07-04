import java.util.*;
public class RP {
	//for getting the index of min value in arr
	public static int getMin(int[] arr, int n){
	    int res = 0;
	    int min = Integer.MAX_VALUE;
	    for(int i = 0; i < n; i++) {
	        if(arr[i] < min){
	                min = arr[i];
	                res = i;
	        }
	    }
	    return res;
	}

	//for getting the index of max value in arr
	public static int getMax(int[] arr, int n){
	    int res = 0;
	    int max = Integer.MIN_VALUE;
	    for(int i = 0; i < n; i++) {
	        if(arr[i] >= max){
	          max = arr[i];
	          res = i;
	        }
	    }
	    return res;
	}

	//for balancing load
	public static int[] loadBalancing(int n, int[] arr) {
		int total = 0;
		for(int i : arr) {
			total += i;
		}
		int avg = total/n; //avg of arr
		int c = 0;

		for(int i : arr) {
			if(i < avg) {
				c++;
			}
		}
		int max = 0, min = 0;
		System.out.println("\nChunkservers with load less than avg load are " + c + ".");
		for(int i = 0; i < c; i++) {
			min = getMin(arr, n);
			
			max = getMax(arr, n);

			if(min == n-1){
				arr[0] += arr[min];				
			}
			else {
				arr[min+1] += arr[min];
			}
			int k = arr[max] - avg;
			arr[max] -= k;
			if(min == n-1){
				arr[0] = (k > avg)?avg:k;
			}
			else {
				arr[min] = (k > avg)?avg:k;
			}
		}
		return arr;
	}
	
	//for reading new query
	public static int featureExtraction(int n, int[] arr) {
		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Enter query :");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();

		int c = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == ',') {
				c++;
			}
		}
		if(c <= 2) {
			System.out.println("\nQuery is least loaded!");
			return 1;
		}
		System.out.println("\nQuery is highly loaded!");
		return 2;
	}
	
	//for selecting server for query on the basis of query load
	public static int serverSelection(int n, int[] arr, int op) {
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
		if(op == 1) {
			double min = Double.MAX_VALUE;
			int ind = 0;
			for(int i = 0; i < n; i++) {
				double d = farr[i]/total;
				if(min > d) {
					min = d;
					ind = i;
				}
			}
			return (ind+1);
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

	//for visualizing server load
	public static void printServerStatus(int[] arr) {
		for(int i = 0; i< arr.length; i++) {
			int a = arr[i];
			System.out.print("Server Id : " + (i + 1) + " |  Load : ");
			for(int j = 0; j < a; j++) {
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
		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		printServerStatus(arr);

		arr = loadBalancing(n, arr);
		System.out.println("\nServer status after load balancing : ");
		printServerStatus(arr);

		int op = featureExtraction(n, arr);
		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		int res = serverSelection(n, arr, op);
		String queryLoad = (op == 1) ? "least loaded" : "heavy loaded";
		System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n Result :");
		System.out.println("As Query is " + queryLoad + ". Hence, Request will be assigned to server " + res + ".\n\n");
	}
}
