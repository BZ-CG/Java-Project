package cn.edu.pzhu.cg;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableTest {

	final static int size = 16;
	int cnt = 1;
	static Object[][] board = new Object[16][16];
	
	
	public static void main(String[] args) {
		JTableTest jTable = new JTableTest();
		jTable.chess_board(0,0,1,1,size);
	    char[][] colum = new char[16][16];
		for(int i = 0;i < size;i++){
			for(int j = 0;j < size;j++)
				colum[i][i] = '#';
		  }
		JTable j1 = new JTable(board, colum);
		//j1.setPreferredScrollableViewportSize(new Dimension(800, 800));
		
		j1.doLayout();
		//Component component = super.prepareRenderer(renderer, row, column);
		
		JScrollPane jPane = new JScrollPane(j1);
		JFrame jFrame = new JFrame("Test");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setContentPane(jPane);
		jFrame.pack();
		jFrame.show();
		
//		  for(int i = 0;i < size;i++){
//		        for(int j = 0;j < size;j++)
//		            System.out.print(board[i][j] + "  ");
//		        System.out.println();   
//		    }
		
	}
	void chess_board(int tr,int tc,int dr,int dc,int size){
	    if(size == 1)
	        return;
	    int s = size/2;
	    int temp = cnt++;
	    //覆盖左上角
	    if(dr < tr + s && dc < tc + s){
	        chess_board(tr,tc,dr,dc,s);//在此棋盘中
	    }else{//用L骨牌覆盖右下角
	        board[tr + s - 1][tc + s - 1] = temp;
	        chess_board(tr,tc,tr + s - 1,tc + s - 1,s);
	    }
	    //覆盖右上角
	    if(dr < tr + s && dc >= tc + s){
	        chess_board(tr,tc + s,dr,dc,s);//在此棋盘中
	    }else{//用L骨牌覆盖左下角
	        board[tr + s - 1][tc + s] = temp;
	        chess_board(tr,tc + s,tr + s - 1,tc + s,s);
	    }
	    //覆盖左下角
	    if(dr >= tr + s && dc < tc + s){
	        chess_board(tr + s,tc,dr,dc,s);//在此棋盘中
	    }else{//用L骨牌覆盖右上角
	        board[tr + s][tc + s - 1] = temp;
	        chess_board(tr + s,tc,tr + s,tc + s - 1,s);
	    }
	     //覆盖右下角
	    if(dr >= tr + s && dc >= tc + s){
	        chess_board(tr + s,tc + s,dr,dc,s);//在此棋盘中
	    }else{//用L骨牌覆盖左上角
	        board[tr + s][tc + s] = temp;
	        chess_board(tr + s,tc + s,tr + s,tc + s,s);
	    }
	    
	    
	}
}
