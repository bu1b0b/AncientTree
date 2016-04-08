package ru.ancienttree.chronicler;

import java.io.Serializable;
import java.time.LocalDate;

import ru.ancienttree.interfaces.Person;

public class MiniPerson implements Person, Serializable {

	private static final long serialVersionUID = -1587421156860070989L;

	private int personID;

	private double personLayoutX;
	private double personLayoutY;

	private String name1;
	private String name2;
	private String name3;

	private LocalDate firstDate;
	private LocalDate lastDate;

	public MiniPerson() {

	}

	public double getPersonLayoutX() {
		return personLayoutX;
	}

	public void setPersonLayoutX(double personLayoutX) {
		this.personLayoutX = personLayoutX;
	}

	public double getPersonLayoutY() {
		return personLayoutY;
	}

	public void setPersonLayoutY(double personLayoutY) {
		this.personLayoutY = personLayoutY;
	}

	@Override
	public String getName1() {
		return name1;
	}

	@Override
	public void setName1(String name1) {
		this.name1 = name1;
	}

	@Override
	public String getName2() {
		return name2;
	}

	@Override
	public void setName2(String name2) {
		this.name2 = name2;
	}

	@Override
	public String getName3() {
		return name3;
	}

	@Override
	public void setName3(String name3) {
		this.name3 = name3;
	}

	@Override
	public LocalDate getFirstDate() {

		return firstDate;
	}

	@Override
	public LocalDate getLastDate() {
		return lastDate;
	}

	@Override
	public void setFirstDate(LocalDate firstDate) {
		this.firstDate = firstDate;
	}

	@Override
	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

} // class end
