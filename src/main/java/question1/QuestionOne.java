/*
There is a file containing a word and its possible meanings (like a Dictionary). The contents of the file look like this:

Apple – a fruit, a tech firm
Table – an object, contains rows and columns when used in context of computers
Orange – a fruit

Given a path to the file, do the following:

a)    Create a method called doesFileExist(String path) which takes the path of the file and tells the user if the file exists at that path or not. Assume all paths are relative to your project structure. If the file does not exist, catch the requisite exception.
b)    Read each word and its possible meanings and print them out. Your output should look like this:

Word1
Meaning 1
Meaning 2
Word2
Meaning1
Meaning2
*/

package question1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QuestionOne {

	public static void main(String[] args) {
		
		String path = "resource//Dictionary.txt";
		doesFileExist(path);

	}
	
	public static void doesFileExist(String path) {
			File file = new File(path);
			BufferedReader br;
			try {
				if(!file.exists()) {
					System.out.println("File not found");
				}else {
					br = new BufferedReader(new FileReader(file));
					String str;
					while((str = br.readLine()) != null) {
						String[] line = str.split("-");
						String word = line[0];
						String[] meaning = line[1].split(",");
						System.out.println(word);
						for(String st : meaning) {
							System.out.println(st);
						}
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		
	}

}
