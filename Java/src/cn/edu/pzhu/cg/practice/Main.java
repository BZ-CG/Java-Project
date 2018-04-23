package cn.edu.pzhu.cg.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int M = 0,N = 0;
		List<test> list = new ArrayList<test>();
		Scanner cin = new Scanner(System.in);
		while(true){
			M = cin.nextInt();
			N = cin.nextInt();
			list.clear();
			if(M == 0 && N == 0)
				break;
			
			for(int i = 0;i < M;i++){
				test t1 = new test();
				t1.time = cin.nextInt();
				t1.value = cin.nextInt();
				t1.setTemp((double)t1.value/t1.time);
				list.add(t1);
				
			}
			Collections.sort(list);
			double sum = 0;
			for(int i = 0;i < list.size();i++){
				if(N > list.get(i).getTime()){
					N = N - list.get(i).getTime();
					sum += list.get(i).getValue();
				}else{
					sum = sum + N *list.get(i).getTemp();
					break;
				}
			}
			System.out.println(String.format("%.2f", sum));
		}
			
	}
}	
	class test implements Comparable<test>{

		int time;
		int value;
		double temp;
		

		public test() {
			super();
			// TODO Auto-generated constructor stub
		}
		

		public test(int time, int value, double temp) {
			super();
			this.time = time;
			this.value = value;
			this.temp = temp;
		}
		

		public int getTime() {
			return time;
		}


		public void setTime(int time) {
			this.time = time;
		}


		public int getValue() {
			return value;
		}


		public void setValue(int value) {
			this.value = value;
		}


		public double getTemp() {
			return temp;
		}


		public void setTemp(double temp) {
			this.temp = temp;
		}


		
		public int compareTo(test o) {
			double i = this.temp - o.temp;
			if(i >= 0)
				return -1;
			return 1;	
		}
		
	}

	

