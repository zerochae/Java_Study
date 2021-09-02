package kr.or.ddit.basic;

import java.util.HashSet;

public class EqualsHashcodeTest {

	public static void main(String[] args) {

		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");

		Person p2 = new Person();
		p2.setId(1);
		p2.setName("홍길동");

		Person p3 = p1;

		System.out.println(p1.equals(p2));

		System.out.println("===========================================");

		HashSet<Person> testSet = new HashSet<Person>();
		testSet.add(p1);
		testSet.add(p2);

		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(p3.hashCode());
	}

}

class Person {

	private int id;

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

	private String name;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		// 같은 유형의 클래스인지 검사
		if (this.getClass() != obj.getClass()) {
			return false;
		}

		// 매개변수의 값을 현재 객체유형으로 형변환
		Person temp = (Person) obj;

		if (this.name == null && temp.name != null) {
			return false;
		}

		if (this.id == temp.id && this.name == temp.name) {
			return true;
		}
		if (this.id == temp.id && this.name.equals(temp.name)) {
			return true;
		}
		return false;

	}

	@Override
	public int hashCode() {
		if (name != null) {
			return ("" + id + name).hashCode();
		} else {
			return ("" + id).hashCode();
		}
	}
}
