package maven_installer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;

public class JarInstallerGUI extends JFrame {

	private static final long serialVersionUID = -2044168589251480409L;
	
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem quitMenuItem;
	private JLabel jarFileLabel;
	private JLabel groupIdLabel;
	private JLabel artifactIdLabel;
	private JLabel versionLabel;
	private JTextField jarFileTextField;
	private JTextField groupIdTextField;
	private JTextField artifactIdTextField;
	private JTextField versionTextField;
	private JButton browseButton;
	private JCheckBox hasPomCheckBox;
	private JButton installButton;
	private JTextArea logTextArea;
	private JPanel panel;
	private JMenu logMenu;
	private JMenuItem clearLogMenuItem;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JarInstallerGUI frame = new JarInstallerGUI();
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
	public JarInstallerGUI() {
		initGUI();
	}
	private void initGUI() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 648);
		setMinimumSize(new Dimension(600, 600));
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.addActionListener(e -> quit_actionPerformed(e));
//		quit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				quitactionPerformed(e);
//			}
//		});
		fileMenu.add(quitMenuItem);
		
		logMenu = new JMenu("Log");
		menuBar.add(logMenu);
		
		clearLogMenuItem = new JMenuItem("Clear");
		clearLogMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearLogMenuItem_actionPerformed(e);
			}
		});
		logMenu.add(clearLogMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 10, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		jarFileLabel = new JLabel("Jar file:");
		jarFileLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_jarFileLabel = new GridBagConstraints();
		gbc_jarFileLabel.anchor = GridBagConstraints.WEST;
		gbc_jarFileLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jarFileLabel.gridx = 0;
		gbc_jarFileLabel.gridy = 0;
		contentPane.add(jarFileLabel, gbc_jarFileLabel);
		
		jarFileTextField = new JTextField();
		jarFileTextField.setDropTarget(new DropTarget() {

			private static final long serialVersionUID = 2630925503918308167L;

			@SuppressWarnings("unchecked")
			@Override
			public synchronized void drop(DropTargetDropEvent event) {
				event.acceptDrop(DnDConstants.ACTION_COPY) ;
				try {
					List<File> files = (List<File>) event.getTransferable().getTransferData(DataFlavor.javaFileListFlavor) ;
					// use the last file
					for(File file : files) {
						jarFileTextField.setText(file.getAbsolutePath()) ;
					}
				} catch (UnsupportedFlavorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		GridBagConstraints gbc_jarFileTextField = new GridBagConstraints();
		gbc_jarFileTextField.insets = new Insets(0, 0, 5, 5);
		gbc_jarFileTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jarFileTextField.gridx = 1;
		gbc_jarFileTextField.gridy = 0;
		contentPane.add(jarFileTextField, gbc_jarFileTextField);
		jarFileTextField.setColumns(10);
		
		browseButton = new JButton("Browse...");
		GridBagConstraints gbc_browseButton = new GridBagConstraints();
		gbc_browseButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_browseButton.insets = new Insets(0, 0, 5, 0);
		gbc_browseButton.gridx = 2;
		gbc_browseButton.gridy = 0;
		contentPane.add(browseButton, gbc_browseButton);
		
		groupIdLabel = new JLabel("Group ID:");
		GridBagConstraints gbc_groupIdLabel = new GridBagConstraints();
		gbc_groupIdLabel.anchor = GridBagConstraints.WEST;
		gbc_groupIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_groupIdLabel.gridx = 0;
		gbc_groupIdLabel.gridy = 1;
		contentPane.add(groupIdLabel, gbc_groupIdLabel);
		
		groupIdTextField = new JTextField();
		GridBagConstraints gbc_groupIdTextField = new GridBagConstraints();
		gbc_groupIdTextField.insets = new Insets(0, 0, 5, 5);
		gbc_groupIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_groupIdTextField.gridx = 1;
		gbc_groupIdTextField.gridy = 1;
		contentPane.add(groupIdTextField, gbc_groupIdTextField);
		groupIdTextField.setColumns(10);
		
		hasPomCheckBox = new JCheckBox("has POM");
		GridBagConstraints gbc_hasPomCheckBox = new GridBagConstraints();
		gbc_hasPomCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_hasPomCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_hasPomCheckBox.gridx = 2;
		gbc_hasPomCheckBox.gridy = 1;
		contentPane.add(hasPomCheckBox, gbc_hasPomCheckBox);
		
		artifactIdLabel = new JLabel("Artifact ID:");
		GridBagConstraints gbc_artifactIdLabel = new GridBagConstraints();
		gbc_artifactIdLabel.anchor = GridBagConstraints.WEST;
		gbc_artifactIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_artifactIdLabel.gridx = 0;
		gbc_artifactIdLabel.gridy = 2;
		contentPane.add(artifactIdLabel, gbc_artifactIdLabel);
		
		artifactIdTextField = new JTextField();
		GridBagConstraints gbc_artifactIdTextField = new GridBagConstraints();
		gbc_artifactIdTextField.insets = new Insets(0, 0, 5, 5);
		gbc_artifactIdTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_artifactIdTextField.gridx = 1;
		gbc_artifactIdTextField.gridy = 2;
		contentPane.add(artifactIdTextField, gbc_artifactIdTextField);
		artifactIdTextField.setColumns(10);
		
		versionLabel = new JLabel("Version:");
		GridBagConstraints gbc_versionLabel = new GridBagConstraints();
		gbc_versionLabel.anchor = GridBagConstraints.WEST;
		gbc_versionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_versionLabel.gridx = 0;
		gbc_versionLabel.gridy = 3;
		contentPane.add(versionLabel, gbc_versionLabel);
		
		versionTextField = new JTextField();
		GridBagConstraints gbc_versionTextField = new GridBagConstraints();
		gbc_versionTextField.insets = new Insets(0, 0, 5, 5);
		gbc_versionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_versionTextField.gridx = 1;
		gbc_versionTextField.gridy = 3;
		contentPane.add(versionTextField, gbc_versionTextField);
		versionTextField.setColumns(10);
		
		installButton = new JButton("Install");
		installButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				installButton_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_installButton = new GridBagConstraints();
		gbc_installButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_installButton.insets = new Insets(0, 0, 5, 0);
		gbc_installButton.gridx = 2;
		gbc_installButton.gridy = 3;
		contentPane.add(installButton, gbc_installButton);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Log", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		
		logTextArea = new JTextArea();
		scrollPane.setViewportView(logTextArea);
	}

	// concept of even handling: press --> action
	protected void quit_actionPerformed(ActionEvent e) {
		System.out.println("Quitting Application...");
		System.exit(0); // normal application termination
	}
	
	protected void clearLogMenuItem_actionPerformed(ActionEvent e) {
		logTextArea.setText(""); // empty string
	}
	
	protected void installButton_actionPerformed(ActionEvent e) {
		// goal: run a command line command: mvn
		// mvn install:install-file -Dfile=… -DgroupId=… -DartifactId=… -Dversion=… -Dpackaging=jar
		String f1 = String.format("-Dfile=%s", jarFileTextField.getText()) ;
		String f2 = String.format("-DgroupId=%s", groupIdTextField.getText()) ;
		String f3 = String.format("-DartifactId=%s", artifactIdTextField.getText()) ;
		String f4 = String.format("-Dversion=%s", versionTextField.getText()) ;
		String f5 = "-Dpackaging=jar" ;
		String command = String.format("mvn install:install-file %s %s %s %s %s", f1, f2, f3, f4, f5) ;
		// print the command to the log
		if(logTextArea.getText().length()>0) {
			logTextArea.append("=".repeat(command.length())+"\n");
		}
		logTextArea.append(command+"\n");
		// now we want to execute the command
		Runtime runtime = Runtime.getRuntime();
		try {
			Process p = runtime.exec(command);
			Scanner scanner = new Scanner(p.getInputStream());
			while(scanner.hasNextLine()) {
				logTextArea.append(scanner.nextLine()+"\n");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		
		
	}
}
