/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.habravan.stampmanagementsystem.gui;

import com.habravan.stampmanagementsystem.Stamp;
import com.habravan.stampmanagementsystem.StampManagement;
import com.habravan.stampmanagementsystem.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 *
 * @author Dorina
 */
public final class UserDashboardGUI extends javax.swing.JFrame {

    // Declaring isAddingToWishlist as a class-level variable to be able to use after in the logic code.
    private final boolean isAddingToWishlist = false;
    private boolean isAddingToOwnership = false;

    StampManagement stampManagement = new StampManagement();
    // Access the static currentUser variable from class.
    private User currentUser = UserLoginGUI.currentUser;

    // JList for displaying ownership stamps (User Interface component)
    private final javax.swing.JList<String> ownershipListUI;  // This will display the list of stamp names

    private DefaultListModel<String> ownershipListModel;
    private DefaultListModel<String> wishlistModel;
    private ArrayList<Stamp> ownershipData = new ArrayList<>();
    private ArrayList<Stamp> wishlist = new ArrayList<>();

    private javax.swing.JList<String> wishlistListUI;

    private Stamp selectedStamp = null; // Declaration globally of selected stamp
  

    /**
     * Creates new form UserDashboardGUI
     */
    public UserDashboardGUI(User currentUser) {
        this.currentUser = currentUser;
        initComponents();
        loadStampsFromCSV();
        setupCategoryTabs();
        loadListsFromCSV();

        loadStampsFromCSV();
        loadListsAfterLogin();  // Load ownership and wishlist lists from CSV

       

        DefaultListModel<String> ownershipListModel = new DefaultListModel<>();
        ownershipListUI = new javax.swing.JList<>(ownershipListModel);
        ownershipListUI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setLayout(new BorderLayout()); // Set the layout to BorderLayout
        setResizable(true);  // Allow the window to resize

        JScrollPane wishlistScrollPane = new JScrollPane(whishListPanel);
        wishlistScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//wishlistScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ownershipListPanel.setPreferredSize(new Dimension(600, 400));
        whishListPanel.setPreferredSize(new Dimension(600, 400));
        ownershipListPanel.setLayout(new BoxLayout(ownershipListPanel, BoxLayout.Y_AXIS));
        whishListPanel.setLayout(new BoxLayout(whishListPanel, BoxLayout.Y_AXIS)); // Arrange components vertically

        commemorativeScrollPane.setViewportView(commemorativePanel);
        commemorativePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        definitiveScrollPane.setViewportView(definitivePanel);
        definitivePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        mintScrollPane.setViewportView(mintPanel);
        mintPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        usedScrollPane.setViewportView(usedPanel);
        usedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        commemorativeScrollPane.setViewportView(commemorativePanel);
        commemorativePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        definitiveScrollPane.setViewportView(definitivePanel);
        definitivePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        mintScrollPane.setViewportView(mintPanel);
        mintPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        usedScrollPane.setViewportView(usedPanel);
        usedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        commemorativePanel.setLayout(new GridLayout(0, 2, 20, 20));
        commemorativeScrollPane.setViewportView(commemorativePanel);
        commemorativeScrollPane.setPreferredSize(new Dimension(500, 300));
        commemorativeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        definitivePanel.setLayout(new GridLayout(0, 2, 20, 20));
        definitiveScrollPane.setViewportView(definitivePanel);
        definitiveScrollPane.setPreferredSize(new Dimension(500, 300));
        definitiveScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mintPanel.setLayout(new GridLayout(0, 2, 20, 20));
        mintScrollPane.setViewportView(mintPanel);
        mintScrollPane.setPreferredSize(new Dimension(500, 300));
        mintScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        usedPanel.setLayout(new GridLayout(0, 2, 20, 20));
        usedScrollPane.setViewportView(usedPanel);
        usedScrollPane.setPreferredSize(new Dimension(500, 300));
        usedScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        ownershipScrollPane.setViewportView(ownershipListPanel);
        ownershipListPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        whishlistScrollPane.setViewportView(whishListPanel);
        whishListPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        ownershipListPanel.setLayout(new GridLayout(0, 2, 20, 20));
        ownershipScrollPane.setViewportView(ownershipListPanel);
        ownershipScrollPane.setPreferredSize(new Dimension(400, 700));
        ownershipScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        ownershipScrollPane.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        whishListPanel.setLayout(new GridLayout(0, 2, 20, 20));
        whishlistScrollPane.setViewportView(whishListPanel);
        whishlistScrollPane.setPreferredSize(new Dimension(400, 700));
        whishlistScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        removeFromOwnershipList.setPreferredSize(new java.awt.Dimension(200, 40));
        removeFromOwnershipList.setMaximumSize(new java.awt.Dimension(200, 40));
        removeFromOwnershipList.setMinimumSize(new java.awt.Dimension(200, 40));

 


    }

    private void moveToWishlist(Stamp selectedStamp) {
        // Remove from ownership list
        ownershipData.remove(selectedStamp);

        // Add to wishlist
        wishlist.add(selectedStamp);

        // Save the updated lists to CSV
        saveListsToCSV();

        // Update the User Intrrface  to reflect the changes
        updateOwnershipUI();
        updateWishlistUI();
    }

    private void moveToOwnership(Stamp selectedStamp) {
        // Remove from wishlist
        wishlist.remove(selectedStamp);

        // Add to ownership list
        ownershipData.add(selectedStamp);

        // Save the updated lists to CSV
        saveListsToCSV();

        // Update the User Interface to reflect the changes
        updateOwnershipUI();
        updateWishlistUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        titleLabel = new javax.swing.JLabel();
        removeFromOwnershipList = new javax.swing.JButton();
        removeFromWishlist = new javax.swing.JButton();
        addStampToOwnershipButton = new javax.swing.JButton();
        editStampButton = new javax.swing.JButton();
        deleteStampButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        ownedStampList = new javax.swing.JLabel();
        stampWishlist = new javax.swing.JLabel();
        usedScrollPane = new javax.swing.JScrollPane();
        usedPanel = new javax.swing.JPanel();
        commemorativeScrollPane = new javax.swing.JScrollPane();
        commemorativePanel = new javax.swing.JPanel();
        definitiveScrollPane = new javax.swing.JScrollPane();
        definitivePanel = new javax.swing.JPanel();
        mintScrollPane = new javax.swing.JScrollPane();
        mintPanel = new javax.swing.JPanel();
        addStampToWishlistButton = new javax.swing.JButton();
        ownershipScrollPane = new javax.swing.JScrollPane();
        ownershipListPanel = new javax.swing.JPanel();
        whishlistScrollPane = new javax.swing.JScrollPane();
        whishListPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("                                                                              Welcome to your Stamp Management  System Account");

        removeFromOwnershipList.setText("Remove from Ownership List");
        removeFromOwnershipList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(181, 150, 87)));
        removeFromOwnershipList.setMaximumSize(new java.awt.Dimension(157, 25));
        removeFromOwnershipList.setMinimumSize(new java.awt.Dimension(127, 25));
        removeFromOwnershipList.setPreferredSize(new java.awt.Dimension(157, 25));
        removeFromOwnershipList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromOwnershipListActionPerformed(evt);
            }
        });

        removeFromWishlist.setText("Remove from Wishlist");
        removeFromWishlist.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(181, 150, 87)));
        removeFromWishlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFromWishlistActionPerformed(evt);
            }
        });

        addStampToOwnershipButton.setText("Add Stamp To Ownership");
        addStampToOwnershipButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(181, 150, 87)));
        addStampToOwnershipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStampToOwnershipButtonActionPerformed(evt);
            }
        });

        editStampButton.setText("Edit Stamp");
        editStampButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(181, 150, 87)));
        editStampButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editStampButtonActionPerformed(evt);
            }
        });

        deleteStampButton.setText("Delete Stamp");
        deleteStampButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(181, 150, 87)));
        deleteStampButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStampButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(181, 150, 87)));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        ownedStampList.setForeground(new java.awt.Color(255, 255, 255));
        ownedStampList.setText("          Ownership List");

        stampWishlist.setForeground(new java.awt.Color(255, 255, 255));
        stampWishlist.setText("       Wishlist");

        javax.swing.GroupLayout usedPanelLayout = new javax.swing.GroupLayout(usedPanel);
        usedPanel.setLayout(usedPanelLayout);
        usedPanelLayout.setHorizontalGroup(
            usedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );
        usedPanelLayout.setVerticalGroup(
            usedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        usedScrollPane.setViewportView(usedPanel);

        javax.swing.GroupLayout commemorativePanelLayout = new javax.swing.GroupLayout(commemorativePanel);
        commemorativePanel.setLayout(commemorativePanelLayout);
        commemorativePanelLayout.setHorizontalGroup(
            commemorativePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );
        commemorativePanelLayout.setVerticalGroup(
            commemorativePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        commemorativeScrollPane.setViewportView(commemorativePanel);

        javax.swing.GroupLayout definitivePanelLayout = new javax.swing.GroupLayout(definitivePanel);
        definitivePanel.setLayout(definitivePanelLayout);
        definitivePanelLayout.setHorizontalGroup(
            definitivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );
        definitivePanelLayout.setVerticalGroup(
            definitivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        definitiveScrollPane.setViewportView(definitivePanel);

        javax.swing.GroupLayout mintPanelLayout = new javax.swing.GroupLayout(mintPanel);
        mintPanel.setLayout(mintPanelLayout);
        mintPanelLayout.setHorizontalGroup(
            mintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        mintPanelLayout.setVerticalGroup(
            mintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        mintScrollPane.setViewportView(mintPanel);

        addStampToWishlistButton.setText("Add Stamp To Wishlist");
        addStampToWishlistButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(181, 150, 87)));
        addStampToWishlistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStampToWishlistButtonActionPerformed(evt);
            }
        });

        ownershipListPanel.setBackground(new java.awt.Color(157, 157, 157));
        ownershipListPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(106, 100, 100), 1, true));
        ownershipListPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout ownershipListPanelLayout = new javax.swing.GroupLayout(ownershipListPanel);
        ownershipListPanel.setLayout(ownershipListPanelLayout);
        ownershipListPanelLayout.setHorizontalGroup(
            ownershipListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        ownershipListPanelLayout.setVerticalGroup(
            ownershipListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        ownershipScrollPane.setViewportView(ownershipListPanel);

        whishListPanel.setBackground(new java.awt.Color(157, 157, 157));
        whishListPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(106, 100, 100)));

        javax.swing.GroupLayout whishListPanelLayout = new javax.swing.GroupLayout(whishListPanel);
        whishListPanel.setLayout(whishListPanelLayout);
        whishListPanelLayout.setHorizontalGroup(
            whishListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );
        whishListPanelLayout.setVerticalGroup(
            whishListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        whishlistScrollPane.setViewportView(whishListPanel);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("  Here you can organise your stamp collection");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("     Scroll through each category to view the stamps");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("      Definitive");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mint");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Used");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Commemorative");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(811, 811, 811)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addStampToOwnershipButton, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(commemorativeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(definitiveScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addStampToWishlistButton, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(mintScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(37, 37, 37))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(usedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(removeFromOwnershipList, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(removeFromWishlist, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(ownershipScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(whishlistScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(ownedStampList, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(stampWishlist, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(449, 449, 449)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(deleteStampButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editStampButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(156, 156, 156))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(logoutButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(stampWishlist, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ownedStampList, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mintScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(definitiveScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(commemorativeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addStampToOwnershipButton)
                            .addComponent(addStampToWishlistButton)))
                    .addComponent(ownershipScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(whishlistScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(removeFromWishlist, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(removeFromOwnershipList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(editStampButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteStampButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void editStampButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editStampButtonActionPerformed
        // TODO add your handling code here:
        editStampButton.addActionListener(e -> {
            if (selectedStamp != null) {
                editStamp(selectedStamp);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a stamp to edit.");
            }
        });
    }//GEN-LAST:event_editStampButtonActionPerformed

    private void addStampToOwnershipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStampToOwnershipButtonActionPerformed
        // TODO add your handling code here:
        // adding the stamp only to ownership
        if (selectedStamp != null && !ownershipData.contains(selectedStamp)) {
            ownershipData.add(selectedStamp);
            updateOwnershipUI();  // Update the User Interface to reflect the new stamp
            saveListsToCSV();  // Save the updated list to CSV
        } else {
            JOptionPane.showMessageDialog(this, "This stamp is already in the ownership list or no stamp selected.");
        }
    }//GEN-LAST:event_addStampToOwnershipButtonActionPerformed

    private void deleteStampButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStampButtonActionPerformed
        // TODO add yosur handling code here:
        deleteSelectedStamp();


    }//GEN-LAST:event_deleteStampButtonActionPerformed

    private void removeFromWishlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromWishlistActionPerformed
        // TODO add your handling code here:

        deleteSelectedStamp();
    }//GEN-LAST:event_removeFromWishlistActionPerformed

    private void removeFromOwnershipListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFromOwnershipListActionPerformed
        // TODO add your handling code here
        deleteSelectedStamp();
    }//GEN-LAST:event_removeFromOwnershipListActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        saveUserData();
        saveListsToCSV();
        saveListsBeforeLogout();
        // Show the main menu
        new StampManagementGUI().setVisible(true);

        // Close the current window UserDashboardGUI
        dispose();


    }//GEN-LAST:event_logoutButtonActionPerformed

    private void addStampToWishlistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStampToWishlistButtonActionPerformed
        // TODO add your handling code here:
//        / adding the stamp only to wishlist
        if (selectedStamp != null && !wishlist.contains(selectedStamp)) {
            wishlist.add(selectedStamp);
            updateWishlistUI();  // Update the UserInterface to reflect the new stamp
            saveListsToCSV();  // Save the updated list to CSV
        } else {
            JOptionPane.showMessageDialog(this, "This stamp is already in the wishlist or no stamp selected.");
        }

    }//GEN-LAST:event_addStampToWishlistButtonActionPerformed

    private void addStampToList() {
        // Check if a stamp is selected
        if (selectedStamp != null) {
            if (isAddingToWishlist) {
                // Add the selected stamp to the wishlist
                if (!wishlist.contains(selectedStamp)) {
                    wishlist.add(selectedStamp);
                }
                // Update the User Interface for wishlist
                updateWishlistUI();
            } else {
                // Add the selected stamp to ownership
                if (!ownershipData.contains(selectedStamp)) {
                    ownershipData.add(selectedStamp);
                }
                // Update the User Interface for ownership
                updateOwnershipUI();
            }

            // After adding the stamp, save the updated lists to CSV
            saveListsToCSV();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a stamp first.");
        }
    }

    private void loadOwnershipCSVList(File ownershipFile) {

        // Load the ownership list from CSV
        // File ownershipFile = new File("ownership_list.csv");
        try (BufferedReader ownershipReader = new BufferedReader(new FileReader(ownershipFile))) {
            String line;
            while ((line = ownershipReader.readLine()) != null) {
                String[] data = line.split(",");
                Stamp stamp = new Stamp(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], data[6], data[7]);
                ownershipData.add(stamp);
                System.out.println("LOADED OWNERSHIP STAMP" + stamp.getStampName());

                // ownershipListModel.addElement(stampName);  // Display the stamp name in the list
            }
            System.out.println("Ownership list loaded from CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWishlistCSVList(File wishlistFile) {
//        // Load the wishlist from CSV
//        File wishlistFile = new File("wishlist.csv");
        try (BufferedReader wishlistReader = new BufferedReader(new FileReader(wishlistFile))) {
            String line;
            while ((line = wishlistReader.readLine()) != null) {
                String[] data = line.split(",");
                Stamp stamp = new Stamp(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], data[6], data[7]);
                ownershipData.add(stamp);
                System.out.println("LOADED WISHLIST STAMP" + stamp.getStampName());
//                // Add the stamp to wishlist
//                wishlistListModel.addElement(stampName);  // Display the stamp name in the list
            }
            System.out.println("Wishlist loaded from CSV.");
            System.out.println("Ownership List Size: " + ownershipData.size());
            System.out.println("Wishlist Size: " + wishlist.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStampsFromCSV() {

        commemorativePanel.removeAll();
        definitivePanel.removeAll();
        mintPanel.removeAll();
        usedPanel.removeAll();
        List<Stamp> stamps = stampManagement.getStamps();
        System.out.println("DEBUG: Found " + stamps.size() + " stamps in CSV.");

        for (Stamp stamp : stamps) {
            JPanel stampCard = createStampCard(stamp);  // Create the stamp card with image and details
            switch (stamp.getStampCategory().toLowerCase()) {
                case "commemorative":
                    commemorativePanel.add(stampCard);
                    break;
                case "definitive":
                    definitivePanel.add(stampCard);
                    break;
                case "mint":
                    mintPanel.add(stampCard);
                    break;
                case "used":
                    usedPanel.add(stampCard);
                    break;
                default:
                    System.out.println("❌ WARNING: Unknown category for " + stamp.getStampName());
            }

        }

        // Update panel sizes and refresh User Interface
        commemorativePanel.setPreferredSize(new Dimension(500, Math.max(300, stamps.size() * 160)));
        definitivePanel.setPreferredSize(new Dimension(500, Math.max(300, stamps.size() * 160)));
        mintPanel.setPreferredSize(new Dimension(500, Math.max(300, stamps.size() * 160)));
        usedPanel.setPreferredSize(new Dimension(500, Math.max(300, stamps.size() * 160)));

        // Refresh the User Interface
        commemorativePanel.revalidate();
        commemorativePanel.repaint();
        definitivePanel.revalidate();
        definitivePanel.repaint();
        mintPanel.revalidate();
        mintPanel.repaint();
        usedPanel.revalidate();
        usedPanel.repaint();

    }
//Loads list only after login

    void loadListsAfterLogin() {
        loadListsFromCSV();  // Load ownership and wishlist from CSV
        // Load wishlist stamps from CSV

        // Update the ownership User interface
        updateOwnershipUI();
        updateWishlistUI();
    }

    //Loads only ownership from CSV
    private void loadOwnershipWishlistFromCSV() {

        // file paths for the ownership and wishlist CSV files
        File ownershipFile = new File("ownership_list.csv");
        File wishlistFile = new File("wishlist.csv");

        // Load the ownership list from the CSV
        try (BufferedReader ownershipReader = new BufferedReader(new FileReader(ownershipFile))) {
            String line;
            while ((line = ownershipReader.readLine()) != null) {
                String[] data = line.split(",");
                String stampName = data[0];
                int stampYear = Integer.parseInt(data[1]);
                String stampCountry = data[2];
                String stampCategory = data[3];
                String imagePath = data[4];
                String description = data[5];
                String stampOwner = data[6];
                String price = data[7];

                Stamp stamp = new Stamp(stampName, stampYear, stampCountry, stampCategory, imagePath, description, stampOwner, price);
                ownershipData.add(stamp);  // Add the stamp to the ownership list
            }
            System.out.println("Ownership list loaded from CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load the wishlist from the CSV
        try (BufferedReader wishlistReader = new BufferedReader(new FileReader(wishlistFile))) {
            String line;
            while ((line = wishlistReader.readLine()) != null) {
                String[] data = line.split(",");
                String stampName = data[0];
                int stampYear = Integer.parseInt(data[1]);
                String stampCountry = data[2];
                String stampCategory = data[3];
                String imagePath = data[4];
                String description = data[5];
                String stampOwner = data[6];
                String price = data[7];

                Stamp stamp = new Stamp(stampName, stampYear, stampCountry, stampCategory, imagePath, description, stampOwner, price);
                wishlist.add(stamp);  // Add the stamp to the wishlist
            }
            System.out.println("Wishlist loaded from CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // After loading, update the User Interface I to reflect the ownership and wishlist
        updateOwnershipUI();
        updateWishlistUI();
    }

    private void setupCategoryTabs() {
        JTabbedPane tabbedPane = new JTabbedPane();

        definitivePanel.setLayout(new GridLayout(0, 2, 20, 20));
        commemorativePanel.setLayout(new GridLayout(0, 2, 20, 20));
        usedPanel.setLayout(new GridLayout(0, 2, 20, 20));
        mintPanel.setLayout(new GridLayout(0, 2, 20, 20));

        tabbedPane.addTab("Definitive", definitivePanel);
        tabbedPane.addTab("Commemorative", commemorativePanel);
        tabbedPane.addTab("Used", usedPanel);
        tabbedPane.addTab("Mint", mintPanel);

        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

// For adding to ownership list
    private void addToOwnership(Stamp selectedStamp) {
        // Add the selected stamp to the ownership list
        ownershipData.add(selectedStamp);
        // Update the ownership list User Interface (JList)
        updateOwnershipUI();

        // Save the updated lists to CSV
        saveListsToCSV();

    }

    // Update the ownership list User Interface 
    private void updateOwnershipUI() {
        ownershipListPanel.removeAll();

        // Add all stamps from ownership data
        for (Stamp stamp : ownershipData) {
            JPanel stampCard = createOwnershipStampCard(stamp);  // Create the stamp card
            ownershipListPanel.add(stampCard);  // Add the card to the ownership panel
        }

        // Refresh the UI
        ownershipListPanel.revalidate();
        ownershipListPanel.repaint();

    }

    //Creating the stamp card
    private JPanel createStampCard(Stamp stamp) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setPreferredSize(new Dimension(150, 220));

        String imagePath = stamp.getImagePath();
        File imageFile = new File(imagePath.replace("/", File.separator));

        JLabel imageLabel;
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(new ImageIcon(imageFile.getAbsolutePath()).getImage()
                    .getScaledInstance(130, 90, Image.SCALE_SMOOTH)); // Adjust width/height
            imageLabel = new JLabel(icon);
        } else {
            imageLabel = new JLabel("No Image Available", SwingConstants.CENTER);
        }

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Update the selected stamp
                selectedStamp = stamp;

                card.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));  // Selected border color

                resetOtherCards(card);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Highlight border when hovering over image
                card.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));  // Highlight border on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Reset the border when hover ends, if not selected
                if (selectedStamp != stamp) {
                    card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }
            }
        });

        // Stamp Info
        JLabel infoLabel = new JLabel("<html><b>" + stamp.getStampName() + "</b><br>Year: " + stamp.getStampYear() + "</b><br>Category: " + stamp.getStampCategory()
                + "<br>Price: $" + stamp.getPrice() + "<br>" + stamp.getDescription() + "</html>", SwingConstants.CENTER);

        card.add(imageLabel);  // Add image to card
        card.add(infoLabel);   // Add stamp details to the card

        return card;
    }

    private void resetOtherCards(JPanel selectedCard) {
        Component[] components = commemorativePanel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel && component != selectedCard) {
                ((JPanel) component).setBorder(BorderFactory.createLineBorder(Color.GRAY));  // Reset border
            }
        }
    }

    // method to load stamps and update the lists in the User Interface
    public void loginUser() {

        loadListsAfterLogin();
        updateOwnershipUI();
        updateWishlistUI();

    }

    // Save the updated lists ownership and wishlist to the CSV files
    private void saveListsToCSV() {
        String username = currentUser.getUsername();

        //  paths for the ownership and wishlist CSV files
        File ownershipFile = new File(username + "_ownership.csv");
        File wishlistFile = new File(username + "_wishlist.csv");

        try (BufferedWriter ownershipWriter = new BufferedWriter(new FileWriter(ownershipFile, true))) {
            for (Stamp stamp : ownershipData) {
                if (!isStampAlreadyInFile(ownershipFile, stamp)) {
                    ownershipWriter.write(stamp.getStampName() + ","
                            + stamp.getStampYear() + ","
                            + stamp.getStampCountry() + ","
                            + (stamp.getImagePath().isEmpty() ? "null" : stamp.getImagePath()) + ","
                            + stamp.getDescription() + ","
                            + stamp.getStampCategory() + ","
                            + stamp.getStampOwner() + ","
                            + stamp.getPrice());

//                ownershipWriter.write(stamp.getStampName() + "," + stamp.getStampCategory() + "," + stamp.getPrice() + "," + stamp.getDescription() + "," + stamp.getImagePath());
                    ownershipWriter.newLine();  // New line after each stamp
                }
            }
            System.out.println("Ownership list saved to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the wishlist to the CSV (appending) not creating a new one. Here was the error.
        try (BufferedWriter wishlistWriter = new BufferedWriter(new FileWriter(wishlistFile, true))) {
            for (Stamp stamp : wishlist) {
//                wishlistWriter.write(stamp.getStampName() + "," + stamp.getStampCategory() + "," + stamp.getPrice() + "," + stamp.getDescription() + "," + stamp.getImagePath());
                if (!isStampAlreadyInFile(wishlistFile, stamp)) {
                    wishlistWriter.write(stamp.getStampName() + ","
                            + stamp.getStampYear() + ","
                            + stamp.getStampCountry() + ","
                            + (stamp.getImagePath().isEmpty() ? "null" : stamp.getImagePath()) + ","
                            + stamp.getDescription() + ","
                            + stamp.getStampCategory() + ","
                            + stamp.getStampOwner() + ","
                            + stamp.getPrice());

                    wishlistWriter.newLine();  // New line after each stamp
                }
            }
            System.out.println("Wishlist saved to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Verify if the stamp is already define. Prevent duplicates.
    private boolean isStampAlreadyInFile(File file, Stamp stamp) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(stamp.getStampName())) {
                    return true;  // Stamp already exists in the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;  // Stamp doesn't exist in the file
    }

    /// Method to load the ownership and wishlist lists from CSV files
    public void loadListsFromCSV() {

        ownershipData.clear();
        wishlist.clear();
        String username = currentUser.getUsername();
        File ownershipFile = new File(username + "_ownership.csv");
        File wishlistFile = new File(username + "_wishlist.csv");
        ownershipData.clear();
        wishlist.clear();

//    File ownershipFile = new File("ownership_list.csv");
//    File wishlistFile = new File("wishlist.csv");
        // Load the ownership list from the CSV
        try (BufferedReader ownershipReader = new BufferedReader(new FileReader(ownershipFile))) {
            String line;
            while ((line = ownershipReader.readLine()) != null) {
                String[] data = line.split(",");

                String stampName = data[0];
                int stampYear = Integer.parseInt(data[1]);
                String stampCountry = data[2];
                String imagePath = data[3];
                String description = data[4];
                String stampCategory = data[5]; //Categories
                String stampOwner = data[6];
                String price = data[7];

                // Create Stamp object
                Stamp stamp = new Stamp(stampName, stampYear, stampCountry, imagePath, description, stampCategory, stampOwner, price);

                // Add to ownership data
                ownershipData.add(stamp);
            }
            System.out.println("Ownership list loaded from CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load the wishlist from the CSV
        try (BufferedReader wishlistReader = new BufferedReader(new FileReader(wishlistFile))) {
            String line;
            while ((line = wishlistReader.readLine()) != null) {
                String[] data = line.split(",");

                String stampName = data[0];
                int stampYear = Integer.parseInt(data[1]);
                String stampCountry = data[2];
                String imagePath = data[3];
                String description = data[4];
                String stampCategory = data[5]; // Categories
                String stampOwner = data[6];
                String price = data[7];

                // Create Stamp object
                Stamp stamp = new Stamp(stampName, stampYear, stampCountry, imagePath, description, stampCategory, stampOwner, price);

                // Add to wishlist
                wishlist.add(stamp);
            }
            System.out.println("Wishlist loaded from CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // After loading, update the UserInterface to reflect the ownership and wishlist
        updateOwnershipUI();

        updateWishlistUI();
    }

//    // For adding to wishlist
    private void addToWishlist(Stamp selectedStamp) {
        wishlist.add(selectedStamp);

        // Update the wishlist panel to reflect the new addition
        updateWishlistUI();
        // Save to CSV 
        saveListsToCSV();

    }

    // Method to update the wishlist
    private void updateWishlistUI() {
        whishListPanel.removeAll();

        // Add all stamps from wishlist data
        for (Stamp stamp : wishlist) {
            JPanel stampCard = createWishlistStampCard(stamp);  // Create the stamp card
            whishListPanel.add(stampCard);  // Add the card to the wishlist panel
        }

        whishListPanel.revalidate();
        whishListPanel.repaint();
    }

    // Method to create the card for ownership stamps
    private JPanel createOwnershipStampCard(Stamp stamp) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(150, 220));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        String imagePath = stamp.getImagePath();
        File imageFile = new File(imagePath);
        JLabel imageLabel;
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(new ImageIcon(imageFile.getAbsolutePath()).getImage()
                    .getScaledInstance(130, 90, Image.SCALE_SMOOTH));  // Resizing the image
            imageLabel = new JLabel(icon);
        } else {
            imageLabel = new JLabel("No Image Available", SwingConstants.CENTER);
        }

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));  //Changing the border colour on hoovering.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedStamp != stamp) {
                    card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Update the selected stamp
                selectedStamp = stamp;

                // Highlight the selected stamp with a blue border
                card.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));

                resetOtherCards(card);
            }
        });

        // Stamp info (name............)
        JLabel infoLabel = new JLabel("<html><b>" + stamp.getStampName() + "</b><br>Year: " + stamp.getStampYear() + "</b><br>Category: " + stamp.getStampCategory()
                + "<br>Price: $" + stamp.getPrice() + "<br>" + stamp.getDescription() + "</html>", SwingConstants.CENTER);

        // Add the image and info to the card
        card.add(imageLabel);
        card.add(infoLabel);

        return card;
    }

    // Method to create the card for ownership stamps
    private JPanel createWishlistStampCard(Stamp stamp) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(150, 220));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Default border color

        // Load and set image for stamp
        String imagePath = stamp.getImagePath();
        File imageFile = new File(imagePath);
        JLabel imageLabel;
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(new ImageIcon(imageFile.getAbsolutePath()).getImage()
                    .getScaledInstance(130, 90, Image.SCALE_SMOOTH));  // Resize the image
            imageLabel = new JLabel(icon);
        } else {
            imageLabel = new JLabel("No Image Available", SwingConstants.CENTER);
        }

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedStamp != stamp) {
                    card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Update the selected stamp
                selectedStamp = stamp;

                // Highlight the selected stamp with a blue border
                card.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));

                resetOtherCards(card);
            }
        });

        // Stamp info (name.......)
        JLabel infoLabel = new JLabel("<html><b>" + stamp.getStampName() + "</b><br>Year: " + stamp.getStampYear() + "</b><br>Category: " + stamp.getStampCategory()
                + "<br>Price: $" + stamp.getPrice() + "<br>" + stamp.getDescription() + "</html>", SwingConstants.CENTER);

        // Add the image and info to the card
        card.add(imageLabel);
        card.add(infoLabel);

        return card;
    }

/// Delete the selected stamp
    private void deleteSelectedStamp() {
        if (selectedStamp != null) {
            // Confirm deletion
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this stamp?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                // Check if the stamp is in the ownership list or wishlist
                if (ownershipData.contains(selectedStamp)) {
                    ownershipData.remove(selectedStamp);
                    updateOwnershipUI();
                    JOptionPane.showMessageDialog(this, "Stamp deleted from Ownership list.");
                } else if (wishlist.contains(selectedStamp)) {
                    wishlist.remove(selectedStamp);
                    updateWishlistUI();
                    JOptionPane.showMessageDialog(this, "Stamp deleted from Wishlist.");
                }

                // Save the updated lists to CSV
                saveListsToCSV();

                // Reset the selected stamp after it was deleted
                selectedStamp = null;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a stamp first.");
        }
    }

    private void saveUserData() {
        // Get the current logged-in user
        String username = currentUser.getUsername();

        // Define file paths for the ownership and wishlist CSV files
        File ownershipFile = new File(username + "_ownership.csv");
        File wishlistFile = new File(username + "_wishlist.csv");
        // Save the lists to the CSV files
        ;
        saveListsToCSV();
        // Write the ownership list to the CSV
        try (BufferedWriter ownershipWriter = new BufferedWriter(new FileWriter(ownershipFile))) {
            for (Stamp stamp : ownershipData) {
//            ownershipWriter.write(stamp.getStampName() + "," + stamp.getStampCategory() + "," + stamp.getPrice() + "," + stamp.getDescription() + "," + stamp.getImagePath());
                ownershipWriter.write(stamp.getStampName() + ","
                        + stamp.getStampYear() + ","
                        + stamp.getStampCountry() + ","
                        + (stamp.getImagePath().isEmpty() ? "null" : stamp.getImagePath()) + ","
                        + stamp.getDescription() + ","
                        + stamp.getStampCategory() + ","
                        + stamp.getStampOwner() + ","
                        + stamp.getPrice());
                ownershipWriter.newLine();  // New line after each stamp
            }
            System.out.println("Ownership list saved to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the wishlist to the CSV
        try (BufferedWriter wishlistWriter = new BufferedWriter(new FileWriter(wishlistFile))) {
            for (Stamp stamp : wishlist) {
//            wishlistWriter.write(stamp.getStampName() + "," + stamp.getStampCategory() + "," + stamp.getPrice() + "," + stamp.getDescription() + "," + stamp.getImagePath());

                wishlistWriter.write(stamp.getStampName() + ","
                        + stamp.getStampYear() + ","
                        + stamp.getStampCountry() + ","
                        + (stamp.getImagePath().isEmpty() ? "null" : stamp.getImagePath()) + ","
                        + stamp.getDescription() + ","
                        + stamp.getStampCategory() + ","
                        + stamp.getStampOwner() + ","
                        + stamp.getPrice());
                wishlistWriter.newLine();  // New line after each stamp
            }
            System.out.println("Wishlist saved to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void loadUserData() {

        if (currentUser == null) {
            System.out.println("Error: currentUser is null.");
            return;
        }
        // Get the current logged-in user
        String username = currentUser.getUsername();

        // file paths for the ownership and wishlist CSV files
        File ownershipFile = new File(username + "_ownership.csv");
        File wishlistFile = new File(username + "_wishlist.csv");

        loadOwnershipCSVList(ownershipFile);
        loadWishlistCSVList(wishlistFile);
        // Load the ownership list from the CSV
        try (BufferedReader ownershipReader = new BufferedReader(new FileReader(ownershipFile))) {
            String line;
            while ((line = ownershipReader.readLine()) != null) {

                String[] data = line.split(",");
                String stampName = data[0];
                int stampYear = 0;
                try {
                    stampYear = Integer.parseInt(data[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing year for stamp: " + stampName);
                    e.printStackTrace();//Parsing the attribute.
                }
                String stampCountry = data[2];
                String imagePath = data[3];
                String description = data[4];
                String stampCategory = data[5];
                String stampOwner = data[6];
                String price = data[7];
                Stamp stamp = new Stamp(stampName, stampYear, stampCountry, imagePath, description, stampCategory, stampOwner, price);
                ownershipData.add(stamp);  // Add the stamp to the ownership list
            }
            System.out.println("Ownership list loaded from CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load the wishlist from the CSV
        try (BufferedReader wishlistReader = new BufferedReader(new FileReader(wishlistFile))) {
            String line;
            while ((line = wishlistReader.readLine()) != null) {

                String[] data = line.split(",");
                String stampName = data[0];
                int stampYear = 0;
                try {
                    stampYear = Integer.parseInt(data[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing year for stamp: " + stampName);
                    e.printStackTrace();
                }
                String stampCountry = data[2];
                String imagePath = data[3];
                String description = data[4];
                String stampCategory = data[5];
                String stampOwner = data[6];
                String price = data[7];

                // Create a new Stamp object and add it to the wishlist
                Stamp stamp = new Stamp(stampName, stampYear, stampCountry, imagePath, description, stampCategory, stampOwner, price);
                wishlist.add(stamp);  // Add the stamp to the wishlist
            }
            System.out.println("Wishlist loaded from CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // After loading, update the UI to reflect the ownership and wishlist
        updateOwnershipUI();
        updateWishlistUI();
    }

    private void saveListsBeforeLogout() {
        // Save the updated lists (ownership and wishlist) to CSV
        saveListsToCSV();
    }

    private void editStamp(Stamp selectedStamp) {
        String newName = JOptionPane.showInputDialog(this, "Enter new stamp name:", selectedStamp.getStampName());
        if (newName == null || newName.isEmpty()) {
            return;
        }

        String newYear = JOptionPane.showInputDialog(this, "Enter new year:", selectedStamp.getStampYear());
        if (newYear == null || newYear.isEmpty()) {
            return;
        }

        String newCountry = JOptionPane.showInputDialog(this, "Enter new country:", selectedStamp.getStampCountry());
        if (newCountry == null || newCountry.isEmpty()) {
            return;
        }

        String newPrice = JOptionPane.showInputDialog(this, "Enter new price:", selectedStamp.getPrice());
        if (newPrice == null || newPrice.isEmpty()) {
            return;
        }

        String newDescription = JOptionPane.showInputDialog(this, "Enter new description:", selectedStamp.getDescription());
        if (newDescription == null || newDescription.isEmpty()) {
            return;
        }

        // Validate the input (for example, check if year and price are valid numbers)
        int newYearInt;
        double newPriceDouble;
        try {
            newYearInt = Integer.parseInt(newYear);
            newPriceDouble = Double.parseDouble(newPrice);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid year or price format. Please try again.");
            return;
        }

        selectedStamp.setStampName(newName);
        selectedStamp.setStampYear(newYearInt);
        selectedStamp.setStampCountry(newCountry);
        selectedStamp.setPrice(newPrice);
        selectedStamp.setDescription(newDescription);

        // After modifying the stamp, update the User Interface
        updateOwnershipUI(); // or updateWishlist
        saveListsToCSV(); // Save updated lists to CSV
    }
    



private void deleteStampFromList(Stamp selectedStamp, boolean isFromOwnership) {
    // List of static categories that cannot be deleted
    List<String> staticCategories = Arrays.asList("Definitive", "Commemorative", "Mint", "Used");

    // Check if the selected stamp belongs to a static category
    if (staticCategories.contains(selectedStamp.getStampCategory())) {
        // Display a message that deletion is not allowed for stamps from static categories
        JOptionPane.showMessageDialog(this, "You do not have access to delete this stamp from static categories.", "Access Denied", JOptionPane.WARNING_MESSAGE);
        System.out.println("Selected Stamp Category: " + selectedStamp.getStampCategory());

        return; // Exit the method if the stamp belongs to a static category
    }

    // If it's not from a static category, proceed with the deletion confirmation
    int confirmation = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this stamp?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION);

    if (confirmation == JOptionPane.YES_OPTION) {
        // Deleting from ownership list
        if (isFromOwnership) {
            ownershipData.remove(selectedStamp);  // Remove from the ownership list
            updateOwnershipUI();  // Update UI after deletion
            JOptionPane.showMessageDialog(this, "Stamp deleted from Ownership list.");
        } else {
            // Deleting from wishlist
            wishlist.remove(selectedStamp);  // Remove from the wishlist
            updateWishlistUI();  // Update UI after deletion
            JOptionPane.showMessageDialog(this, "Stamp deleted from Wishlist.");
        }

        // Save the updated lists to CSV
        saveListsToCSV();
    }
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserDashboardGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserDashboardGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserDashboardGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserDashboardGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserDashboardGUI(UserLoginGUI.currentUser).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStampToOwnershipButton;
    private javax.swing.JButton addStampToWishlistButton;
    private javax.swing.JPanel commemorativePanel;
    private javax.swing.JScrollPane commemorativeScrollPane;
    private javax.swing.JPanel definitivePanel;
    private javax.swing.JScrollPane definitiveScrollPane;
    private javax.swing.JButton deleteStampButton;
    private javax.swing.JButton editStampButton;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mintPanel;
    private javax.swing.JScrollPane mintScrollPane;
    private javax.swing.JLabel ownedStampList;
    private javax.swing.JPanel ownershipListPanel;
    private javax.swing.JScrollPane ownershipScrollPane;
    private javax.swing.JButton removeFromOwnershipList;
    private javax.swing.JButton removeFromWishlist;
    private javax.swing.JLabel stampWishlist;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel usedPanel;
    private javax.swing.JScrollPane usedScrollPane;
    private javax.swing.JPanel whishListPanel;
    private javax.swing.JScrollPane whishlistScrollPane;
    // End of variables declaration//GEN-END:variables
}
