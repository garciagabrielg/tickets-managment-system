package model.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import model.entities.Senhas;
import model.services.GerenciamentoDeSenhas;

public class TicketUI extends JFrame {
	private JLabel ticketNumberLabel;
	private JPanel lastCalledTicketPanel;
	private JPanel lastCalledTicketDisplayPanel;
	private JList<String> ticketsList;
	private DefaultListModel<String> ticketsListModel;
	private DefaultListModel<String> lastCalledTicketsListModel;
	private JList<String> lastCalledTicketsList;
	private JButton callPreferentialButton;
	private JButton callNextButton;
	private JButton callBeforeButton;
	private JButton repeatCallButton;
	private JButton callNormalButton;
	private GerenciamentoDeSenhas gds;
	private boolean wasTheCallBackButtonPressed;

	public TicketUI() {
		this.gds = new GerenciamentoDeSenhas();
		setTitle("Ticket System");
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		wasTheCallBackButtonPressed = false;

		// Ticket Number Label
		ticketNumberLabel = new JLabel("Senha: ");
		add(ticketNumberLabel, BorderLayout.NORTH);

		// Last Called Ticket Panel
		lastCalledTicketPanel = new JPanel();
		lastCalledTicketPanel.setBackground(Color.WHITE);

		add(lastCalledTicketPanel, BorderLayout.WEST);

		// Last Called Ticket Display Panel
		lastCalledTicketDisplayPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    
			    // Draw the word "Senha" at the top
			    g.setFont(new Font("Arial", Font.BOLD, 24));
			    FontMetrics fm = g.getFontMetrics();
			    int stringWidth = fm.stringWidth("Senha");
			    int x = (getWidth() - stringWidth) / 2;
			    int y = fm.getAscent(); // This will position the text at the top
			    g.drawString("Senha", x, y);
			    
			    // Draw the last called ticket
			    Senhas lastCalledTicket = gds.getSenhasChamadas().isEmpty() ? null : gds.getSenhasChamadas().peek();
			    if (lastCalledTicket != null) {
			        g.setFont(new Font("Arial", Font.BOLD, 100));
			        g.drawString(lastCalledTicket.toString(), getWidth() / 2 -55, getHeight() / 2 + 50);
			    }
			}

		};
		lastCalledTicketDisplayPanel.setBackground(Color.WHITE);
		lastCalledTicketDisplayPanel.setPreferredSize(new Dimension(600, 200)); // Set fixed size
		lastCalledTicketDisplayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border

		add(lastCalledTicketDisplayPanel, BorderLayout.CENTER);

		// List of last called tickets stack
		lastCalledTicketsListModel = new DefaultListModel<>();
		lastCalledTicketsList = new JList<>(lastCalledTicketsListModel);
		lastCalledTicketsList.setFont(new Font("Arial", Font.PLAIN, 36)); // Set font size

		// Create a JScrollPane for the JList
		JScrollPane lastCalledTicketsScrollPane = new JScrollPane(lastCalledTicketsList);
		lastCalledTicketsScrollPane.setPreferredSize(new Dimension(150, 400)); // Set preferred size

		// Add the JScrollPane to the EAST side of the layout
		add(lastCalledTicketsScrollPane, BorderLayout.EAST);

		// Button Panel
		JPanel buttonPanel = new JPanel(new BorderLayout());

		// Panel for west-aligned buttons
		JPanel westButtonPanel = new JPanel();
		westButtonPanel.setLayout(new FlowLayout());

		// Call Preferential Button
		callPreferentialButton = new JButton("Chamar preferencial");
		westButtonPanel.add(callPreferentialButton);

		// Add the west-aligned buttons to the main button panel
		buttonPanel.add(westButtonPanel, BorderLayout.WEST);

		// Panel for east-aligned buttons
		JPanel eastButtonPanel = new JPanel(new FlowLayout());

		// Call Normal Button
		callNormalButton = new JButton("Chamar normal");
		eastButtonPanel.add(callNormalButton, BorderLayout.NORTH);

		// Add the east-aligned buttons to the main button panel
		buttonPanel.add(eastButtonPanel, BorderLayout.EAST);

		// Panel for center-aligned buttons
		JPanel centerButtonPanel = new JPanel(new GridLayout(3, 1, 3, 2));

		// Call Next Button
		callNextButton = new JButton("Chamar próxima senha");
		centerButtonPanel.add(callNextButton);

		// Call Before Button
		callBeforeButton = new JButton("Chamar senha anterior");
		centerButtonPanel.add(callBeforeButton);

		// Repeat Call Button
		repeatCallButton = new JButton("Repetir última senha");
		centerButtonPanel.add(repeatCallButton);

		// Add the center-aligned buttons to the main button panel
		buttonPanel.add(centerButtonPanel, BorderLayout.CENTER);
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
		

		// Action Listeners
		callPreferentialButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gds.chamarApenasPreferencial();
				updateLastCalledTicket();
			}
		});

		callNextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gds.chamarProximo();
				updateLastCalledTicket();
			}
		});

		callBeforeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gds.voltarSenhasChamadas();
				updateLastCalledTicket();
				wasTheCallBackButtonPressed = true;
			}
		});

		repeatCallButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gds.repetirUltimaSenha();
				updateLastCalledTicket();
			}
		});

		callNormalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gds.chamarApenasComum();
				updateLastCalledTicket();
			}
		});
	}

	// Method to update the last called ticket label
	private void updateLastCalledTicket() {
	    Senhas lastCalledTicket = gds.getSenhasChamadas().isEmpty() ? null : gds.getSenhasChamadas().peek();
	    if (lastCalledTicket != null) {
	        lastCalledTicketsListModel.add(0, lastCalledTicket.toString());
	    } else if (gds.getSenhasChamadas().isEmpty() && wasTheCallBackButtonPressed == true){
	        JOptionPane.showMessageDialog(this, "Sem senhas para chamar", "No Tickets", JOptionPane.INFORMATION_MESSAGE);
	    }
	    repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TicketUI();
			}
		});
	}
}
