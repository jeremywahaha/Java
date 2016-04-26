package tw.cheyingwu.ckip;

import java.util.ArrayList;
import java.util.List;

public abstract class WordSegmentationService {
	protected String rawText;
	protected String returnText;
	protected List<Term> term;

	public WordSegmentationService() {
		this.rawText = "";
		this.returnText = "";
		this.term = new ArrayList<Term>();
	}
	public String getRawText() {
		return rawText;
	}
	public void setRawText(String rawText) {
		this.rawText = rawText;
	}
	public String getReturnText() {
		return returnText;
	}
	protected void setReturnText(String returnText) {
		this.returnText = returnText;
	}
	public abstract List<Term> getTerm();

	protected void setTerm(List<Term> term) {
		this.term = term;
	}

	public abstract void send();

}