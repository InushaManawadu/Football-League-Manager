package w1790317.demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;
import java.util.Date;
import java.util.function.Predicate;

public class FxGUI extends Application {

    private static final PremierLeagueManager pm = new PremierLeagueManager();

    @Override
    public void start(Stage primaryStage) throws Exception {
        tableGUI();
    }

    public void tableGUI() {

        TableView table = new TableView();
        table.setPrefSize(1200,300);
        table.setEditable(true);

        Label lbl_Heading = new Label("F O O T B A L L   L E A G U E   M A N A G E R");
        lbl_Heading.setPadding(new Insets(20,0,20,420));
        lbl_Heading.setId("lbl_Heading");

        HBox lbl_Hbox = new HBox();
        lbl_Hbox.setId("lbl_Hbox");
        lbl_Hbox.getChildren().add(lbl_Heading);

        /*Adding a random match and
         * updating its records */
        Button btn_Random = new Button("Random");
        btn_Random.setId("btn_Random");
        btn_Random.setPrefWidth(125);
        btn_Random.setPadding(new Insets(5));

        Label lbl_ClubOne = new Label("Club One: ");
        lbl_ClubOne.setId("label");

        Label lbl_ClubTwo = new Label("Club Two: ");
        lbl_ClubTwo.setId("label");

        Label lbl_ClubOneGoals = new Label();
        lbl_ClubOneGoals.setId("label");
        Label lbl_ClubTwoGoals = new Label();
        lbl_ClubTwoGoals.setId("label");

        HBox clubOne_Hbox = new HBox(10);
        clubOne_Hbox.getChildren().addAll(lbl_ClubOne,lbl_ClubOneGoals);

        HBox clubTwo_Hbox = new HBox(10);
        clubTwo_Hbox.getChildren().addAll(lbl_ClubTwo,lbl_ClubTwoGoals);

        VBox vBoxClubs = new VBox();
        vBoxClubs.getChildren().addAll(clubOne_Hbox,clubTwo_Hbox);

        btn_Random.setOnAction(event -> {
            PremierLeagueManager.day++;
            pm.randomMatch();
            /*Re-populating the table after adding a played match*/
            lbl_ClubOneGoals.setText(String.valueOf(pm.getClubList().get(PremierLeagueManager.randomOne).getName()) + " | " +pm.getClubList().get(PremierLeagueManager.randomOne).getNumOfPoints());
            lbl_ClubTwoGoals.setText( String.valueOf(pm.getClubList().get(PremierLeagueManager.randomTwo).getName()) + " | " +pm.getClubList().get(PremierLeagueManager.randomTwo).getNumOfPoints());

            table.setItems(FXCollections.observableArrayList(pm.getClubList()));
            table.refresh();
        });

        Pane random_Pane = new Pane();

        HBox clubOne = new HBox(10);
        clubOne.getChildren().addAll(lbl_ClubOne, lbl_ClubOneGoals);
        clubOne.setPadding(new Insets(20,40,20,40));

        HBox clubTwo = new HBox(10);
        clubTwo.getChildren().addAll(lbl_ClubTwo,lbl_ClubTwoGoals);
        clubTwo.setPadding(new Insets(20,40,20,40));

        VBox random_VBox = new VBox(10);
        random_VBox.getChildren().addAll(clubOne,clubTwo);
        random_Pane.getChildren().addAll(random_VBox);

        /*Sorting the table according to the goals*/
        Button btn_Goals = new Button("Goals Sort");
        btn_Goals.setId("btn_Goals");
        btn_Goals.setPrefWidth(125);
        btn_Goals.setPadding(new Insets(5));

        btn_Goals.setOnAction(event -> {
            //Repopulating the tables
            table.setItems(FXCollections.observableArrayList(pm.sortGoals()));
            table.refresh();
        });

        Button btn_Wins = new Button("Wins Sort");
        btn_Wins.setId("btn_Wins");
        btn_Wins.setPrefWidth(125);
        btn_Wins.setPadding(new Insets(5));

        btn_Wins.setOnAction(event -> {
            //Repopulating the tables
            table.setItems(FXCollections.observableArrayList(pm.sortWins()));
            table.refresh();
        });

        Button btn_Date = new Button("Date Sort");
        btn_Date.setId("btn_Date");
        btn_Date.setPrefWidth(125);
        btn_Date.setPadding(new Insets(5));

        btn_Date.setOnAction(event -> {
            //Repopulating the tables
            table.setItems(FXCollections.observableArrayList(pm.sortDates()));
            table.refresh();
        });

        /*View all dates*/
        Button btn_All = new Button("All Matches");
        btn_All.setId("btn_All");
        btn_All.setPrefWidth(125);
        btn_All.setPadding(new Insets(5));

        btn_All.setOnAction(event -> {
            tableDate();
        });

        Button btn_Points = new Button("Points Sort");
        btn_Points.setId("btn_Points");
        btn_Points.setPrefWidth(125);
        btn_Points.setPadding(new Insets(5));

        btn_Points.setOnAction(event -> {
            table.setItems(FXCollections.observableArrayList(pm.sortPoints()));
            table.refresh();
        });

        HBox buttonBox = new HBox(150);
        buttonBox.setPadding(new Insets(30,20,40,20));
        buttonBox.getChildren().addAll(btn_Random,btn_Goals,btn_Wins,btn_Date,btn_All,btn_Points);

        TableColumn<String,FootballClub> clubColumn = new TableColumn<>("Club Name");
        clubColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        clubColumn.setPrefWidth(125);

        TableColumn<String,FootballClub> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationColumn.setPrefWidth(125);

        TableColumn<Integer,FootballClub> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfPoints"));
        pointsColumn.setPrefWidth(80);

        TableColumn<Integer,FootballClub> winsColumn = new TableColumn("Wins");
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        winsColumn.setPrefWidth(80);

        TableColumn<Integer,FootballClub> defeatColumn = new TableColumn("Defeats");
        defeatColumn.setCellValueFactory(new PropertyValueFactory<>("defeats"));
        defeatColumn.setPrefWidth(80);

        TableColumn<Integer,FootballClub> drawsColumn = new TableColumn("Draws");
        drawsColumn.setCellValueFactory(new PropertyValueFactory<>("draws"));
        drawsColumn.setPrefWidth(80);

        TableColumn <Integer,FootballClub> scoredColumn = new TableColumn("Scored");
        scoredColumn.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));
        scoredColumn.setPrefWidth(125);

        TableColumn<Integer,FootballClub> matchesColumn = new TableColumn("Matches");
        matchesColumn.setCellValueFactory(new PropertyValueFactory<>("numOfMatches"));
        matchesColumn.setPrefWidth(125);

        TableColumn<Date,FootballClub> dateColumn = new TableColumn("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setPrefWidth(125);

        table.getColumns().addAll(clubColumn,locationColumn,pointsColumn,winsColumn,defeatColumn,drawsColumn,scoredColumn,matchesColumn,dateColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        /*Populating table with available records*/
        List<FootballClub> footballClubList = new ArrayList<FootballClub>();
        for(FootballClub footballClub : pm.getClubList()) {
            footballClubList.add(footballClub);
        }
        table.setItems(FXCollections.observableArrayList(footballClubList));


        /*Search view implementation*/
        Label lbl_Search = new Label("Search");
        lbl_Search.setId("lbl_Search");

        TextField searchField = new TextField();
        searchField.setPrefWidth(250);
        searchField.setPrefHeight(30);
        searchField.setId("searchField");
        searchField.setPromptText("Year-Month-Day / Name");

        HBox search_Hbox = new HBox(30);
        search_Hbox.setPadding(new Insets(20,50,20,20));

        search_Hbox.getChildren().addAll(lbl_Search,searchField);
        /*Search view implementation*/
        FilteredList<FootballClub> filteredList = new FilteredList<FootballClub>(FXCollections.observableArrayList(PremierLeagueManager.clubs), e-> true);
        searchField.setOnKeyReleased(event -> {
            searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super FootballClub>) footballClub-> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if(footballClub.getDate().toString().contains(newValue)){
                        return true;
                    }
                    else if(footballClub.getName().contains(newValue)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<FootballClub> sortedList = new SortedList<FootballClub>(filteredList);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);
        });

        VBox vBox = new VBox(10);
        vBox.setId("pane");
        vBox.getChildren().addAll(lbl_Hbox,buttonBox,search_Hbox,table,random_Pane);

        Stage stage = new Stage();
        stage.setTitle("GYM-Management-Table");
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("Styles.css");
        stage.setScene(scene);
        stage.show();
    }

    public void tableDate() {

        Label lbl_Heading = new Label("M A T C H  D A T E S");
        lbl_Heading.setPadding(new Insets(20,0,20,140));
        lbl_Heading.setId("lbl_Heading");

        HBox lbl_Hbox = new HBox();
        lbl_Hbox.setId("lbl_Hbox");
        lbl_Hbox.getChildren().add(lbl_Heading);

        TableView table = new TableView();
        table.prefWidth(200);
        table.setEditable(true);

        TableColumn<String,Match> clubColumn = new TableColumn<>("Club Name");
        clubColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        clubColumn.setPrefWidth(125);

        TableColumn<Date,Match> dateColumn = new TableColumn("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setPrefWidth(125);

        table.getColumns().addAll(clubColumn,dateColumn);
        table.setItems(FXCollections.observableArrayList(PremierLeagueManager.matches));
        table.refresh();

        Button btn_Date = new Button("Date Sort");
        btn_Date.setId("btn_Date");
        btn_Date.setPrefWidth(125);
        btn_Date.setPadding(new Insets(5));

        HBox btn_DateBox = new HBox();
        btn_DateBox.getChildren().add(btn_Date);
        btn_DateBox.setPadding(new Insets(20,0,20,20));

        btn_Date.setOnAction(event -> {
            //Repopulating the tables
            table.setItems(FXCollections.observableArrayList(pm.sortMatchDates()));
            table.refresh();
        });

        Label lbl_Search = new Label("Search");
        lbl_Search.setId("lbl_Search");

        TextField searchField = new TextField();
        searchField.setPrefWidth(300);
        searchField.setPrefHeight(30);
        searchField.setId("searchField");
        searchField.setPromptText("Year-Month-Day / Name");

        HBox search_Hbox = new HBox(30);
        search_Hbox.setPadding(new Insets(20,50,20,20));

        search_Hbox.getChildren().addAll(lbl_Search,searchField);
        /*Search view implementation*/
        FilteredList<Match> filteredList = new FilteredList<Match>(FXCollections.observableArrayList(PremierLeagueManager.matches), e-> true);
        searchField.setOnKeyReleased(event -> {
            searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super Match>) match-> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if(match.getDate().toString().contains(newValue)){
                        return true;
                    }
                    else if(match.getName().contains(newValue)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<Match> sortedList = new SortedList<Match>(filteredList);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);
        });

        VBox vBox = new VBox(10);
        vBox.setId("pane_2");
        vBox.getChildren().addAll(lbl_Hbox,btn_DateBox,search_Hbox,table);

        Stage stage = new Stage();
        stage.setTitle("GYM-Management-Table");
        Scene scene = new Scene(vBox,500,600);
        scene.getStylesheets().add("Styles.css");
        stage.setScene(scene);
        stage.show();
    }
}
