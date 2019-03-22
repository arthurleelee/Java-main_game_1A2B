package end;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class home extends JFrame implements MouseListener, ActionListener {
	
	JPanel jpn;
	JPanel jpc;
	JPanel jps;
	static ImageIcon[] image = new ImageIcon[2];
	JLabel jl;
	JLabel jl1;
	JButton jb;
	JButton jb1;
	JTextArea text = new JTextArea("滑鼠移上圖片有簡單介紹~~\n點擊圖片則進入遊戲~~\n\n\n");
	Clip clip;
	home() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		this.setTitle("遊戲世界");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900,900);
		this.setLocation(400, 50);
		this.setVisible(true);
		jpn = new JPanel();
		jpc = new JPanel();
		jps = new JPanel();
		jps.setBackground(Color.ORANGE);
		image[0] = new ImageIcon("a87image.jpg");
		image[1] = new ImageIcon("a.png");
		jb = new JButton(image[0]);
		jb1 = new JButton(image[1]);
		jpn.setLayout(new GridLayout(1,2));
		jpc.setLayout(new GridLayout(1,2));
		jl = new JLabel("五子棋");
		jl1 = new JLabel("1A2B");
		jl.setFont(new Font("標楷體",Font.BOLD,40));
		jl1.setFont(new Font("標楷體",Font.BOLD,40));
		jl.setForeground(Color.GREEN);
		jl1.setForeground(Color.GREEN);
		jl.setBackground(Color.BLUE);
		jl.setOpaque(true);
		jl1.setBackground(Color.BLUE);
		jl1.setOpaque(true);
		text.setFont(new Font("標楷體",Font.BOLD,57));
		text.setBackground(Color.ORANGE);
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
		jb.addMouseListener(this);
		jb1.addMouseListener(this);
		jb.addActionListener(this);
		jb1.addActionListener(this);
		
		
		jpn.add(jl);
		jpn.add(jl1);
		jpc.add(jb);
		jpc.add(jb1);
		jps.add(text);
		this.add(jpn,BorderLayout.NORTH);
		this.add(jpc,BorderLayout.CENTER);
		this.add(jps,BorderLayout.SOUTH);
		audio();
	}
	
	
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new home();
	}
	
	
	
	//bgmusic
			public void audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
				File audioFile = new File("Spring_In_My_Step.wav");
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
				AudioFormat audioFormat = audioInputStream.getFormat();
				int bufferSize = (int) Math.min(audioInputStream.getFrameLength() * audioFormat.getFrameSize(), Integer.MAX_VALUE); //緩衝大小
				DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat, bufferSize);
				clip = (Clip) AudioSystem.getLine(dataLineInfo);
				clip.open(audioInputStream);
				clip.start();
				clip.loop(Integer.MAX_VALUE);
			}



	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		text.setFont(new Font("標楷體",Font.BOLD,20));
		if(e.getSource() == jb1) {
			text.setText("遊戲介紹：\n" +  
					"這是一種相當普遍常見的數學遊戲，規則很簡單，極好上手，\n它的數學概念主要為排列組合，共有5040種可能性供對手猜測，\n也因為排列組合的複雜多變性，使得這種遊戲雖然簡單卻不單調，十分耐玩。\n\n" + 
					"遊戲規則：\n" + 
					"你和對手分別選定一個四位數，各位數字不要重複。\n" + 
					"遊戲開始後，由雙方分別猜對方所選定的四位數，\n猜測的結果將會列在自己的猜測歷史列表，並以Ａ和Ｂ來表示結果。\n" +  
					"Ａ代表猜測的數字中，數字相同且位置也正確的個數。\n" + 
					"Ｂ代表猜測的數字中，數字相同但位置不一樣的個數。\n" + 
					"舉例來說，如果對方的數字為１２３４，且你猜的數字為５２８３，\n其中２被猜到且位置正確，３也被猜到但位置不對，所以結果會出現１Ａ１Ｂ。\n" +  
					"比賽由先完整猜出對方數字的人獲得勝利（也就是先得到４Ａ的玩家）" );
		}else {
			text.setText("遊戲介紹：\n" +  
					"五子棋是一種兩人對弈的純策略型棋類遊戲，\n"
					+ "源於古代中國的傳統黑白棋遊戲，但真正發展經營是在日本，然後風靡歐洲。\n"+
					"五子棋經歷了許多改良，主要是規則的變化與修訂，\n其中多半是為了黑白雙方的平衡，而對黑方做了許多規定和限制。\n"+
					"五子棋既有簡單易學的特點，易於上手，因此成為通俗常見的休閒娛樂，\n又其技巧深奧，能組織一般大眾的比賽與活動，也能舉辦高水平的國際比賽。\n"+
					"五子棋在開局時便能影響勝負，甚至不同的珠型就幾乎會決定結局，\n相較於圍棋，更常見一著錯而滿盤輸，富有緊湊刺激的樂趣。\n\n" + 
					"遊戲規則：\n" + 
					"此版本為最原始規則\n"+
					"行棋：黑子先行，一人輪流一著下於棋盤空點處。\n" + 
					"勝負：先把五枚或以上己棋相連成任何橫縱斜方向為勝。（長連仍算勝利" );
		}
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		text.setFont(new Font("標楷體",Font.BOLD,57));
		text.setText("滑鼠移上圖片有簡單介紹~~\n點擊圖片則進入遊戲~~\n\n\n");
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb) {
			clip.close();
			new a87();
		}else {
			try {
				clip.close();
				new ahome();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		home.this.setVisible(false);
	}


}

