import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import edu.fudan.ml.types.Dictionary;
import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.cn.tag.POSTagger;

public class PrepareTrainData {
	public CWSTagger cwsTagger;
	public POSTagger posTagger;
	
	public PrepareTrainData() {
		try {
			this.cwsTagger = new CWSTagger("./models/seg.m", new Dictionary("models/dict.txt"));
			this.posTagger = new POSTagger(cwsTagger, "models/pos.m");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readContentFromFile(String fileName,String outputFileName) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		int startIndex=0;
		int endIndex=0;
		FileOutputStream fos = null;
		OutputStreamWriter osw=null;
		try {
			fileReader = new FileReader(fileName);
			 bufferedReader=new BufferedReader(fileReader);
			String tempString=null;
			fos = new FileOutputStream(outputFileName, true);
			osw = new OutputStreamWriter(fos, "utf-8");
			
			while((tempString=bufferedReader.readLine())!=null) {
				if(tempString.startsWith("<content>")) {
					startIndex=tempString.indexOf("<content>");
					endIndex=tempString.indexOf("</content>");
					tempString=tempString.substring(startIndex+9, endIndex);
//					��tempString���зִʺʹ��Ա�ע�������ݴ��Խ��й���
					osw.write(this.segmenteStr(tempString)+" ");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fileReader.close();
				bufferedReader.close();
				fos.flush();
				osw.flush();
				fos.close();
				osw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public String segmenteStr(String original) {
		StringBuilder stringBuilder=new StringBuilder();
		if(original!=null&&original.length()!=0) {
			String tagStr=this.posTagger.tag(original);
			String[]tokens=tagStr.split(" ");
			for(String word:tokens) {
				if(!(word.contains("����")||word.contains("���")||word.contains("���")||
						word.contains("��ַ")||word.contains("����")||word.contains("������")||word.contains("̾��"))) {
					stringBuilder.append(word.split("/")[0]+" ");
				}
			}
		}
		return stringBuilder.toString();
	}
}
