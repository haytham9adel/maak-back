package com.trackingbus.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rudiment Webtech Solutions
 *
 */
@Entity
@Table(name="tbl_faq")
public class FaqModel {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -723583058586873479L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="faq_id")
	private Integer faq_id;
	
	@Column(name="category")
	private String category;
	
	@Column(name="question")
	private String question;
	
	@Column(name="answer")
	private String answer;
	
	
	@Column(name="question_ar")
	private String question_ar;
	
	@Column(name="answer_ar")
	private String answer_ar;

	
	@Column(name="lang")
	private Integer lang;
	
	
	
	public Integer getLang() {
		return lang;
	}

	public void setLang(Integer lang) {
		this.lang = lang;
	}

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
	
	
	
}
