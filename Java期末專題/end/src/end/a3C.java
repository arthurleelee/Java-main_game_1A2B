package end;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;


import javax.sound.sampled.*;
import javax.swing.*;





public class a3C extends JFrame implements ActionListener
{
    
	static int[] A = new int[4];
	int[] B = new int[4];
	int use =0;
	int click = 0;
	int net =0;
	static int firstn =0;
	static String op ="";
	JPanel jpn;
	JPanel jpw;
	static JLabel jlnow;
	JPanel jpw0;
	JPanel jpw1;
	JPanel jpe;
	JPanel jpe1;
	JPanel jpe2;
	JPanel jpe3;
	JPanel jpe4;
	JPanel jps;
	JTextArea text;
	static JTextArea re;
	static JTextArea re1;
	JScrollPane js;
	JScrollPane js1;
	JButton[] jb = new JButton[12];
	String r = "";
	static Clip clip;
	public a3C() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		this.setTitle("猜數字1A2B---雙人連線對戰--客戶端");
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
		audio();
		String ip = JOptionPane.showInputDialog(null, "<html><font size=30>請輸入伺服器ip位置:" , "位置" ,JOptionPane.QUESTION_MESSAGE);
		new ClientThreadCode(ip, 8000).start();//建立物件，傳入IP和Port並執行等待接受連線的動作
		
	}
	
	
	
	
		
	//判斷
	public String guess() {
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
	        	re.append(r+"--"+a+"A\n");
	        	return a+"A";
	        }
	        else{
	        	re.append(r+"--"+a+"A"+b+"B\n");
	        	return a+"A"+b+"B";
		    }
	    }
		return "";
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
		jpe1 = new JPanel();
		jpe2 = new JPanel();
		jpe3 = new JPanel();
		jpe4 = new JPanel();
		jpe1.setBackground(Color.PINK);
		jpe2.setBackground(Color.GREEN);
		jpe.setLayout(new BorderLayout());
		jpe1.setLayout(new BorderLayout());
		jpe2.setLayout(new BorderLayout());
		JLabel jl = new JLabel("自己每輪輸入紀錄與結果:");
		JLabel jl1 = new JLabel("對方紀錄:");
		jl.setFont(new Font("標楷體",Font.BOLD,30));
		jl.setForeground(Color.BLACK);
		jl1.setFont(new Font("標楷體",Font.BOLD,30));
		jl1.setForeground(Color.BLACK);
		re = new JTextArea("");
		re.setFont(new Font("標楷體",Font.BOLD,45));
		re.setForeground(Color.BLACK);
		re1 = new JTextArea("");
		re1.setFont(new Font("標楷體",Font.BOLD,45));
		re1.setForeground(Color.BLACK);
		js = new JScrollPane(re,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js1 = new JScrollPane(re1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jpe.add(jpe1,BorderLayout.WEST);
		jpe.add(jpe2,BorderLayout.CENTER);
		jpe1.add(jl,BorderLayout.NORTH);
		jpe1.add(js,BorderLayout.CENTER);
		jpe2.add(jl1,BorderLayout.NORTH);
		jpe2.add(js1,BorderLayout.CENTER);
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
		
		
		
	@Override
	public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		if(jlnow.getText() == "連線成功！") {
			net = 1;
		}
		if(net == 1) {
			if(click <4 && (JButton)e.getSource() != jb[11] && (JButton)e.getSource() != jb[10]) {
				r += ((JButton)e.getSource()).getText();
				jlnow.setText(r);
				System.out.println(r);
				click++;
			}else if((JButton)e.getSource() == jb[11]) {
				r = "";
				jlnow.setText(r);
				click =0;
			}else if((JButton)e.getSource() == jb[10] && click ==4) {
				try {
					String g = guess();
					op = jlnow.getText()+"--"+g;
					if(g.equals("4A")) {
						clip.stop();
						firstn = 3;
						audio1();
						JOptionPane.showMessageDialog(null, "<html><font size=30>恭喜答對囉~~\n<html><font size=30>總共猜了"+use+"<html><font size=30>次" , "遊戲結束" ,JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
					r = "";
					jlnow.setText(r);
					click =0;
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] argv) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
		new a3C();
    }
}



class ClientThreadCode extends Thread
{
    private Socket m_socket;//和伺服器端進行連線
    String before ="";
    public ClientThreadCode(String ip, int port)
    {
        try
        {
            m_socket = new Socket(ip, port);//建立連線。(ip為伺服器端的ip，port為伺服器端開啟的port)
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    String q;
    
    @Override
    public void run()
    {
        try
        {
            if (m_socket != null)//連線成功才繼續往下執行
            {

	            a3C.jlnow.setText("等待連線......");
	            q = JOptionPane.showInputDialog(null, "<html><font size=30>請出給對方一組4個0~9的數字，且數字不能重複:" , "出題" ,JOptionPane.QUESTION_MESSAGE);
	            while(correct() == 1) {
	            	q = JOptionPane.showInputDialog(null, "<html><font size=30>無效!!請出給對方一組4個0~9的數字，且數字不能重複:" , "出題" ,JOptionPane.QUESTION_MESSAGE);
	            }
	            while(true) {
	            	//由於Server端使用 PrintStream 和 BufferedReader 來接收和寄送訊息，所以Client端也要相同
	                PrintStream writer = new PrintStream(m_socket.getOutputStream());
	                if(a3C.firstn ==0) {
		            	writer.println(q);
		            	writer.flush();//清空緩衝區並送出資料
			            
		            }else if(a3C.firstn ==1) {
			            writer.println(a3C.op);
			            writer.flush();//清空緩衝區並送出資料
		            }else if(a3C.firstn ==3) {
		            	writer.println("over");
		            	writer.flush();//清空緩衝區並送出資料
		            	break;
		            }

	                BufferedReader reader = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
	                String rd = reader.readLine();
	                System.out.println("Server:" + rd);
	                if(a3C.firstn ==1) {
	                	if(rd.length() >9 && !rd.equals(before) ) {
	                		a3C.re1.append(rd+"\n");
	                		before = rd;
	                	}
	                	if(rd.equals("over")) {
	                		a3C.clip.stop();
	                		m_socket.close();
	        				JOptionPane.showMessageDialog(null, "<html><font size=30>你輸了!!" , "遊戲結束" ,JOptionPane.INFORMATION_MESSAGE);
	        				System.exit(0);
	                	}

		            }else if(a3C.firstn ==0) {
		            	secret(rd);
			            a3C.jlnow.setText("連線成功！");
			            a3C.firstn =1;
		            }
		            
		            
            	}
            
                m_socket.close();

            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
        
    }
    
    //檢查自己出題
    public int correct() {
    	if(correct1()) {
    		return 1;
    	}
    	int[] c = new int[q.length()];
    	int m = Integer.parseInt(q);
  		for(int i =0; i <4; i++){
  			c[i] = m %10;
  			m /=10; 
  		}
  		System.out.println(q.length());
  		if(c[0] == c[1] || c[0] == c[2] || c[0] == c[3] || c[1] == c[2] || c[1] == c[3] || c[2] == c[3] ) {
  			return 1;
  		}else {
  			return 0;
  		}
    }
    
  //檢查自己出題
    public boolean correct1() {
    	if(q.equals("")) {
    		return true;
    	}else if(q.length() >4 || q.length() <4 ) {
    		return true;
    	}
    	for(int i=0; i < q.length()-1;i++ ) {
    		if(q.charAt(i) <'0' || q.charAt(i) >'9') {
    			return true;
    		}
    	}
    	return false;
    }
    
  //出數字
  	public void secret(String s) {
  		int m = Integer.parseInt(s);
  		for(int i =0; i <4; i++){
  			a3C.A[i] = m %10;
  			m /=10; 
  		}
  		/*int test;
  		for( test =3; test >=0; test--){
  			System.out.printf("%d", a3C.A[test]);
  		}*/
  	}
}