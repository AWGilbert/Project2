package csciproject2;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JRadioButton;



public class SongPanel extends JPanel
{
	private ImageIcon image;
	private Library allTheSongs;
	private Song song;
	private JTextField txtGenre;
	
	public SongPanel() 
	{	
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600, 450));
		setLayout(null);
		image = new ImageIcon(this.getClass().getResource("csciproject2/MusicImage.jpg"));
		
		
		JLabel lblSongName = new JLabel();
		lblSongName.setText(song.getName());
		lblSongName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSongName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSongName.setForeground(new Color(255, 255, 255));
		lblSongName.setBounds(282, 280, 279, 55);
		add(lblSongName);
		
		JButton btnNewButton = new JButton("Skip");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(128, 0, 128));
		btnNewButton.setBounds(444, 377, 117, 34);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Play/Pause");
		btnNewButton_1.setBackground(new Color(128, 0, 128));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(282, 377, 117, 34);
		add(btnNewButton_1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(322, 345, 211, 11);
		add(progressBar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(SongPanel.class.getResource("/songproject2/MusicImage.jpg")));
		label.setBounds(266, 22, 311, 248);
		add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 286, 211, 125);
		add(scrollPane);
		
		JTextArea SongsByG = new JTextArea();
		scrollPane.setViewportView(SongsByG);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText(comboBox.getSelectedItem().toString());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Library songs = allTheSongs.findSongByGenre("" + comboBox.getSelectedItem());
			String aString = "";
			Iterator<Song> iter = songs.getIterator();
			while (iter.hasNext()) {
				iter.next();
				aString += "\n";
			}
			JLabel songByG = null;
			songByG.setText(aString);
			}});
		comboBox.setBounds(27, 226, 152, 34);
		add(comboBox);
		
		txtGenre = new JTextField();
		txtGenre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGenre.setBackground(new Color(0, 0, 0));
		txtGenre.setText("Genre:");
		txtGenre.setForeground(new Color(255, 255, 255));
		txtGenre.setToolTipText("");
		txtGenre.setBounds(27, 190, 96, 26);
		add(txtGenre);
		txtGenre.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setForeground(new Color(0, 0, 0));
		btnRemove.setBackground(new Color(128, 0, 128));
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(27, 28, 96, 34);
		add(btnRemove);
		
		JButton btnIncorrectName = new JButton("Incorrect Name");
		btnIncorrectName.setBackground(new Color(128, 0, 128));
		btnIncorrectName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIncorrectName.setBounds(27, 72, 169, 34);
		add(btnIncorrectName);
		btnIncorrectName.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						allTheSongs.remove(song);
					}
				});
		
		JRadioButton rdbtnFavorite = new JRadioButton("Favorite Song");
		rdbtnFavorite.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnFavorite.setBounds(27, 128, 169, 34);
		add(rdbtnFavorite);
		
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		final Color DARK_PURPLE = new Color(100,0,100);
		g.setColor(DARK_PURPLE);
		
		for(int i = 0; i < 10; i++)
		{
			g.drawLine(100 + i, 0, 100 + i, getHeight());
			g.drawLine(0, 50 + i/2, getWidth(), 50 + i);
		}
		
	}

}