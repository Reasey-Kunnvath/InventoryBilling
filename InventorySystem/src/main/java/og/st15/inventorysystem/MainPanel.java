/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package og.st15.inventorysystem;

import java.sql.PreparedStatement;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainPanel extends javax.swing.JFrame {

    public String username;
    public String SKU;
    public String pName;
    public int pPrice;
    public String pImg;

    public boolean UpdateMode = false;

    private static final String URL = "jdbc:postgresql://localhost:5432/Inventory";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";
    private int numCards;

    public MainPanel() {
        initComponents();
    }

    public MainPanel(String SKU, String Name, int Price, String img) {
        initComponents();
        this.SKU = SKU;
        this.pName = Name;
        this.pPrice = Price;
        this.pImg = img;
//        JOptionPane.showMessageDialog(this, this.pName + this.pPrice);
        updateFields(this.SKU, this.pName, this.pPrice, this.pImg);
    }

    public MainPanel(String username) {
        initComponents();

        cardContainer.setLayout(new GridLayout(0, 4, 5, 5));
//        productPanel.setViewportView(cardContainer);
        productPanel.setAutoscrolls(true);
        this.username = username;
        this.msg.setText("Welcome back " + this.username + "!");
        toggleControls(false);
        btnSaveProduct.setVisible(false);
        btnCancel.setVisible(false);
        btnUploadImg.setVisible(false);

        btnUpdateCancel.setVisible(false);
        btnUpdateProduct.setVisible(false);
        btnDeleteProduct.setVisible(false);
    }

    public void setupProductCards() {
        // Clear the container
        cardContainer.removeAll();

        // Fetch products from the database
        List<ProductCard> cards = fetchProductsFromDatabase();

        // Loop through products and add cards to the container
        for (ProductCard card : cards) {
            cardContainer.add(card);

            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    MainPanel mp = new MainPanel(SKU, pName, pPrice, pImg);
                    toggleControls(true);
                    btnUpdateCancel.setVisible(true);
                    btnUpdateProduct.setVisible(true);
                    btnDeleteProduct.setVisible(true);
                    btnUploadImg.setVisible(true);
                    imageName.setVisible(true);
                    
                    btnSaveProduct.setVisible(false);
                    btnCancel.setVisible(false);
                    
                    txtAddPName.setText(card.Name);
                    txtAddPPrice.setText(Integer.toString(card.Price));
                    txtAddPSku.setText(card.SKU);
                    imageName.setText(card.img);

                    UpdateMode = true;
                }
            });

        }

        // Calculate the preferred size dynamically
        int cardWidth = 170;
        int cardHeight = 250;
        int gap = 10;
        int containerWidth = productPanel.getViewport().getWidth() > 0
                ? productPanel.getViewport().getWidth()
                : 800;

        int cardsPerRow = Math.max(1, (containerWidth - gap) / (cardWidth + gap));
        int numCards = cards.size();
        int numRows = (int) Math.ceil((double) numCards / cardsPerRow);

        int totalHeight = numRows * (cardHeight + gap) + gap;

        cardContainer.setPreferredSize(new Dimension(
                containerWidth,
                totalHeight
        ));
    }

    private List<ProductCard> fetchProductsFromDatabase() {
        List<ProductCard> cards = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT id, product_name, product_price, sku, image FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String sku = rs.getString("sku");
                String name = rs.getString("product_name");
                int price = rs.getInt("product_price");
                String img = rs.getString("image");
                cards.add(new ProductCard(sku, name, price, img));
            }
        } catch (SQLException e) {
            e.printStackTrace();
//            // For demo purposes, add some mock data if DB fails
//            cards.add(new CardItem("SKU001", "Apple", 1));
//            cards.add(new CardItem("SKU002", "Bread", 3));
//            cards.add(new CardItem("SKU003", "Milk", 4));
        }

        return cards;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProductMgmt = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        productPanel = new javax.swing.JScrollPane();
        cardContainer = new javax.swing.JPanel();
        btnAddNewProduct = new javax.swing.JButton();
        txtAddPPrice = new javax.swing.JTextField();
        txtAddPName = new javax.swing.JTextField();
        lblAddPPrice = new javax.swing.JLabel();
        lblAddPSku = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnSaveProduct = new javax.swing.JButton();
        txtAddPSku = new javax.swing.JTextField();
        lblAddPName = new javax.swing.JLabel();
        btnUploadImg = new javax.swing.JButton();
        imageName = new javax.swing.JLabel();
        btnUpdateProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        btnUpdateCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        msg = new javax.swing.JLabel();
        btnCancel1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 740));
        setPreferredSize(new java.awt.Dimension(1200, 740));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productPanel.setBackground(new java.awt.Color(192, 192, 192));
        productPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        productPanel.setToolTipText("");
        productPanel.setPreferredSize(new java.awt.Dimension(100, 200));

        cardContainer.setBackground(new java.awt.Color(102, 102, 102));
        cardContainer.setPreferredSize(new java.awt.Dimension(100, 2));
        productPanel.setViewportView(cardContainer);

        jPanel1.add(productPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 780, 630));

        btnAddNewProduct.setText("Add New Product");
        btnAddNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewProductActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddNewProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 270, -1));
        jPanel1.add(txtAddPPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 260, -1));
        jPanel1.add(txtAddPName, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 260, -1));

        lblAddPPrice.setText("Product Price");
        jPanel1.add(lblAddPPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, -1, -1));

        lblAddPSku.setText("SKU");
        jPanel1.add(lblAddPSku, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, -1, -1));

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 200, -1, -1));

        btnSaveProduct.setText("Save");
        btnSaveProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveProductActionPerformed(evt);
            }
        });
        jPanel1.add(btnSaveProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 200, 70, -1));
        jPanel1.add(txtAddPSku, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 260, -1));

        lblAddPName.setText("Product Name");
        jPanel1.add(lblAddPName, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, -1, -1));

        btnUploadImg.setText("Upload");
        btnUploadImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImgActionPerformed(evt);
            }
        });
        jPanel1.add(btnUploadImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 200, -1, -1));
        jPanel1.add(imageName, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, -1, -1));

        btnUpdateProduct.setText("Update");
        btnUpdateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProductActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdateProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 200, -1, -1));

        btnDeleteProduct.setText("Delete");
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });
        jPanel1.add(btnDeleteProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 200, -1, -1));

        btnUpdateCancel.setText("Cancel");
        btnUpdateCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdateCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 230, -1, -1));

        ProductMgmt.addTab("Manage Products", jPanel1);
        ProductMgmt.addTab("Create Invoice", jPanel2);

        getContentPane().add(ProductMgmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1110, 680));
        ProductMgmt.getAccessibleContext().setAccessibleName("Manage Product");

        msg.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        msg.setText("jLabel1");
        getContentPane().add(msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        btnCancel1.setText("Cancel");
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 200, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void updateFields(String SKU, String Name, int Price, String img) {
//        txtAddPName.setText(Name);
//        JOptionPane.showMessageDialog(productPanel, txtAddPName.getText());
    }

    private void btnAddNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewProductActionPerformed
        toggleControls(true);
        btnSaveProduct.setVisible(true);
        btnCancel.setVisible(true);
        btnUploadImg.setVisible(true);
        
        btnUpdateCancel.setVisible(false);
        btnUpdateProduct.setVisible(false);
        btnDeleteProduct.setVisible(false);
       
        
        txtAddPName.setText("");
        txtAddPPrice.setText("");
        txtAddPSku.setText("");
        imageName.setText("");
    }//GEN-LAST:event_btnAddNewProductActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        toggleControls(false);
        btnSaveProduct.setVisible(false);
        btnCancel.setVisible(false);
        btnUploadImg.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    public void toggleControls(boolean status) {
        lblAddPSku.setVisible(status);
        txtAddPSku.setVisible(status);
        lblAddPName.setVisible(status);
        txtAddPName.setVisible(status);
        lblAddPPrice.setVisible(status);
        txtAddPPrice.setVisible(status);
    }

    private void btnSaveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveProductActionPerformed
        if (txtAddPName.getText().isEmpty() || txtAddPPrice.getText().isEmpty() || txtAddPSku.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields required");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO products (product_name, product_price, sku, image) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, txtAddPName.getText());
            pstmt.setInt(2, Integer.parseInt(txtAddPPrice.getText()));
            pstmt.setString(3, txtAddPSku.getText());
            pstmt.setString(4, imageName.getText());
            pstmt.executeUpdate();
            setupProductCards();
            toggleControls(false);

            btnSaveProduct.setVisible(false);
            btnCancel.setVisible(false);
            btnUploadImg.setVisible(false);
        } catch (SQLException e) {
            e.printStackTrace();
//            // For demo purposes, add some mock data if DB fails
//            cards.add(new CardItem("SKU001", "Apple", 1));
//            cards.add(new CardItem("SKU002", "Bread", 3));
//            cards.add(new CardItem("SKU003", "Milk", 4));
        }
    }//GEN-LAST:event_btnSaveProductActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void btnUploadImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImgActionPerformed
        JFileChooser chooser = new JFileChooser("C:\\Users\\matri\\OneDrive\\Documents\\NetBeansProjects\\InventoryBilling\\InventoryBilling\\InventorySystem\\src\\main\\java\\og\\st15\\inventorysystem\\image\\");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(getParent());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            imageName.setText(chooser.getSelectedFile().getName());
        }
    }//GEN-LAST:event_btnUploadImgActionPerformed

    private void btnUpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateProductActionPerformed

    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteProductActionPerformed

    private void btnUpdateCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCancelActionPerformed
        toggleControls(false);
        btnUpdateProduct.setVisible(false);
        btnDeleteProduct.setVisible(false);
        btnUpdateCancel.setVisible(false);
        btnUploadImg.setVisible(false);
        imageName.setVisible(false);
    }//GEN-LAST:event_btnUpdateCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
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
            java.util.logging.Logger.getLogger(MainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane ProductMgmt;
    private javax.swing.JButton btnAddNewProduct;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnSaveProduct;
    private javax.swing.JButton btnUpdateCancel;
    private javax.swing.JButton btnUpdateProduct;
    private javax.swing.JButton btnUploadImg;
    private javax.swing.JPanel cardContainer;
    private javax.swing.JLabel imageName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAddPName;
    private javax.swing.JLabel lblAddPPrice;
    private javax.swing.JLabel lblAddPSku;
    private javax.swing.JLabel msg;
    private javax.swing.JScrollPane productPanel;
    private javax.swing.JTextField txtAddPName;
    private javax.swing.JTextField txtAddPPrice;
    private javax.swing.JTextField txtAddPSku;
    // End of variables declaration//GEN-END:variables
}
