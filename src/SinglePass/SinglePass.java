package SinglePass;

import java.util.List;
import java.util.Map;
import java.util.Set;

import PreProcess.PreProcessing;


/**
 * single-pass聚类算法
 * @author gaoxy
 *
 * 2017年7月27日
 */
public class SinglePass {

	public PreProcessing preProcessing;
	public ClusterSet clusterSet;
	
	public SinglePass() {
		this.preProcessing=new PreProcessing();
		this.clusterSet=new ClusterSet();
	}
	
	public String message=null;
	/**
	 * 读取一条文本，并进行预处理，最终得到文本的关键词map
	 * @return
	 */
	public Map<String,Integer> readContent() {
		//读取文本并放入到message中
		
		//对读入的message进行预处理
		this.message=this.preProcessing.filterMeg(message);
		if(this.message==null) {
			System.out.println("无效信息");
		}else {
			Map<String,Integer> keywordMap=KeywordAndVectorized.getKeyExtractor().extract(this.message, Constant.keywordNo);
			if(keywordMap.size()>0)return keywordMap;
			else return null;
		}
		return null;
	}
	public void singlePassCore() {
		//当前没有簇中心
		Map<String,Integer>keywords=(Map<String, Integer>) this.readContent();
		if(keywords==null||keywords.size()==0)return;
		Set<String>keywordSet=keywords.keySet();
		if(this.clusterSet.getClusterSet().size()==0) {
			this.clusterSet.addNewCluster(keywords.keySet());
		}else {
			//计算message与簇中心的距离
			 
		}
	}
	/**
	 * 计算message与簇中心的距离
	 * @param message
	 * @return
	 */
	public float calDist(Set<String>keywords) {
		List<Cluster>clusters=this.clusterSet.getClusterSet();
		float minDist=Float.MAX_VALUE;
		float tempDist=Float.MAX_VALUE;
		Cluster closeCluster=null;
		for(Cluster cluster:clusters) {
			tempDist=this.calDist(keywords, cluster);
			if(minDist<tempDist) {
				minDist=tempDist;
				closeCluster=cluster;
			}
		}
		if(minDist<Constant.distThreshold) {
			//将keywords加入到cluster中
			closeCluster.getCentorids().addAll(keywords);
		}else {//创建一个新的cluster
			this.clusterSet.addNewCluster(keywords);
		}
		return 0;
	}
	
	/**
	 * 在cluster的中心中找出与每个keyword最接近的关键词，并计算距离
	 * @param keywords
	 * @param cluster
	 * @return
	 */
	public float calDist(Set<String>keywords,Cluster cluster) {
		float dist=0;
		float minDist=Float.MAX_VALUE;
		for(String keyword:keywords) {
			//找出在该cluster中与keyword最近的一个词
			if(KeywordAndVectorized.getDict().contains(keyword)) {
				float[]keywordVec=(float[]) KeywordAndVectorized.getDict().get(keyword);
				Set<String> centroids=cluster.getCentorids();
				float tempDist=Float.MAX_VALUE;
				for(String centroid:centroids) {
					tempDist=Common.calCosDist(keywordVec,(float[]) KeywordAndVectorized.getDict().get(centroid));
					if(minDist>tempDist)minDist=tempDist;
				}
				dist+=minDist;
			}
		}

		return dist;
	}
}
