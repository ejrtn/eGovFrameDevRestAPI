package egovframework.ecall.main.vo;

public class CommonVo {
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "CommonVo [key=" + key + ", value=" + value + "]";
	}
	
	
}
