package GUI;

import Logs.OSLog;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

public class GUI extends JFrame {  
    
    private JPanel logPanel, deviceLogPanel, dateSearchPanel, stringSearchPanel;
    private JTextArea logInfoTextArea;
    private ArrayList<OSLog> logList;
    private final JLabel titleLabel;
    private JTabbedPane mainPanel;
    private JButton osLogButton, applicationLogButton, securityLogButton, searchStringButton;
    private JTextField searchStringTextArea;
    
    public GUI() {
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        titleLabel = new JLabel("Security Log Monitoring Application");
        add(titleLabel, BorderLayout.NORTH);
        add(getLogPanel(), BorderLayout.CENTER);
        add(getMainPanel(), BorderLayout.SOUTH);
        pack();    
    } 
    
    public JTabbedPane getMainPanel() {
        
        mainPanel = new JTabbedPane( JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT );
        
        mainPanel.addTab( "Security/Device Type", getDeviceLogPanel() );
        mainPanel.addTab( "Date Range", getDateSearchPanel() );
        mainPanel.addTab( "String Search", getStringSearchPanel() );
        
        return mainPanel;
    }
    
    public JPanel getLogPanel() {
         
        logPanel = new JPanel();        
        
        logInfoTextArea = new JTextArea(15, 30);      
        JScrollPane scrollPane = new JScrollPane(logInfoTextArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        logInfoTextArea.setLineWrap(true);
        logInfoTextArea.setWrapStyleWord(true);
        logInfoTextArea.setEditable(false);
        logPanel.add(scrollPane);
    
        
        logList = new ArrayList<OSLog>();
        OSLog log = new OSLog();
        log.setLog(1);
        logList = log.getLogList(1);
     
        for (int i = 0; i < logList.size(); i++) {
            logInfoTextArea.append(logList.get(i).getDeviceName());
            logInfoTextArea.append(" - ");
            logInfoTextArea.append(logList.get(i).getOriginalLog());
            logInfoTextArea.append("\n");     
        }

        return logPanel;
    }
    
    public JPanel getDeviceLogPanel() {
        
        deviceLogPanel = new JPanel();
        
        osLogButton = new JButton("OS Logs");
        applicationLogButton = new JButton("Application Logs");
        securityLogButton = new JButton("Security Logs");
        
        deviceLogPanel.add(osLogButton);
        deviceLogPanel.add(applicationLogButton);
        deviceLogPanel.add(securityLogButton);        
        
        return deviceLogPanel;
    }
     
    public JPanel getDateSearchPanel() {
         
        dateSearchPanel = new JPanel();
        
        UtilDateModel model = new UtilDateModel();
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        String formattedDate = formatter.format(LocalDate.now());
        
        String [] dateParts = formattedDate.split("-");
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];
        
        
        System.out.println(formattedDate);
        System.out.println("day = " + day);
        System.out.println("month = " + month);
        System.out.println("Year = " + year);
        
        int currentDay = Integer.valueOf(day);
        int currentMonth = Integer.valueOf(month);
        int currentYear = Integer.valueOf(year);
        
        //model.setDate( currentDay, currentMonth, currentYear );

        Properties p = new Properties();
        p.put("text.today", "Today");
        //p.put("text.today", currentDay);
        p.put("text.month", "Month");
        //p.put("text.month", currentMonth);
        p.put("text.year", "Year");
        //p.put("text.year", currentYear);
 
        JDatePanelImpl datePanel = new JDatePanelImpl( model, p );

        //JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        //JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        dateSearchPanel.add(datePanel);
    
    
        
        return dateSearchPanel;
    }
    
    public JPanel getStringSearchPanel() {
        
        stringSearchPanel = new JPanel();
        
        searchStringTextArea = new JTextField("Enter search string", 15);
        searchStringButton = new JButton("GO");
        
        stringSearchPanel.add(searchStringTextArea);
        stringSearchPanel.add(searchStringButton);        
        
        return stringSearchPanel;
    }
    
    public static void main(String[] args) {

        new GUI().setVisible(true);
    }  

    
}
