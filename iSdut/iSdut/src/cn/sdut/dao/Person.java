package cn.sdut.dao;

/**
 * 定义list中分数的存储
 * 
 */
public class Person implements Comparable<Person> {
	private int id;
	private String name;
	private String score;

	public Person() {
		super();
	}

	public Person(int i, String string, String string2) {
		super();
		this.id = i;
		this.name = string;
		this.score = string2;
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public int compareTo(Person arg0) {
		return this.name.compareTo(arg0.name);
	}

}
