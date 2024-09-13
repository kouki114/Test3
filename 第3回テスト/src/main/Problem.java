package main;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Problem extends Text{
	public List<String[]> problems = new ArrayList<>();
	public Problem() {
		Set<Integer> quizNumber = new LinkedHashSet<>();
		while(true) {
			int num = new java.util.Random().nextInt(text.length);//問題数分番号格納
			quizNumber.add(num);
			if(quizNumber.size() == text.length) {
				break;
			}
		}
		for(int i : quizNumber) {
			problems.add(text[i]);
		}
	}
	public  boolean description(String[] problem,String ans) {
			if(ans.equals(problem[1])) {
				return true;
			}else {
				return false;
			}
	}
	public boolean choice(String[] problem,String ansStr,Set<Integer> textNumber) {
		int ans = Integer.parseInt(ansStr);
		int ac = ansChenger(ans,textNumber);//解答として入力した数字をrundomメソッドで元の選択肢の順番に変換している
//		System.out.println(ac);
		if(problem[4].equals(problem[ac + 4])) {
			return true;
		} else {
			return false;
		}
	}
	public  boolean perfect(String[] problem,String ans , Set<Integer> textNumber) {
		int[] cutAns = new int[ans.length()];
		for(int i = 0; i < ans.length() ; i++) {
			cutAns[i] = ansChenger(Integer.parseInt(ans.substring(i,i + 1)),textNumber);
		}
//		System.out.println(ac);
		int good = 0;
		for(int j : cutAns) {
			for(int k = 0 ; k < Integer.parseInt(problem[1]) ; k++) {
				if(problem[4 + k].equals(problem[j + 4])) {
					good++;
				}
			}
		}
		if(good == Integer.parseInt(problem[1])) {
			return true;
		}else {
			return false;
		}
	}
	public boolean sort(String[] problem,String ans ,Set<Integer> textNumber) {
		int[] cutAns = new int[ans.length()];
		for(int i = 0; i < ans.length() ; i++) {
			try {
			cutAns[i] = ansChenger(Integer.parseInt(ans.substring(i,i + 1)),textNumber);
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("数字で答えて");
			}
		}
//		System.out.println(ac);
		int good = 0;
		for(int j = 0 ; j < cutAns.length ; j++) {
//			System.out.println(j);
//			System.out.println(cutAns[j]);
			if(problem[4 + j].equals(problem[cutAns[j] + 4])) {
				good++;
			}
		}
		if(good == Integer.parseInt(problem[1])) {
			return true;
		}else {
			return false;
		}
	}
	private int ansChenger(int i,Set<Integer> lhs) {
		//順番をランダム化させた問題のProblemクラス内にあった時の順盤の取得
		//このとき1番（5行目）のものが正解である
		
		int count = 1;
		for(int j : lhs) {
			if(i == count) {
				return j;
			}
			count++;
		}
		return -1;
	}
	private LinkedHashSet<Integer> rundom(String[] problem) {
//		問題の順盤をランダム化させるための順盤取得
//		ここで選択問題の選択肢の数だけ0から乱数をSetに格納
//		
		LinkedHashSet<Integer> num = new LinkedHashSet<Integer>();
		while(true) {
			for(int i = 0 ; i < (problem.length - 4) ; i++ ) {
				int number = new java.util.Random().nextInt(problem.length - 4);
				num.add(number);
			}
			if(num.size() == (problem.length - 4)) {
				break;
			}
		}
		return num;
	}
	
}
