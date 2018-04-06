import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.awt.*;
import java.awt.event.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLFO{
    JTable jt;
    JLabel jl;
    JFrame jf;
    String[][] data;
    String[] cols={"ID","Name","Surname","Age","Gender"};
    String path="C:\\Users\\andi\\Desktop\\D1.XML";
    JScrollPane jsp;

    XMLFO(){
        jf=new JFrame("XML File Opener");
        jl=new JLabel("Hey-o");
        jf.setLayout(new FlowLayout());
        jf.setSize(550,600);
        jf.add(jl);
        data=dataArray(path);
        jt= new JTable(data,cols);
        jsp=new JScrollPane(jt);
        jf.add(jsp);
        jt.setAutoCreateRowSorter(true);
        jf.setLocationRelativeTo(null);
        JMenuBar jmb=new JMenuBar();
        JMenu jmF=new JMenu("File");
        JMenuItem jmiOpen=new JMenuItem("Open");
        jmF.add(jmiOpen);
        jmiOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pathFrame();
            }
        });
        jmF.addSeparator();
        JMenuItem jmiExit=new JMenuItem("Exit");
        jmF.add(jmiExit);
        jmiExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JMenu jmT=new JMenu("Help");
        JMenuItem jmiAbout=new JMenuItem("About");
        jmT.add(jmiAbout);
        jmiAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                infoFrame();
            }
        });
        jmb.add(jmF);
        jmb.add(jmT);
        jf.setJMenuBar(jmb);
        jl.setText(path);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public String[][] dataArray(String location){
        String[][] data = {
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""}};
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(location);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getDocumentElement().getChildNodes();
            int j;
            data = new String[nList.getLength()][5];
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i); //nje student
                //if (node.getNodeType() == node.ELEMENT_NODE) {
                j = -1;
                NodeList nList1 = node.getChildNodes();//te dhenat e nje studenti
                for (int a = 0; a < nList1.getLength(); a++) {
                    Element eElement = (Element) nList1.item(a);// nje e dhene si element
                    data[i][++j] = eElement.getTextContent();
                    cols[j]=eElement.getNodeName();
                    // }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void pathFrame(){
        JFrame jf1=new JFrame("Enter the path to the XML file");
        jf1.setLayout(new FlowLayout());
        jf1.setSize(400,200);
        jf1.setResizable(false);
        jf1.setVisible(true);
        jf1.setLocationRelativeTo(null);
        JLabel jl1=new JLabel("Please enter the path of the file.");
        jf1.add(jl1);
        JTextField jtf=new JTextField(20);
        jtf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                path=jtf.getText();
                data=dataArray(path);
                displayTable();
                jf1.dispose();
            }
        });
        jf1.add(jtf);
        jf1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void infoFrame(){
        JFrame jf2=new JFrame("About the creator");
        jf2.setLayout(new FlowLayout());
        jf2.setSize(400,150);
        jf2.setResizable(false);
        jf2.setLocationRelativeTo(null);
        JLabel jl1=new JLabel("<html>This desktop application is meant to open XML <br/>files w" +
                "here nodes of the root have information<br/> displayed as their 5 children. <br/>" +
                " <br/>Created by Andi Braimllari.</html>");
        jf2.add(jl1);
        jf2.setVisible(true);
        jf2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void displayTable(){
        jsp.getViewport().remove(jt);
        jt=new JTable(data,cols);
        jsp=new JScrollPane(jt);
        jf.add(jsp);
        jl.setText("The path of the file is: "+path);
        jf.add(jl);
    }
    public static void main(String A[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new XMLFO();
            }
        });
    }
}