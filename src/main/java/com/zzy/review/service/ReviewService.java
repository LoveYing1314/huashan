package com.zzy.review.service;

import java.util.Date;

import com.zzy.BaseUtils.DateConvert;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.StatusUtils;
import com.zzy.goods.dao.GoodsDao;
import com.zzy.goods.pojo.Goods;
import com.zzy.review.dao.ReviewDao;
import com.zzy.review.pojo.Review;
import com.zzy.user.dao.UserDao;
import com.zzy.user.pojo.User;

public class ReviewService {
	
	private ReviewDao reviewDao;
	private UserDao userDao;
	private GoodsDao goodsDao;
	
	public void setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	public JsonBean<Review> save(String userId, String goodsId, String reviewContext) {
		
		JsonBean<Review> jsonBean = new JsonBean<>();
		
		User existUser = userDao.findUserByUserId(Integer.parseInt(userId));
		Goods existGoods = goodsDao.findGoodsById(goodsId);
		
		if(existUser == null){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("�û�ID����");
			return jsonBean;
		}
		
		if(existGoods == null){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("��ƷID����");
			return jsonBean;
		}
		
		Review review = new Review(reviewContext, DateConvert.DateConvertToLong(new Date()), existGoods, existUser);
		reviewDao.save(review);
		jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
		jsonBean.setMsg("����ɹ�");
		jsonBean.setData(null);
		
		return jsonBean;
	}

	public JsonBean<Review> deleteByrId(String rId) {
		
		JsonBean<Review> jsonBean = new JsonBean<>();
		
		if(rId == null || Integer.parseInt(rId) <= 0){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("����ID����");
			return jsonBean;
		}
		
		int result = reviewDao.deleteByrId(Integer.parseInt(rId));
		
		if(result != 1){
			jsonBean.setId(StatusUtils.DATA_DELETE_FAIL);
			jsonBean.setMsg("����ɾ��ʧ��");
			return jsonBean;
		}else{
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("ɾ���ɹ�");
			return jsonBean;
		}
	}
	
}
