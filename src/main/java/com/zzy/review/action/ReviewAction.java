package com.zzy.review.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.ReturnJsonByResponse;
import com.zzy.review.pojo.Review;
import com.zzy.review.service.ReviewService;

public class ReviewAction extends ActionSupport implements ModelDriven<Review>{
	
	private ReviewService reviewService;
	
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	private String userId;
	private String goodsId;
	private String reviewContext;
	private Review review;
	private String rId;
	
	public void setrId(String rId) {
		this.rId = rId;
	}
	
	public void setReview(Review review) {
		this.review = review;
	}
	
	public Review getReview() {
		return review;
	}
	
	public void setReviewContext(String reviewContext) {
		this.reviewContext = reviewContext;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	public String reviewSave(){
		JsonBean<Review> jsonBean = reviewService.save(userId, goodsId, reviewContext);
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}
	
	public String reviewDelete(){
		JsonBean<Review> jsonBean = reviewService.deleteByrId(rId);
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}

	@Override
	public Review getModel() {
		// TODO Auto-generated method stub
		return review;
	}

}
