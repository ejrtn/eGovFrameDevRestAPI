package egovframework.ecall.main.vo;

import java.time.LocalDateTime;


public class NoticeVo {
	private String id;
	private String title;
	private String writer;
	private LocalDateTime udate;
	private int click_cnt;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public LocalDateTime getUdate() {
		return udate;
	}
	public void setUdate(LocalDateTime udate) {
		this.udate = udate;
	}
	public int getClick_cnt() {
		return click_cnt;
	}
	public void setClick_cnt(int click_cnt) {
		this.click_cnt = click_cnt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "NoticeVo [id=" + id + ", title=" + title + ", writer=" + writer + ", udate=" + udate + ", click_cnt="
				+ click_cnt + ", content=" + content + "]";
	}
	
	
}
