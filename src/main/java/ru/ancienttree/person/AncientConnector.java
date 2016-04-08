package ru.ancienttree.person;

import java.util.Hashtable;
import java.util.Map.Entry;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import jfxtras.labs.util.event.MouseControlUtil;
import ru.ancienttree.main.PaneControlUtil;

public class AncientConnector extends Circle {

	private Hashtable<Integer, ConnectionLine> connectionCircles;
	private byte outputCirclesCount;

	private int ancientConnectorID;

	public AncientConnector() {
		super();
		this.setId("ancientConnector");
		this.setRadius(10);
		this.setLayoutX(60);

		outputCirclesCount = 0;

		connectionCircles = new Hashtable<Integer, ConnectionLine>();

		MouseControlUtil.makeDraggable(this);
		initAncientConnectorListeners(this);
	}

	public void repaintAncientConnections() {
		for (Entry<Integer, ConnectionLine> entry : connectionCircles.entrySet()) {
			entry.getValue().repaintConnectionLine();
		}
	}

	public void addCircleConnection(Circle circle) {
		Object value = connectionCircles.get(((FullPerson) circle.getParent()).getPersonID());
		if (value == null) {
			if (circle.getId().equals("inputCircle")) {
				addNewConnection(circle);
			} else if (circle.getId().equals("outputCircle") && outputCirclesCount <= 1) {
				addNewConnection(circle);
				outputCirclesCount++;
			}
		} // if (value != null) break
	} // addCircleConnection end

	private void addNewConnection(Circle circle) {
		connectionCircles.put(((FullPerson) circle.getParent()).getPersonID(), new ConnectionLine(this, circle));
		initConnectionLineListener(circle, connectionCircles.get(((FullPerson) circle.getParent()).getPersonID()));
	}

	private void initConnectionLineListener(Circle circle, ConnectionLine connectionLine) {
		connectionLine.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.SECONDARY) {
					if (circle.getId().equals("outputCircle")) {
						outputCirclesCount--;
					}
					PaneControlUtil.getAncientTreePaneController().getPersonMainPane().getChildren().remove(connectionLine);
					connectionCircles.remove(((FullPerson) circle.getParent()).getPersonID());
				}
			}
		});

		connectionLine.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.DELETE) {
					if (circle.getId().equals("outputCircle")) {
						outputCirclesCount--;
					}
					PaneControlUtil.getAncientTreePaneController().getPersonMainPane().getChildren().remove(connectionLine);
					connectionCircles.remove(((FullPerson) circle.getParent()).getPersonID());
				}
			}
		});
	}

	private void initAncientConnectorListeners(Circle circle) {

		circle.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				requestFocus();
				PaneControlUtil.getAncientTreePaneController().clearTargetPerson();
			}
		});

		circle.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (circle.getLayoutX() < 25) {
					circle.setLayoutX(50);
				}
				if (circle.getLayoutY() < 25) {
					circle.setLayoutY(50);
				}
				if (circle.getLayoutX() < 25 && circle.getLayoutY() < 25) {
					circle.setLayoutX(50);
					circle.setLayoutY(50);
				}
				PaneControlUtil.getAncientTreePaneController().repaintConnections();
			}
		});

		circle.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				requestFocus();
				PaneControlUtil.getAncientTreePaneController().repaintConnections();
			}
		});

		circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				requestFocus();
				if (e.getButton() == MouseButton.SECONDARY) {
					for (Entry<Integer, ConnectionLine> entry : connectionCircles.entrySet()) {
						PaneControlUtil.getAncientTreePaneController().getPersonMainPane().getChildren().remove(entry.getValue());
					}
					PaneControlUtil.getAncientTreePaneController().getPersonMainPane().getChildren().remove(circle);
					PaneControlUtil.getAncientTreePaneController().getPersonMainPane().getAncientConnectionsList().remove(circle);
				}
			}
		});

	} // initListeners end

	public int getAncientConnectorID() {
		return ancientConnectorID;
	}

	public void setAncientConnectorID(int ancientConnectorID) {
		this.ancientConnectorID = ancientConnectorID;
	}

	public void removeConnectionWithPerson(FullPerson person) {
		try {
			if (connectionCircles.get(person.getPersonID()).getOutputCircle().getId().equals("outputCircle")) {
				outputCirclesCount--;
			}
		} catch (Exception e) {
			System.out.println("У удаляемого Person нет соединений");
		}
		PaneControlUtil.getAncientTreePaneController().getPersonMainPane().getChildren().remove(connectionCircles.get(person.getPersonID()));
		connectionCircles.remove(person.getPersonID());
	}

	public Hashtable<Integer, ConnectionLine> getConnectionCircles() {
		return connectionCircles;
	}

	public void setConnectionCircles(Hashtable<Integer, ConnectionLine> connectionCircles) {
		this.connectionCircles = connectionCircles;
	}

}// class end
