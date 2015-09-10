/*
 Elanna Grossman
 Financial Application: compare loans with various interest rates
 Creates a GUI with event handling to process input for calculating loan rates
 */ 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.NumberFormat;

public class ComputeInterest extends JFrame
{
   private JTextField jtfLoanAmount = new JTextField(8);
   private JTextField jftNumberOfYears = new JTextField(4);
   private JTextArea jtaPrintInt = new JTextArea();
   private JButton jbtTable = new JButton("Show Table");
   
   public static void main(String[] args)
   {
      ComputeInterest frame = new ComputeInterest();
      frame.pack();
      frame.setLocationRelativeTo(null); // Center the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("ComputeInterestTest");
      frame.setVisible(true);
   }//end main
   
   public ComputeInterest()
   {
      //Panel p1
      JPanel p1 = new JPanel(new FlowLayout());
      p1.add(new JLabel("Loan Amount"));
      p1.add(jtfLoanAmount);
      p1.add(new JLabel("Number of Years"));
      p1.add(jftNumberOfYears);
      p1.add(jbtTable);
      
      //scroll pane
      JScrollPane scrollPane = new JScrollPane(jtaPrintInt = new JTextArea());
      scrollPane.setPreferredSize(new Dimension(0, 200));
      jtaPrintInt.setWrapStyleWord(true);
      jtaPrintInt.setLineWrap(true);
      
      //Panel p2
      JPanel p2 = new JPanel(new BorderLayout());
      p2.add(scrollPane, BorderLayout.CENTER);
      
      JPanel p = new JPanel(new BorderLayout());
      p.add(p1, BorderLayout.CENTER);
      p.add(p2, BorderLayout.SOUTH);
      
      //Add p to the frame
      add(p);
      
      //register listener
      if (jtfLoanAmount.getText() != null && jftNumberOfYears != null)
      {
         jbtTable.addActionListener(new Listener());
      }//end if
   }//end ComputeInterest
   
   class Listener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         //variable declarations
         double loanAmount = new Double(jtfLoanAmount.getText().trim()).doubleValue();
         int numberOfYears = new Integer(jftNumberOfYears.getText().trim()).intValue();
         double monthlyPayment;
         double totalPayment;
         
         //Create NumberFormat object
         NumberFormat percent = NumberFormat.getPercentInstance();
         percent.setMinimumFractionDigits(3);
         
         //print header
         jtaPrintInt.append("Interest Rate\tMonthly Payment\tTotal Payment\n");
         
         for (double i = 5.0; i < 8.01; i = i + .125)
         {
            // Calculate payment
            monthlyPayment = loanAmount * (i  / 1200) / (1 - 1 / Math.pow(1 + (i  / 1200), numberOfYears * 12));
            totalPayment = monthlyPayment * numberOfYears * 12;
            double perDisp = i / 100; //inelegant way to display to results correctly while also calculating correctly
            // Print results
            jtaPrintInt.append(percent.format(perDisp) + "\t" + (int)(monthlyPayment * 100) / 100.0 + "\t\t" + (int)(totalPayment * 100) / 100.0 + "\n");
         }//end for
      }// end actionPerformed
   }//Listener
}