import javafx.application.*;
import javafx.scene.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import java.util.Arrays;

public class FXCalculator extends Application{
    TextField tf;
    String boldCSS="-fx-font-weight: bold;";
    String fontSCSS="-fx-font-size: 15px;";
    public void start(Stage primaryStage){
        InnerShadow inS=new InnerShadow(7,Color.DODGERBLUE);
        primaryStage.setTitle("FX Calculator");
        FlowPane rootNode=new FlowPane(10,10);
        rootNode.setAlignment(Pos.CENTER);
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("C:\\Users\\andib\\Desktop\\mk.PNG")));
        Scene myScene=new Scene(rootNode,370,350);
        primaryStage.setScene(myScene);
        primaryStage.setResizable(false);
        tf=new TextField();
        tf.setPromptText("Calculate!");
        tf.setPrefColumnCount(17);
        tf.setStyle("-fx-padding: 8;"+fontSCSS+boldCSS);
        rootNode.setStyle("-fx-background-color: SILVER;");

        EventHandler<ActionEvent> eH= event -> tf.setText(tf.getText()+
                ((Button)event.getSource()).getAccessibleText());

        /*MenuBar mb=new MenuBar();
        Menu m1=new Menu("File");
        MenuItem miExit=new MenuItem("Exit");
        m1.getItems().add(miExit);
        miExit.setOnAction(event -> Platform.exit());
        Menu m2=new Menu("About");
        MenuItem miInfo=new MenuItem("Info");
        miInfo.setOnAction(event->{
            FlowPane rootNode1=new FlowPane();
            Stage stage1=new Stage();
            Scene scene1=new Scene(rootNode1);
            rootNode1.setMinSize(40,80);
            stage1.setTitle("Information");
            stage1.show();

        });
        m2.getItems().add(miInfo);
        mb.getMenus().addAll(m1,m2);*/

        Button btC=new Button("C");
        btC.setEffect(new InnerShadow(25,Color.WHITE));
        btC.setMinWidth(35);
        btC.setMinHeight(35);
        btC.setStyle(boldCSS+"-fx-font-size: 12px;");
        btC.setOnAction(event->tf.setText(""));

        Button bt9=new Button("9");
        bt9.setMinSize(100,40);
        bt9.setEffect(inS);
        bt9.setStyle(fontSCSS);
        bt9.setAccessibleText("9");
        bt9.setOnAction(eH);
        //bt9.setOnKeyTyped(kE);

        Button bt8=new Button("8");
        bt8.setMinSize(100,40);
        bt8.setEffect(inS);
        bt8.setStyle(fontSCSS);
        bt8.setAccessibleText("8");
        bt8.setOnAction(eH);

        Button bt7=new Button("7");
        bt7.setMinSize(100,40);
        bt7.setEffect(inS);
        bt7.setStyle(fontSCSS);
        bt7.setAccessibleText("7");
        bt7.setOnAction(eH);

        Button bt6=new Button("6");
        bt6.setMinSize(100,40);
        bt6.setEffect(inS);
        bt6.setStyle(fontSCSS);
        bt6.setAccessibleText("6");
        bt6.setOnAction(eH);

        Button bt5=new Button("5");
        bt5.setMinSize(100,40);
        bt5.setEffect(inS);
        bt5.setStyle(fontSCSS);
        bt5.setAccessibleText("5");
        bt5.setOnAction(eH);

        Button bt4=new Button("4");
        bt4.setMinSize(100,40);
        bt4.setEffect(inS);
        bt4.setStyle(fontSCSS);
        bt4.setAccessibleText("4");
        bt4.setOnAction(eH);

        Button bt3=new Button("3");
        bt3.setMinSize(100,40);
        bt3.setEffect(inS);
        bt3.setStyle(fontSCSS);
        bt3.setAccessibleText("3");
        bt3.setOnAction(eH);

        Button bt2=new Button("2");
        bt2.setMinSize(100,40);
        bt2.setEffect(inS);
        bt2.setStyle(fontSCSS);
        bt2.setAccessibleText("2");
        bt2.setOnAction(eH);

        Button bt1=new Button("1");
        bt1.setMinSize(100,40);
        bt1.setEffect(inS);
        bt1.setStyle(fontSCSS);
        bt1.setAccessibleText("1");
        bt1.setOnAction(eH);

        Button bt0=new Button("0");
        bt0.setMinSize(100,40);
        bt0.setEffect(inS);
        bt0.setStyle(fontSCSS);
        bt0.setAccessibleText("0");
        bt0.setOnAction(eH);

        Button bteq= new Button("=");
        bteq.setMinSize(100,40);
        bteq.setEffect(new InnerShadow(7,Color.DARKOLIVEGREEN));
        bteq.setStyle(fontSCSS);
        bteq.setOnAction(event->tf.setText(""+calc(tf.getText())));

        Button btpl=new Button("+");
        btpl.setMinSize(100,40);
        btpl.setOnAction(event -> {
            if (preCalc(tf.getText()))
                tf.setText(calc(tf.getText())+"+");
            else
                tf.setText(tf.getText()+"+");
        });
        btpl.setStyle(fontSCSS);
        btpl.setEffect(new InnerShadow(7,Color.FORESTGREEN));

        Button btsub=new Button("-");
        btsub.setMinSize(100,40);
        btsub.setOnAction(event -> {
            if (preCalc(tf.getText()))
                tf.setText(calc(tf.getText())+"-");
            else
                tf.setText(tf.getText()+"-");
        });
        btsub.setStyle(fontSCSS);
        btsub.setEffect(new InnerShadow(7,Color.FORESTGREEN));

        Button btmult=new Button("*");
        btmult.setMinSize(100,40);
        btmult.setOnAction(event -> {
            if (preCalc(tf.getText()))
                tf.setText(calc(tf.getText())+"*");
            else
                tf.setText(tf.getText()+"*");
        });
        btmult.setStyle(fontSCSS);
        btmult.setEffect(new InnerShadow(7,Color.FORESTGREEN));

        Button btdiv=new Button("/");
        btdiv.setMinSize(100,40);
        btdiv.setOnAction(event -> {
            if (preCalc(tf.getText()))
                tf.setText(calc(tf.getText())+"/");
            else
                tf.setText(tf.getText()+"/");
        });
        btdiv.setStyle(fontSCSS);
        btdiv.setEffect(new InnerShadow(7,Color.FORESTGREEN));
        Label lbAB=new Label("                            " +
                "                               " +
                "                                 " +
                "                             " +
                "Andi Braimllari");
        lbAB.setStyle("-fx-font-size: 8px");
        rootNode.getChildren().addAll(tf,btC,bt9,bt8,bt7,bt6,bt5,bt4,bt3,bt2,bt1,btpl,
                bt0,btsub,btdiv,btmult,bteq,lbAB);
        primaryStage.show();
    }
    double calc(String str){
        int opM=str.charAt(0);
        int arrOp[]={str.indexOf('+'),str.indexOf('-'),str.indexOf('*'),str.indexOf('/')};
        Arrays.sort(arrOp);
        int op=arrOp[3];
        //int op=str.indexOf('+')>str.indexOf('-')?str.indexOf('+'):str.indexOf("-");
        //if(op==-1)
        //op=str.indexOf('*')>str.indexOf('/')?str.indexOf('*'):str.indexOf("/");
        //if(str.indexOf('+')!=str.lastIndexOf('+')||str.indexOf('-')!=str.lastIndexOf('-')||
        //      str.indexOf('*')!=str.lastIndexOf('*')||str.indexOf('/')!=str.lastIndexOf('/'))
        //return 0;
        String num1=str.substring(0,op);
        String num2=str.substring(op+1);
        //if(str.charAt(0)=='-'&&op==0)
        //  return Double.parseDouble(str.charAt(0)+num1);
        //if(num1.substring(num1.length()-2)!=".0")
        //  if(str.indexOf('+')+str.indexOf('-')+str.indexOf('*')+str.indexOf('/')>-2)
        //    return 0;
        switch(str.charAt(op)) {
            case '+':
                return Double.parseDouble(num1) + Double.parseDouble(num2);
            case '-':
                return Double.parseDouble(num1) - Double.parseDouble(num2);
            case '*':
                return Double.parseDouble(num1) * Double.parseDouble(num2);
            case '/':
                return Double.parseDouble(num1) / Double.parseDouble(num2);
            default:
                return 999;

        }
    }
    boolean preCalc(String str){
        if(str.length()<3)
            return false;
        int op=str.indexOf('+')>str.indexOf('-')?str.indexOf('+'):str.indexOf("-");
        if(op==-1)
            op=str.indexOf('*')>str.indexOf('/')?str.indexOf('*'):str.indexOf("/");
        if(op==-1)
            return false;
        if(op==0&&str.charAt(0)=='-')
            return false;
        String num1=str.substring(0,op);
        String num2=str.substring(op+1);
        //System.out.println(num1+" "+num2);
        if(op!=-1&&num2.length()!=0)
            return true;
        return false;
    }
}