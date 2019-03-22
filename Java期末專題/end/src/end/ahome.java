package end;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class ahome extends JFrame implements ItemListener, ActionListener {
	
	private String[] list = {"人猜電腦--無限制","人猜電腦--倒數計時制","雙人連線對戰--伺服器","雙人連線對戰--客戶端"};
	private JComboBox<String> cb = new JComboBox<>(list);
	String game = list[0];
	JButton ok;
	Clip clip;
	static public ImageIcon image = new ImageIcon("1A2B.jpg");
	public ahome() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.setTitle("1A2B");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(512,600);
		this.setLocation(400, 50);
		ImagePanel jpc = new ImagePanel();
		JPanel jps = new JPanel();
		jps.setLayout(new FlowLayout());
		cb.addItemListener(this);
		ok = new JButton("確定");
		ok.addActionListener(this);
		jps.add(cb);
		jps.add(ok);
		this.add(jps,BorderLayout.SOUTH);
		this.add(jpc,BorderLayout.CENTER);
		this.setVisible(true);
		audio();
		
	}

	
	//bgmusic
		public void audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
			File audioFile = new File("Hero_Down.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat audioFormat = audioInputStream.getFormat();
			int bufferSize = (int) Math.min(audioInputStream.getFrameLength() * audioFormat.getFrameSize(), Integer.MAX_VALUE); //緩衝大小
			DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat, bufferSize);
			clip = (Clip) AudioSystem.getLine(dataLineInfo);
			clip.open(audioInputStream);
			clip.start();
			clip.loop(Integer.MAX_VALUE);
		}
	
	
	
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// TODO Auto-generated method stub
		new ahome();

	}
	//選擇列文字
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		game = (String)cb.getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(game.equals(list[0])) {
			try {
				clip.close();
				new a1();
				ahome.this.setVisible(false);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(game.equals(list[1])) {
			try {
				clip.close();
				new a2();
				ahome.this.setVisible(false);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(game.equals(list[2])) {
			try {
				clip.close();
				new a3();
				ahome.this.setVisible(false);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(game.equals(list[3])) {
			try {
				clip.close();
				new a3C();
				ahome.this.setVisible(false);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
//圖
class ImagePanel extends JPanel{
	public ImagePanel() {
		this.setBackground(Color.BLACK);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ahome.image.getImage(), 0, 0, null);
		
	}
}
