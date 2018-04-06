import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class MSQLR{
    JFrame jF;
    JLabel jL=new JLabel("Enter the DatabaseName/TableName/Username/Password with a space in-between them.");
    String[] databaseInfo={"companyemployees","montreal","root","admin"};
    String dbInfo;
    String[] infoArray;
    MSQLR() {
        jF=new JFrame("Read MySQL data");
        jF.setLayout(new FlowLayout());
        jF.setSize(700,300);
        jF.setLocationRelativeTo(null);
        jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea jTA=new JTextArea(50,50);
        JTextField jTF=new JTextField(50);
        jF.add(jTF);
        jF.add(jL);
        infoArray=selectAll(databaseInfo[0],databaseInfo[1],databaseInfo[2],databaseInfo[3]);
        jTF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dbInfo=jTF.getText();
                databaseInfo=dbInfo.split("\\s+");
                infoArray=selectAll(databaseInfo[0],databaseInfo[1],databaseInfo[2],databaseInfo[3]);
                jTA.setText("");
                for(int i=0;i<infoArray.length;i++) {
                    infoArray[i] = infoArray[i].replace("null", "");
                    jTA.append(infoArray[i] + "\n");
                }
            }
        });
        for(int i=0;i<infoArray.length;i++) {
            infoArray[i]=infoArray[i].replace("null","");
            jTA.append(infoArray[i]+"\n");
        }
        jF.add(jTA);
        jF.setVisible(true);
    }

    String[] selectAll(String databaseName, String tableName, String userName,String password) {
        String[] arr={"","",""};
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);
            Statement stat=conn.createStatement();
            ResultSet rSet=stat.executeQuery("SELECT * FROM "+tableName+";");
            ResultSetMetaData rSetMD=rSet.getMetaData();
            rSet.last();
            int rowCount=rSet.getRow();
            rSet.beforeFirst();
            arr=new String[rowCount];
            int i=-1;
            while(rSet.next()){
                ++i;
                for(int a=1;a<=rSetMD.getColumnCount();a++)
                    arr[i]+=rSet.getString(a)+" ";
            }
        } catch (Exception e) {
        }
        return arr;
    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new MSQLR();
            }
        });
    }
}