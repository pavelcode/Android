package com.cblue.savadata.sqlite;

public class Student
{
	private int _id;
	private String name;
	private int age;

	public Student()
	{
		super();
	}

	public Student(int sid, String name, int age)
	{
		super();
		this._id = sid;
		this.name = name;
		this.age = age;
	}

	public int getSid()
	{
		return _id;
	}

	public void setSid(int sid)
	{
		this._id = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	@Override
	public String toString()
	{
		return "sid=" + _id + ";name=" + name + ";age=" + age;
	}
}
