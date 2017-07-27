package SinglePass;

import java.util.ArrayList;
import java.util.List;

public class TextVector {
	List<float[]>keywordVec;
	public String text;
	public TextVector(String text) {
		this.keywordVec=new ArrayList<float[]>();
		this.text=text;
	}
	/**
	 * ��������ǰ���ı�
	 */
	public void vectorizedText() {
		if(this.text==null||this.text.length()==0) return;
		//	�����ǹؼ���
		String keywords=KeywordAndVectorized.keyExtractor.extract(this.text, 20,true);
		//ʹ��word2vec��dict��������
		String[] key_words=keywords.split(",");
		float[]vec;
		for(String word:key_words) {
			word=word.split("=")[0];
			if(!KeywordAndVectorized.getDict().containsKey(word)) {
				//����word2vec���ɸùؼ��ʵ�����
				vec=KeywordAndVectorized.getWord2Vec().getWordVector(word);
				KeywordAndVectorized.addNewWord(word, vec);
			}else
				vec=(float[]) KeywordAndVectorized.getDict().get(word);
			this.keywordVec.add(vec);
		}
	}
	public List<float[]> getKeywordVec() {
		return keywordVec;
	}
	public String getText() {
		return text;
	}
	public float compare(TextVector vec) {
		float distance=0;
		List<float[]>list=vec.getKeywordVec();
		int size1=this.keywordVec.size();
		int size2=list.size();
		for(int i=0;i<size1;i++) {
			float tempDist=Float.MAX_VALUE;
			float[]word1=this.keywordVec.get(i);
			for(int j=0;j<size2;j++) {
				float[]word2=list.get(j);
				float wordDist=calCosDist(word1,word2);
				if(tempDist>wordDist)
					tempDist=wordDist;
			}
			distance+=tempDist;
		}
		float average=distance/(size1*size2);
		return distance;
	}

	/**
	 * ������������֮������Ҿ���
	 * @param word1
	 * @param word2
	 * @return
	 */
	private float calCosDist(float[] word1, float[] word2) {
		if(word1.length!=word2.length) {
			try {
				throw new Exception("DIFFER LENGTH ARE NOT ALLOWED");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int length=word1.length;
		float dist=0;
		float dist1=0,dist2=0;
		for(int i=0;i<length;i++) {
			dist=word1[i]*word2[i];
			dist1=word1[i]*word1[i];
			dist2=word2[i]*word2[i];
		}
		return (float) (dist/(Math.sqrt(dist1)*Math.sqrt(dist2)));
	}
}