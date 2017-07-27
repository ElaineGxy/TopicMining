package SinglePass;

import java.util.List;
import java.util.Map;
import java.util.Set;

import PreProcess.PreProcessing;


/**
 * single-pass�����㷨
 * @author gaoxy
 *
 * 2017��7��27��
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
	 * ��ȡһ���ı���������Ԥ�������յõ��ı��Ĺؼ���map
	 * @return
	 */
	public Map<String,Integer> readContent() {
		//��ȡ�ı������뵽message��
		
		//�Զ����message����Ԥ����
		this.message=this.preProcessing.filterMeg(message);
		if(this.message==null) {
			System.out.println("��Ч��Ϣ");
		}else {
			Map<String,Integer> keywordMap=KeywordAndVectorized.getKeyExtractor().extract(this.message, Constant.keywordNo);
			if(keywordMap.size()>0)return keywordMap;
			else return null;
		}
		return null;
	}
	public void singlePassCore() {
		//��ǰû�д�����
		Map<String,Integer>keywords=(Map<String, Integer>) this.readContent();
		if(keywords==null||keywords.size()==0)return;
		Set<String>keywordSet=keywords.keySet();
		if(this.clusterSet.getClusterSet().size()==0) {
			this.clusterSet.addNewCluster(keywords.keySet());
		}else {
			//����message������ĵľ���
			 
		}
	}
	/**
	 * ����message������ĵľ���
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
			//��keywords���뵽cluster��
			closeCluster.getCentorids().addAll(keywords);
		}else {//����һ���µ�cluster
			this.clusterSet.addNewCluster(keywords);
		}
		return 0;
	}
	
	/**
	 * ��cluster���������ҳ���ÿ��keyword��ӽ��Ĺؼ��ʣ����������
	 * @param keywords
	 * @param cluster
	 * @return
	 */
	public float calDist(Set<String>keywords,Cluster cluster) {
		float dist=0;
		float minDist=Float.MAX_VALUE;
		for(String keyword:keywords) {
			//�ҳ��ڸ�cluster����keyword�����һ����
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
