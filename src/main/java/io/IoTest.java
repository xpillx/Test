package io;

import java.io.*;
import java.util.Scanner;

public class IoTest {

	public static void main(String[] args) throws IOException {
		// bufferReadline();
		// bufferRead();
		// fileWriteAndRead();
		//createFile();
		// scannerInput();
		isdictronay("F:1");
	}

	private static void bufferRead() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char c = ' ';
		while (c != 'e') {
			c = (char) br.read();
			System.out.print(c);
		}
	}

	private static void bufferReadline() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		while (!str.equals("end")) {
			str = br.readLine();
			System.out.print(str);
		}
	}

	private static void fileWriteAndRead() throws IOException {
		File file = new File("F:file.txt");
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
		osw.append("aaaaa");
		osw.append("\r\n");
		osw.append("bbbbbb");
		osw.close();
		fos.close();

		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "utf-8");
		StringBuffer sb = new StringBuffer();
		while (isr.ready()) {
			sb.append((char) isr.read());
		}
		System.out.println(sb.toString());
		isr.close();
		fis.close();
	}

	private static void createFile() {
		String st = "F:1/2/3/4/5";
		File file = new File(st);
		file.mkdirs();
	}

	private static void scannerInput() {
		Scanner scan = new Scanner(System.in);

		if (scan.hasNextLine()) {
			String str2 = scan.nextLine();
			System.out.println("hasNextLine输入的数据为：" + str2);
		}
		// if (scan.hasNext()) {
		// String str1 = scan.next();
		// System.out.println("hasNext输入的数据为：" + str1);
		// }

	}

	private static void isdictronay(String dir) {
		//String dirname = "F:1";
		File f1 = new File(dir);
		if (f1.isDirectory()) {
			System.out.println("目录 " + dir);
			String s[] = f1.list();
			for (int i = 0; i < s.length; i++) {
				File f = new File(dir + "/" + s[i]);
				if (f.isDirectory()) {
					System.out.println(s[i] + " 是一个目录");
					isdictronay(dir + "/" + s[i]);
				} else {
					System.out.println(s[i] + " 是一个文件");
				}
			}
		} else {
			System.out.println(dir + " 不是一个目录");
		}
	}

}
