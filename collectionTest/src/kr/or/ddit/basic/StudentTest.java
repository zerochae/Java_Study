package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class StudentTest {
	
	
	public static void main(String[] args) {
		
		ArrayList<Student> list = new ArrayList<>();
		
		list.add(new Student(1, "홍길동", 74, 89, 92));
		list.add(new Student(7, "강감찬", 67, 89, 97));
		list.add(new Student(2, "이순신", 74, 96, 92));
		list.add(new Student(5, "성춘향", 74, 96, 92));
		list.add(new Student(4, "일지매", 74, 89, 23));
		list.add(new Student(3, "변학도", 74, 85, 92));
		list.add(new Student(6, "김철수", 34, 89, 92));
		
		System.out.println("정렬전");
		for (Student student : list) {
			System.out.println(student);
		}
		
		System.out.println("====================================================================================");
		System.out.println("학번 오름차순 정렬");
		Collections.sort(list);
		for (Student student : list) {
			System.out.println(student);
		}
		
		Collections.sort(list,new scoreSort());
		
		System.out.println("=====================================================================================");
		System.out.println("총점순 오름차순");
		for (Student student : list) {
			System.out.println(student);
		}
		
		for (Student student : list) {
			int result = 1;
			for (Student student2 : list) {
				if(student.getTotalScore() < student2.getTotalScore()) {
					result ++;
				}
			}
			student.setResult(result);
		}

		System.out.println("=====================================================================================");
		System.out.println("등수");
		for (Student student : list) {
			System.out.println(student);
		}
	}
}

class Student implements Comparable<Student>{
	
	int num;
	String name;
	int korean;
	int english;
	int math;
	int totalScore;
	int result;
	
	public Student(int num, String name, int korean, int english, int math) {
		super();
		this.num = num;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.totalScore = korean + english + math;
	}
	
	public int getNum() {
		return num; 
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKorean() {
		return korean;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}

	public static void testResult(ArrayList<Student> list) {
	
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", korean=" + korean + ", english=" + english + ", math="
				+ math + ", totalScore=" + totalScore + ", result=" + result + "]";
	}
 
	
	@Override
	public int compareTo(Student o) {
		
		return this.getNum() - o.getNum();
	}
	
}
class scoreSort implements Comparator<Student>{
	
	@Override
	public int compare(Student o1, Student o2) {

		if(o1.getTotalScore() == o2.getTotalScore()) return o1.getName().compareTo(o2.getName());
		return (o1.getTotalScore() - o2.getTotalScore()) * -1;
	}
	
}
