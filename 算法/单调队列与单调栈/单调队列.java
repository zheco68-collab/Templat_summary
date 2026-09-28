import java.io.*;
import java.util.*;
public class 单调队列 {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(sz.nextToken());
		int k = Integer.parseInt(sz.nextToken());
		
		sz = new StringTokenizer(bf.readLine());
		int[]arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(sz.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		Deque<Integer> dq = new LinkedList<>();
		
		for(int i=0;i<n;i++) {
			while(!dq.isEmpty()&&arr[dq.getLast()]>=arr[i]) {
				dq.pollLast();
			}
			dq.addLast(i);
			if(!dq.isEmpty()&&dq.getFirst()<=i-k){
				dq.pollFirst();
			}
			if(i>=k-1) {
				sb.append(arr[dq.getFirst()]).append(" ");
			}
		}
		
		sb.append('\n');
		dq.clear();
		
		for(int i=0;i<n;i++) {
			while(!dq.isEmpty()&&arr[dq.getLast()]<=arr[i]) {
				dq.pollLast();
			}
			dq.addLast(i);
			if(!dq.isEmpty()&&dq.getFirst()<=i-k){
				dq.pollFirst();
			}
			if(i>=k-1) {
				sb.append(arr[dq.getFirst()]).append(" ");
			}
		}
		System.out.println(sb);
	}
}