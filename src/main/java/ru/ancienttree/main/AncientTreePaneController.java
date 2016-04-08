package ru.ancienttree.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jfxtras.labs.scene.layout.ScalableContentPane;
import ru.ancienttree.chronicler.AncientTreeChronicler;
import ru.ancienttree.chronicler.MiniAncientConnector;
import ru.ancienttree.chronicler.MiniPerson;
import ru.ancienttree.interfaces.Person;
import ru.ancienttree.person.AncientConnector;
import ru.ancienttree.person.ConnectionLine;
import ru.ancienttree.person.FullPerson;
import ru.ancienttree.person.PersonPane;

public class AncientTreePaneController implements Initializable {

	private int ATUnitCounter;

	@FXML
	private BorderPane rootBorderPane;
	@FXML
	private ScrollPane mainScrollPane;
	@FXML
	private ListView<Person> personListView;
	@FXML
	private GridPane rightGridPane;
	@FXML
	private TextField personName1;
	@FXML
	private TextField personName2;
	@FXML
	private TextField personName3;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker stopDate;

	@FXML
	private Label personCountLabel;
	@FXML
	private Label treeStatusLabel;

	@FXML
	private Button backToLastPersonButton;
	@FXML
	private Button savePersonPaneButton;
	@FXML
	private Button loadPersonPaneButton;
	@FXML
	private Button clearPersonPaneButton;

	private ObservableList personsList;
	private ObservableList<AncientConnector> ancientConnectionsList;

	private FullPerson target;

	private PersonPane personMainPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ATUnitCounter = 1;

		ScalableContentPane s = new ScalableContentPane();

		personMainPane = new PersonPane();

		personCountLabel.setText("" + personMainPane.getPersonsList().size());

		personsList = FXCollections.observableArrayList();
		ancientConnectionsList = FXCollections.observableArrayList();

		personListView.setItems(personsList);

		PaneControlUtil.setAncientTreePaneController(this);

		mainScrollPane.setHvalue(0.5);
		mainScrollPane.setVvalue(0.5);

		mainScrollPane.setContent(personMainPane);

		initListeners();

	}

	private void initListeners() {
		backToLastPersonButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				backToLastPersonButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: #ffffff; ");
			}
		});
		backToLastPersonButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				backToLastPersonButton.setStyle("");
			}
		});
		savePersonPaneButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				savePersonPaneButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: #ffffff; ");
			}
		});
		savePersonPaneButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				savePersonPaneButton.setStyle("");
			}
		});

		loadPersonPaneButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				loadPersonPaneButton.setStyle("-fx-background-color: #F57F17; -fx-text-fill: #ffffff; ");
			}
		});
		loadPersonPaneButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				loadPersonPaneButton.setStyle("");
			}
		});

		clearPersonPaneButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				clearPersonPaneButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: #ffffff; ");
			}
		});
		clearPersonPaneButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				clearPersonPaneButton.setStyle("");
			}
		});
	}

	public void saveOldPersonPane() {
		FileOutputStream fout;
		ObjectOutputStream oos;
		try {
			fout = new FileOutputStream("D:\\AncientTreeChronicler.atc");
			oos = new ObjectOutputStream(fout);

			AncientTreeChronicler chronicler = new AncientTreeChronicler();
			chronicler.setATUnitCounter(ATUnitCounter);
			chronicler.setScrollBarHorizontalValue(mainScrollPane.getHvalue());
			chronicler.setScrollBarVerticalValue(mainScrollPane.getVvalue());
			for (FullPerson person : personMainPane.getPersonsList()) {
				MiniPerson miniPerson = new MiniPerson();
				miniPerson.setFirstDate(person.getFirstDate());
				miniPerson.setLastDate(person.getLastDate());
				miniPerson.setName1(person.getName1());
				miniPerson.setName2(person.getName2());
				miniPerson.setName3(person.getName3());
				miniPerson.setPersonLayoutX(person.getLayoutX());
				miniPerson.setPersonLayoutY(person.getLayoutY());
				miniPerson.setPersonID(person.getPersonID());
				chronicler.getMiniPersons().add(miniPerson);
			}

			for (AncientConnector ancientConnector : personMainPane.getAncientConnectionsList()) {
				MiniAncientConnector miniAncientConnector = new MiniAncientConnector();
				miniAncientConnector.setAncientConnectorLayoutX(ancientConnector.getLayoutX());
				miniAncientConnector.setAncientConnectorLayoutY(ancientConnector.getLayoutY());
				miniAncientConnector.setAncientConnectorID(ancientConnector.getAncientConnectorID());

				if (ancientConnector.getConnectionCircles().size() != 0) {
					for (Entry<Integer, ConnectionLine> entry : ancientConnector.getConnectionCircles().entrySet()) {
						for (FullPerson person : personMainPane.getPersonsList()) {
							if (person.getPersonID() == entry.getKey()) {
								miniAncientConnector.getConnectionCircles().put(entry.getKey(),
										((ConnectionLine) entry.getValue()).getOutputCircle().getId());
							}
						}
					}
				}
				chronicler.getMiniAncientConnectors().add(miniAncientConnector);
			}

			oos.writeObject(chronicler);
			treeStatusLabel.setText("Сохранение выполнено");
			fout.close();
			oos.close();

			System.out.println("Сохранение выполнено успешно");
		} catch (Exception ex) {
			treeStatusLabel.setText("Сохранение не выполнено");
			System.out.println("Не удалось выполнить сохранение");
		}

	} // saveOldPersonPane end

	public void loadOldPersonPane() {
		FileInputStream fin;
		ObjectInputStream ois;
		try {
			fin = new FileInputStream("D:\\AncientTreeChronicler.atc");
			ois = new ObjectInputStream(fin);
			AncientTreeChronicler chronicler = (AncientTreeChronicler) ois.readObject();

			ATUnitCounter = chronicler.getATUnitCounter();
			personMainPane = new PersonPane();
			personsList = FXCollections.observableArrayList();
			ancientConnectionsList = FXCollections.observableArrayList();
			personListView.setItems(personsList);
			mainScrollPane.setContent(personMainPane);

			mainScrollPane.setHvalue(chronicler.getScrollBarHorizontalValue());
			mainScrollPane.setVvalue(chronicler.getScrollBarVerticalValue());

			for (MiniPerson miniPerson : chronicler.getMiniPersons()) {
				FullPerson fullPerson = new FullPerson();
				fullPerson.setFirstDate(miniPerson.getFirstDate());
				fullPerson.setLastDate(miniPerson.getLastDate());
				fullPerson.setName1(miniPerson.getName1());
				fullPerson.setName2(miniPerson.getName2());
				fullPerson.setName3(miniPerson.getName3());
				fullPerson.setLayoutX(miniPerson.getPersonLayoutX());
				fullPerson.setLayoutY(miniPerson.getPersonLayoutY());
				fullPerson.setPersonID(miniPerson.getPersonID());
				personMainPane.getChildren().add(fullPerson);
				personMainPane.getPersonsList().add(fullPerson);
				personsList.add(fullPerson.toString());
				fullPerson.toFront();
			}

			for (MiniAncientConnector miniAncientConnector : chronicler.getMiniAncientConnectors()) {
				AncientConnector ancientConnector = new AncientConnector();
				ancientConnector.setAncientConnectorID(miniAncientConnector.getAncientConnectorID());
				ancientConnector.setLayoutX(miniAncientConnector.getAncientConnectorLayoutX());
				ancientConnector.setLayoutY(miniAncientConnector.getAncientConnectorLayoutY());

				if (miniAncientConnector.getConnectionCircles().size() != 0) {
					for (Entry<Integer, String> entry : miniAncientConnector.getConnectionCircles().entrySet()) {
						for (FullPerson person : personMainPane.getPersonsList()) {
							if (person.getPersonID() == entry.getKey()) {
								if (entry.getValue().equals("outputCircle")) {
									ancientConnector.addCircleConnection(person.getOutputCircle());
								}
								if (entry.getValue().equals("inputCircle")) {
									ancientConnector.addCircleConnection(person.getInputCircle());
								}
							}
						}
					}
				}
				personMainPane.getChildren().addAll(ancientConnector);
				personMainPane.getAncientConnectionsList().add(ancientConnector);
				ancientConnectionsList.add(ancientConnector);
				ancientConnector.toFront();

			}

			personCountLabel.setText("" + personMainPane.getPersonsList().size());
			treeStatusLabel.setText("Загрузка выполнена");
			fin.close();
			ois.close();

			System.out.println("Загрузка выполнена успешно");
		} catch (Exception ex) {
			treeStatusLabel.setText("Загрузка не выполнена");
			System.out.println("Не удалось выполнить загрузку");
		}

	} // loadOldPersonPane end

	public void clearPersonPane() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Удалить все");
		alert.setHeaderText("Вы уверены, что хотите очистить древо??");
		alert.setContentText("");

		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("icon256.png").toString()));

		ButtonType buttonTypeOne = new ButtonType("Да");
		ButtonType buttonTypeCancel = new ButtonType("Нет", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			ATUnitCounter = 1;
			personsList.clear();
			ancientConnectionsList.clear();
			personMainPane = new PersonPane();
			mainScrollPane.setContent(personMainPane);
			personCountLabel.setText("" + personMainPane.getPersonsList().size());
			treeStatusLabel.setText("Древо очищено");
		}
	} // clearPersonPane end

	public void repaintConnections() {
		for (AncientConnector ancCon : ancientConnectionsList) {
			ancCon.repaintAncientConnections();
		}
	}

	public void addNewAncientConnector() {
		AncientConnector ancConn = new AncientConnector();
		ancConn.setAncientConnectorID(ATUnitCounter);
		ATUnitCounter++;
		initNodePosition(ancConn);
		personMainPane.getChildren().addAll(ancConn);
		personMainPane.getAncientConnectionsList().add(ancConn);
		ancientConnectionsList.add(ancConn);
		ancConn.toFront();
	}

	public void addNewPersonButtonAction() {
		try {
			FullPerson fullPerson = new FullPerson();
			fullPerson.setPersonID(ATUnitCounter);
			ATUnitCounter++;
			initNodePosition(fullPerson);
			personMainPane.getChildren().addAll(fullPerson);
			personMainPane.getPersonsList().add(fullPerson);
			personsList.add(fullPerson.toString());
			fullPerson.toFront();
			personCountLabel.setText("" + personMainPane.getPersonsList().size());
		} catch (Exception e) {
			System.out.println("Не удалось создать Person");
		}
	}

	public void backToLastTarget() {
		try {
			if (personMainPane.getPersonsList().size() > 0) {
				double x = ((FullPerson) personMainPane.getPersonsList().get(personMainPane.getPersonsList().size() - 1)).getLayoutX();
				double y = ((FullPerson) personMainPane.getPersonsList().get(personMainPane.getPersonsList().size() - 1)).getLayoutY();
				mainScrollPane.setHvalue(x / personMainPane.getWidth());
				mainScrollPane.setVvalue(y / personMainPane.getHeight());
			}
		} catch (Exception e) {
			System.out.println("Не удалось вернутсья к Person");
		}
	}

	public void setTargetPerson(FullPerson target) {
		if (this.target != null) {
			this.target.setStyle("-fx-background-color: #253441; -fx-border-color: #567696; ");
		}

		this.target = target;
		this.target.setStyle("-fx-background-color: #2f4f6c; -fx-border-color: #9abcdd; ");
		personName1.setText(target.getName1());
		personName2.setText(target.getName2());
		personName3.setText(target.getName3());
		startDate.setValue(target.getFirstDate());
		stopDate.setValue(target.getLastDate());
	}

	public void savePersonInfo() {
		try {
			if (target != null) {
				personsList.remove(target.toString());
				target.setName1(personName1.getText());
				target.setName2(personName2.getText());
				target.setName3(personName3.getText());
				target.setFirstDate(startDate.getValue());
				target.setLastDate(stopDate.getValue());
				personsList.add(target.toString());
				personListView.refresh();
			}
		} catch (Exception e) {
			System.out.println("Не удалось сохранить информацию");
		}
	}

	public void clearFields() {
		personName1.clear();
		personName2.clear();
		personName3.clear();
		startDate.setValue(null);
		stopDate.setValue(null);
	}

	public void clearTargetPerson() {
		if (target != null) {
			target.setStyle("-fx-background-color: #253441; -fx-border-color: #567696; ");
			target = null;
			clearFields();
		}
	}

	public void removePerson() {
		try {
			if (target != null) {
				for (AncientConnector ancCon : ancientConnectionsList) {
					ancCon.removeConnectionWithPerson(target);
				}
				personsList.remove(target.toString());
				personMainPane.getChildren().remove(target);
				personMainPane.getPersonsList().remove(target);
				target.clearPersonData();
				clearTargetPerson();
				personCountLabel.setText("" + personMainPane.getPersonsList().size());
			}
		} catch (Exception e) {
			System.out.println("Не удалось выполнить удаление Person");
		}
	}

	private void initNodePosition(Node node) {
		if (mainScrollPane.getVvalue() < 0.2) {
			node.setLayoutY(personMainPane.getHeight() * mainScrollPane.getVvalue() + 10);
		} else if (mainScrollPane.getVvalue() > 0.8) {
			node.setLayoutY(personMainPane.getHeight() * mainScrollPane.getVvalue() - 110);
		} else {
			node.setLayoutY(personMainPane.getHeight() * mainScrollPane.getVvalue() - 50);
		}
		if (mainScrollPane.getHvalue() < 0.2) {
			node.setLayoutX(personMainPane.getWidth() * mainScrollPane.getHvalue() + 10);
		} else if (mainScrollPane.getHvalue() > 0.8) {
			node.setLayoutX(personMainPane.getWidth() * mainScrollPane.getHvalue() - 130);
		} else {
			node.setLayoutX(personMainPane.getWidth() * mainScrollPane.getHvalue() - 60);
		}
	}

	public PersonPane getPersonMainPane() {
		return this.personMainPane;
	}

} // class end
