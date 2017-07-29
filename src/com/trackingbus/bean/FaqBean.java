package com.trackingbus.bean;

public class FaqBean {
	private Integer faq_id;
	private String category;
	private String question;
	private String answer;
	
	
	private String question_ar;
	private String answer_ar;
	private Integer lang;
	
	public Integer getFaq_id() {
		return faq_id;
	}
	public void setFaq_id(Integer faq_id) {
		this.faq_id = faq_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestion_ar() {
		return question_ar;
	}
	public void setQuestion_ar(String question_ar) {
		this.question_ar = question_ar;
	}
	public String getAnswer_ar() {
		return answer_ar;
	}
	public void setAnswer_ar(String answer_ar) {
		this.answer_ar = answer_ar;
	}
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	
	
}
