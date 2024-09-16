package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class NewFrame2 extends JFrame {
	List<String[]> miss = new ArrayList<>();
	int corrent = 0;
	int uncorrent = 0;
	Set<Integer> textNum;
	int problemNumber = 0; 
	boolean TextOrAns = true;
	Problem problem = new Problem();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JButton b1 = new JButton("1");
	JButton b2 = new JButton("2");
	JButton b3 = new JButton("3");
	JButton b4 = new JButton("4");
	JButton b5 = new JButton("5");
	JButton b6 = new JButton("6");
	JButton b7 = new JButton("削除");
	JButton b8 = new JButton("決定");
	JTextPane problemText = new JTextPane();
	JTextPane ansText = new JTextPane();
	JTextField result = new JTextField();
	public NewFrame2() {
		super("test");
		this.setLayout(new GridLayout(4,1));
		this.add(problemText);
		problemText.setText("ここには問題が出ます\n"
				+ "左上が問題の種類です\n"
				+ "記述の場合はボタンの上の欄に直接入力してください\n"
				+ "それ以外の場合は入力するかボタンで入力してください\n"
				+ "入力後、決定を押すと正解が出ます\n"
				+ "（Enterで決定できません）");
		this.add(ansText);
		ansText.setText("ここには答えが出ます\n"
				+ "答えが出た後に決定を押すと次の問題に移ります");
		this.add(result);
		this.add(p2);
		p2.setLayout(new GridLayout(2,4));
		p2.add(b1);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				result.setText(result.getText() + 1);
			}
			
		});
		p2.add(b2);
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				result.setText(result.getText() + 2);
			}
			
		});
		p2.add(b3);
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				result.setText(result.getText() + 3);
			}
			
		});
		p2.add(b4);
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				result.setText(result.getText() + 4);
			}
			
		});
		p2.add(b5);
		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				result.setText(result.getText() + 5);
			}
			
		});;
		p2.add(b6);
		b6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				result.setText(result.getText() + 6);
			}
			
		});
		p2.add(b7);
		b7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				try {
				String str = result.getText();
				str = str.substring(0,str.length() - 1);
				result.setText(str);
				}catch(java.lang.StringIndexOutOfBoundsException exce) {
					exce.printStackTrace();
				}
			}
			
		});
		p2.add(b8);
		b8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				if(problem.problems.size() > problemNumber) {
					if(TextOrAns == true) {
						result.setText("");
						ansText.setText("");
						switch(problem.problems.get(problemNumber)[0]) {
							case "記述" ->{
								TextDescription(problem.problems.get(problemNumber));
							}
							case "選択" ->{
								textNum = TextChoice(problem.problems.get(problemNumber));
							}
							case "完答" ->{
								textNum = TextPerfect(problem.problems.get(problemNumber));
							}
							case "並べ替え"->{
								textNum = TextSort(problem.problems.get(problemNumber));
							}
						}
						TextOrAns = false;
					}else if (TextOrAns == false) {
						boolean ans = false;
						switch(problem.problems.get(problemNumber)[0]) {
							case "記述" ->{
								ans = problem.description(problem.problems.get(problemNumber),result.getText());
							}
							case "選択" ->{
								ans = problem.choice(problem.problems.get(problemNumber),result.getText(),textNum);
							}
							case "完答" ->{
								ans = problem.perfect(problem.problems.get(problemNumber),result.getText(),textNum);
							}
							case "並べ替え"->{
								ans = problem.sort(problem.problems.get(problemNumber),result.getText(),textNum);
							}
						}
						if(ans == true) {
							ansText.setText("正解　");
							corrent++;
						}else {
							ansText.setText("不正解　");
							uncorrent++;
							miss.add(problem.problems.get(problemNumber));
						}
						switch(problem.problems.get(problemNumber)[0]) {
						case "記述" ->{
							ansText.setText(ansText.getText() + problem.problems.get(problemNumber)[2]);
						}
						case "選択" ->{
							ansText.setText(ansText.getText() + problem.problems.get(problemNumber)[2]);
							ansText.setText(ansText.getText() + problem.problems.get(problemNumber)[4]);
						}
						case "完答" ->{
							ansText.setText(ansText.getText() + problem.problems.get(problemNumber)[2]);
						}
						case "並べ替え" ->{
							ansText.setText(ansText.getText() + problem.problems.get(problemNumber)[2]);
						}
						}
						ansText.setText(ansText.getText() + "決定を押すと次の問題に移ります");
						TextOrAns = true;
						textNum = null;
						problemNumber++;
					}
				}else if(miss.size() > 0){
					result.setText("");
					ansText.setText("");
					problemText.setText("全て終わりました\n"
							+ "正解した問題は" + corrent 
							+"不正解の問題は"  + uncorrent
							+"ここからは不正解の問題タイム"
							);
					problemNumber = 0;
					corrent = 0;
					uncorrent = 0;
					problem.problems = miss;
					miss = null;
				}else {
					System.out.println("ほんとに終わり");
				}
			}
			
		});
		this.setSize(500, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×ボタンを押したときの動き　閉じたらプログラム停止
		this.setLocationRelativeTo(null);//ウィンドウの表示位置
		this.setVisible(true);
		
	}
	public void TextDescription(String[] problem) {
			problemText.setText("記述問題\n");
			problemText.setText(problemText.getText() + problem[3]);
	}
	public Set<Integer> TextChoice(String[] problem) {
		problemText.setText("選択問題\n");
		problemText.setText(problemText.getText() + problem[3]);
		Set<Integer> textNumber = rundom(problem);
		int count = 1;
		for(int j : textNumber) {//rundomメソッドで作った数字に対応する行の選択肢を出力する
			problemText.setText(problemText.getText() + (count + " : " + problem[j + 4]));
			count++;
		}
		return textNumber;
	}
	public Set<Integer> TextPerfect(String[] problem) {
		problemText.setText("完答問題\n");
		problemText.setText(problemText.getText() + problem[3]);
		Set<Integer> textNumber = rundom(problem);
		int count = 1;
		for(int j : textNumber) {//rundomメソッドで作った数字に対応する行の選択肢を出力する
			problemText.setText(problemText.getText() + count + " : " + problem[j + 4]);
			count++;
		}
		return textNumber;
	}
	public Set<Integer> TextSort(String[] problem) {
		problemText.setText("並べ替え\n");
		problemText.setText(problemText.getText() + problem[3]);
		Set<Integer> textNumber = rundom(problem);
		int count = 1;
		for(int j : textNumber) {//rundomメソッドで作った数字に対応する行の選択肢を出力する
			System.out.println(count + " : " + problem[j + 4]);
			problemText.setText(problemText.getText() + count + " : " + problem[j + 4]);
			count++;
		}
		return textNumber;
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
