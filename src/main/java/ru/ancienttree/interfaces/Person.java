package ru.ancienttree.interfaces;

import java.time.LocalDate;

public interface Person {

	public void setFirstDate(LocalDate firstDate);

	public void setLastDate(LocalDate lastDate);

	public LocalDate getFirstDate();

	public LocalDate getLastDate();

	public String getName1();

	public void setName1(String name1);

	public String getName2();

	public void setName2(String name2);

	public String getName3();

	public void setName3(String name3);

	public String toString();

}
