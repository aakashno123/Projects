import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Stack;

import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.SwingUtilities;
import javax.swing.JDesktopPane;

public class ABC extends JFrame {

	private JPanel contentPane;
	public JScrollPane scrollPane;
	public File file;
	int sumC=0;
	
	
	public int slider3Old=0,change=0;
	Point p=new Point(0,0),p1=new Point(0, 0);
	String path;
	String [] EffectList={"Select Effect","Greyscale","Red","Green","Blue","Negative"};
	JComboBox comboBox; 
	public BufferedImage Uimage,oimage=null,image=null;
 public static ABC frame;
 private JButton btnSave;
 private JLabel lblEffects;
 private JLabel label;
private JScrollPane scrollPane_1;
 private JLabel lblHue;
 private JLabel label_1;
 private JLabel lblSaturation;
 private JSlider slider_1;
 private JLabel lblBrightness;
 private JSlider slider_2;
 private JButton btnUndo;
 public Stack <BufferedImage>stak=new Stack<BufferedImage>();
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		try {
			
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ABC();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ABC() {
		setBackground(SystemColor.desktop);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setResizable(false);
		setTitle("Image Editor");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnOpenImage = new JButton("File");
		btnOpenImage.setFont(new Font("Georgia", Font.PLAIN, 17));
		menuBar.add(btnOpenImage);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Georgia", Font.PLAIN, 17));
		menuBar.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Save();
			}
		});
		
		btnOpenImage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OpenFile();
			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1076, 690);
	
		contentPane.add(scrollPane);
		
		label = new JLabel("");
		label.setBackground(Color.WHITE);
		scrollPane.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				p=p1;
				p1=e.getPoint();
			 
			 
			 SwingUtilities.convertPoint(scrollPane, p, scrollPane);
			
			}
		});
		
		scrollPane.setViewportView(label);
		
		comboBox = new JComboBox(EffectList);
		comboBox.setBounds(1088, 37, 245, 22);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==comboBox){
					JComboBox cb=(JComboBox) e.getSource();
					String effect=(String)cb.getSelectedItem();
					stak.push(deepCopy(oimage));

					
					
					switch(effect){
					
					case "Greyscale":Greyscale(oimage); break;
					case "Red":RedEffect(oimage);break;
					case "Green":GreenEffect(oimage);break;
					case "Blue":BlueEffect(oimage);break;
					case "Negative":Negative(oimage);break;
					
					default:JOptionPane.showMessageDialog(frame, "Please select a valid effect!!");
					}
					
				}
			}
		} );
		comboBox.setToolTipText("");
		contentPane.add(comboBox);
		
		lblEffects = new JLabel("Effects");
		lblEffects.setBackground(Color.WHITE);
		lblEffects.setBounds(1088, 13, 51, 20);
		lblEffects.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		contentPane.add(lblEffects);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 102, 102));
		desktopPane.setBounds(1075, 72, 285, 617);
		contentPane.add(desktopPane);
		
		 scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(279, 614, -275, -609);
		desktopPane.add(scrollPane_1);
		
		lblBrightness = new JLabel("Blue");
		lblBrightness.setBounds(12, 228, 79, 20);
		desktopPane.add(lblBrightness);
		lblBrightness.setForeground(Color.BLUE);
		lblBrightness.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		
		slider_2 = new JSlider(0,250,0);
		slider_2.setBounds(12, 261, 245, 52);
		desktopPane.add(slider_2);
		slider_2.setBackground(Color.WHITE);
		slider_2.setBorder(new LineBorder(Color.BLUE, 1, true));
		
			slider_2.setMinorTickSpacing(25);
			slider_2.setPaintTicks(true);
			slider_2.setPaintLabels(true);
			slider_2.setMajorTickSpacing(50);
			
			
			
			lblSaturation = new JLabel("Green");
			lblSaturation.setBounds(12, 130, 45, 20);
			desktopPane.add(lblSaturation);
			lblSaturation.setForeground(Color.GREEN);
			lblSaturation.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
			
			slider_1 = new JSlider();
			slider_1.setBounds(12, 163, 245, 52);
			desktopPane.add(slider_1);
			slider_1.setMaximum(250);
			slider_1.setValue(0);
			slider_1.setBackground(Color.WHITE);
			slider_1.setBorder(new LineBorder(Color.GREEN, 1, true));
			slider_1.setMinorTickSpacing(25);
			slider_1.setPaintTicks(true);
			slider_1.setPaintLabels(true);
			slider_1.setMajorTickSpacing(50);
			
			lblHue = new JLabel("Red");
			lblHue.setBounds(12, 32, 31, 20);
			desktopPane.add(lblHue);
			lblHue.setBackground(Color.WHITE);
			lblHue.setForeground(Color.RED);
			lblHue.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
			
			JSlider slider = new JSlider(0,250,0);
			slider.setBounds(12, 65, 245, 52);
			desktopPane.add(slider);
			slider.setMinorTickSpacing(25);
			slider.setBackground(Color.WHITE);
			slider.setBorder(new LineBorder(Color.RED, 1, true));
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.setMajorTickSpacing(50);
			
			JButton btnCrop = new JButton("Crop");
			btnCrop.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
			btnCrop.setBounds(12, 361, 245, 29);
			btnCrop.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Crop(oimage);
					
				}
			});
			
			desktopPane.add(btnCrop);
			
			btnUndo = new JButton("Undo");
			btnUndo.setBounds(12, 443, 245, 29);
			desktopPane.add(btnUndo);
			btnUndo.setFont(new Font("Georgia", Font.PLAIN, 17));
			btnUndo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				if (stak.empty())
				{
					oimage=deepCopy(Uimage);
				}
				else
				{
					oimage=stak.pop();
				}
				SetImage(oimage);
				p=new Point(0, 0);
				p1=p;
				}
			});
			slider.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					Red(oimage,slider.getValue());
				}
			});
			slider_1.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					Green(oimage,slider_1.getValue());
				}
			});
			
			slider_2.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					Blue(oimage,slider_2.getValue());
					
					
				}
			});
		
		
		
		
	}
	
public void OpenFile(){
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"); 
	JFileChooser fc=new JFileChooser();
	fc.setCurrentDirectory(new File("C:\\Users\\AKASH\\Desktop"));
	fc.setDialogTitle("Open File");
	fc.setFileFilter(filter);
	try{
		if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			file=new File(fc.getSelectedFile().getAbsolutePath());
			path=fc.getSelectedFile().getAbsolutePath();
			Uimage=ImageIO.read(file);
			image=ImageIO.read(file);
			oimage=image;
			SetImage(image);
		}
		
	}catch(Exception e){
		JOptionPane.showMessageDialog(null,"Cannot open the selected file!!");
	}
}
	

public void SetImage(BufferedImage img){
	try {
		oimage=img;
		ImageIcon icon=new ImageIcon(img);
		label.setIcon(icon);
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void Greyscale(BufferedImage Bimage){
	
	int width=Bimage.getWidth();
	int height=Bimage.getHeight();
	int p,a,r,g,b,avg;
	for(int i=0;i<height;i++){
		for(int j=0;j<width;j++){
			p=Bimage.getRGB(j, i);
			a=(p>>24)&0xff;
			r=(p>>16)&0xff;
			g=(p>>8)&0xff;
			b=p&0xff;
			avg=(r+g+b)/3;
			p=(a<<24)|(avg<<16)|(avg<<8)|(avg);
			
			Bimage.setRGB(j, i, p);
		}
	}   
	oimage=Bimage;
	SetImage(oimage);
}


static BufferedImage deepCopy(BufferedImage bi) {
 ColorModel cm = bi.getColorModel();
 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
 WritableRaster raster = bi.copyData(null);
 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
}

public void RedEffect(BufferedImage Bimage){
	int width=Bimage.getWidth();
	int height=Bimage.getHeight();
	int p,a,r,g,b;
	for(int i=0;i<height;i++){
		for(int j=0;j<width;j++){
			p=Bimage.getRGB(j, i);
			a=(p>>24)&0xff;
			r=(p>>16)&0xff;
			g=(p>>8)&0xff;
			b=p&0xff;
			
			p=(a<<24)|(r<<16)|(g/2<<8)|(b/2);
			
			Bimage.setRGB(j, i, p);
		}
	}   
	oimage=Bimage;
	SetImage(oimage);
}

public void GreenEffect(BufferedImage Bimage){
	int width=Bimage.getWidth();
	int height=Bimage.getHeight();
	int p,a,r,g,b;
	for(int i=0;i<height;i++){
		for(int j=0;j<width;j++){
			p=Bimage.getRGB(j, i);
			a=(p>>24)&0xff;
			r=(p>>16)&0xff;
			g=(p>>8)&0xff;
			b=p&0xff;
			
			p=(a<<24)|(r/2<<16)|(g<<8)|(b/2);
			
			Bimage.setRGB(j, i, p);
		}
	}   
	oimage=Bimage;
	SetImage(oimage);
}


public void BlueEffect(BufferedImage Bimage){
	int width=Bimage.getWidth();
	int height=Bimage.getHeight();
	int p,a,r,g,b;
	for(int i=0;i<height;i++){
		for(int j=0;j<width;j++){
			p=Bimage.getRGB(j, i);
			a=(p>>24)&0xff;
			r=(p>>16)&0xff;
			g=(p>>8)&0xff;
			b=p&0xff;
			
			p=(a<<24)|(r/2<<16)|(g/2<<8)|(b);
			
			Bimage.setRGB(j, i, p);
		}
	}   
	oimage=Bimage;
	SetImage(oimage);
}


public void Negative(BufferedImage Bimage){
	int width=Bimage.getWidth();
	int height=Bimage.getHeight();
	int p,a,r,g,b;
	for(int i=0;i<height;i++){
		for(int j=0;j<width;j++){
			p=Bimage.getRGB(j, i);
			a=(p>>24)&0xff;
			r=(p>>16)&0xff;
			g=(p>>8)&0xff;
			b=p&0xff;
			p=(a<<24)|(255-r<<16)|(255-g<<8)|(255-b);
			
			Bimage.setRGB(j, i, p);
		}
	}   
	oimage=Bimage;
	SetImage(oimage);	
}

public void Blue(BufferedImage Bimage,int change){
	
	int width=Bimage.getWidth();
	int height=Bimage.getHeight();
	int p,a,r,g;
	stak.push(deepCopy(oimage));

	for(int i=0;i<height;i++){
		for(int j=0;j<width;j++){
			p=Bimage.getRGB(j, i);
			a=(p>>24)&0xff;
			r=(p>>16)&0xff;
			g=(p>>8)&0xff;
			
			
			
			p=(a<<24)|(r<<16)|(g<<8)|(change);
			Bimage.setRGB(j, i, p);
		}
	}   
	oimage=Bimage;
	SetImage(oimage);
	
}

public void Green(BufferedImage Bimage,int change){
	
	int width=Bimage.getWidth();
	int height=Bimage.getHeight();
	int p,a,r,b;
	stak.push(deepCopy(oimage));

	for(int i=0;i<height;i++){
		for(int j=0;j<width;j++){
			p=Bimage.getRGB(j, i);
			a=(p>>24)&0xff;
			r=(p>>16)&0xff;
			
			b=p&0xff;
			
			p=(a<<24)|(r<<16)|(change<<8)|(b);
			
			Bimage.setRGB(j, i, p);
		}
	}   
	oimage=Bimage;
	SetImage(oimage);
	
}

public void Red(BufferedImage Bimage,int change){
	
	int width=Bimage.getWidth();
	int height=Bimage.getHeight();
	int p,a,g,b;
	stak.push(deepCopy(oimage));

	for(int i=0;i<height;i++){
		for(int j=0;j<width;j++){
			p=Bimage.getRGB(j, i);
			a=(p>>24)&0xff;
			
			g=(p>>8)&0xff;
			b=p&0xff;
			
			p=(a<<24)|(change<<16)|(g<<8)|(b);
			
			Bimage.setRGB(j, i, p);
		}
	}   
	oimage=Bimage;
	SetImage(oimage);
	
}



public void Crop(BufferedImage Bimage){
	int x=p.x,y=p.y;
	int w=p1.x,h=p1.y;
	stak.push(deepCopy(oimage));
	
	oimage=Bimage.getSubimage(x, y, w, h);
	SetImage(oimage);
}



public void Save(){
	try{
		
		File ff=new File(path+"OUTPUT.jpg");
		
		ImageIO.write(oimage, "jpg", ff);
	}catch(Exception e){
		JOptionPane.showMessageDialog(frame, "Could not Save the file!!");
	}
	
}








}