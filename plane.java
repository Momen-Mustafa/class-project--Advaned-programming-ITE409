import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class plane extends JPanel implements ActionListener {
	
	JComboBox box;
	static String f_code;
	static int reserved;
	static String seat;

	JButton[][]btnf1=new JButton[5][4];
	JButton[][]btnf2=new JButton[5][4];
	JButton[][]btnf3=new JButton[5][4];
	String[][] names ={ {"A1","B1","C1","D1"},{"A2","B2","C2","D2"},{"A3","B3","C3","D3"},{"A4","B4","C4","D4"},{"A5","B5","C5","D5"}};
	int [] counter1 = new int[5][4];
	int [] counter2 = new int[5][4];
	int [] counter3 = new int[5][4];
	
	public plane(){
			
		box=new JComboBox();
		box.addItem("AUI22 London To Melbourne");
		box.addItem("AUI33 Berlin To Texas");
		box.addItem("AUI44 Moscow To Madrid");
		add(box);
		box.setBounds(400, 0, 190, 30);
	    box.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				
				if(box.getSelectedIndex()==0) {	
					for(int i=0; i<5;i++) {
						for(int j=0; j<4;j++) {
							btnf1[i][j].setVisible(true);
							btnf2[i][j].setVisible(false);
							btnf3[i][j].setVisible(false);	
						}
					}
				}
					
				if(box.getSelectedIndex()==1) {					
					for(int i=0; i<5;i++) {
						for(int j=0; j<4;j++) {
							btnf1[i][j].setVisible(false);
							btnf2[i][j].setVisible(true);
							btnf3[i][j].setVisible(false);	
						}
					}	
				}
				
				if(box.getSelectedIndex()==2) {
					for(int i=0; i<5;i++) {
							for(int j=0; j<4;j++){
								btnf1[i][j].setVisible(false);
								btnf2[i][j].setVisible(false);
								btnf3[i][j].setVisible(true);
							}
						}
				}
			}
		}
		); 
		
		setLayout(null);
		// create the first airplane buttons
		int []x= {430,370,280,220};
		for(int i=0; i<5;i++) {
			for(int j=0; j<4;j++) {
				btnf1[i][j]=new JButton(names[i][j]);
				btnf1[i][j].addActionListener(this);
				add(btnf1[i][j]);
				btnf1[i][j].setBackground(Color.GREEN);
				btnf1[i][j].setBounds((400+i*100),x[j], 50, 50);
				btnf1[i][j].setFocusable(false);
			//	counter1[i][j]=0;
			}
		}

		//create the second airplane buttons
		for(int i=0; i<5;i++) {
			for(int j=0; j<4;j++) {
				btnf2[i][j]=new JButton(names[i][j]);
				btnf2[i][j].addActionListener(this);
				add(btnf2[i][j]);
				btnf2[i][j].setBackground(Color.GREEN);
				btnf2[i][j].setBounds((400+i*100),x[j], 50, 50);
				btnf2[i][j].setFocusable(false);
			}
		}

		//create the third airplane buttons
		for(int i=0; i<5;i++) {
			for(int j=0; j<4;j++) {
				btnf3[i][j]=new JButton(names[i][j]);
				btnf3[i][j].addActionListener(this);
				add(btnf3[i][j]);
				btnf3[i][j].setBackground(Color.GREEN);
				btnf3[i][j].setBounds((400+i*100),x[j], 50, 50);
				btnf3[i][j].setFocusable(false);
			}
		}

		JPanel panal = new JPanel(new BorderLayout());
		panal.setBackground(new Color(0,0,0,90));
		panal.setSize(1000,30);
		add(panal);
			
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.drawOval(50, 150, 1500, 400);
		g.drawLine(500, 166, 700, 0);
		g.drawLine(800, 150, 900, 0);
		
		g.drawLine(500, 532, 700, 700);
		g.drawLine(800, 550, 900, 700);
		
		g.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		g.drawString("Flights:",350,20);
	}

	public static void main(String[] args) {
		JFrame window = new JFrame(); 
		plane panel = new plane();
		window.add(panel);
		panel.setBackground(Color.WHITE); 
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setSize(1000, 700);
	    window.setTitle("AUIS Airlines Reservation System");
	    window.setVisible(true);
	      
	    try(
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/airlinereservationdb","root","");	
			PreparedStatement stat= con.prepareStatement("update seat sets_reserved=? where s_number= ? AND f_code= ?");
			){
	    	  stat.setInt(1,reserved);
	    	  stat.setString(2,seat );
	    	  stat.setString(3,f_code);
	    	  stat.executeUpdate();			
	    } 
		catch(SQLException ex) {
			  ex.getMessage();		
		}
	}
	
 	@Override
	public void actionPerformed(ActionEvent e) {	
		for(int i=0; i<5;i++) {
			for(int j=0; j<4;j++) {
				if(e.getSource()==btnf1[i][j]) {
					btnf1[i][j].setBackground(Color.red);
					
					
					//adding the name of the seat to the seat variable in order to send it to the database
					f_code="AUI22";
					seat=names[i][j];
					reserved= 1;
					
					//for checking the seat name that will go to the database
					/*System.out.println(i+" "+j);
					System.out.println(seat);*/
				}
			}
		}
					
		for(int i=0; i<5;i++) {
			for(int j=0; j<4;j++) {
				if(e.getSource()==btnf2[i][j]) {
					
					btnf2[i][j].setBackground(Color.red);
					
					//adding the name of the seat to the seat variable in order to send it to the database
					seat=names[i][j];
					f_code="AUI33";
					reserved= 1;
					//for checking the seat name that will go to the database
					/*System.out.println(i+" "+j);
					System.out.println(seat);*/	
				}
			}
		}
			
		for(int i=0; i<5;i++) {
			for(int j=0; j<4;j++) {
				if(e.getSource()==btnf3[i][j]) {
					btnf3[i][j].setBackground(Color.red);
					
					//adding the name of the seat to the seat variable in order to send it to the database
					seat=names[i][j];
					f_code="AUI44";
					reserved=1;
					
					//for checking the seat name that will go to the database
					/*System.out.println(i+" "+j);
					System.out.println(seat);*/
				}
			}
		}
					
	}
	
}
