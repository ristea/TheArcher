package arcasul.GUI;

/**
 *
 * @author Lydya0103
 */
public class LostGamePanel extends javax.swing.JPanel {
    private boolean MenuButtonPressed;
    private boolean AddScoreButtonPressed;
    /**
     * Creates new form LostGamePanel
     */
    public LostGamePanel() {
        initComponents();
        MenuButtonPressed = false;
        AddScoreButtonPressed = false;
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
        jTextArea1 = new javax.swing.JTextArea();
        menuJButton = new javax.swing.JButton();
        Text1JLabel = new javax.swing.JLabel();
        NameTextField = new javax.swing.JTextField();
        addScorJButton = new javax.swing.JButton();
        TextJLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        menuJButton.setText("MENU");
        menuJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuJButtonMouseClicked(evt);
            }
        });

        Text1JLabel.setText("Nume");

        addScorJButton.setText("Adauga Scor");
        addScorJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addScorJButtonMouseClicked(evt);
            }
        });

        TextJLabel.setText("Scorul meu");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(Text1JLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextJLabel)
                            .addComponent(addScorJButton)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
            .addGroup(layout.createSequentialGroup()
                .addGap(345, 345, 345)
                .addComponent(menuJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(Text1JLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addScorJButton)
                        .addGap(80, 80, 80)
                        .addComponent(TextJLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addGap(49, 49, 49)
                .addComponent(menuJButton)
                .addContainerGap(60, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuJButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuJButtonMouseClicked
        MenuButtonPressed = true;
    }//GEN-LAST:event_menuJButtonMouseClicked

    private void addScorJButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addScorJButtonMouseClicked
        if(NameTextField.getText().equals("") == false)
            AddScoreButtonPressed = true;
        else
            AddScoreButtonPressed = false;
    }//GEN-LAST:event_addScorJButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NameTextField;
    private javax.swing.JLabel Text1JLabel;
    private javax.swing.JLabel TextJLabel;
    private javax.swing.JButton addScorJButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton menuJButton;
    // End of variables declaration//GEN-END:variables
    
    public boolean getMenuButtonPressed() {
        return MenuButtonPressed;
    }
    
    public void setMenuButtonPressed(boolean MenuPressed) {
        MenuButtonPressed = MenuPressed;
    }
    
    public boolean getAddScoreButtonPressed() {
        return AddScoreButtonPressed;
    }
    
    public void setAddScoreButtonPressed(boolean Button) {
        AddScoreButtonPressed = Button;
    }
    
    public void setScoreAreaText(String text) {
        jTextArea1.setText(text);
    }
    
    public void setScore(String score) {
        jLabel3.setText(score);        
    }
    
    public String getPlayerName() {
        String temp = NameTextField.getText();
        NameTextField.setText("");
        return temp;
    }
}