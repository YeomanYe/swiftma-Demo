package cn.edu.tjut.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

public class Student {
	private String name;
	private int age;
	private double score;
	public static BasicDB db = null;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public Student(String name,int age,double score){
		this.name = name;
		this.age = age;
		this.score = score;
	}
	
	public Student(){
		
	}

	private static BasicDB  getDb(){
		if(null == db){
			try {
				db = new BasicDB("C:/Users/KINGBOOK/Desktop/","students");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return db;
	}
	
	private static byte[] toBytes(Student student) throws IOException{
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    DataOutputStream dout = new DataOutputStream(bout);
	    dout.writeUTF(student.getName());
	    dout.writeInt(student.getAge());
	    dout.writeDouble(student.getScore());
	    return bout.toByteArray();
	}
	
	private static Student toStudent(byte[] bArr){
		Student std = new Student();
		ByteArrayInputStream bin = new ByteArrayInputStream(bArr);
		DataInputStream din = new DataInputStream(bin);
		try {
			std.setName(din.readUTF());
			std.setAge(din.readInt());
			std.setScore(din.readDouble());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return std;
	}

	public static void saveStudents(Map<String, Student> students) throws IOException{
		db = getDb();
	    for(Map.Entry<String,Student> kv: students.entrySet()){
	        db.put(kv.getKey(),toBytes(kv.getValue()));
	    }
	    db.close();
	}
	
	public static Student loadStudent(String str){
		db = getDb();
		Student std = null;
		try {
			byte[] bArr = db.get(str);
			std = toStudent(bArr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return std;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", score=" + score + "]";
	}
	
	
}
