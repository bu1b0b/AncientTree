package ru.ancienttree.chronicler;

import java.io.Serializable;
import java.util.ArrayList;

public class AncientTreeChronicler implements Serializable {

	private static final long serialVersionUID = 5074884277680058972L;
	private double AncientTreeChroniclerVersion = 1.0;

	private int ATUnitCounter;

	private double scrollBarHorizontalValue;
	private double scrollBarVerticalValue;

	private ArrayList<MiniPerson> miniPersons;
	private ArrayList<MiniAncientConnector> miniAncientConnectors;

	public AncientTreeChronicler() {
		initAncientTreeChronicler();
	}

	private void initAncientTreeChronicler() {
		miniPersons = new ArrayList<MiniPerson>();
		miniAncientConnectors = new ArrayList<MiniAncientConnector>();
	}

	public int getATUnitCounter() {
		return ATUnitCounter;
	}

	public void setATUnitCounter(int aTUnitCounter) {
		ATUnitCounter = aTUnitCounter;
	}

	public double getScrollBarHorizontalValue() {
		return scrollBarHorizontalValue;
	}

	public void setScrollBarHorizontalValue(double scrollBarHorizontalValue) {
		this.scrollBarHorizontalValue = scrollBarHorizontalValue;
	}

	public double getScrollBarVerticalValue() {
		return scrollBarVerticalValue;
	}

	public void setScrollBarVerticalValue(double scrollBarVerticalValue) {
		this.scrollBarVerticalValue = scrollBarVerticalValue;
	}

	public ArrayList<MiniAncientConnector> getMiniAncientConnectors() {
		return miniAncientConnectors;
	}

	public void setMiniAncientConnectors(ArrayList<MiniAncientConnector> miniAncientConnectors) {
		this.miniAncientConnectors = miniAncientConnectors;
	}

	public ArrayList<MiniPerson> getMiniPersons() {
		return miniPersons;
	}

	public void setMiniPersons(ArrayList<MiniPerson> miniPersons) {
		this.miniPersons = miniPersons;
	}

	public double getAncientTreeChroniclerVersion() {
		return AncientTreeChroniclerVersion;
	}

	public void setAncientTreeChroniclerVersion(double ancientTreeChroniclerVersion) {
		AncientTreeChroniclerVersion = ancientTreeChroniclerVersion;
	}

} // class end
