package SinglePass;

import java.util.HashSet;
import java.util.Set;

/**
 * �ص����ʾ
 * @author gaoxy
 * 2017��7��27��
 */
public class Cluster {
	public Set<String> centroids;//��Ŵ��йؼ���
	
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
	 * ��һ���ؼ�����ӵ���Ӧ�������
	 * @param keyword
	 * @return boolean���ͣ������keyword�Ѿ����ڣ��򷵻�false�����򷵻�true
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
