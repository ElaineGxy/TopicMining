package SinglePass;

import java.io.IOException;
import java.util.Hashtable;

import org.fnlp.app.keyword.AbstractExtractor;
import org.fnlp.app.keyword.WordExtract;

import com.ansj.vec.Word2VEC;

import edu.fudan.ml.types.Dictionary;
import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.cn.tag.POSTagger;
import edu.fudan.nlp.corpus.StopWords;
import edu.fudan.util.exception.LoadModelException;

/**
 * 创建提取关键词并对此关键词进行向量化的类
 * @author gaoxy
 *
 * 2017年7月27日
 */
public class KeywordAndVectorized {
	private static Hashtable<String, float[]> dict=null;//存放当前遇到过的所有关键词的向量
	public static CWSTagger cwsTagger=null;
	public static POSTagger posTagger=null;
	public static StopWords stopWords=null;
	public static AbstractExtractor keyExtractor=null;
	public static Word2VEC word2Vec =null;
	
	public static Hashtable getDict() {
		if(dict==null) {
			dict=new Hashtable();
		}
		return dict;
	}
	/**
	 * Add word to dict
	 * @param word
	 * @param value
	 * @return true if add successfully else return false;
	 */
	public static boolean addNewWord(String word,float[] value) {
		if(KeywordAndVectorized.getDict().containsKey(word)) {
			System.out.println("This word has already exist");
			return false;
		}else {
			int seqNo=KeywordAndVectorized.dict.size();
			KeywordAndVectorized.getDict().put(word, value);
			return true;
		}
	}
	public static CWSTagger getCwsTagger() {
		if(cwsTagger==null) {
			try {
				cwsTagger=new CWSTagger("./models/seg.m", new Dictionary("./models/dict.txt"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("Error: Instance CWSTagger");
				e.printStackTrace();
			}
		}
		return cwsTagger;
	}
	public static POSTagger getPosTagger() {
		if(posTagger==null) {
			try {
				posTagger=new POSTagger(cwsTagger, "models/pos.m");
			} catch (LoadModelException e) {
				System.err.println("Error: Instant POSTagger");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return posTagger;
	}
	public static StopWords getStopWords() {
		if(stopWords==null) {
			stopWords= new StopWords("models/stopwords");
		}
		return stopWords;
	}
	public static AbstractExtractor getKeyExtractor() {
		if(keyExtractor==null) {
			keyExtractor = new WordExtract(KeywordAndVectorized.getCwsTagger(),KeywordAndVectorized.getStopWords());
		}
		return keyExtractor;
	}
	public static Word2VEC getWord2Vec() {
		if(word2Vec==null) {
			word2Vec=new Word2VEC();
			try {
				word2Vec.loadJavaModel("library/javaSkip1");
			} catch (IOException e) {
				System.err.println("Error: Word2Vec load java model");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return word2Vec;
	}
	
}
