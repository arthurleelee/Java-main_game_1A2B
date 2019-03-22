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
	JTextArea text = new JTextArea("�ƹ����W�Ϥ���²�椶��~~\n�I���Ϥ��h�i�J�C��~~\n\n\n");
	Clip clip;
	home() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		this.setTitle("�C���@��");
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
		jl = new JLabel("���l��");
		jl1 = new JLabel("1A2B");
		jl.setFont(new Font("�з���",Font.BOLD,40));
		jl1.setFont(new Font("�з���",Font.BOLD,40));
		jl.setForeground(Color.GREEN);
		jl1.setForeground(Color.GREEN);
		jl.setBackground(Color.BLUE);
		jl.setOpaque(true);
		jl1.setBackground(Color.BLUE);
		jl1.setOpaque(true);
		text.setFont(new Font("�з���",Font.BOLD,57));
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
				int bufferSize = (int) Math.min(audioInputStream.getFrameLength() * audioFormat.getFrameSize(), Integer.MAX_VALUE); //�w�Ĥj�p
				DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat, bufferSize);
				clip = (Clip) AudioSystem.getLine(dataLineInfo);
				clip.open(audioInputStream);
				clip.start();
				clip.loop(Integer.MAX_VALUE);
			}



	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		text.setFont(new Font("�з���",Font.BOLD,20));
		if(e.getSource() == jb1) {
			text.setText("�C�����СG\n" +  
					"�o�O�@�ج۷��M�`�����ƾǹC���A�W�h��²��A���n�W��A\n�����ƾǷ����D�n���ƦC�զX�A�@��5040�إi��ʨѹ��q���A\n�]�]���ƦC�զX�������h�ܩʡA�ϱo�o�عC�����M²��o����աA�Q���@���C\n\n" + 
					"�C���W�h�G\n" + 
					"�A�M�����O��w�@�ӥ|��ơA�U��Ʀr���n���ơC\n" + 
					"�C���}�l��A��������O�q���ҿ�w���|��ơA\n�q�������G�N�|�C�b�ۤv���q�����v�C��A�åH�ϩM�ШӪ�ܵ��G�C\n" +  
					"�ϥN��q�����Ʀr���A�Ʀr�ۦP�B��m�]���T���ӼơC\n" + 
					"�ХN��q�����Ʀr���A�Ʀr�ۦP����m���@�˪��ӼơC\n" + 
					"�|�Ҩӻ��A�p�G��誺�Ʀr�����������A�B�A�q���Ʀr�����������A\n�䤤���Q�q��B��m���T�A���]�Q�q�����m����A�ҥH���G�|�X�{���Ϣ��СC\n" +  
					"���ɥѥ�����q�X���Ʀr���H��o�ӧQ�]�]�N�O���o�좳�Ϫ����a�^" );
		}else {
			text.setText("�C�����СG\n" +  
					"���l�ѬO�@�ب�H��٪��µ����������C���A\n"
					+ "����j�N���ꪺ�ǲζ¥մѹC���A���u���o�i�g��O�b�饻�A�M�᭷���ڬw�C\n"+
					"���l�Ѹg���F�\�h��}�A�D�n�O�W�h���ܤƻP�׭q�A\n�䤤�h�b�O���F�¥����誺���šA�ӹ�¤谵�F�\�h�W�w�M����C\n"+
					"���l�ѬJ��²����Ǫ��S�I�A����W��A�]�������q�U�`�����𶢮T�֡A\n�S��ޥ��`���A���´�@��j�������ɻP���ʡA�]���|�찪��������ڤ��ɡC\n"+
					"���l�Ѧb�}���ɫK��v�T�ӭt�A�Ʀܤ��P���]���N�X�G�|�M�w�����A\n�۸����ѡA��`���@�ۿ��Ӻ��L��A�I������E���ֽ�C\n\n" + 
					"�C���W�h�G\n" + 
					"���������̭�l�W�h\n"+
					"��ѡG�¤l����A�@�H���y�@�ۤU��ѽL���I�B�C\n" + 
					"�ӭt�G���⤭�T�ΥH�W�v�Ѭ۳s��������a�פ�V���ӡC�]���s����ӧQ" );
		}
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		text.setFont(new Font("�з���",Font.BOLD,57));
		text.setText("�ƹ����W�Ϥ���²�椶��~~\n�I���Ϥ��h�i�J�C��~~\n\n\n");
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

