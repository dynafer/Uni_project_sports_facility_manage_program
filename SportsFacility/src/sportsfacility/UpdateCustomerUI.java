/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsfacility;

import javax.swing.table.DefaultTableModel;


/**
 *
 * @author kimh22
 */
public class UpdateCustomerUI extends javax.swing.JFrame {

    /**
     * Creates new form UpdateCustomerUI
     */
    int customerID;
    AdvisorController aControl;
    AdvisorController bControl;
    public UpdateCustomerUI(int cusId) {
        customerID = cusId;
        aControl = new AdvisorController(customerID, false);
        bControl = new AdvisorController(customerID, true);
        displayCustomerDetail();
        CustomerID_Label.setText(Integer.toString(customerID));
        Customer customer = (Customer) aControl.custList.get(0);
        nameText.setText(customer.getName());
        nameText.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                String specialCharacters=" !#$%&'()*+,-./:;<=>?@[]^_`{|}~";
                char c = evt.getKeyChar();
                String cc = ""+evt.getKeyChar()+"";
                if(Character.isDigit(c) || specialCharacters.contains(cc.subSequence(0, 1))) {
                    getToolkit().beep();
                    evt.consume();
                }
            }
        });
        String itemWillShow = aControl.isUpdatable(customerID, true);
        if(null != itemWillShow) switch (itemWillShow) {
            case "Silver":
                MembershipOption.removeItem("Gold");
                MembershipOption.removeItem("Platinum");
                break;
            case "Gold":
                MembershipOption.removeItem("Platinum");
                break;
            case "Platinum,NotGold":
                MembershipOption.removeItem("Gold");
                break;
            case "Platinum,Gold":
                break;
            default:
                MembershipOption.removeItem("Gold");
                MembershipOption.removeItem("Platinum");
                break;
        }
        String selectItem = aControl.isUpdatable(customerID, true);
        if(null != selectItem) switch (selectItem) {
            case "Silver":
                break;
            case "Gold":
                MembershipOption.setSelectedItem("Gold");
                break;
            case "Platinum,NotGold":
                MembershipOption.setSelectedItem("Platinum");
                break;
            case "Platinum,Gold":
                MembershipOption.setSelectedItem("Platinum");
                break;
            default:
                break;
        }
        JoinedDate_Label.setText(String.valueOf(customer.getJoinedDate().toLocaleString().split(" ")[0]));
        payAnnualBool_Label.setText(customer.getIspaidannual());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void displayCustomerDetail() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        custID_Label = new javax.swing.JLabel();
        custName_Label = new javax.swing.JLabel();
        custMembership_Label = new javax.swing.JLabel();
        custJoinedDate_Label = new javax.swing.JLabel();
        CustomerID_Label = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        MembershipOption = new javax.swing.JComboBox<>();
        JoinedDate_Label = new javax.swing.JLabel();
        DeleteButton = new javax.swing.JButton();
        UpdateButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookingList = new javax.swing.JTable();
        numberofBooking_Label = new javax.swing.JLabel();
        numberBooking_Label = new javax.swing.JLabel();
        payAnnual_Label = new javax.swing.JLabel();
        payAnnualBool_Label = new javax.swing.JLabel();

        setTitle("Update detail");
        setResizable(false);
        setState(1);
        setType(java.awt.Window.Type.POPUP);

        bookingList.setCellSelectionEnabled(true);
        bookingList.setColumnSelectionAllowed(false);
        bookingList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bookingList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Booking Type", "Facility ID", "Class ID", "Booked Date", "Schedule"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        DefaultTableModel dtm = (DefaultTableModel)bookingList.getModel();
        for(int i = 0; i < bControl.bookList.size(); i++) {
          Booking temps = (Booking)bControl.bookList.get(i);
          String[] dump_data = { Integer.toString(temps.getBookingid()), temps.getBookingtype(), temps.getFacilityid(), temps.getClassid(), temps.getBookeddate().toLocaleString().split(" ")[0], temps.getSchedule().toLocaleString().split(" ")[0] };
          dtm.addRow(dump_data);
          bookingList.setValueAt(temps.getBookingid(), i, 0);
          bookingList.setValueAt(temps.getBookingtype(), i, 1);
          bookingList.setValueAt(temps.getFacilityid(), i, 2);
          bookingList.setValueAt(temps.getClassid(), i, 3);
          bookingList.setValueAt(temps.getBookeddate().toLocaleString().split(" ")[0], i, 4);
          bookingList.setValueAt(temps.getSchedule().toLocaleString().split(" ")[0], i, 5);
        }
        jScrollPane1.setViewportView(bookingList);
        bookingList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (bookingList.getColumnModel().getColumnCount() > 0) {
            bookingList.getColumnModel().getColumn(0).setResizable(false);
            bookingList.getColumnModel().getColumn(1).setResizable(false);
            bookingList.getColumnModel().getColumn(2).setResizable(false);
            bookingList.getColumnModel().getColumn(3).setResizable(false);
            bookingList.getColumnModel().getColumn(4).setResizable(false);
            bookingList.getColumnModel().getColumn(5).setResizable(false);
        }
        
        custID_Label.setText("Customer ID");

        custName_Label.setText("Name");

        custMembership_Label.setText("Membership");

        custJoinedDate_Label.setText("Joined Date");
        
        payAnnual_Label.setText("Pay Annual");

        payAnnualBool_Label.setText("Boolean");

        CustomerID_Label.setText("ID");

        MembershipOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silver", "Gold", "Platinum" }));

        JoinedDate_Label.setText("Date");
        JoinedDate_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        numberofBooking_Label.setText("Number of Booking");
        numberBooking_Label.setText(Integer.toString(bControl.getCurrentYearBookings())+" / "+Integer.toString(bControl.bookList.size()));

        DeleteButton.setText("Delete");
        
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCustomer();
            }
        });

        UpdateButton.setText("Update");
        
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCustomer();
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelUpdate();
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(payAnnual_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(502, 502, 502))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DeleteButton)
                                .addGap(196, 196, 196)
                                .addComponent(UpdateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CancelButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(custName_Label)
                                            .addComponent(custID_Label)
                                            .addComponent(numberofBooking_Label))
                                        .addGap(41, 41, 41)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(numberBooking_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(MembershipOption, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CustomerID_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(JoinedDate_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(payAnnualBool_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(custMembership_Label)
                                    .addComponent(custJoinedDate_Label))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(0, 9, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(custID_Label)
                    .addComponent(CustomerID_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(custName_Label)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(custMembership_Label)
                    .addComponent(MembershipOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(custJoinedDate_Label)
                    .addComponent(JoinedDate_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payAnnual_Label)
                    .addComponent(payAnnualBool_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numberofBooking_Label)
                    .addComponent(numberBooking_Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelButton)
                    .addComponent(DeleteButton)
                    .addComponent(UpdateButton))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }

    private void updateCustomer() {
        int mbship = MembershipOption.getSelectedIndex();
        if("Platinum".equals(MembershipOption.getItemAt(MembershipOption.getSelectedIndex()))) {
            mbship = 2;
        }
        boolean update = aControl.updateCustomerInfo(customerID, nameText.getText(), mbship);
        if(update == false) {
            System.out.println("An error occured during updating the customer information.");
        } else {
            this.setVisible(false);
            this.dispose();
        }
    }
    
    private void deleteCustomer() {
        boolean deleteResult = aControl.deleteCustomer(customerID);
        if(deleteResult == true) {
            this.setVisible(false);
            this.dispose();
        } else {
            System.out.println("An error occured during deleting the customer information.");
        }
    }

    private void cancelUpdate() {                                             
        this.setVisible(false);
        this.dispose();
    }                                            

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateCustomerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new ChangeInfo1(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton CancelButton;
    private javax.swing.JLabel CustomerID_Label;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JLabel JoinedDate_Label;
    private javax.swing.JComboBox<String> MembershipOption;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JLabel custID_Label;
    private javax.swing.JLabel custJoinedDate_Label;
    private javax.swing.JLabel custMembership_Label;
    private javax.swing.JLabel custName_Label;
    private javax.swing.JLabel numberBooking_Label;
    private javax.swing.JLabel numberofBooking_Label;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable bookingList;
    private javax.swing.JLabel payAnnualBool_Label;
    private javax.swing.JLabel payAnnual_Label;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration                   
}