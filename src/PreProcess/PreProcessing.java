package PreProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fnlp.app.keyword.AbstractExtractor;
import org.fnlp.app.keyword.WordExtract;

import edu.fudan.ml.types.Dictionary;
import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.cn.tag.NERTagger;
import edu.fudan.nlp.cn.tag.POSTagger;
import edu.fudan.nlp.corpus.StopWords;
import edu.fudan.util.exception.LoadModelException;

/**
 * @author gaoxy
 *
 *         2017��7��25�� contains��filter out short message��remove useless
 *         information��word segmentation
 */
public class PreProcessing {
	public POSTagger posTagger = null;
	public CWSTagger cwsTagger = null;
	public NERTagger nerTagger = null;
	public StopWords stopWord = null;

	public void readContentFromFile(String fileName) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		int startIndex = 0;
		int endIndex = 0;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String tempString = null;

			while ((tempString = bufferedReader.readLine()) != null) {
				if (tempString.startsWith("<content>")) {
					startIndex = tempString.indexOf("<content>");
					endIndex = tempString.indexOf("</content>");
					tempString = tempString.substring(startIndex + 9, endIndex);
					System.out.println(this.posTagger.tag(tempString));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Read File Done!");
	}

	public PreProcessing() {
		try {
			// ����Զ���ʵ�
			this.cwsTagger = new CWSTagger("./models/seg.m", new Dictionary("models/dict.txt"));
			this.posTagger = new POSTagger(cwsTagger, "models/pos.m");
			this.nerTagger = new NERTagger("./models/seg.m", "./models/pos.m");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����Ϣ���зִʣ������ݴ��ԶԵ��ʽ���ɸѡ�������̵���Ϣ���˵�
	 * 
	 * @param original
	 * @return List<String>:������������ʣ�µĴ��Լ�����(����\����)
	 */
	public String filterMeg(String original) {
		if (original == null || original.length() == 0)
			return null;
		String[] posWords = this.posTagger.tag(original).split(" "); // ���˵����ʡ���ʡ������� int
		int validNum = 0;
		for (String word : posWords) {
			if (word != null && word.length() != 0) {
				if (!(word.contains("����") || word.contains("���") || word.contains("���") || word.contains("��ַ")
						|| word.contains("����") || word.contains("������") || word.contains("̾��"))) {
					validNum++;
				}
			}
		}
		if (validNum >= 4)
			return original;
		else
			return null;
	}

/*	public Map<String,Integer> keywordExtraction(String original) {
		String content = null;
		stopWord = new StopWords("models/stopwords");
		AbstractExtractor key = new WordExtract(cwsTagger, stopWord);
		Map<String,Integer>keywordMap=key.extract(original, 5);
		return keywordMap;
	}*/

	public String removeInfo(String original) {
		return null;
	}

	// public Map<String,String>
	public void addToCustomDict(String cust_words) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			if (cust_words != null && cust_words.length() != 0) {
				String[] words = cust_words.split(" ");
				fos = new FileOutputStream("./models/dict.txt", true);
				osw = new OutputStreamWriter(fos, "utf-8");

				for (String word : words)
					osw.write('\n' + word);
				osw.close();
				fos.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadNewDist() {
		try {
			this.cwsTagger = new CWSTagger("./models/seg.m", new Dictionary("models/dict.txt"));
			this.posTagger = new POSTagger(cwsTagger, "models/pos.m");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
