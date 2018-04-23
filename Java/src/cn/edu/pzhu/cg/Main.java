package cn.edu.pzhu.cg;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int n,m;
		while(cin.hasNextInt()){
			int x = 0,y = 0,z = 0;
			boolean flag = false;
			
			n = cin.nextInt();
			m = cin.nextInt();
			
			for(y = (3*n - m/14);y > 0;y--){
				if((3*n - m - 14*y) > 0 && (3*n - m - 14*y)%8 == 0){
					x = (3*n - m - 14*y)/8;
					z = m - x - y;
					if(z > 0){
						flag = true;
						break;
					}
				}
			}
			if(flag)
				System.out.println(x+" "+y+" "+z);
			else
				System.out.println("-1");
			}
		
		
	}
	
}

