package end;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.JLabel;


public class a87 extends JFrame implements ActionListener  {

	public static JButton[][] button_win = new JButton[15][15];
	JButton[][] buttons = new JButton[15][15];
	int NOWTURN = 0;
	int WIN = 0 ;
	
	
	public a87() {
		this.setTitle("五子棋");
		this.setLocation(200, 150);
		this.setSize(1200, 850);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel center_jp = new JPanel();
		GridLayout gl = new GridLayout(15,15);
		center_jp.setLayout(gl);
		
		
		
		//i=行,j=列
		for(int i =0; i < 15 ; i++) {
			for(int j=0;j< 15 ;j++) {
			
				buttons[i][j] = new JButton();
				buttons[i][j].setBackground(Color.GREEN);
				buttons[i][j].addActionListener(this);
				
				center_jp.add(buttons[i][j]);
				//最後刪掉
				//buttons[i][j].setText(String.valueOf(i*15+j));
				
			}
		}
		for(int i =0; i < 15 ; i++) {
			for(int j=0;j< 15 ;j++) {
			
				button_win[i][j] = new JButton();
				button_win[i][j] = buttons[i][j];
				
			}
		}
		
		
		JPanel west_jp = new JPanel();
		west_jp.setBackground(Color.WHITE);
		
		JTextArea jl = new JTextArea("五子棋\n"
				+ "規則為先手黑棋\n"
				+ "然後白棋\n"
				+ "誰先達成\n"
				+ "橫.豎.斜向\n"
				+ "其中一向\n"
				+ "連續5子即可獲勝!");
		jl.setFont(new Font("標楷體", Font.BOLD, 32));
		jl.setBackground(Color.WHITE);
		jl.setForeground(Color.BLACK);
		jl.setOpaque(true);
		west_jp.add(jl);
		
		this.add(west_jp,BorderLayout.WEST);		
		this.add(center_jp,BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		a87 sw = new a87();
		sw.setVisible(true);
	}
	
	
		public boolean winRow(int row, int col) {
			int count = 1;
			if(col!=14) {	
				for (int i = col + 1; i < 15; i++) {
					if (button_win[row][col].getBackground().equals(button_win[row][i].getBackground())) {
						count++;
					} else
						break;
				}
			}
			if(col!=0) {
				for (int i = col - 1; i >= 0; i--) {
					if (button_win[row][col].getBackground().equals(button_win[row][i].getBackground())) {
						count++;
					} else
						break;
				}
			}
			if (count >= 5) {
				return true;
				
			} else
				return false;
		}
		
		public boolean winCol(int row, int col) {
			int count = 1;
			if(col!=14) {	
				for (int i = col + 1; i < 15; i++) {
					if (button_win[row][col].getBackground().equals(button_win[i][col].getBackground())) {
						count++;
					} else
						break;
				}
			}	
			if(col!=0) {
				for (int i = col - 1; i >= 0; i--) {
					if (button_win[row][col].getBackground().equals(button_win[i][col].getBackground())) {
						count++;
					} else
						break;
				}
			}	
			if (count >= 5) {
				return true;
			} else
				return false;
		}
		
		public boolean winUpperR(int row, int col) {
			int count = 1;
			if(row==14 || col==14) {
				
			}else {
				for (int i = col + 1, j = row + 1; i < 15 || j < 15 ; i++,j++) {
					if (button_win[row][col].getBackground().equals(button_win[j][i].getBackground())) {
						count++;
					} else
						break;
				}
			}
			if(row==0 || col==0) {
				
			}else {
				for (int i = col - 1, j = row - 1; i >= 0 || j >= 0 ; i--,j--) {
					if (button_win[row][col].getBackground().equals(button_win[j][i].getBackground())) {
						count++;
					} else
						break;
				}
			}
			if (count >= 5) {
				return true;
			} else
				return false;
		}
		
		public boolean winUpperL(int row, int col) {
			int count = 1;
			if(row==14 || col==0) {
				
			}else {
				for (int i = col - 1, j = row + 1; i >= 0 || j < 15 ; i-- ,j++) {
					if (button_win[row][col].getBackground().equals(button_win[j][i].getBackground())) {
						count++;
					} else
						break;
				}
			}
			if(col==14 || row==0) {
				
			}else {
				for (int i = col + 1, j = row - 1; i < 15 || j >= 0 ; i++ ,j-- ) {
					if (button_win[row][col].getBackground().equals(button_win[j][i].getBackground())) {
						count++;
					} else
						break;
				}
			}
			if (count >= 5) {
				return true;
			} else
				return false;
		}
		
		public int Win(int row, int col) {
			if(winRow(row , col) || winCol(row, col) ||   winUpperR(row , col)  || winUpperL(row , col) ){ 
				return 1 ;
			}else
			  return 0;
		}
		
		
	
 

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int row =0,col =0;
		JButton btn = (JButton)e.getSource();
		for(int i =0; i < 15 ; i++) {
			for(int j=0;j< 15 ;j++) {
				
				if( btn.equals(buttons[i][j])) {
					row = i;
					col = j;
				}
			}
		}
		if(btn.getBackground().equals(Color.BLACK) ||
	       btn.getBackground().equals(Color.WHITE)	) {
			System.out.println(row+","+col);				
			
		}else if(NOWTURN==0){
			btn.setBackground(Color.BLACK);
			NOWTURN=1;
			winRow(row,col);
			winCol(row,col);
			winUpperR(row,col);
			winUpperL(row,col);
			Win(row,col);
			
		}else {
			btn.setBackground(Color.WHITE);
			NOWTURN=0;
			winRow(row,col);
			winCol(row,col);
			winUpperR(row,col);
			winUpperL(row,col);
			Win(row,col);
			
		}

		if(Win(row,col)==1) {
			if(NOWTURN==1) {
				JOptionPane.showMessageDialog(null, "<html><font size=40><font color=\"red\">黑子贏" , "遊戲結束" ,JOptionPane.ERROR_MESSAGE);
				for(int i =0; i < 15 ; i++) {
					for(int j=0;j< 15 ;j++) {				
					buttons[i][j].setBackground(Color.GREEN);		
					}
				}
				NOWTURN=0;
			}else if(NOWTURN==0) {
				JOptionPane.showMessageDialog(null, "<html><font size=40><font color=\"red\">白子贏" , "遊戲結束" ,JOptionPane.ERROR_MESSAGE);
				for(int i =0; i < 15 ; i++) {
					for(int j=0;j< 15 ;j++) {				
					buttons[i][j].setBackground(Color.GREEN);		
					}
				}
			}
		}
	}	
}
