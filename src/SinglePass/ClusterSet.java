package SinglePass;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用于存放所有簇中心,可以添加创建新簇
 * @author gaoxy
 *
 * 2017年7月27日
 */
public class ClusterSet {
	public List<Cluster>clusterSet=new ArrayList<Cluster>();

	
	public  ClusterSet() {
	}

	public List<Cluster> getClusterSet() {
		return clusterSet;
	}

	/**
	 * 通过现有的keywords创建新的cluster，并添加到clusterSet
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
	 * 直接将输入参数cluster添加到clusterSet中
	 * @param cluster
	 */
	public void addNewCluster(Cluster cluster) {
		clusterSet.add(cluster);
	}
}
