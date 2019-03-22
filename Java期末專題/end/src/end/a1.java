package end;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class a1 extends JFrame implements ActionListener{
	
	int[] A = new int[4];
	int[] B = new int[4];
	int use =0;
	int click = 0;
	JPanel jpn;
	JPanel jpw;
	JLabel jlnow;
	JPanel jpw0;
	JPanel jpw1;
	JPanel jpe;
	JPanel jps;
	JTextArea text;
	JTextArea re;
	JScrollPane js;
	JButton[] jb = new JButton[12];
	String r = "";
	Clip clip;
	public a1() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		this.setTitle("猜數字1A2B---人猜電腦");
		this.setLocation(200, 150);
		this.setSize(1200, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textArea();
		this.add(jpn,BorderLayout.NORTH);
		showre();
		this.add(jpw,BorderLayout.WEST);
		result();
		this.add(jpe,BorderLayout.CENTER);
		button();
		this.add(jps,BorderLayout.SOUTH);
		secret();
		audio();
	}
	
	//電腦出數字
	public void secret() {
		int w =0,v =0,y =0,z=0;
		while(w == v || w == y || w == z || v == y || v == z || y == z) {
			w = (int)(Math.random()*10);
			v = (int)(Math.random()*10);
			y = (int)(Math.random()*10);
			z = (int)(Math.random()*10);
		}
		A[3] = z; A[2] = y; A[1] = v; A[0] = w;
	}
	
	//判斷
	public void guess() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		int m = Integer.parseInt(r);
		for(int i =0; i <4; i++){
		    B[i] = m %10;
		    m /=10; 
	    }
		if( B[0] == B[1] || B[0] == B[2] || B[0] == B[3] || B[1] == B[2] || B[1] == B[3] || B[2] == B[3] ){
		    jlnow.setText("無效輸入\n數字不能重複!!");
		    r = "";
			click =0;
	    }else{
	        int a =0, b =0;
	        for( int i=0; i <4; i++){
		        if( B[i] == A[i]){
			       a++;
		        }
	        }
	        if( B[0] == A[1] || B[0] == A[2] || B[0] == A[3] ){
		        b++;
	        }
	        if( B[1] == A[0] || B[1] == A[2] || B[1] == A[3] ){
		        b++;
	        }
	        if( B[2] == A[1] || B[2] == A[0] || B[2] == A[3] ){
		        b++;
	        }
	        if( B[3] == A[1] || B[3] == A[2] || B[3] == A[0] ){
		        b++;
	        }
	        use++;
	        if( a == 4){
	        	re.append(r+"		"+a+"A\n");
				r = "";
				jlnow.setText(r);
				click =0;
				clip.stop();
				audio1();
				JOptionPane.showMessageDialog(null, "<html><font size=30>恭喜答對囉~~\n<html><font size=30>總共猜了"+use+"<html><font size=30>次" , "遊戲結束" ,JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
	        }
	        else{
	        	re.append(r+"		"+a+"A"+b+"B\n");
				r = "";
				jlnow.setText(r);
				click =0;
	    	    if( use ==1){
	    	    	JOptionPane.showMessageDialog(null, "<html><font size=30>我覺得不行!!\n<html><font size=30>連這都不會\n<html><font size=30>那你有freestyle嗎?" , "疑問" ,JOptionPane.QUESTION_MESSAGE);
			    }
			    else if( use ==2){
			    	JOptionPane.showMessageDialog(null, "<html><font size=30>看來你的腦袋\n<html><font size=30> Not Found 404~~" , "錯誤" ,JOptionPane.ERROR_MESSAGE);
			    }
			    else if( use ==3){
			    	JOptionPane.showMessageDialog(null, "<html><font size=30>說好的答對呢?\n<html><font size=30>我累了~~\n<html><font size=30>不說了~~" , "加油好嗎?",JOptionPane.PLAIN_MESSAGE);
		    	}
			    else{
			    	JOptionPane.showMessageDialog(null, "<html><font size=30>答錯!!!!" , "警告" ,JOptionPane.WARNING_MESSAGE);
			    }
		    }
	    }
	}
	
	
	
	//按鈕
	public void button(){
		jps = new JPanel();
		jps.setLayout(new FlowLayout());
		JLabel jl = new JLabel("請按下按鈕輸入:");
		jl.setFont(new Font("標楷體",Font.BOLD,30));
		jl.setForeground(Color.BLACK);
		jps.add(jl);
		for(int i =0;i <10;i++) {
			jb[i] = new JButton(""+i);
			jb[i].setFont(new Font("標楷體",Font.BOLD,50));
			jb[i].setBackground(Color.YELLOW);
			jb[i].addActionListener(this);
			jb[i].setFocusable(false);
			jps.add(jb[i]);
		}
		jps.setBackground(Color.ORANGE);
		
	}
	
	
	//文字
	public void textArea() {
		jpn = new JPanel();
		jpn.setBackground(Color.ORANGE);
		text = new JTextArea("重要!!請先熟讀~~\n\n遊戲規則:\nA為數字位置皆正確，B為數字正確位置不正確\n每回輸入一組4個0~9的數字，且數字不能重複\n輸入錯誤請按重新輸入");
		text.setFont(new Font("標楷體",Font.BOLD,30));
		text.setForeground(Color.RED);
		text.addFocusListener(new FocusListener() {


			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				text.getCaret().setVisible(false);
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		text.setBackground(Color.ORANGE);
		jpn.add(text);
	}
	
	
	//顯示
	public void showre() {
		jpw = new JPanel();
		jpw.setBackground(Color.GREEN);
		jpw0 = new JPanel();
		jpw1 = new JPanel();
		jpw.setLayout(new BorderLayout());
		jpw0.setLayout(new BorderLayout());
		jpw1.setLayout(new FlowLayout());
		JLabel jl = new JLabel("現時輸入:");
		jl.setFont(new Font("標楷體",Font.BOLD,30));
		jl.setForeground(Color.BLACK);
		jlnow = new JLabel("");
		jlnow.setFont(new Font("標楷體",Font.BOLD,50));
		jlnow.setForeground(Color.BLACK);
		jb[10] = new JButton("確定");
		jb[11] = new JButton("重新輸入");
		jb[10].setFont(new Font("標楷體",Font.BOLD,50));
		jb[10].setBackground(Color.YELLOW);
		jb[11].setFont(new Font("標楷體",Font.BOLD,50));
		jb[11].setBackground(Color.YELLOW);
		jb[10].addActionListener(this);
		jb[11].addActionListener(this);
		jb[10].setFocusable(false);
		jb[11].setFocusable(false);
		jpw.add(jl,BorderLayout.NORTH);
		jpw0.add(jlnow,BorderLayout.CENTER);
		jpw.add(jpw0,BorderLayout.CENTER);
		jpw.add(jpw1,BorderLayout.SOUTH);
		jpw1.add(jb[10]);
		jpw1.add(jb[11]);
		
	}
	
	
	//每輪結果
	public void result() {
		jpe = new JPanel();
		jpe.setBackground(Color.YELLOW);
		JLabel jl = new JLabel("每輪輸入紀錄與結果:");
		jl.setFont(new Font("標楷體",Font.BOLD,30));
		jl.setForeground(Color.BLACK);
		re = new JTextArea("");
		re.setFont(new Font("標楷體",Font.BOLD,45));
		re.setForeground(Color.BLACK);
		js = new JScrollPane(re,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jpe.setLayout(new BorderLayout());
		jpe.add(jl,BorderLayout.NORTH);
		jpe.add(js,BorderLayout.CENTER);
	}
	
	
	//bgmusic
	public void audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File audioFile = new File("If_I_Had_a_Chicken.wav");
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
		AudioFormat audioFormat = audioInputStream.getFormat();
		int bufferSize = (int) Math.min(audioInputStream.getFrameLength() * audioFormat.getFrameSize(), Integer.MAX_VALUE); //緩衝大小
		DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat, bufferSize);
		clip = (Clip) AudioSystem.getLine(dataLineInfo);
		clip.open(audioInputStream);
		clip.start();
		clip.loop(Integer.MAX_VALUE);
	}
	
	
	//win
	public void audio1() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File audioFile = new File("victory.wav");
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
		AudioFormat audioFormat = audioInputStream.getFormat();
		int bufferSize = (int) Math.min(audioInputStream.getFrameLength() * audioFormat.getFrameSize(), Integer.MAX_VALUE); //緩衝大小
		DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat, bufferSize);
		Clip clip1 = (Clip) AudioSystem.getLine(dataLineInfo);
		clip1.open(audioInputStream);
		clip1.start();
	}
	

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// TODO Auto-generated method stub
		new a1();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(click <4 && (JButton)e.getSource() != jb[11] && (JButton)e.getSource() != jb[10]) {
			r += ((JButton)e.getSource()).getText();
			jlnow.setText(r);
			click++;
		}else if((JButton)e.getSource() == jb[11]) {
			r = "";
			jlnow.setText(r);
			click =0;
		}else if((JButton)e.getSource() == jb[10] && click ==4) {
			try {
				guess();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
