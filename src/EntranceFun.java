import java.util.Map;

import org.fnlp.app.keyword.AbstractExtractor;
import org.fnlp.app.keyword.WordExtract;

import edu.fudan.nlp.cn.tag.CWSTagger;
import edu.fudan.nlp.corpus.StopWords;
import edu.fudan.util.exception.LoadModelException;

public class EntranceFun {
	public static Map<String,Integer> keywordExtract(String text,int keywordNum){
		Map<String,Integer> keywordMap = null;
		StopWords stopwords=new StopWords("./models/stopwords");
		try {
			CWSTagger seg=new CWSTagger("./models/seg.m");//���зִ�
			WordExtract keyExtractor=new WordExtract(seg,stopwords);
			keywordMap=keyExtractor.extract(text,keywordNum);//��Ҫ�ִʵ��ı���Ԥ�ƹؼ��ʸ���
		} catch (LoadModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return keywordMap;
	}

	public static void main(String[]args) throws Exception {
//		String text="������ر��ش���·��ͨ�¹ʳ���������24Сʱ����������26����ҹ�Ѿ�ȫ���Ƴ��¹��ֳ���֮ǰ���µ�D301�ζ�����ͷ���ڳ�����";
//		int keywordNum=10;
//		Map<String,Integer>keywords=keywordExtract(text,keywordNum);
//		Iterator<Map.Entry<String, Integer>> iterator=keywords.entrySet().iterator();
//		System.out.println("Keyword List:");
//		while(iterator.hasNext()) {
//			Map.Entry<String, Integer> entry=iterator.next();
//			System.out.println(entry.getKey()+" "+entry.getValue());
//		}
//		String text="������ر��ش���·��ͨ�¹ʳ���������24Сʱ��������  http://ictclas.nlpir.org/nlpir/ ���������ű��� 26����ҹ�Ѿ�ȫ���Ƴ��¹��ֳ���֮ǰ���µ�D301�ζ�����ͷ���ڳ�����";
//		PreProcessing preProcessing=new PreProcessing();
////		preProcessing.filterMeg(text);
//		preProcessing.addToCustomDict("������");
//		preProcessing.loadNewDist();
//		preProcessing.filterMeg(text);
//		System.out.println("done");
//		String inputFileName="./news_tensite_xml.smarty.dat";
//		String outputFileName="./trainData.txt";
//		PrepareTrainData prepareTrainData=new PrepareTrainData();
//		prepareTrainData.readContentFromFile(inputFileName, outputFileName);
		StopWords sw= new StopWords("models/stopwords");
		CWSTagger seg = new CWSTagger("models/seg.m");
		AbstractExtractor key = new WordExtract(seg,sw);
		
		System.out.println(key.extract("������ѧУ��������������֮������ѧ��������λ�������㵽�ļ�λ��ѧУ������һ���Ϊ�й�ѧ�����(����)�����һ�������������������һ�����еĵ�һλ�ã����������ݾ��Ǳ�������˿������ר������������������ר����(2009��11��22����)����������ð���õ¹�1988��Leibniz���������������걨������Ҫ������ĳɹ��Ĵ󰸣���������֮���������й�ѧ�����ٶ����һ����ĵ�һλ�ã����ν����ʵ�顣������ʵ�����۴����ٰ����ļ������������Ͷ������ȣ��������ִ��Ƽ���ʷ��Ū�����ٵ���߼�¼�������ٰ����ļ�����������������ð���Leibniz�����ǵ¹�(����ŵ������Ȼ��ѧ��ǿ��)����߿�ѧ�����䡱�������ǳ�Ϯ����һƪ����ƪ���ģ���������һ��������ѧ���۵㣬��α��XX��ѧ��ʲôѧλ�����޷����⡣�ڵ¹�������Ŀ�ѧ��������һ�����������ǻ�ŵ�������ˣ���������������δ��˵��ð����ŵ�������ģ����������ð���õ¹�Leibniz��Ӧ��˵������ѧ�����ټ����������߼�¼����������ð���Leibniz������Ϊ200��ŷԪ���൱��280����Ԫ��Ϊŵ�������������࣬������������ģ���Ϯ�������õ�XǧԪ��������޷����⣬���ԵĴ�������ѧ��Ū�������漰������ʷ��¼�������ٵ��ֺ��̵�I/O(Ͷ�������)��������Inputֻ������������˵(д)һ�䡰�һ�ù�1988��Leibniz����������������ʾ(��Ȼ�޷���ʾ)��״�����£��򽱽��֧Ʊ��(��Ȼ�ò���)�����Ͷ����ˣ�����Output���ǽ��������ڣ����ڣ��Ϻ��Ƽ���Ӣ���Ϻ�����һ�Ͷ����£��������ܳ����׵Ĳ�ʿ�ƺŵȷ���̤��������Ŀ��Ͼ�ӣ���ЩΪ����һƪ�������ģ����´��������綹�ĵƹ��£������ӽ����߸�����̾���磬��ν���㱾�������ս����𡱣����ԵĴ��������¼���������������1988��Leibniz��������֤�ļ���Ҳ���ͣ�����û�м�������ʾ����֤���⣬ȫ�����ڹٷ����ף����Ϻ��ط�־��(����ƪ��1995)�����Ϻ��ط�־��(����ƪ��2002)���Լ�ר��Ϊ���ܸ�����ѧѧ���ɾͶ��ر��д�ġ�������ѧ������¼��(1992)��Ȩ���Եò��ݰ�����ɣ���������ù�Leibniz������ʵ���������϶������Ӳ��Ӳ����(������)���ҵ��ǡ���������ȫ��ͨ�󣬹���Ͱ䷢Leibniz���ġ��¹���ѧ��չ����ίԱ�ᡷ����ȫ�򹫿�������񽱵�����������1988��������У���ʹ�ø߱�������΢��Ҳ�Ҳ����������������е��κ�һ�������ˣ����Ϻ��ط�־������������ѧ������¼����д�й����������1988��Leibniz�󽱵ĸߵ�����ȫΪ�ٻ���ֻ�и�����ѧʹ��α��ġ����1988��Leibniz�󽱡�Ϊ�������õ��Ϻ���1991��Ƽ���Ӣ�����������ļ������ǰٷ�֮�ٵ���ʵ�����ˣ�������˿���ϳ������������������µ����£�������������У��������ʱ�õ���Leibniz������(������xys20091007)������������������Ľҷ�����(�����ӣ�����ѩ���¹�ѧ�ӵ�����ѧ�ߵĽҷ�����)�ѽ���ʮƪ(������������վ��)��������һ�����С�������������Ӱ�����أ����������Ѿ���������������������������һλ�ӽ��쵼����ʿ���߱��ߣ��������������븱�����߹٣�������涨����ͼ첿����Ȩ���ʣ���˰����ҷ������ڵ��ϰ����ǣ�Ҳֻ�ܸɵ��ۡ����ڡ�����֮������������ѧУ����Ϊ�й�ѧ����ٶ���ĵ�һ�����е�һλ�ã���ֱ�ӵ���ʤ�Ƶ�������ǰ����֪��������µ�ѧ�߽�������ע�����������1988��Leibniz�󽱵����ͻ��Լ�������ȫ���硣", 20, true));
		
		//�����Ѿ��ֺôʵľ���
	}
}
