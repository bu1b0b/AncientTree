package ru.ancienttree.person;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import jfxtras.labs.util.event.MouseControlUtil;
import ru.ancienttree.interfaces.Person;
import ru.ancienttree.main.PaneControlUtil;

public class FullPerson extends Pane implements Person {

	private FullPerson thisFullPerson;

	private int personID;

	private String name1;
	private String name2;
	private String name3;

	private LocalDate firstDate;
	private LocalDate lastDate;

	private Label nameLabel1;
	private Label nameLabel2;
	private Label nameLabel3;

	private MyCicle outputCircle;
	private MyCicle inputCircle;

	public FullPerson() {
		super();

		thisFullPerson = this;

		this.setId("fullperson");
		this.setFocusTraversable(true);

		nameLabel1 = new MyLabel("Фамилия");
		nameLabel2 = new MyLabel("Имя");
		nameLabel3 = new MyLabel("Отчество");

		outputCircle = new MyCicle();
		outputCircle.setId("outputCircle");
		inputCircle = new MyCicle();
		inputCircle.setId("inputCircle");

		outputCircle.setLayoutY(0);
		inputCircle.setLayoutY(60);

		nameLabel1.setLayoutY(6);
		nameLabel2.setLayoutY(20);
		nameLabel3.setLayoutY(34);

		this.getChildren().addAll(outputCircle, inputCircle, nameLabel1, nameLabel2, nameLabel3);

		MouseControlUtil.makeDraggable(this);

		initListeners();

	}

	private void initListeners() {

		this.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (newPropertyValue) {
					PaneControlUtil.getAncientTreePaneController().setTargetPerson(thisFullPerson);
				}
			}
		});

		this.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				requestFocus();
				if (mouseEvent.getSource().getClass() == thisFullPerson.getClass()) {
					MouseControlUtil.makeDraggable(thisFullPerson);
				}
			}
		});

		this.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (thisFullPerson.getLayoutX() < 0) {
					thisFullPerson.setLayoutX(50);
				}
				if (thisFullPerson.getLayoutY() < 0) {
					thisFullPerson.setLayoutY(50);
				}
				if (thisFullPerson.getLayoutX() < 0 && thisFullPerson.getLayoutY() < 0) {
					thisFullPerson.setLayoutX(50);
					thisFullPerson.setLayoutY(50);
				}
				PaneControlUtil.getAncientTreePaneController().repaintConnections();
			}
		});

		this.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				PaneControlUtil.getAncientTreePaneController().repaintConnections();
			}
		});

		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.DELETE) {
					PaneControlUtil.getAncientTreePaneController().removePerson();
				}
			}
		});

	} // initListeners end

	@Override
	public String getName1() {
		return name1;
	}
	@Override
	public void setName1(String name1) {
		this.name1 = name1;
		nameLabel1.setText(name1);
	}
	@Override
	public String getName2() {
		return name2;
	}
	@Override
	public void setName2(String name2) {
		this.name2 = name2;
		nameLabel2.setText(name2);
	}
	@Override
	public String getName3() {
		return name3;
	}
	@Override
	public void setName3(String name3) {
		this.name3 = name3;
		nameLabel3.setText(name3);
	}

	public LocalDate getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(LocalDate firstDate) {
		this.firstDate = firstDate;
	}

	public LocalDate getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}

	public Circle getOutputCircle() {
		return this.outputCircle;
	}
	public Circle getInputCircle() {
		return this.inputCircle;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	@Override
	public String toString() {
		if (name1 != null && name2 != null && name3 != null) {
			return name1 + " " + name2 + " " + name3;
		} else {
			return "" + this.hashCode();
		}
	}

	public void clearPersonData() {
		this.name1 = null;
		this.name2 = null;
		this.name3 = null;

		this.firstDate = null;
		this.lastDate = null;

		this.nameLabel1 = null;
		this.nameLabel2 = null;
		this.nameLabel3 = null;

		this.outputCircle = null;
		this.inputCircle = null;

		this.thisFullPerson.setOnMouseDragged(null);

		this.thisFullPerson = null;
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	class MyCicle extends Circle {

		public MyCicle() {
			super();
			this.setRadius(6);
			this.setLayoutX(60);

			initListeners();
		}

		private void initListeners() {
			this.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					try {
						if (mouseEvent.getPickResult().getIntersectedNode().getId().equals("ancientConnector")) {
							((AncientConnector) mouseEvent.getPickResult().getIntersectedNode()).addCircleConnection(MyCicle.this);
						}
					} catch (Exception e) {
						System.out.println("Не удалось добавить точку соединения!");

					}
				}
			});
			this.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (mouseEvent.getSource().getClass() == MyCicle.class) {
						thisFullPerson.setOnMouseDragged(null);
					}
				}
			});
		}

	}

	class MyLabel extends Label {

		public MyLabel(String s) {
			super(s);
			this.setId("nameLabel");
			this.setMaxSize(110, 10);
			this.setLayoutX(5);
		}

	}

} // class end
