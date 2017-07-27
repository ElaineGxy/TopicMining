package SinglePass;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * 簇的类表示,所属该类簇的关键词，以及对应出现的词频
 * @author gaoxy
 * 2017年7月27日
 */
public class Cluster2 {
	public Hashtable<String,Integer> centroids;//存放簇中关键词,以及出现的词频（权重）
	
	public Cluster2() {
		this.centroids=new Hashtable<String,Integer>();
	}

	public Hashtable<String,Integer> getCentorids() {
		return this.centroids;
	}

	public void setCentorids(Hashtable<String,Integer> centroids) {
		this.centroids = centroids;
	}
	/**
	 * 将一个关键词添加到对应的类簇中
	 * @param keyword
	 * @return boolean类型，如果该keyword已经存在，则返回false，否则返回true
	 */
	public boolean addToCentroids(String keyword) {
		if(this.centroids.contains(keyword)) {
			int fre=this.centroids.get(keyword)+1;
			this.centroids.put(keyword, fre+1);
			System.out.println("Keyword has already exist!");
			return false;
		}
		else{
			this.getCentorids().put(keyword,1);
			return true;
		}
	}
}
