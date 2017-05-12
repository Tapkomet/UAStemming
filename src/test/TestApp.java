package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.tartarus.snowball.SnowballProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
public class TestApp {
	
	private static Map<String, String> index;
	
	static String resultFile = "Results.txt";
	static String startClass = "org.tartarus.snowball.ext.Ukrainian";
	static String testDataFile = "TestUkrainian.txt";

    public static void main(String [] args) throws Throwable {
	
    	index = new HashMap<String, String>();

    	Class stemClass = Class.forName(startClass);
        SnowballProgram stemmer = (SnowballProgram) stemClass.newInstance();


	InputStream instream;
	    instream = new FileInputStream(testDataFile);

        OutputStream outstream;
	    outstream = new FileOutputStream(resultFile);

	Reader reader = new InputStreamReader(instream);
	reader = new BufferedReader(reader);

	Writer output = new OutputStreamWriter(outstream);
	output = new BufferedWriter(output);
	Locale ukrainian = new Locale("UA");
	StringBuffer input = new StringBuffer();
	

	
	int character;
	while ((character = reader.read()) != -1) {
	    char ch = (char) character;
	    if (Character.isWhitespace(ch)) {
	    	
	    
	    
	    String reworked = input.toString().replaceAll("[^а-яА-ЯїЇґҐєЄіІ'-]", "").toLowerCase(ukrainian);

	    if(reworked.length()>0&&!Character.isWhitespace(reworked.charAt(0))){
		stemmer.setCurrent(reworked);
		stemmer.stem();
		
		addWord(reworked, stemmer.getCurrent());
		
	    }
		input.delete(0, input.length());
	    } else {
		input.append(ch);
	    }
	}
	
	for(String key: index.keySet()){
		if(index.get(key).length()>0){
		output.write(index.get(key)+" "+key + "\n");
		}
	}
	
	output.flush();
	
	 ArrayList<String> rows = new ArrayList<String>();
	    BufferedReader read = new BufferedReader(new FileReader(resultFile));

	    String s;
	    while((s = read.readLine())!=null)
	        rows.add(s);

	    Collections.sort(rows);

	    FileWriter writer = new FileWriter(resultFile);
	    for(String cur: rows)
	        writer.write(cur+"\n");

	    reader.close();
	    output.close();
	    read.close();
	    writer.close();
	
    }
    
    public static void addWord(String start, String stemmed){
    	if(!index.containsKey(start)){
    		index.put(start, stemmed);
    	}
    }
    
}
