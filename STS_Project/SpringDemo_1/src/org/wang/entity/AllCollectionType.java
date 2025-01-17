package org.wang.entity;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class AllCollectionType {
	private List<String> list;
	private String[] array;
	private Map<String, String> map;
	private Set<String> set;
	private Properties props;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String[] getArray() {
		return array;
	}

	public void setArray(String[] array) {
		this.array = array;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	@Override
	public String toString() {
		String strContent = "";
		for (String str : array) {
			strContent += str + ",";
		}

		return "list:" + this.list + "\n set:" + this.set + "\n map :" + this.map + "\n proper:" + this.props
				+ "\n array:" + strContent;
	}

}
