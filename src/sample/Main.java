package sample;

import com.mysql.cj.protocol.Resultset;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class Main extends Application implements EventHandler<ActionEvent> {
    Button firstbutton;
    Button nextbutton;
    Button lastbutton;
    Button insertbutton;
    Button clearbutton;
    Button updatebutton;
    Label lLastname;
    TextField tLastname;
    Label lFirstname;
    TextField tFirstname;
    Label lmi;
    TextField tmi;
    Label lstuid;
    TextField tstuid;
    Label lyear;
    TextField tyear;
    Label lcountry;
    TextField tcountry;
    Label lemail;
    TextField temail;
    int nextcount=0;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {

        primaryStage.setTitle("Test Assignment");

        VBox parentlayout = new VBox();
        parentlayout.setSpacing(5);

        firstbutton = new Button(" First ");
        nextbutton = new Button(" Next ");
        lastbutton = new Button(" Last ");
        insertbutton = new Button(" Insert ");
        clearbutton = new Button(" Clear ");
        updatebutton = new Button(" Update ");

        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(5);
        layout.getChildren().add(firstbutton);
        layout.getChildren().add(nextbutton);
        layout.getChildren().add(lastbutton);
        layout.getChildren().add(insertbutton);
        layout.getChildren().add(clearbutton);
        layout.getChildren().add(updatebutton);
        firstbutton.setOnAction(this);
        nextbutton.setOnAction(this);
        lastbutton.setOnAction(this);
        insertbutton.setOnAction(this);
        clearbutton.setOnAction(this);
        updatebutton.setOnAction(this);
        parentlayout.getChildren().add(layout);

        lLastname= new Label(" Last Name ");
        tLastname= new TextField();
        lFirstname= new Label(" First Name ");
        tFirstname= new TextField();
        lmi= new Label(" MI ");
        tmi=new TextField();
        HBox secondlayout = new HBox();
        secondlayout.setAlignment(Pos.CENTER_LEFT);
        secondlayout.getChildren().add(lLastname);
        secondlayout.getChildren().add(tLastname);
        secondlayout.getChildren().add(lFirstname);
        secondlayout.getChildren().add(tFirstname);
        secondlayout.getChildren().add(lmi);
        secondlayout.getChildren().add(tmi);
        parentlayout.getChildren().add(secondlayout);

        HBox trirdlayout = new HBox();
        trirdlayout.setAlignment(Pos.CENTER_LEFT);
        lstuid= new Label(" Student ID ");
        tstuid=new TextField();
        trirdlayout.getChildren().add(lstuid);
        trirdlayout.getChildren().add(tstuid);
        parentlayout.getChildren().add(trirdlayout);

        HBox forthlayout = new HBox();
        forthlayout.setAlignment(Pos.CENTER_LEFT);
        lyear= new Label(" Year ");
        tyear=new TextField();
        lcountry= new Label(" City ");
        tcountry=new TextField();
        forthlayout.getChildren().add(lyear);
        forthlayout.getChildren().add(tyear);
        forthlayout.getChildren().add(lcountry);
        forthlayout.getChildren().add(tcountry);
        parentlayout.getChildren().add(forthlayout);


        HBox fifthlayout = new HBox();
        fifthlayout.setAlignment(Pos.CENTER_LEFT);
        lemail= new Label(" Email ");
        temail=new TextField();
        fifthlayout.getChildren().add(lemail);
        fifthlayout.getChildren().add(temail);
        parentlayout.getChildren().add(fifthlayout);

        Scene scene = new Scene(parentlayout, 600 , 250);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void handle(ActionEvent event) {
        String jdbcurl="jdbc:mysql://localhost:3306/address";
        String username="root";
        String password="root";

        Statement stmt = null;
        Connection connections=null;
        if(event.getSource()==firstbutton)
        {
            nextcount=1;
            try {
                connections = DriverManager.getConnection(jdbcurl, username, password);
                stmt = connections.createStatement();
                String sql="SELECT * FROM address.address LIMIT 1";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    //Retrieve by column name
                    int mi  = rs.getInt("mi");
                    String Stuid = rs.getString("studentID");
                    String first = rs.getString("firstname");
                    String last = rs.getString("lastname");
                    String city = rs.getString("city");
                    String email = rs.getString("lastname");
                    int year = rs.getInt("year");

                    //Display values
                    String smi=String.valueOf(mi);
                    tmi.setText(smi);
                    tFirstname.setText(first);
                    tLastname.setText(last);
                    tstuid.setText(Stuid);
                    temail.setText(email);
                    String syear=String.valueOf(year);
                    tyear.setText(syear);
                    tcountry.setText(city);

                }


                if (connections != null) {
                    System.out.println("Connected");
                }
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        connections.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(connections!=null)
                        connections.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
        }
        else if(event.getSource()==nextbutton)
        {
            try {
                nextcount=nextcount+1;
                connections = DriverManager.getConnection(jdbcurl, username, password);
                stmt = connections.createStatement();

                String sql = "SELECT count(*) FROM address.address";
                ResultSet rs1 = stmt.executeQuery(sql);
                rs1.next();
                int count = rs1.getInt(1);
                String sql2;
                ResultSet rs;
                if(nextcount<count)
                {
                    sql2 = "SELECT * FROM address.address LIMIT "+nextcount+",1";
                    rs = stmt.executeQuery(sql2);
                    while(rs.next()){
                        //Retrieve by column name
                        int mi  = rs.getInt("mi");
                        String Stuid = rs.getString("studentID");
                        String first = rs.getString("firstname");
                        String last = rs.getString("lastname");
                        String city = rs.getString("city");
                        String email = rs.getString("lastname");
                        int year = rs.getInt("year");

                        //Display values
                        String smi=String.valueOf(mi);
                        tmi.setText(smi);
                        tFirstname.setText(first);
                        tLastname.setText(last);
                        tstuid.setText(Stuid);
                        temail.setText(email);
                        String syear=String.valueOf(year);
                        tyear.setText(syear);
                        tcountry.setText(city);

                    }
                }
                if (connections != null) {
                    System.out.println("Connected");
                }
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        connections.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(connections!=null)
                        connections.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
        }
        else if(event.getSource()==lastbutton)
        {

            try {
                connections = DriverManager.getConnection(jdbcurl, username, password);
                stmt = connections.createStatement();

                String sql = "SELECT count(*) FROM address.address";
                ResultSet rs1 = stmt.executeQuery(sql);
                rs1.next();
                int count = rs1.getInt(1);
                nextcount=count;
                int n=count-1;
                String sql2 = "SELECT * FROM address.address LIMIT "+n+",1";
                ResultSet rs = stmt.executeQuery(sql2);

                while(rs.next()){
                    //Retrieve by column name
                    int mi  = rs.getInt("mi");
                    String Stuid = rs.getString("studentID");
                    String first = rs.getString("firstname");
                    String last = rs.getString("lastname");
                    String city = rs.getString("city");
                    String email = rs.getString("lastname");
                    int year = rs.getInt("year");

                    //Display values
                    String smi=String.valueOf(mi);
                    tmi.setText(smi);
                    tFirstname.setText(first);
                    tLastname.setText(last);
                    tstuid.setText(Stuid);
                    temail.setText(email);
                    String syear=String.valueOf(year);
                    tyear.setText(syear);
                    tcountry.setText(city);

                }


                if (connections != null) {
                    System.out.println("Connected");
                }
            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        connections.close();
                }catch(SQLException se){
                }// do nothing
                try{
                    if(connections!=null)
                        connections.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
        }
        else if(event.getSource()==insertbutton)
        {
            if ((tmi.getText() != null && !tmi.getText().isEmpty())) {
                if ((tLastname.getText() != null && !tLastname.getText().isEmpty())) {
                    if ((tFirstname.getText() != null && !tFirstname.getText().isEmpty())) {
                        try {
                            String fname=tFirstname.getText();
                            String lname=tLastname.getText();
                            String mi=tmi.getText();
                            String year=tyear.getText();
                            String email=temail.getText();
                            String Studentid=tstuid.getText();
                            String city=tcountry.getText();

                            connections = DriverManager.getConnection(jdbcurl, username, password);
                            stmt = connections.createStatement();
                            String sql="INSERT INTO address.address(mi,firstname,lastname,studentID,year,city,email) VALUES ('"+ mi +"','"+ fname +"','"+ lname +"','"+ Studentid +"','"+ year +"','"+ city +"','"+ email +"')";
                            stmt.executeUpdate(sql);


                            if (connections != null) {
                                System.out.println("Connected");
                            }
                        }catch(SQLException se){
                            //Handle errors for JDBC
                            se.printStackTrace();
                        }catch(Exception e){
                            //Handle errors for Class.forName
                            e.printStackTrace();
                        }finally{
                            //finally block used to close resources
                            try{
                                if(stmt!=null)
                                    connections.close();
                            }catch(SQLException se){
                            }// do nothing
                            try{
                                if(connections!=null)
                                    connections.close();
                            }catch(SQLException se){
                                se.printStackTrace();
                            }//end finally try
                        }//end try
                        System.out.println("Goodbye!");

                    }

                }
            } else {

            }
        }
        else if(event.getSource()==clearbutton)
        {
            tmi.setText("");
            tFirstname.setText("");
            tLastname.setText("");
            tstuid.setText("");
            temail.setText("");
            tyear.setText("");
            tcountry.setText("");
        }
        else if(event.getSource()==updatebutton)
        {
            if ((tmi.getText() != null && !tmi.getText().isEmpty())) {
                if ((tLastname.getText() != null && !tLastname.getText().isEmpty())) {
                    if ((tFirstname.getText() != null && !tFirstname.getText().isEmpty())) {
                        try {
                            String fname=tFirstname.getText();
                            String lname=tLastname.getText();
                            String mi=tmi.getText();
                            String year=tyear.getText();
                            String email=temail.getText();
                            String Studentid=tstuid.getText();
                            String city=tcountry.getText();

                            connections = DriverManager.getConnection(jdbcurl, username, password);
                            stmt = connections.createStatement();
                            String sql="UPDATE address.address SET firstname='"+ fname +"', lastname='"+ lname +"', studentID='"+ Studentid +"', year='"+ year +"', city='"+ city+"', email='"+ email +"' WHERE mi='"+ mi +"'";

                            stmt.executeUpdate(sql);


                            if (connections != null) {
                                System.out.println("Connected");
                            }
                        }catch(SQLException se){
                            //Handle errors for JDBC
                            se.printStackTrace();
                        }catch(Exception e){
                            //Handle errors for Class.forName
                            e.printStackTrace();
                        }finally{
                            //finally block used to close resources
                            try{
                                if(stmt!=null)
                                    connections.close();
                            }catch(SQLException se){
                            }// do nothing
                            try{
                                if(connections!=null)
                                    connections.close();
                            }catch(SQLException se){
                                se.printStackTrace();
                            }//end finally try
                        }//end try
                        System.out.println("Goodbye!");

                    }

                }
            } else {

            }
        }

    }
}
