package GUI;

import Search.Search;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class GUI extends JFrame {  
    
    private JPanel logPanel, deviceLogPanel, alertsPanel;
    private JTextArea logInfoTextArea;
    private final JLabel titleLabel;
    private JTabbedPane mainPanel;
    
    public GUI() {
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        titleLabel = new JLabel("Security Log Monitoring Application");
        add(titleLabel, BorderLayout.NORTH);
        add(getLogPanel(), BorderLayout.CENTER);
        add(getControlPanel(), BorderLayout.SOUTH);
        pack();    
    } 
      
    public JTabbedPane getControlPanel() {
        
        mainPanel = new JTabbedPane( JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT );
        
        mainPanel.addTab( "Logs", getDeviceLogPanel() );
        mainPanel.addTab( "Alerts", getAlertsPanel() );
        
        return mainPanel;
    }
    
    public JPanel getLogPanel() {
         
        logPanel = new JPanel();  
        List<String> returnedLogs = new ArrayList<String>();
        
        logInfoTextArea = new JTextArea(15, 30);      
        JScrollPane scrollPane = new JScrollPane(logInfoTextArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        logInfoTextArea.setLineWrap(true);
        logInfoTextArea.setWrapStyleWord(true);
        logInfoTextArea.setEditable(false);
        logPanel.add(scrollPane);
        
        Search logs = new Search( "PC1" );
        
        returnedLogs = logs.getDeviceLogs("PC1");

        for (int i = 0; i < returnedLogs.size(); i++) {
            logInfoTextArea.append(returnedLogs.get(i));
            logInfoTextArea.append("\n");  
            logInfoTextArea.append("\n");
        }

        return logPanel;
    }
    
    public JPanel getDeviceLogPanel() {
        
        deviceLogPanel = new JPanel();
        
        JRadioButton pc1Button = new JRadioButton("PC 1");
        pc1Button.setSelected(true);
        
        JRadioButton apacheWebserverButton = new JRadioButton("Apache Webserver");
        apacheWebserverButton.setEnabled(false);
        
        ButtonGroup deviceButtonGroup = new ButtonGroup();
        deviceButtonGroup.add(pc1Button);
        deviceButtonGroup.add(apacheWebserverButton);
        
        deviceLogPanel.add(pc1Button);
        deviceLogPanel.add(apacheWebserverButton);
        
        
        return deviceLogPanel;
    }
    
    public JPanel getAlertsPanel() {
        
        alertsPanel = new JPanel();
        
        return alertsPanel;
    }
        
    public static void main(String[] args) {

        new GUI().setVisible(true);
    }  
   
}
