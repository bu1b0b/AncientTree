package ru.ancienttree.person;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import ru.ancienttree.main.PaneControlUtil;

public class PersonPane extends Pane {

	private ArrayList<FullPerson> personsList;
	private ArrayList<AncientConnector> ancientConnectionsList;

	public PersonPane() {
		super();
		this.setId("personMainPane");
		this.setFocusTraversable(true);

		personsList = new ArrayList<FullPerson>();
		ancientConnectionsList = new ArrayList<AncientConnector>();

		initListeners(this);
	}

	private void initListeners(Pane personMainPane) {
		personMainPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getPickResult().getIntersectedNode().getClass().equals(personMainPane.getClass())) {
					PaneControlUtil.getAncientTreePaneController().clearTargetPerson();
				}
			}
		});
	}

	public void addNewFullPerson(FullPerson person) {
		personsList.add(person);
	}
	public void removeFullPerson(FullPerson person) {
		personsList.remove(person);
	}

	public ArrayList<FullPerson> getPersonsList() {
		return personsList;
	}

	public void setPersonsList(ArrayList<FullPerson> personsList) {
		this.personsList = personsList;
	}

	public ArrayList<AncientConnector> getAncientConnectionsList() {
		return ancientConnectionsList;
	}

	public void setAncientConnectionsList(ArrayList<AncientConnector> ancientConnectionsList) {
		this.ancientConnectionsList = ancientConnectionsList;
	}

} // class end
