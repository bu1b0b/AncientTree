package ru.ancienttree.chronicler;

import java.io.Serializable;
import java.util.Hashtable;

public class MiniAncientConnector implements Serializable {

	private static final long serialVersionUID = 1546212404456477794L;

	private double ancientConnectorLayoutX;
	private double ancientConnectorLayoutY;

	private int ancientConnectorID;

	private Hashtable<Integer, String> connectionCircles;

	public MiniAncientConnector() {

		initMiniAncientConnector();

	}

	private void initMiniAncientConnector() {
		connectionCircles = new Hashtable<Integer, String>();
	}

	public double getAncientConnectorLayoutX() {
		return ancientConnectorLayoutX;
	}

	public void setAncientConnectorLayoutX(double ancientConnectorLayoutX) {
		this.ancientConnectorLayoutX = ancientConnectorLayoutX;
	}

	public double getAncientConnectorLayoutY() {
		return ancientConnectorLayoutY;
	}

	public void setAncientConnectorLayoutY(double ancientConnectorLayoutY) {
		this.ancientConnectorLayoutY = ancientConnectorLayoutY;
	}

	public Hashtable<Integer, String> getConnectionCircles() {
		return connectionCircles;
	}

	public void setConnectionCircles(Hashtable<Integer, String> connectionCircles) {
		this.connectionCircles = connectionCircles;
	}

	public int getAncientConnectorID() {
		return ancientConnectorID;
	}

	public void setAncientConnectorID(int ancientConnectorID) {
		this.ancientConnectorID = ancientConnectorID;
	}

} // class end
