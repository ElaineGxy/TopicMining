package SinglePass;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * �ص����ʾ,��������صĹؼ��ʣ��Լ���Ӧ���ֵĴ�Ƶ
 * @author gaoxy
 * 2017��7��27��
 */
public class Cluster2 {
	public Hashtable<String,Integer> centroids;//��Ŵ��йؼ���,�Լ����ֵĴ�Ƶ��Ȩ�أ�
	
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
	 * ��һ���ؼ�����ӵ���Ӧ�������
	 * @param keyword
	 * @return boolean���ͣ������keyword�Ѿ����ڣ��򷵻�false�����򷵻�true
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
