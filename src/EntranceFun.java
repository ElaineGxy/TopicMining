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
			CWSTagger seg=new CWSTagger("./models/seg.m");//进行分词
			WordExtract keyExtractor=new WordExtract(seg,stopwords);
			keywordMap=keyExtractor.extract(text,keywordNum);//需要分词的文本，预计关键词个数
		} catch (LoadModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return keywordMap;
	}

	public static void main(String[]args) throws Exception {
//		String text="甬温线特别重大铁路交通事故车辆经过近24小时的清理工作，26日深夜已经全部移出事故现场，之前埋下的D301次动车车头被挖出运走";
//		int keywordNum=10;
//		Map<String,Integer>keywords=keywordExtract(text,keywordNum);
//		Iterator<Map.Entry<String, Integer>> iterator=keywords.entrySet().iterator();
//		System.out.println("Keyword List:");
//		while(iterator.hasNext()) {
//			Map.Entry<String, Integer> entry=iterator.next();
//			System.out.println(entry.getKey()+" "+entry.getValue());
//		}
//		String text="甬温线特别重大铁路交通事故车辆经过近24小时的清理工作  http://ictclas.nlpir.org/nlpir/ ，据孙政才报道 26日深夜已经全部移出事故现场，之前埋下的D301次动车车头被挖出运走";
//		PreProcessing preProcessing=new PreProcessing();
////		preProcessing.filterMeg(text);
//		preProcessing.addToCustomDict("孙政才");
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
		
		System.out.println(key.extract("复旦大学校长杨玉良被美国之音列名学术作假首位若将被点到的几位大学校长放在一起称为中国学术造假(嫌疑)队伍第一方阵，那杨玉良就是这第一方阵中的第一位置，其作假内容就是被《新语丝》特设专辑——《杨玉良问题专辑》(2009，11，22设立)持续报道的冒充获得德国1988年Leibniz奖，并利它所得申报其它重要奖励获的成功的大案，被《美国之音》名列中国学术作假队伍第一方阵的第一位置，真可谓名至实归。若最后查实，无论从作假案件的级别，作假收益的投入产出比，均创下现代科技历史中弄虚作假的最高记录。从作假案件的级别来看：被杨玉良冒充的Leibniz奖，是德国(当今诺贝尔自然科学奖强国)的最高科学奖，其”重量”是抄袭他人一篇，两篇论文，剽窃他人一条，两条学术观点，或伪造XX大学的什么学位等所无法比拟。在德国，那里的科学家再往上一级的荣誉就是获诺贝尔奖了，而国际上至今尚未听说有冒充获得诺贝尔奖的，因此杨玉良冒充获得德国Leibniz奖应该说创下了学术作假级别的世界最高记录。被杨玉良冒充的Leibniz奖奖金为200万欧元，相当于280万美元，为诺贝尔奖的三倍多，这更是剽窃论文，抄袭著作所得的X千元人民幤所无法比拟，绝对的创下中外学术弄虚作假涉及金额的历史记录；从作假得手后盘点I/O(投入产出比)来看：其Input只是杨玉良轻轻说(写)一句“我获得过1988年Leibniz奖”，甚至连“出示(当然无法出示)奖状，奖章，或奖金的支票”(当然拿不出)的辛劳都免了，而其Output则是晋升副教授，教授，上海科技精英，上海市五一劳动奖章，教育部杰出贡献的博士称号等纷至踏来，令人目不暇接，那些为剽窃一篇三流论文，拉下窗帘，在如豆的灯光下，剪刀加浆糊者个个自叹不如，可谓“零本万利，日进斗金”，绝对的创下世界记录；而杨玉良“获得1988年Leibniz奖”的书证的级别也不低，除了没有见到他出示过获奖证书外，全出现在官方文献：《上海地方志》(杨浦篇，1995)，《上海地方志》(青年篇，2002)，以及专门为介绍复旦大学学术成就而特别编写的《复旦大学教授名录》(1992)，权威性得不容半点质疑，杨玉良获得过Leibniz奖的真实性是铁板上钉钉般的硬碰硬。但(对杨玉)不幸的是“因特网”全球开通后，管理和颁发Leibniz奖的《德国科学发展基金委员会》竟向全球公开了历年获奖的名单，其中1988年获奖名单中，即使用高倍电子显微镜也找不到杨玉良三个字中的任何一个。至此，《上海地方志》，《复旦大学教授名录》所写有关杨玉良获得1988年Leibniz大奖的高调报道全为假话，只有复旦大学使用伪造的“获得1988年Leibniz大奖”为杨玉良得到上海市1991年科技精英的特殊荣誉的记载则是百分之百的真实。至此，《新语丝》上出现了质疑杨玉良文章的文章：《请问杨玉良校长，您几时得到过Leibniz奖？》(董正葛：xys20091007)，但三年多来，后续的揭发文章(方舟子，寒江雪，德国学子等其它学者的揭发文章)已近三十篇(还不计其他网站的)，杨玉良一声不啃。是他身正不怕影子歪呢？还是身歪已经不敢作声？杨玉良心里有数。一位接近领导的人士告诉笔者，杨玉良属于中央副部级高官，按级别规定基层纪检部门无权过问，如此包括揭发者在内的老百姓们，也只能干瞪眼。现在《美国之音》将复旦大学校长列为中国学术造假队伍的第一方阵中第一位置，不直接点名胜似点名，此前还不知道这个故事的学者将会好奇关注杨玉良，获得1988年Leibniz大奖的世纪谎言即将传遍全世界。", 20, true));
		
		//处理已经分好词的句子
	}
}
