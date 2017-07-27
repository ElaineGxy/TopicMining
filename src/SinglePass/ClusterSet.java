package SinglePass;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * ���ڴ�����д�����,������Ӵ����´�
 * @author gaoxy
 *
 * 2017��7��27��
 */
public class ClusterSet {
	public List<Cluster>clusterSet=new ArrayList<Cluster>();

	
	public  ClusterSet() {
	}

	public List<Cluster> getClusterSet() {
		return clusterSet;
	}

	/**
	 * ͨ�����е�keywords�����µ�cluster������ӵ�clusterSet
	 * @param keywords
	 */
	public void addNewCluster(Set<String>keywords) {
		Cluster cluster=new Cluster();
		for(String keyword:keywords) {
			cluster.addToCentroids(keyword);
		}
		clusterSet.add(cluster);
	}
	/**
	 * ֱ�ӽ��������cluster��ӵ�clusterSet��
	 * @param cluster
	 */
	public void addNewCluster(Cluster cluster) {
		clusterSet.add(cluster);
	}
}
