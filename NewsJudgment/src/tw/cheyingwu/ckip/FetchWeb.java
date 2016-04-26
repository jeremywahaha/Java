package tw.cheyingwu.ckip;
import java.lang.String;
import java.util.ArrayList;
import java.io.* ;
import java.net.*;

public class FetchWeb {
	public static void main(String[] args) throws IOException {
		
		
		String str;
		String content = null;
		URL u = new URL("http://ent.appledaily.com.tw/realtimenews/article/entertainment/20160325/824792/%E7%A7%80%E8%98%AD%E7%91%AA%E9%9B%85%E5%80%8B%E5%94%B1%E5%88%9D%E7%99%BB%E5%A0%B4%E3%80%80%E9%80%BC%E4%BA%BA%E6%8D%A8%E6%A3%84%E5%B0%8F%E5%AD%A9");
		Object obj=u.getContent();
		InputStreamReader isr=new InputStreamReader((InputStream) obj,"UTF-8");      
	    BufferedReader br=new BufferedReader(isr);
	      
	    while((str=br.readLine())!=null)
	    {
	    	content += str + '\n';
	    }
	    
	    //System.out.println(ans);
	    String tmp,ans;
		String cut1 = "p id=\"summary\" style=\"word-wrap: break-word";
		String cut2 = "<span style";
	    int cutpoint1,cutpoint2;
		cutpoint1 = content.indexOf(cut1);
		tmp = content.substring(cutpoint1+46, content.length()).trim();
		cutpoint2 = tmp.indexOf(cut2);
		ans = tmp.substring(0, cutpoint2).trim();
		
		WordSegmentationService c;
        ArrayList<String> inputList = new ArrayList<String>();
        ArrayList<String> TagList = new ArrayList<String>();

        c = new CKIP("ir.itc.ntnu.edu.tw" ,1501, "ntnucsie106", "NtNuCsie106");
         
        c.setRawText(ans);
        c.send();
         
        for (Term t : c.getTerm()) {
           
            inputList.add(t.getTerm());
            TagList.add(t.getTag());
        }
         
        try {
            FileWriter fr1 = new FileWriter("output.txt");
            BufferedWriter bw = new BufferedWriter(fr1);
            for(int i=0;i<inputList.size();i++)
            {
                bw.write(inputList.get(i));
                bw.write("\t"+TagList.get(i));
                bw.newLine();
            }
            bw.close();
             
             
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
}
