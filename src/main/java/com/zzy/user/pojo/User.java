package com.zzy.user.pojo;

import com.zzy.school.pojo.School;

/**
 * @author ��־Զ
 * ʱ��:2018-3-20
 * ˵���������û��� Pojo �࣬ ��Ӧ���ݿ��е� user ��
 */

public class User {
	
	
	private Integer userId;     //�û���ID
	
	
	private String userName;    //�û�����
	
	
	private String userPassword;  //�û�����
	
	
	private String userEmail;     //�û������ַ
	
	
	private String userPhone;      //�û��绰����
	
	
	private String userAddress;    //�û���סַ
	
	private School userSchool; //�Ñ���ѧУ

	public School getUserSchool() {
		return userSchool;
	}

	public void setUserSchool(School userSchool) {
		this.userSchool = userSchool;
	}

	private String userImagePath;   //�û�ͷ���ļ����·��
	 
	
	private String userDesc;          //�û�����
	
	
	private String userMakeDate;        //�û��޸Ļ���ע��ʱ��

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public String getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	public String getUserImagePath() {
		return userImagePath;
	}


	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}


	public String getUserDesc() {
		return userDesc;
	}


	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}


	public String getUserMakeDate() {
		return userMakeDate;
	}


	public void setUserMakeDate(String userMakeDate) {
		this.userMakeDate = userMakeDate;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail="
				+ userEmail + ", userPhone=" + userPhone + ", userAddress=" + userAddress + ", userSchool=" + userSchool.toString()
				+ ", userImagePath=" + userImagePath + ", userDesc=" + userDesc + ", userMakeDate=" + userMakeDate
				+ "]";
	}
}
