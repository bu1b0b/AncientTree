package ru.ancienttree.person;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import ru.ancienttree.main.PaneControlUtil;

public class ConnectionLine extends Line {

	private Circle circle1;
	private Circle circle2;

	public ConnectionLine(Circle circle1, Circle circle2) {
		super();
		this.setId("connectionLine");
		this.setFocusTraversable(true);
		this.circle1 = circle1;
		this.circle2 = circle2;
		initListeners(this);
		drawConnectionLine();
		PaneControlUtil.getAncientTreePaneController().getPersonMainPane().getChildren().add(this);
		this.toBack();
	}

	public Circle getOutputCircle() {
		return circle2;
	}

	public void drawConnectionLine() {
		this.setStartX(circle1.getLayoutX());
		this.setStartY(circle1.getLayoutY());
		this.setEndX(circle2.getParent().getLayoutX() + circle2.getLayoutX());
		this.setEndY(circle2.getParent().getLayoutY() + circle2.getLayoutY());
	}

	public void repaintConnectionLine() {
		this.setStartX(circle1.getLayoutX());
		this.setStartY(circle1.getLayoutY());
		this.setEndX(circle2.getParent().getLayoutX() + circle2.getLayoutX());
		this.setEndY(circle2.getParent().getLayoutY() + circle2.getLayoutY());
	}

	private void initListeners(Line line) {
		this.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (newPropertyValue) {
					line.setStyle("-fx-stroke: #18ffff; ");
					PaneControlUtil.getAncientTreePaneController().clearTargetPerson();
				} else if (oldPropertyValue) {
					line.setStyle("");
				}
			}
		});

		this.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton() == MouseButton.PRIMARY) {
					requestFocus();
				}
			}
		});

	}

}// class end
