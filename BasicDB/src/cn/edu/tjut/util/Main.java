package cn.edu.tjut.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
	public static void main(String[] args){
		String allStr = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		Map<String,Student> stds = new HashMap<String,Student>();
		for(int i=0;i<100;i++){
			char a = allStr.charAt(Math.abs(rand.nextInt()) % 26);
			char b = allStr.charAt(Math.abs(rand.nextInt()) % 26);
			char c = allStr.charAt(Math.abs(rand.nextInt()) % 26);
			Student std = new Student(a+""+b+c,rand.nextInt(),rand.nextDouble());
			stds.put(i + "", std);
		}
		/*try {
			Student.saveStudents(stds);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		for(int i=0;i<100;i++){
			Student std = Student.loadStudent(i+"");
			System.out.println(std);
		}
		
	}
}
