package com.zzy.goods.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.zzy.categorySecond.pojo.CategorySecond;
import com.zzy.goodImages.pojo.GoodsImage;
import com.zzy.user.pojo.User;

public class Goods {
	
	/**
	 * ��ƷID
	 */
	private Integer gId;
	
	/**
	 * ��Ʒ����
	 */
	private String gName;
	
	/**
	 * ��Ʒ����۸�
	 */
	private double gBuyPrice;
	
	/**
	 * ��Ʒ׼�����ֵļ۸�
	 */
	private double gSellPrice;
	
	/**
	 * ��Ʒչʾ�ķ��棬�������Ҫ��Ʒ
	 */
	private String gCoverImage;
	
	private Integer gIsSold;
	
	public Integer getgIsSold() {
		return gIsSold;
	}
	public void setgIsSold(Integer gIsSold) {
		this.gIsSold = gIsSold;
	}
	/**
	 * ��Ʒ������
	 */
	private String gDesc;
	
	/**
	 * ��Ʒ�Ĵ���ʱ��
	 */
	private String gMakeDate;
	
	/**
	 * ������������
	 */
	private CategorySecond categorySecond;
	
	/**
	 * ����User
	 */
	private User user;
	
	/**
	 * ��Ʒ����������չʾͼƬ��Ϣ
	 */
	
	private Set<GoodsImage> goodsImages = new HashSet<>();
	
	public String getgCoverImage() {
		return gCoverImage;
	}
	public void setgCoverImage(String gCoverImage) {
		this.gCoverImage = gCoverImage;
	}
	public Set<GoodsImage> getGoodsImages() {
		return goodsImages;
	}
	public void setGoodsImages(Set<GoodsImage> goodsImages) {
		this.goodsImages = goodsImages;
	}
	public Integer getgId() {
		return gId;
	}
	public void setgId(Integer gId) {
		this.gId = gId;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public double getgBuyPrice() {
		return gBuyPrice;
	}
	public void setgBuyPrice(double gBuyPrice) {
		this.gBuyPrice = gBuyPrice;
	}
	public double getgSellPrice() {
		return gSellPrice;
	}
	public void setgSellPrice(double gSellPrice) {
		this.gSellPrice = gSellPrice;
	}
	public String getgDesc() {
		return gDesc;
	}
	public void setgDesc(String gDesc) {
		this.gDesc = gDesc;
	}
	public String getgMakeDate() {
		return gMakeDate;
	}
	public void setgMakeDate(String gMakeDate) {
		this.gMakeDate = gMakeDate;
	}
	
	
	public CategorySecond getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
