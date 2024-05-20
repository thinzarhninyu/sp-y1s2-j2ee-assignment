// THINZAR HNIN YU
// 2201014
// DIT/FT/1B/03
package CA2;

/**
 *
 * @author User
 */
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.JList;
import javax.swing.ComboBoxModel;

public class RentalSystemGUI extends javax.swing.JFrame {

    private RentalSystem rs = new RentalSystem();
    private RentalReadWriteFromFile rw = new RentalReadWriteFromFile();
    private int comicIndex = 0;
    private int renteeIndex = 0;
    private int renteeComicIndex = 0;
    private DefaultTableModel model;
    private DefaultTableModel ratingModel;
    private DefaultTableCellRenderer cellRenderer;
    private Comic[] comicArray = rs.getComics();
    private Rentee[] renteeArray = rs.getRentees();
    private double[] ratingArray = rs.getRatings();
    private JList list;
    private ComboBoxModel dropdown;

    /**
     * Creates new form RentalSystemGUI
     */
    public RentalSystemGUI() {
        initComponents();

        // getting table models and setting column width/names
        ratingModel = (DefaultTableModel) tblRating.getModel();
        tblRating.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblRating.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblRating.getColumnModel().getColumn(2).setPreferredWidth(30);
        model = (DefaultTableModel) tblListOfLoans.getModel();
        String[] columnNames = {"ISBN-13", "Title", "Rental Per Day", "Deposit"};
        model.setColumnIdentifiers(columnNames);

        tblListOfLoans.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblListOfLoans.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblListOfLoans.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblListOfLoans.getColumnModel().getColumn(3).setPreferredWidth(20);

        // display comics
        displayComic(comicIndex);

        // display rentees
        displayRentee(renteeIndex);

        // display ratings
        displayRating();
    }

    // display comics
    private void displayComic(int comicIndex) {
        // provide comic details
        jPanel1.setBorder(BorderFactory.createTitledBorder("Comic " + (comicIndex + 1) + " of " + comicArray.length));
        txtIsbn.setText(comicArray[comicIndex].getIsbn());
        txtTitle.setText(comicArray[comicIndex].getTitle());
        txtRentalCost.setText(String.format("%.2f", comicArray[comicIndex].calculateDayPrice()));
        txtDeposit.setText(String.format("%.2f", comicArray[comicIndex].calculateDepositFee()));
        txtAdditionalInfo.setText(comicArray[comicIndex].getLanguage());
    }

    // display rentees
    private void displayRentee(int renteeIndex) {
        // provide rentee details
        jPanel2.setBorder(BorderFactory.createTitledBorder("Rentee " + (renteeIndex + 1) + " of " + renteeArray.length));
        txtMemberID.setText(renteeArray[renteeIndex].getMemberID());
        txtName.setText(renteeArray[renteeIndex].getName());
        // set row back to zero as rentee changes
        model.setRowCount(0);
        for (int i = 0; i < renteeArray[renteeIndex].getComics().length; i++) {
            // add rentee's comics to table
            for (int k = 0; k < comicArray.length; k++) {
                if ((renteeArray[renteeIndex].getComics()[i]).equals(comicArray[k].getIsbn())) {
                    renteeComicIndex = k;
                }
            }
            model.addRow(new Object[0]);
            model.setValueAt(comicArray[renteeComicIndex].getIsbn(), i, 0);
            model.setValueAt(comicArray[renteeComicIndex].getTitle(), i, 1);
            model.setValueAt("$" + String.format("%.2f", comicArray[renteeComicIndex].calculateDayPrice()), i, 2);
            model.setValueAt("$" + String.format("%.2f", comicArray[renteeComicIndex].calculateDepositFee()), i, 3);
        }
        model.fireTableDataChanged();
    }

    // display ratings
    public void displayRating() {
        // set row to zero
        ratingModel.setRowCount(0);
        // add ratings to table
        for (int i = 0; i < ratingArray.length; i++) {
            ratingModel.addRow(new Object[0]);
            ratingModel.setValueAt(comicArray[i].getTitle(), i, 0);
            ratingModel.setValueAt("$" + String.format("%.2f", comicArray[i].calculateDayPrice()), i, 1);
            if (ratingArray[i] == 0) {
                ratingModel.setValueAt("Not Rated", i, 2);
            } else {
                ratingModel.setValueAt(Double.toString(ratingArray[i]), i, 2);
            }
        }
        ratingModel.fireTableDataChanged();
    }

    // get comics for comic rating drop down
    private ComboBoxModel getComicTitles() {
        Comic[] comics = rs.getComics();
        String[] comicTitles = new String[comics.length];
        for (int i = 0; i < comicTitles.length; i++) {
            comicTitles[i] = comics[i].getTitle();
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(comicTitles);
        JComboBox cbComicTitle = new JComboBox();
        cbComicTitle.setModel(model);
        return model;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIsbn = new javax.swing.JTextField();
        txtTitle = new javax.swing.JTextField();
        txtRentalCost = new javax.swing.JTextField();
        txtDeposit = new javax.swing.JTextField();
        txtComicAdditionalInfo = new javax.swing.JScrollPane();
        txtAdditionalInfo = new javax.swing.JTextArea();
        btnPreviousComic = new javax.swing.JButton();
        btnNextComic = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnGetEarningStats = new javax.swing.JButton();
        btnClearMessage = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        btnPrintEarningStats = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMemberID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        btnPreviousMember = new javax.swing.JButton();
        btnNextMember = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListOfLoans = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        rbComic = new javax.swing.JRadioButton();
        rbMember = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        txtSearchInput = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnSaveExit = new javax.swing.JButton();
        btnLogIn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        cbComicTitle = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        sliderRating = new javax.swing.JSlider();
        btnRate = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRating = new javax.swing.JTable();
        btnRecommend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("ISBN-13:");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Title:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Daily Rental Cost:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Additional Information:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Deposit:");

        txtIsbn.setFocusable(false);

        txtTitle.setFocusable(false);

        txtRentalCost.setFocusable(false);

        txtDeposit.setFocusable(false);

        txtAdditionalInfo.setColumns(20);
        txtAdditionalInfo.setRows(5);
        txtAdditionalInfo.setFocusable(false);
        txtComicAdditionalInfo.setViewportView(txtAdditionalInfo);

        btnPreviousComic.setText("Previous");
        btnPreviousComic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousComicActionPerformed(evt);
            }
        });

        btnNextComic.setText("Next");
        btnNextComic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextComicActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPreviousComic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNextComic))
                    .addComponent(txtComicAdditionalInfo)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtIsbn, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                .addComponent(txtTitle)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtRentalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRentalCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtComicAdditionalInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPreviousComic)
                    .addComponent(btnNextComic))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "System Message", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnGetEarningStats.setBackground(new java.awt.Color(0, 51, 153));
        btnGetEarningStats.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGetEarningStats.setText("Get Earning Stats");
        btnGetEarningStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetEarningStatsActionPerformed(evt);
            }
        });

        btnClearMessage.setBackground(new java.awt.Color(0, 51, 153));
        btnClearMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClearMessage.setText("Clear Message");
        btnClearMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearMessageActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        txtMessage.setFocusable(false);
        jScrollPane2.setViewportView(txtMessage);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnPrintEarningStats.setBackground(new java.awt.Color(0, 51, 153));
        btnPrintEarningStats.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrintEarningStats.setText("Print Earning Stats");
        btnPrintEarningStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintEarningStatsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnGetEarningStats)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrintEarningStats)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClearMessage))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGetEarningStats)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClearMessage)
                        .addComponent(btnPrintEarningStats)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Member-ID:");
        jLabel5.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Name:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("List of Loans:");

        txtMemberID.setFocusable(false);

        txtName.setFocusable(false);

        btnPreviousMember.setText("Previous");
        btnPreviousMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousMemberActionPerformed(evt);
            }
        });

        btnNextMember.setText("Next");
        btnNextMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMemberActionPerformed(evt);
            }
        });

        tblListOfLoans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ISBN-13", "Title", "Rental Per Day", "Deposit Fee"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblListOfLoans);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMemberID, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addComponent(txtName)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnPreviousMember)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNextMember))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMemberID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPreviousMember)
                    .addComponent(btnNextMember))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Comic Rental System");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        buttonGroup1.add(rbComic);
        rbComic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbComic.setText("Comic by ISBN-13");

        buttonGroup1.add(rbMember);
        rbMember.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbMember.setText("Rentee by MemberID:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Search:");

        btnSearch.setBackground(new java.awt.Color(19, 103, 0));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtSearchInput, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rbComic)
                        .addGap(177, 177, 177)
                        .addComponent(rbMember)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbComic)
                    .addComponent(rbMember))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSaveExit.setBackground(new java.awt.Color(204, 0, 51));
        btnSaveExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSaveExit.setText("Save & Exit");
        btnSaveExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveExitActionPerformed(evt);
            }
        });

        btnLogIn.setBackground(new java.awt.Color(204, 0, 51));
        btnLogIn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogIn.setText("Log In as Admin");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rate", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        cbComicTitle.setModel(getComicTitles());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Comic Title:");

        sliderRating.setMaximum(10);
        sliderRating.setValue(5);

        btnRate.setText("Submit");
        btnRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(sliderRating, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbComicTitle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(btnRate)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbComicTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sliderRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ratings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblRating.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Price/Day", "Rating"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblRating);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        btnRecommend.setBackground(new java.awt.Color(0, 204, 204));
        btnRecommend.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRecommend.setForeground(new java.awt.Color(51, 0, 153));
        btnRecommend.setText("Get Comic Recommendation");
        btnRecommend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecommendActionPerformed(evt);
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRecommend)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLogIn)
                                .addGap(31, 31, 31)
                                .addComponent(btnSaveExit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(588, 588, 588)
                .addComponent(jLabel9)
                .addGap(122, 122, 122))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRecommend)
                            .addComponent(btnLogIn)
                            .addComponent(btnSaveExit))
                        .addGap(59, 59, 59))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        // if rentee search
        if (rbMember.isSelected()) {
            // display rentee and change border text
            renteeIndex = rs.searchMember(txtSearchInput.getText());
            if (renteeIndex != -1) {
                displayRentee(renteeIndex);
                txtMessage.setText("Found and displayed rentee with the Member ID: " + txtSearchInput.getText());
            } else {
                txtMessage.setText("No Such Member ID \"" + txtSearchInput.getText() + "\" in the data!");
            }
        } // if comic search
        else {
            // display comic and change border text
            comicIndex = rs.searchComic(txtSearchInput.getText());
            if (comicIndex != -1) {
                displayComic(comicIndex);

                txtMessage.setText("Found and displayed item with the ISBN-13: " + txtSearchInput.getText());
            } else {
                txtMessage.setText("No Such ISBN-13 \"" + txtSearchInput.getText() + "\" in the data!");
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    // if next button is clicked
    private void btnNextComicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextComicActionPerformed
        // TODO add your handling code here:
        // if comicIndex is less than comicArray.length-1, increment comicIndex by 1
        if (comicIndex < comicArray.length - 1) {
            comicIndex++;
        } // else, reset comicIndex to 0 to circulate
        else {
            comicIndex = 0;
        }
        // display comics
        displayComic(comicIndex);
    }//GEN-LAST:event_btnNextComicActionPerformed

    private void btnGetEarningStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetEarningStatsActionPerformed
        // TODO add your handling code here:
        // get earning statistics
        txtMessage.setText(rs.printStatistics());
    }//GEN-LAST:event_btnGetEarningStatsActionPerformed

    private void btnClearMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearMessageActionPerformed
        // TODO add your handling code here:
        // clear message
        txtMessage.setText("");
    }//GEN-LAST:event_btnClearMessageActionPerformed

    private void btnPrintEarningStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintEarningStatsActionPerformed
        // TODO add your handling code here:
        // print earning statistics
        String message = rs.printStatistics();
        rs.printStatistics(message);
    }//GEN-LAST:event_btnPrintEarningStatsActionPerformed

    private void btnPreviousComicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousComicActionPerformed
        // TODO add your handling code here:
        // if comicIndex is greater than zero, decrement comicIndex by 1
        if (comicIndex > 0) {
            comicIndex--;
        } // else reset comicIndex to comicArray.length-1 to circulate
        else {
            comicIndex = comicArray.length - 1;
        }
        // display comics
        displayComic(comicIndex);
    }//GEN-LAST:event_btnPreviousComicActionPerformed

    private void btnNextMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMemberActionPerformed
        // TODO add your handling code here:
        // if renteeIndex is less than renteeArray.length-1, increment renteeIndex by 1
        if (renteeIndex < renteeArray.length - 1) {
            renteeIndex++;
        } // else, reset renteeIndex to 0 to circulate
        else {
            renteeIndex = 0;
        }
        // display rentee
        displayRentee(renteeIndex);
    }//GEN-LAST:event_btnNextMemberActionPerformed

    private void btnPreviousMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousMemberActionPerformed
        // TODO add your handling code here:
        // if renteeIndex is greater than zero, decrement renteeIndex by 1
        if (renteeIndex > 0) {
            renteeIndex--;
        } // else, reset renteeIndex to renteeArray.length-1 to circulate
        else {
            renteeIndex = renteeArray.length - 1;
        }
        // display rentee
        displayRentee(renteeIndex);
    }//GEN-LAST:event_btnPreviousMemberActionPerformed

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        // TODO add your handling code here:
        // go to AdminLogInGUI
        AdminLogInGUI asg = new AdminLogInGUI();
        asg.setVisible(true);
        // close RentalSystemGUI
        this.dispose();
    }//GEN-LAST:event_btnLogInActionPerformed

    private void btnSaveExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveExitActionPerformed
        // TODO add your handling code here:
        // save rentees to rentee.dat
        rw.saveRentee(renteeArray);
        // display thank you message
        rs.thankYou();
        // close RentalSystemGUI
        this.dispose();
    }//GEN-LAST:event_btnSaveExitActionPerformed

    private void btnRecommendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecommendActionPerformed
        // TODO add your handling code here:
        // get comic recommendation
        String recommended = rs.recommend();
        // get comic recommended
        for (int i = 0; i < comicArray.length; i++) {
            if (recommended.equals(comicArray[i].getIsbn())) {
                comicIndex = i;
            }
        }
        // display comic in table
        displayComic(comicIndex);
        txtMessage.setText("Recommended title with the title: " + comicArray[comicIndex].getTitle());
    }//GEN-LAST:event_btnRecommendActionPerformed

    private void btnRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRateActionPerformed
        // TODO add your handling code here:
        // rate comic
        txtMessage.setText(rs.rate(cbComicTitle.getSelectedItem().toString(), sliderRating.getValue()));
        // display rating
        displayRating();
    }//GEN-LAST:event_btnRateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        RentalSystem rs = new RentalSystem();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RentalSystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RentalSystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RentalSystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RentalSystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RentalSystemGUI().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearMessage;
    private javax.swing.JButton btnGetEarningStats;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JButton btnNextComic;
    private javax.swing.JButton btnNextMember;
    private javax.swing.JButton btnPreviousComic;
    private javax.swing.JButton btnPreviousMember;
    private javax.swing.JButton btnPrintEarningStats;
    private javax.swing.JButton btnRate;
    private javax.swing.JButton btnRecommend;
    private javax.swing.JButton btnSaveExit;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbComicTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rbComic;
    private javax.swing.JRadioButton rbMember;
    private javax.swing.JSlider sliderRating;
    private javax.swing.JTable tblListOfLoans;
    private javax.swing.JTable tblRating;
    private javax.swing.JTextArea txtAdditionalInfo;
    private javax.swing.JScrollPane txtComicAdditionalInfo;
    private javax.swing.JTextField txtDeposit;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtMemberID;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtRentalCost;
    private javax.swing.JTextField txtSearchInput;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
