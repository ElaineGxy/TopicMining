package SinglePass;

/**
 * 定义一些常用的函数
 * @author gaoxy
 *
 * 2017年7月27日
 */
public class Common {
	public static float calCosDist(float[] word1, float[] word2) {
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
