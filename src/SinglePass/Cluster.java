package SinglePass;

import java.util.HashSet;
import java.util.Set;

/**
 * 簇的类表示
 * @author gaoxy
 * 2017年7月27日
 */
public class Cluster {
	public Set<String> centroids;//存放簇中关键词
	
	public Cluster() {
		this.centroids=new HashSet<String>();
	}

	public Set<String> getCentorids() {
		return this.centroids;
	}

	public void setCentorids(Set<String> centroids) {
		this.centroids = centroids;
	}
	/**
	 * 将一个关键词添加到对应的类簇中
	 * @param keyword
	 * @return boolean类型，如果该keyword已经存在，则返回false，否则返回true
	 */
	public boolean addToCentroids(String keyword) {
		if(this.centroids.contains(keyword)) {
			System.out.println("Keyword has already exist!");
			return false;
		}
		else{
			this.getCentorids().add(keyword);
			return true;
		}
	}
}
