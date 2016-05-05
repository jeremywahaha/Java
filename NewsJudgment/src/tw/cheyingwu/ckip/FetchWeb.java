package tw.cheyingwu.ckip;
import java.lang.String;
import java.util.ArrayList;
import java.io.* ;
import java.net.*;

public class FetchWeb {
	public static void main(String[] args) throws IOException {
		
		String htm;
		String target = "http://www.appledaily.com.tw/realtimenews/article/local/";
		String con = null;
		URL uu = new URL("http://www.appledaily.com.tw/realtimenews/section/local/");
		Object objj=uu.getContent();
		InputStreamReader isrr=new InputStreamReader((InputStream) objj,"UTF-8");      
	    BufferedReader brr=new BufferedReader(isrr);
	    while((htm=brr.readLine())!=null)
	    {
	    	con += htm + '\n';
	    }

	    int cutpoint11 = 1;
	    while(cutpoint11 != -1)
	    {
	    	String tmpp,anss;
			String cut11 = "/realtimenews/article/local/";
			cutpoint11 = con.indexOf(cut11);
			tmpp = con.substring(cutpoint11 + 28, con.length()).trim();
			anss = target + tmpp.substring(0, 16).trim();
			con = tmpp;
		    if(cutpoint11 == -1)
		    	break;
		    //System.out.println(anss + "\n");
		    
		    
			String str;
			String content = null;
			URL u = new URL(anss);
			Object obj=u.getContent();
			InputStreamReader isr=new InputStreamReader((InputStream) obj,"UTF-8");      
		    BufferedReader br=new BufferedReader(isr);
		    while((str=br.readLine())!=null)
		    {
		    	content += str + '\n';
		    }
		    
		    
		    String tmp,ans;
			String cut1 = "p id=\"summary\" style=\"word-wrap: break-word";
			String cut2 = "</p>";
		    int cutpoint1,cutpoint2;
			cutpoint1 = content.indexOf(cut1);
			tmp = content.substring(cutpoint1+46, content.length()).trim();
			cutpoint2 = tmp.indexOf(cut2);
			ans = tmp.substring(0, cutpoint2).trim();
			String cut3 = "<",cut4 = ">",add1,add2;
			int cutpoint3 = 1,cutpoint4;
			while(cutpoint3 != -1)
			{
				cutpoint3 = ans.indexOf(cut3);
				cutpoint4 = ans.indexOf(cut4);
				if(cutpoint3 == -1)
					break;
				add1 = ans.substring(0, cutpoint3).trim();
				add2 = ans.substring(cutpoint4 + 1, ans.length()).trim();
				ans = add1 + add2;
			}
			//System.out.println(ans);
			
			FileWriter fr1 = new FileWriter("output.txt",true);
	        BufferedWriter bw = new BufferedWriter(fr1);
			WordSegmentationService c;
	        c = new CKIP("ir.itc.ntnu.edu.tw" ,1501, "ntnucsie106", "NtNuCsie106");
	        ArrayList<String> inputList = new ArrayList<String>();
	        ArrayList<String> TagList = new ArrayList<String>();
	        c.setRawText(ans);
	        c.send();
	         
	        for (Term t : c.getTerm()) {
	            inputList.add(t.getTerm());
	            TagList.add(t.getTag());
	        }
	         
	        try {
	            
	            for(int i=0;i<inputList.size();i++)
	            {
	            	
	                bw.write(inputList.get(i));
	                bw.write(" ");
	                
	                //bw.write("\t"+TagList.get(i));
	                //bw.newLine();
	            }
	            bw.close();
	             
	             
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	    }
		
	}
}
