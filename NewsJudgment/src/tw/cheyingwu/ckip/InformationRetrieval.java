package tw.cheyingwu.ckip;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.lang.String;


public class InformationRetrieval {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("Output.txt");
		BufferedReader br = new BufferedReader(fr);
		String input = "";
		String ht;
		while ((ht=br.readLine())!=null) {
			input += ht + '\n';
		}
		fr.close();
		System.out.println(input);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int cut,v,total = 1;
		String tmp;
		cut = input.indexOf(" ");
		while(cut != -1)
		{
			tmp = input.substring(0, cut).trim();
			if(map.containsKey(tmp) == true)
			{
				v = map.get(tmp);
				map.remove(tmp);
				map.put(tmp, v + 1);
			}
			else
			{
				map.put(tmp, 1);
			}
			
			input = input.substring(cut + 1, input.length()).trim();
			cut = input.indexOf(" ");
			total += 1;
		}
		
		ArrayList<Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() 
        {
        	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) 
        	{
            	return o2.getValue().compareTo(o1.getValue());
            }
        });
        
       for(Map.Entry<String,Integer> mapping:list)
       { 
            System.out.println(mapping.getKey() + ":" + ((float)mapping.getValue()/total)); 
       }
        
	}

}
