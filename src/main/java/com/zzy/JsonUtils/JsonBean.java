package com.zzy.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class JsonBean<T> {

	/**
	 * ���������״̬��Ϣ
	 */
	private Integer id;

	/**
	 * ���淵�صĵ�������Ϣ
	 */
	private String msg;

	/**
	 * ���ر��������
	 */
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
