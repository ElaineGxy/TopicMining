package SinglePass;

import java.util.Dictionary;
import java.util.Hashtable;

public class Constant {

	public static int vecLength=500;
	public static Dictionary wordFreDist=new Hashtable<String,Integer>();
	public static int count=0;//统计从上一时刻后接入的消息的个数，当到达一定阈值后对词频表进行更新
	public static float distThreshold=1;
	public static int keywordNo=10;
}
