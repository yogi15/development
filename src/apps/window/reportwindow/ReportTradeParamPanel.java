package apps.window.reportwindow;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTextField;


public class ReportTradeParamPanel extends JPanel {
	public com.standbysoft.component.date.swing.JDatePicker jDatePicker1 =new com.standbysoft.component.date.swing.JDatePicker();;
    public com.standbysoft.component.date.swing.JDatePicker jDatePicker2 =new com.standbysoft.component.date.swing.JDatePicker();;
    public com.standbysoft.component.date.swing.JDatePicker jDatePicker3 =new com.standbysoft.component.date.swing.JDatePicker();;
    public com.standbysoft.component.date.swing.JDatePicker jDatePicker4 =new com.standbysoft.component.date.swing.JDatePicker();;
    public com.standbysoft.component.date.swing.JDatePicker jDatePicker5 =new com.standbysoft.component.date.swing.JDatePicker();;
    public com.standbysoft.component.date.swing.JDatePicker jDatePicker6 = new com.standbysoft.component.date.swing.JDatePicker();;
   // public JTextField jhidden = new JTextField();
    public String columns = "";
	public ReportTradeParamPanel() {
        initComponents();
    }
	  
	    private void initComponents() {
            
	        jPanel1 = new javax.swing.JPanel();
	       // jPanel1.add(jhidden);
	        
	        jPanel2 = new javax.swing.JPanel();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel3 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();
	        jTextField2 = new javax.swing.JTextField();
	        jLabel4 = new javax.swing.JLabel();
	        jTextField3 = new javax.swing.JTextField();
	        jTextField4 = new javax.swing.JTextField();
	        jLabel5 = new javax.swing.JLabel();
	        jTextField5 = new javax.swing.JTextField();
	        jTextField6 = new javax.swing.JTextField();
	        jPanel6 = new javax.swing.JPanel();
	        jLabel11 = new javax.swing.JLabel();
	        jComboBox1 = new javax.swing.JComboBox();
	        jLabel12 = new javax.swing.JLabel();
	        jComboBox2 = new javax.swing.JComboBox();
	        jLabel13 = new javax.swing.JLabel();
	        jComboBox3 = new javax.swing.JComboBox();
	        jLabel14 = new javax.swing.JLabel();
	        jComboBox4 = new javax.swing.JComboBox();
	        jPanel3 = new javax.swing.JPanel();
	        jLabel6 = new javax.swing.JLabel();
	        jButton1 = new javax.swing.JButton();
	        jTextField7 = new javax.swing.JTextField();
	        jLabel7 = new javax.swing.JLabel();
	        jButton2 = new javax.swing.JButton();
	        jTextField8 = new javax.swing.JTextField();
	        jLabel8 = new javax.swing.JLabel();
	        jButton3 = new javax.swing.JButton();
	        jTextField9 = new javax.swing.JTextField();
	        jLabel9 = new javax.swing.JLabel();
	        jButton4 = new javax.swing.JButton();
	        jLabel15 = new javax.swing.JLabel();
	        jButton5 = new javax.swing.JButton();
	        jTextField11 = new javax.swing.JTextField();

	        jLabel1.setText("START DATE ");

	        jLabel2.setText("END DATE");

	        jLabel3.setText("TRADE");

	        jTextField1.setText("  ");
	        

	        jLabel4.setText("MAITURTY");

	        jTextField3.setText("       ");

	        jTextField4.setText("       ");

	        jLabel5.setText("SETTLEMENT");

	        jTextField5.setText("       ");

	        jTextField6.setText("   ");
	        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	        jPanel2.setLayout(jPanel2Layout);
	        jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel5)
	                    .addComponent(jLabel3)
	                    .addComponent(jLabel4))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jLabel1)
	                    .addComponent(jDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
	                    .addComponent(jDatePicker3)
	                    .addComponent(jDatePicker5))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jLabel2)
	                    .addComponent(jDatePicker4, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
	                    .addComponent(jDatePicker6)
	                    .addComponent(jDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
	                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel3)
	                            .addComponent(jDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(jPanel2Layout.createSequentialGroup()
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                    .addComponent(jDatePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel4))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jDatePicker5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(jPanel2Layout.createSequentialGroup()
	                                .addGap(6, 6, 6)
	                                .addComponent(jDatePicker4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jDatePicker6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        jLabel11.setText("PRODUCT TYPE ");

	        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "", "", "" }));

	        jLabel12.setText("SUB TYPE ");

	    //    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  " ", "", "", "" }));

	        jLabel13.setText("PRODUCT ATTRI");

	        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  " ", "", "", "" }));

	        jLabel14.setText("BOOK ");

	        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  " ", "", "", "" }));
	        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
	        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
	        jPanel6.setLayout(jPanel6Layout);
	        jPanel6Layout.setHorizontalGroup(
	            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel6Layout.createSequentialGroup()
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel11)
	                    .addComponent(jLabel12)
	                    .addComponent(jLabel13)
	                    .addComponent(jLabel14))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 151, Short.MAX_VALUE)
	                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 151, Short.MAX_VALUE)
	                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, 0, 151, Short.MAX_VALUE)
	                    .addComponent(jComboBox4, javax.swing.GroupLayout.Alignment.TRAILING, 0, 151, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        jPanel6Layout.setVerticalGroup(
	            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel6Layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel11)
	                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel12)
	                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel13)
	                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel14))
	                .addContainerGap())
	        );

	        jLabel6.setText("CURR");

	        jButton1.setText("jButton1");

	        jTextField7.setText("  ");
	       

	        jLabel7.setText("TRADE ATTR");

	        jButton2.setText("jButton1");

	        jTextField8.setText("");
	        jTextField8.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jTextField8ActionPerformed(evt);
	            }
	        });

	        jLabel8.setText("Legal Entiye ");

	        jButton3.setText("jButton1");
	        jButton3.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton3ActionPerformed(evt);
	            }
	        });

	        jTextField9.setText("");
	        jTextField9.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jTextField9ActionPerformed(evt);
	            }
	        });

	        jLabel9.setText("TRADER ");

	        jButton4.setText("Select Criteria");
	        jButton4.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton4ActionPerformed(evt);
	            }
	        });

	        jLabel15.setText("BUY");

	        jButton5.setText("jButton1");
	        jButton5.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton5ActionPerformed(evt);
	            }
	        });

	        jTextField11.setText("");
	        jTextField11.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jTextField11ActionPerformed(evt);
	            }
	        });
	        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
	        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
	        jPanel3.setLayout(jPanel3Layout);
	        jPanel3Layout.setHorizontalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel3Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel6)
	                    .addComponent(jLabel7)
	                    .addComponent(jLabel8)
	                    .addComponent(jLabel9)
	                    .addComponent(jLabel15)
	                    .addComponent(jButton4))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
	                    .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
	                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
	                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        jPanel3Layout.setVerticalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel3Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel6)
	                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButton1))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel7)
	                    .addComponent(jButton2)
	                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel8)
	                    .addComponent(jButton3)
	                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel3Layout.createSequentialGroup()
	                        .addComponent(jLabel9)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(jLabel15)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(jButton4))
	                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jButton5))))
	        );
	        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
	                            .addGap(7, 7, 7)
	                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addGap(20, 20, 20))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(48, Short.MAX_VALUE))
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	    }// </editor-fold>

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
	}

	private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
	}

	private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
	}

	private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
	}

	private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
	}

	private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
	}

	    // Variables declaration - do not modify
	    public javax.swing.JButton jButton1;
	    public javax.swing.JButton jButton2;
	    public javax.swing.JButton jButton3;
	    public javax.swing.JButton jButton4;
	    public javax.swing.JButton jButton5;
	    public javax.swing.JComboBox jComboBox1;
	    public javax.swing.JComboBox jComboBox2;
	    public javax.swing.JComboBox jComboBox3;
	    public javax.swing.JComboBox jComboBox4;
	    public javax.swing.JLabel jLabel1;
	    public javax.swing.JLabel jLabel11;
	    public javax.swing.JLabel jLabel12;
	    public javax.swing.JLabel jLabel13;
	    public javax.swing.JLabel jLabel14;
	    public javax.swing.JLabel jLabel15;
	    public javax.swing.JLabel jLabel2;
	    public javax.swing.JLabel jLabel3;
	    public javax.swing.JLabel jLabel4;
	    public javax.swing.JLabel jLabel5;
	    public javax.swing.JLabel jLabel6;
	    public javax.swing.JLabel jLabel7;
	    public javax.swing.JLabel jLabel8;
	    public javax.swing.JLabel jLabel9;
	    public javax.swing.JPanel jPanel1;
	    public javax.swing.JPanel jPanel2;
	    public javax.swing.JPanel jPanel3;
	    public javax.swing.JPanel jPanel6;
	    public javax.swing.JTextField jTextField1;
	    public javax.swing.JTextField jTextField11;
	    public javax.swing.JTextField jTextField2;
	    public javax.swing.JTextField jTextField3;
	    public javax.swing.JTextField jTextField4;
	    public javax.swing.JTextField jTextField5;
	    public javax.swing.JTextField jTextField6;
	    public javax.swing.JTextField jTextField7;
	    public javax.swing.JTextField jTextField8;
	    public javax.swing.JTextField jTextField9;
	    // End of variables declaration
	    
	    
	    
	}
