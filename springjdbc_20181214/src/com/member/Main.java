package com.member;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// ȸ������ v2.0
				// 1.ȸ�������Է�  2.ȸ��������ü���  3.����

				Scanner sc = new Scanner(System.in);
				MemberService s = new MemberService();

				while (true) {
					System.out.println();
					System.out.println("** ȸ������ v2.0 **");
					System.out.println("1.ȸ�������Է�  2. ȸ����������  3.ȸ��������ü���  4.ȸ�������˻� 5.����");
					System.out.print("����>");
					int m = sc.nextInt();
					sc.nextLine();

					if (m == 0 || m == 5) {
						break;
					}
					switch (m) {
					case 1:
						s.menu1(sc);
						break;
					case 2:
						s.menu2(sc);
						break;
					case 3:
						s.menu3();
						break;
					case 4:
						s.menu4(sc);
						break;
					}
				}
				sc.close();
				System.out.println("���α׷� ����");
	}

}
