
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Main_frame extends javax.swing.JFrame {

    
    public Main_frame() {
        

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        search_button = new javax.swing.JButton();
        input_box = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        output_area = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        search_button.setText("Search");
        search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_buttonActionPerformed(evt);
            }
        });

        output_area.setColumns(20);
        output_area.setRows(5);
        jScrollPane1.setViewportView(output_area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(input_box, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_button, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(input_box, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_buttonActionPerformed
        // TODO add your handling code here:

        Font banglaFont=new Font("Vrinda", Font.BOLD, 15);
        output_area.setFont(banglaFont);
        input_box.setFont(banglaFont);
        output_area.setText(null);
        input_word = null; 
        input_word = input_box.getText();
        try
        {
            Connection conn = ConnectorDB.ConnectDB();
            String sql;
            sql = "select * from Table_Name where (Word = '"+input_word+"' or Bengali = '"+input_word+"')";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {
                word = rs.getString("Word");
                category = rs.getString("Category");
                description = rs.getString("Description");
                example = rs.getString("Example");
                english = rs.getString("English");
                bangla = rs.getString("Bengali");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Sorry! No word found.");
            }
        }
        catch (SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
        //<editor-fold desc="Adjective">
        if (category.equals("Adjective"))
        {    
            output_area.append("Category (পদ): Adjective(বিশেষণ)\n");
            output_area.append("Concept (ধারণা): " + description + "\n");
            try
            {
                Connection conn1 = ConnectorDB.ConnectDB();
                String sql1 = "select * from Adjective where Word = '"+word+"'";
                PreparedStatement pst1 = conn1.prepareStatement(sql1);
                ResultSet rs1 = pst1.executeQuery();
                
                //<editor-fold desc="variable">
                Integer val_ani = rs1.getInt("Animate");
                Integer val_per = rs1.getInt("Person");
                Integer val_gen = rs1.getInt("Gender");
                
                Integer val_emo;
                Object emo = rs1.getObject("Emotion");
            if (emo!=null)
            {
                val_emo= (Integer) emo;
            }
            else
            {
                val_emo=null;
            }
                Integer val_qual;
                Object qual = rs1.getObject("Quality");
            if (qual!=null)
            {
                val_qual= (Integer) qual;
            }
            else
            {
                val_qual=null;
            }
                Integer val_quan;    
                Object quan = rs1.getObject("Quantity");
            if (quan!=null)
            {
                val_quan= (Integer) quan;
            }
            else
            {
                val_quan=null;
            }
                Integer val_siz;
                Object size = rs1.getObject("Size");
            if (size!=null)
            {
                val_siz= (Integer) size;
            }
            else
            {
                val_siz=null;
            }
                Integer val_bea;
                Object bea = rs1.getObject("Beauty");
            if (bea!=null)
            {
                val_bea= (Integer) bea;
            }
            else
            {
                val_bea=null;
            }
            //</editor-fold>
                
                //<editor-fold desc="Emotion">
                if ((val_emo!=null) && (val_qual ==null) && (val_quan == null) && (val_siz == null) && (val_bea == null))
                {
                    
                    if (val_emo!=0)
                    {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Emotion = '"+val_emo+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Emotion = - '"+val_emo+"' and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nAntonyms: ");
                            ID=1;
                        }
                        antonym=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+antonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        antonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn13 = ConnectorDB.ConnectDB();
                    String sql13 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Emotion = 0 and Word!='"+word+"'";
                    PreparedStatement pst13 = conn13.prepareStatement(sql13);
                    ResultSet rs13 = pst13.executeQuery();
                    ID=-1;
                    while (rs13.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHypernym: ");
                            ID=1;
                        }
                        hyp=rs13.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    rs11.close();
                    rs12.close();
                    rs13.close();
                    pst11.close();
                    pst12.close();
                    pst13.close();
                    conn11.close();
                    conn12.close();
                    conn13.close();
                    }
                    
                    
                    
                    else
                    {
                        Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Emotion = '"+val_emo+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Emotion != '"+val_emo+"' and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHyponyms: ");
                            ID=1;
                        }
                        hyp=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    rs11.close();
                    rs12.close();
                    pst11.close();
                    pst12.close();
                    conn11.close();
                    conn12.close();
                    }
                }
                //</editor-fold>
                
                //<editor-fold desc="Quality">
                else if ((val_emo==null) && (val_qual !=null) && (val_quan == null) && (val_siz == null) && (val_bea == null))
                {
                    if (val_qual!=0)
                    {
                            try
                            {
                    Connection conn22 = ConnectorDB.ConnectDB();
                    String sql22 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Quality = '"+val_qual+"' and Word!='"+word+"'";
                    PreparedStatement pst22 = conn22.prepareStatement(sql22);
                    ResultSet rs22 = pst22.executeQuery();
                    Integer ID=-1;
                    while (rs22.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs22.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                            }
                            catch(SQLException | HeadlessException e)
                        {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    try
                    {
                    Connection conn21 = ConnectorDB.ConnectDB();
                    String sql21 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Quality = - '"+val_qual+"' and Word!='"+word+"'";
                    PreparedStatement pst21 = conn21.prepareStatement(sql21);
                    ResultSet rs21 = pst21.executeQuery();
                    Integer ID=-1;
                    while (rs21.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nAntonyms: ");
                            ID=1;
                        }
                        antonym=rs21.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+antonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        antonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    }
                    catch(SQLException | HeadlessException e)
                        {
                            JOptionPane.showMessageDialog(null, "problem 2");
                        }
                    try{
                    Connection conn23 = ConnectorDB.ConnectDB();
                    String sql23 = "select Word from Adjective where (Animate = '"+val_ani+"' or Animate = 0) and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Quality = 0 and Word!='"+word+"'";
                    PreparedStatement pst23 = conn23.prepareStatement(sql23);
                    ResultSet rs23 = pst23.executeQuery();
                    Integer ID=-1;
                    while (rs23.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHypernyms: ");
                            ID=1;
                        }
                        hyp=rs23.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    
                    }
                    catch(SQLException | HeadlessException e)
                        {
                            JOptionPane.showMessageDialog(null, "problem 2");
                        }
                    }
                    
                    
                    
                    else
                    {
                        Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Quality = 0 and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Adjective where (Animate = '"+val_ani+"' or Animate = 0) and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Quality != 0 and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        System.out.println("here");
                        if (ID == -1)
                        {
                            output_area.append("\nHyponyms: ");
                            ID=1;
                        }
                        hyp=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    output_area.append("\n");
                    rs11.close();
                    rs12.close();
                    pst11.close();
                    pst12.close();
                    conn11.close();
                    conn12.close();
                    }
                }
                //</editor-fold>
                
                //<editor-fold desc="Quantity">
                else if ((val_emo==null) && (val_qual ==null) && (val_quan != null) && (val_siz == null) && (val_bea == null))
                {
                    synonym = null;
                    antonym = null;
                    hyp=null;
                    if (val_quan!=0)
                    {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Quantity = '"+val_quan+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Quantity = - '"+val_quan+"' and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nAntonyms: ");
                            ID=1;
                        }
                        antonym=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+antonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        antonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    
                    Connection conn13 = ConnectorDB.ConnectDB();
                    String sql13 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Quantity = 0 and Word!='"+word+"'";
                    PreparedStatement pst13 = conn13.prepareStatement(sql13);
                    ResultSet rs13 = pst13.executeQuery();
                    ID=-1;
                    while (rs13.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHypernyms: ");
                            ID=1;
                        }
                        hyp=rs13.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    rs11.close();
                    rs12.close();
                    rs13.close();
                    pst11.close();
                    pst12.close();
                    pst13.close();
                    conn11.close();
                    conn12.close();
                    conn13.close();
                    }
                                        
                    
                    else
                    {
                        Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Quantity = '"+val_quan+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Quantity != '"+val_quan+"' and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHyponyms: ");
                            ID=1;
                        }
                        hyp=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    rs11.close();
                    rs12.close();
                    pst11.close();
                    pst12.close();
                    conn11.close();
                    conn12.close();
                    }
                }
                //</editor-fold>
                
                //<editor-fold desc="Size">
                else if ((val_emo==null) && (val_qual ==null) && (val_quan == null) && (val_siz != null) && (val_bea == null))
                {
                    if (val_siz!=0)
                    {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Size = '"+val_siz+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Size = - '"+val_siz+"' and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nAntonyms: ");
                            ID=1;
                        }
                        antonym=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+antonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        antonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    
                    Connection conn13 = ConnectorDB.ConnectDB();
                    String sql13 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Size = 0 and Word!='"+word+"'";
                    PreparedStatement pst13 = conn13.prepareStatement(sql13);
                    ResultSet rs13 = pst13.executeQuery();
                    ID=-1;
                    while (rs13.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHypernyms: ");
                            ID=1;
                        }
                        hyp=rs13.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    rs11.close();
                    rs12.close();
                    rs13.close();
                    pst11.close();
                    pst12.close();
                    pst13.close();
                    conn11.close();
                    conn12.close();
                    conn13.close();
                    }
                    
                    
                    
                    else
                    {
                        Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Size = '"+val_siz+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Size != '"+val_siz+"' and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHyponyms: ");
                            ID=1;
                        }
                        hyp=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    rs11.close();
                    rs12.close();
                    pst11.close();
                    pst12.close();
                    conn11.close();
                    conn12.close();
                    }
                }
                //</editor-fold>
                
                //<editor-fold desc="Beauty">
                else if ((val_emo==null) && (val_qual ==null) && (val_quan == null) && (val_siz == null) && (val_bea != null))
                {
                    if (val_bea!=0)
                    {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Beauty = '"+val_bea+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Beauty = - '"+val_bea+"' and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nAntonyms: ");
                            ID=1;
                        }
                        antonym=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+antonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        antonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    
                    Connection conn13 = ConnectorDB.ConnectDB();
                    String sql13 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Beauty = 0 and Word!='"+word+"'";
                    PreparedStatement pst13 = conn13.prepareStatement(sql13);
                    ResultSet rs13 = pst13.executeQuery();
                    ID=-1;
                    while (rs13.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHypernyms: ");
                            ID=1;
                        }
                        hyp=rs13.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    rs11.close();
                    rs12.close();
                    rs13.close();
                    pst11.close();
                    pst12.close();
                    pst13.close();
                    conn11.close();
                    conn12.close();
                    conn13.close();
                    }
                    
                    
                    
                    else
                    {
                        Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Beauty = '"+val_bea+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Adjective where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Beauty != '"+val_bea+"' and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHyponyms: ");
                            ID=1;
                        }
                        hyp=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    rs11.close();
                    rs12.close();
                    pst11.close();
                    pst12.close();
                    conn11.close();
                    conn12.close();
                    }
                }
               //</editor-fold>
            }
            catch(SQLException | HeadlessException e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
        //</editor-fold>
        
        //<editor-fold desc="Verb">
        else if (category.equals("Verb"))
        {
            output_area.append("Category (পদ): Verb(ক্রিয়া)\n");
            output_area.append("Concept (ধারণা): " + description + "\n");
            try
            {
            String sql2 = "select * from Verb where word = '"+word+"'";
            Connection conn2 = ConnectorDB.ConnectDB();
            PreparedStatement pst2 = conn2.prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();
            
            //<editor-fold desc="Variable">
            Integer val_ani = rs2.getInt("Animate");
            Integer val_hum = rs2.getInt("Human");
            Integer val_gen = rs2.getInt("Gender");
            Integer val_mov;
            Object mov = rs2.getObject("Move");
            if (mov!=null)
            {
                val_mov= (Integer) mov;
            }
            else
            {
                val_mov=null;
            }
            Integer val_cha;
            Object cha = rs2.getObject("Change");
            if (cha!=null)
            {
                val_cha= (Integer) cha;
            }
            else
            {
                val_cha=null;
            }
            Integer val_sta;
            Object sta = rs2.getObject("State");
            if (sta!=null)
            {
                val_sta= (Integer) sta;
            }
            else
            {
                val_sta=null;
            }
            Integer val_dec;
            Object dec = rs2.getObject("Decesion");
            if (dec!=null)
            {
                val_dec= (Integer) dec;
            }
            else
            {
                val_dec=null;
            }
            //</editor-fold>            
          
            //<editor-fold desc="Change">
            if ((val_mov == null)&& (val_cha != null) && (val_sta == null)&&(val_dec == null))
            {
                if (val_cha!=0)
                {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Verb where Animate = '"+val_ani+"' and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Change = '"+val_cha+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            synonym = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "Select Word from Verb where Animate = '"+val_ani+"' and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Change = -'"+val_cha+"'and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nAntonyms: ");
                            ID=1;
                        }
                        antonym=rs12.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+antonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            antonym = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn13 = ConnectorDB.ConnectDB();
                    String sql13 = "Select Word from Verb where (Animate = '"+val_ani+"' or Animate= 0) and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Change = 0 and Word!='"+word+"'";
                    PreparedStatement pst13 = conn13.prepareStatement(sql13);
                    ResultSet rs13 = pst13.executeQuery();
                    ID=-1;
                    while (rs13.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHypernyms: ");
                            ID=1;
                        }
                        hyp=rs13.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            hyp = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                }
                
                else
                {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "Select Word from Verb where Animate = '"+val_ani+"' and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Change = '"+val_cha+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            synonym = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "Select Word from Verb where (Animate = '"+val_ani+"' or Animate= 0) and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Change != 0 and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHyponyms: ");
                            ID=1;
                        }
                        hyp=rs12.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            hyp = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                }
            }
            //</editor-fold>
                        
            //<editor-fold desc="Move">
            else if ((val_mov != null)&& (val_cha == null) && (val_sta == null)&&(val_dec == null))
            {
                if (val_mov!=0)
                {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Verb where Animate = '"+val_ani+"' and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Move = '"+val_mov+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            if (rs00.next())
                            {
                             relation = rs00.getString("Bengali");
                             output_area.append(relation + ", ");
                             synonym = null;
                             relation = null;
                            }
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "Select Word from Verb where (Animate = '"+val_ani+"' or Animate= 0) and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Move = -'"+val_mov+"' and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nAntonyms: ");
                            ID=1;
                        }
                        antonym=rs12.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+antonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            antonym = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn13 = ConnectorDB.ConnectDB();
                    String sql13 = "Select Word from Verb where (Animate = '"+val_ani+"' or Animate= 0) and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Move = 0 and Word!='"+word+"'";
                    PreparedStatement pst13 = conn13.prepareStatement(sql13);
                    ResultSet rs13 = pst13.executeQuery();
                    ID=-1;
                    while (rs13.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHypernyms: ");
                            ID=1;
                        }
                        hyp=rs13.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            hyp = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                }
                
                else
                {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "Select Word from Verb where (Animate = '"+val_ani+"' or Animate= 0) and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Move = 0 and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            synonym = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "Select Word from Verb where (Animate = '"+val_ani+"' or Animate= 0) and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Move != 0 and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHyponyms: ");
                            ID=1;
                        }
                        hyp=rs12.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            hyp = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                }
            }
            //</editor-fold>           
            
            //<editor-fold desc="State">
            else if((val_mov == null)&& (val_cha == null) && (val_sta != null)&&(val_dec == null))
            {
                if (val_sta!=0)
                {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Verb where Animate = '"+val_ani+"' and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and State = '"+val_sta+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            if(rs00.next())
                            {
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            synonym = null;
                            relation = null;
                            }
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "Select Word from Verb where Animate = '"+val_ani+"' and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and State = -'"+val_sta+"'and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nAntonyms: ");
                            ID=1;
                        }
                        antonym=rs12.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+antonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            if(rs00.next())
                            {
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            antonym = null;
                            relation = null;
                            }
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn13 = ConnectorDB.ConnectDB();
                    String sql13 = "Select Word from Verb where (Animate = '"+val_ani+"' or Animate= 0) and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and State = 0 and Word!='"+word+"'";
                    PreparedStatement pst13 = conn13.prepareStatement(sql13);
                    ResultSet rs13 = pst13.executeQuery();
                    ID=-1;
                    while (rs13.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHypernyms: ");
                            ID=1;
                        }
                        hyp=rs13.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            hyp = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                }
                
                else
                {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "Select Word from Verb where Animate = '"+val_ani+"' and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and State = 0 and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            synonym = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "Select Word from Verb where (Animate = '"+val_ani+"' or Animate= 0) and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and State != 0 and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHyponyms: ");
                            ID=1;
                        }
                        hyp=rs12.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            hyp = null;
                            relation = null;
                        }
                        pst00.close();
                        rs00.close();
                    }
                }
            }
            //</editor-fold>
            
            //<editor-fold desc="Decision">
            if((val_mov == null)&& (val_cha == null) && (val_sta == null)&&(val_dec != null))
            {
                if (val_dec!=0)
                {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Verb where Animate = '"+val_ani+"' and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Decesion = '"+val_dec+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            if(rs00.next())
                            {
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            synonym = null;
                            relation = null;
                            }
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "Select Word from Verb where Animate = '"+val_ani+"' and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Decesion != '"+val_dec+"'and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nAntonyms: ");
                            ID=1;
                        }
                        antonym=rs12.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+antonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            if(rs00.next())
                            {
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            antonym = null;
                            relation = null;
                            }
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn13 = ConnectorDB.ConnectDB();
                    String sql13 = "Select Word from Verb where (Animate = '"+val_ani+"' or Animate= 0) and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Decesion = 0 and Word!='"+word+"'";
                    PreparedStatement pst13 = conn13.prepareStatement(sql13);
                    ResultSet rs13 = pst13.executeQuery();
                    ID=-1;
                    while (rs13.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHypernyms: ");
                            ID=1;
                        }
                        hyp=rs13.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            if(rs00.next())
                            {
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            hyp = null;
                            relation = null;
                            }
                        }
                        pst00.close();
                        rs00.close();
                    }
                    rs11.close();
                    rs12.close();
                    rs13.close();
                    pst11.close();
                    pst12.close();
                    pst13.close();
                    conn11.close();
                    conn12.close();
                    conn13.close();
                }
                
                else
                {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "Select Word from Verb where Animate = '"+val_ani+"' and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Decesion = '"+val_dec+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            if(rs00.next())
                            {
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            synonym = null;
                            relation = null;
                            }
                        }
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "Select Word from Verb where (Animate = '"+val_ani+"' or Animate= 0) and Human = '"+val_hum+"' and Gender = '"+val_gen+"' and Decesion != 0 and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHyponyms: ");
                            ID=1;
                        }
                        hyp=rs12.getString("Word");
                        PreparedStatement pst00;
                        ResultSet rs00;
                        try (Connection conn00 = ConnectorDB.ConnectDB()) {
                            String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                            pst00 = conn00.prepareStatement(sql00);
                            rs00 = pst00.executeQuery();
                            if(rs00.next())
                            {
                            relation = rs00.getString("Bengali");
                            output_area.append(relation + ", ");
                            hyp = null;
                            relation = null;
                            }
                        }
                        pst00.close();
                        rs00.close();
                    }
                    rs11.close();
                    rs12.close();
                    pst11.close();
                    pst12.close();
                    conn11.close();
                    conn12.close();
                }
            }
            //</editor-fold>
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        //</editor-fold>
        
        else if(category.equals("Noun"))
        {
            output_area.append("Category (পদ): Noun(বিশেষ্য)\n");
            output_area.append("Concept (ধারণা): " + description + "\n");
            try
            {
                Connection conn1 = ConnectorDB.ConnectDB();
                String sql1 = "select * from Noun where Word = '"+word+"'";
                PreparedStatement pst1 = conn1.prepareStatement(sql1);
                ResultSet rs1 = pst1.executeQuery();
                Integer count = rs1.getInt("Count");
                Integer comm = rs1.getInt("Common");
                Float val_ani = rs1.getFloat("Animate");
                Float val_per = rs1.getFloat("Person");
                Integer val_hon = rs1.getInt("Honorable");
                Integer val_gen = rs1.getInt("Gender");
                Integer val_adu = rs1.getInt("Adult");
                Integer val_mat = rs1.getInt("Material");
                Integer val_sol = rs1.getInt("Solid");
                if (count == 1)
                {
                    if(val_gen!=0)
                    {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Noun where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = '"+val_gen+"' and Common = '"+comm+"' and Honorable = '"+val_hon+"' and Adult = '"+val_adu+"' and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Noun where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Gender = -'"+val_gen+"' and Common = '"+comm+"' and Honorable = '"+val_hon+"' and Adult = '"+val_adu+"' and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nAntonyms: ");
                            ID=1;
                        }
                        antonym=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+antonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        antonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn13 = ConnectorDB.ConnectDB();
                    String sql13 = "select Word from Noun where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Common = 1 and (gender = '"+val_gen+"' or gender = 0) and Word!='"+word+"'";
                    PreparedStatement pst13 = conn13.prepareStatement(sql13);
                    ResultSet rs13 = pst13.executeQuery();
                    ID=-1;
                    while (rs13.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHyponym: ");
                            ID=1;
                        }
                        hyp=rs13.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    Connection conn14 = ConnectorDB.ConnectDB();
                    String sql14 = "select Word from Noun where (Animate between 1.1 and 1.9) and (Person between 1.1 and 1.9) and Word!='"+word+"'";
                    PreparedStatement pst14 = conn14.prepareStatement(sql14);
                    ResultSet rs14 = pst14.executeQuery();
                    ID=-1;
                    Integer i=0;
                    while (rs14.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nMeronym: ");
                            ID=1;
                        }
                        
                        i++;
                        if (i%2!=1)
                        {
                            continue;
                        }
                        mer=rs14.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+mer+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        mer = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    }
                    
                    
                    else
                    {
                        Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Noun where Animate = '"+val_ani+"' and Person = '"+val_per+"' and (Gender = '"+val_gen+"' or Gender is null) and Common = '"+comm+"' and (Honorable = '"+val_hon+"' or Honorable is null) and (Adult = '"+val_adu+"' or Adult is null) and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Synonyms : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    if(comm==-1)
                    {
                        Connection conn13 = ConnectorDB.ConnectDB();
                    String sql13 = "select Word from Noun where Animate = '"+val_ani+"' and Person = '"+val_per+"' and Common = 1 and (gender = '"+val_gen+"' or gender = 0) and Word!='"+word+"'";
                    PreparedStatement pst13 = conn13.prepareStatement(sql13);
                    ResultSet rs13 = pst13.executeQuery();
                    ID=-1;
                    while (rs13.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHypernym: ");
                            ID=1;
                        }
                        hyp=rs13.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    
                    Connection conn14 = ConnectorDB.ConnectDB();
                    String sql14 = "select Word from Noun where (Animate between 1.1 and 1.9) and (Person between 1.1 and 1.9) and Word!='"+word+"'";
                    PreparedStatement pst14 = conn14.prepareStatement(sql14);
                    ResultSet rs14 = pst14.executeQuery();
                    ID=-1;
                    Integer i=0;
                    while (rs14.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nMeronym: ");
                            ID=1;
                        }
                        
                        i++;
                        if (i%2!=1)
                        {
                            continue;
                        }
                        mer=rs14.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+mer+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        mer = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    }
                    else if (comm == 1)
                    {
                        Connection conn12 = ConnectorDB.ConnectDB();
                    String sql12 = "select Word from Noun where (Animate = '"+val_ani+"') and (Person = '"+val_per+"') and Common = -1  and Word!='"+word+"'";
                    PreparedStatement pst12 = conn12.prepareStatement(sql12);
                    ResultSet rs12 = pst12.executeQuery();
                    ID=-1;
                    Integer i=0;
                    while (rs12.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHyponyms: ");
                            ID=1;
                        }
                        i++;
                        if (i%3!=1)
                        {
                            continue;
                        }
                        hyp=rs12.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+hyp+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        hyp = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    
                    Connection conn14 = ConnectorDB.ConnectDB();
                    String sql14 = "select Word from Noun where (Animate between 1.1 and 1.9) and (Person between 1.1 and 1.9) and Word!='"+word+"'";
                    PreparedStatement pst14 = conn14.prepareStatement(sql14);
                    ResultSet rs14 = pst14.executeQuery();
                    ID=-1;
                    i=0;
                    while (rs14.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nMeronym: ");
                            ID=1;
                        }
                        
                        i++;
                        if (i%2!=1)
                        {
                            continue;
                        }
                        mer=rs14.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+mer+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        mer = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    }
                    else
                    {
                    Connection conn14 = ConnectorDB.ConnectDB();
                    String sql14 = "select Word from Noun where (Animate = 1) and Common = 1 and Word!='"+word+"'";
                    PreparedStatement pst14 = conn14.prepareStatement(sql14);
                    ResultSet rs14 = pst14.executeQuery();
                    ID=-1;
                    Integer i=0;
                    while (rs14.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("\nHolonyms: ");
                            ID=1;
                        }
                        
                        i++;
                        if (i%2!=1)
                        {
                            continue;
                        }
                        mer=rs14.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+mer+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        mer = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                    }
                    }
                }
                                
                //if (count =-1)
                else
                {
                    Connection conn11 = ConnectorDB.ConnectDB();
                    String sql11 = "select Word from Noun where Material = '"+val_mat+"' and (Solid = '"+val_sol+"' or Solid is null) and Word!='"+word+"'";
                    PreparedStatement pst11 = conn11.prepareStatement(sql11);
                    ResultSet rs11 = pst11.executeQuery();
                    Integer ID=-1;
                    while (rs11.next())
                    {
                        if (ID == -1)
                        {
                            output_area.append("Same Types : ");
                            ID=1;
                        }
                        synonym=rs11.getString("Word");
                        Connection conn00 = ConnectorDB.ConnectDB();
                        String sql00 = "select Bengali from Table_Name where Word = '"+synonym+"'";
                        PreparedStatement pst00 = conn00.prepareStatement(sql00);
                        ResultSet rs00 = pst00.executeQuery();
                        if(rs00.next())
                        {
                        relation = rs00.getString("Bengali");
                        output_area.append(relation + ", ");                      
                        synonym = null;
                        relation = null;
                        }
                        conn00.close();
                        pst00.close();
                        rs00.close();
                    }
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        //</editor-fole>
        
        output_area.append("\nExample (উদাহরণ): "+example + "\n\n");
    }//GEN-LAST:event_search_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(Main_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_frame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField input_box;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea output_area;
    private javax.swing.JButton search_button;
    // End of variables declaration//GEN-END:variables

public String input_word= null;
public String word = null;
public String category = null;
public String description = null;
public String example = null;
public String english = null;
public String bangla = null;
public String synonym = null;
public String antonym = null;
public String hyp = null;
public String relation = null;
public String mer= null;
}
