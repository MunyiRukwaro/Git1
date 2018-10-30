/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports;

/**
 *
 * @author Jeff
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.*;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
public class AddStock extends javax.swing.JDialog {

    String url = "jdbc:mysql://localhost:3306/kitchen";
    String username = "root";
    String password = "";
    Connection conn;
    Statement stm; 
    JTable DrinksTable,stable;
    int A,B;
    JScrollPane scrollPane;
    String DrinkName,DrinkPrice;
    String DrinkID="";


    /**
     * Creates new form Drinks
     */
    public AddStock(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        PopulateDrinksTable();
    }
public void PopulateDrinksTable(){
jPanel2.removeAll();
Vector columnNames = new Vector();
Vector data = new Vector();
//JPanel p=new JPanel();
try {
Class.forName("com.mysql.jdbc.Driver");
 conn = DriverManager.getConnection(url,username,password);
String sql = "SELECT ID,Name,Price from inventory WHERE Category='Drinks' && Quantity>1";
stm = conn.createStatement();
ResultSet rs = stm.executeQuery( sql );
ResultSetMetaData md = rs.getMetaData();
int columns = md.getColumnCount();
for (int i = 1; i <= columns; i++) {
if(i==1){columnNames.addElement("ID" );}
else if(i==2){columnNames.addElement("Food Name" );}
else if(i==3){columnNames.addElement("Price" );}
else{
    columnNames.addElement(md.getColumnName(i));
}
}
while (rs.next()) {
Vector row = new Vector(columns);
for (int i = 1; i <= columns; i++){
row.addElement( rs.getObject(i) );

}
data.addElement( row );
}
rs.close();
stm.close();
}
catch(Exception e){
System.out.println(e);
}

 stable = new JTable(data, columnNames);
TableColumn col;

for (int i = 0; i < stable.getColumnCount(); i++) {
col = stable.getColumnModel().getColumn(i);
col.setResizable(true);
}
stable.addMouseListener(new MouseAdapter()   
    {  
            @Override
    public void mouseClicked(MouseEvent evt)  
    {  
   int row = stable.getSelectedRow(); 
   DrinkID=String.valueOf(stable.getModel().getValueAt(row, 0));
   DrinkName=String.valueOf(stable.getModel().getValueAt(row, 1));
   DrinkPrice=String.valueOf(stable.getModel().getValueAt(row, 2));
   
       }
    
  } ); 
scrollPane = new JScrollPane(stable);
scrollPane.setSize(668, 379);
scrollPane.setWheelScrollingEnabled(true);

jPanel2.add( scrollPane );
  
//p.setSize(245,220);

}
private void MakeDrinkOrder()
{
try { 
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(url,username,password);
         PreparedStatement statement = conn.prepareStatement
         ("INSERT INTO Orders (`OrderTable`, `FoodID`, `Name`, `Price`) VALUES(?,?,?,?)");
         statement.setString(1,OrderTable.getText());
         statement.setString(2,DrinkID);
         statement.setString(3,DrinkName);
         statement.setString(4,DrinkPrice);
                 
         statement.executeUpdate();
         statement.close();
            
         int k;
            k=JOptionPane.showConfirmDialog(jPanel2,"An order for "+DrinkName+" has been successfully placed! Do you wish to make another drink order?","Order Made",JOptionPane.YES_NO_OPTION);
        if(k==JOptionPane.YES_OPTION)
        {
          ReduceDrink();              
         this.dispose();
         AddStock dialog = new AddStock(new javax.swing.JFrame(), true);
         dialog.setVisible(true);
        }
        else{
            ReduceDrink();
            this.dispose();}    
conn.close();
        }
         catch (Exception exc) {  // process error
            System.out.println("process error" + exc);
                       JOptionPane.showMessageDialog(jPanel2,"Sorry, An Error Occurred!","Error!",JOptionPane.ERROR_MESSAGE);
                }
}
private void ReduceDrink()
{
try { 
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(url,username,password);
         PreparedStatement statement = conn.prepareStatement
         ("UPDATE inventory SET Quantity=Quantity-1 WHERE ID="+DrinkID+"");
                          
         statement.executeUpdate();
         statement.close();
           
conn.close();
        }
         catch (Exception exc) {  // process error
            System.out.println("process error" + exc);
                       JOptionPane.showMessageDialog(jPanel2,"Sorry, An Error Occurred!","Error!",JOptionPane.ERROR_MESSAGE);
                }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        OrderTable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Place Order- Drinks");

        jPanel1.setBackground(new java.awt.Color(153, 0, 153));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Please choose what you want us to serve you, click 'Order Now' and we will serve you right away.");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("You are on Table No:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("This is what is available today:");

        jPanel2.setBackground(new java.awt.Color(153, 0, 153));
        jPanel2.setMaximumSize(new java.awt.Dimension(668, 379));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );

        jToggleButton1.setText("Make Order");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setText("Discard");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        OrderTable.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        OrderTable.setForeground(new java.awt.Color(255, 255, 255));
        OrderTable.setText("15");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jToggleButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addGap(144, 144, 144))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OrderTable)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jToggleButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(OrderTable))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        if(DrinkID=="")
        {
        JOptionPane.showMessageDialog(jPanel2,"Sorry, you have not chosen any food to order for!","No Input!",JOptionPane.WARNING_MESSAGE);

        }
        else{
        int choice;
            choice=JOptionPane.showConfirmDialog(jPanel2,"An order for "+DrinkName+" will be  placed! Are you sure you want to place the order?","Order Confirmation",JOptionPane.YES_NO_OPTION);
        if(choice==JOptionPane.YES_OPTION)
        {
          MakeDrinkOrder();              
                 }}
                // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Windows (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddStock dialog = new AddStock(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel OrderTable;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
