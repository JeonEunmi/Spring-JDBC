package com.scores;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

//���� �޴��� �׼� Ŭ����
public class ScoreService {

	private ScoreDAO scoreDAO;

	public void setScoreDAO(ScoreDAO scoreDAO) {
		this.scoreDAO = scoreDAO;
	}

	// ���� ���� �Է�
	public void menu1(Scanner sc) {
		System.out.println("�ű� ���� ������ �Է��ϴ� �����Դϴ�.");
		System.out.print("�̸�>");
		String name_ = sc.nextLine();
		System.out.print("����1>");
		int subject1 = sc.nextInt();
		sc.nextLine();
		System.out.print("����2>");
		int subject2 = sc.nextInt();
		sc.nextLine();
		System.out.print("����3>");
		int subject3 = sc.nextInt();
		sc.nextLine();
		Score temp = new Score(null, name_, subject1, subject2, subject3, 0, 0.0, 0);
		int result = this.scoreDAO.scoreAdd(temp);
		System.out.printf("%s �� ��(��) ���ԵǾ����ϴ�.%n", result);
	}

	// ���� ���� ���
	public void menu2() {
		List<Score> list = this.scoreDAO.scoreList("all", "");
		this.printScores(list);
	}

	// ���� ���� �˻�
	public void menu3(Scanner sc) {
		// ����޴� �
		// 1.��ȣ���� 2.�̸����� 3.����
		while (true) {
			System.out.println();
			System.out.println("**�������� v2.0 ** > 3.���������˻�");
			System.out.println("1.��ȣ����  2.�̸�����  3.����");
			System.out.print("����>");
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0 || m == 3) {
				break;
			}
			switch (m) {
			case 1:
				this.menu3_sub1(sc);
				break;
			case 2:
				this.menu3_sub2(sc);
				break;
			}
		}
	}

	// ���� ���� �˻� > 1.��ȣ
	private void menu3_sub1(Scanner sc) {
		System.out.print("��ȣ>");
		String sid_ = sc.nextLine();
		List<Score> list = this.scoreDAO.scoreList("sid_", sid_);
		if (list.size() > 0) {
			this.printScores(list);
		} else {
			System.out.println("�˻� ����� �����ϴ�.");
		}
	}

	// ���� ���� �˻� > 2.�̸�
	private void menu3_sub2(Scanner sc) {
		System.out.print("�̸�>");
		String name_ = sc.nextLine();
		List<Score> list = this.scoreDAO.scoreList("name_", name_);
		if (list.size() > 0) {
			this.printScores(list);
		} else {
			System.out.println("�˻� ����� �����ϴ�.");
		}
	}

	// ���� ��� ���� private �޼ҵ�
	private void printScores(List<Score> list) {
		System.out.println("��ȣ / �̸� / ����1 / ����2 / ����3 / ���� / ��� / ����");
		System.out.println("--------------------------------------------------------");
		for (Score s : list) {
			System.out.println(s.toString());
		}
		System.out.println("----------");
		System.out.printf("�� %s��%n", list.size());
	}

}
