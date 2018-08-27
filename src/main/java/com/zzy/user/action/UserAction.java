package com.zzy.user.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookiesAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zzy.AppBeanUtils.GoodsPageBeanApp;
import com.zzy.AppBeanUtils.PageApp;
import com.zzy.BaseUtils.FileUpload;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.ReturnJsonByResponse;
import com.zzy.JsonUtils.StatusUtils;
import com.zzy.user.pojo.User;
import com.zzy.user.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>, CookiesAware {

	private static final long serialVersionUID = 1L;

	// ���ܽ��洫�����û�ע��� ѧ��
	private String studentId;

	private String JSESSIONID;

	private String userSchoolId;

	private File image; // �ϴ�����Ӧ���ļ�
	private String imageContentType; // ��Ӧ�ϴ��ļ�������
	private String imageFileName; // ��Ӧ�ϴ��ļ�������

	public void setUserSchoolId(String userSchoolId) {
		this.userSchoolId = userSchoolId;
	}

	public void setJSESSIONID(String jSESSIONID) {
		JSESSIONID = jSESSIONID;
	}

	// private String cookieUser;
	//
	// public void setCookieUser(String cookieUser) {
	// this.cookieUser = cookieUser;
	// }

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	private String cursor;

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	private JsonBean<User> jsonBean = null;

	public String getStudentId() {
		return studentId;
	}

	public void setJsonBean(JsonBean<User> jsonBean) {
		this.jsonBean = jsonBean;
	}

	public File getImage() {
		return image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public String getImageFilename() {
		return imageFileName;
	}

	private UserService userService; // ʹ��ҵ����java����

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// ģ�� ���� User ����
	private User user = new User();

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getModel() {
		return user;
	}

	public String getUserGoodsByUid() {
		JsonBean<List<GoodsPageBeanApp>> bean = new JsonBean<>();
		PageApp<GoodsPageBeanApp> pageApp = userService.getUserGoodsByUidApp(studentId, cursor);
		if (pageApp == null) {
			bean.setId(StatusUtils.DATA_QUERY_FAIL);
			bean.setMsg("���ݲ���ʧ�ܣ�");
		} else {
			bean.setId(StatusUtils.OPERATION_SUCCESS);
			bean.setMsg("���ݲ��ҳɹ���");
			bean.setData(pageApp.getList());
		}

		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), bean);
		return null;
	}

	/**
	 * �鿴�û���Ϣ
	 * 
	 * @return
	 */
	public String info() {

		User exitIdUser = null;
		jsonBean = new JsonBean<>();
		if (studentId == null || "".equals(studentId)) {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("�û����ݱ����ֶδ��ڿ�ֵ�� ����ɱ����������д");
		} else {
			exitIdUser = userService.findUserByUserId(Integer.parseInt(studentId));
			if (exitIdUser != null) {
				jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
				jsonBean.setMsg("���ݲ�ѯ����");
				jsonBean.setData(exitIdUser);
			} else {
				jsonBean.setId(StatusUtils.DATA_QUERY_FAIL);
				jsonBean.setMsg("δ���ҵ�ָ����Ա��Ϣ!");
			}
		}

		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}

	// ����ע����Ϣ����
	public String regist() {

		jsonBean = new JsonBean<User>();

		User exitIdUser = userService.findUserByUserId(Integer.parseInt(studentId));
		User exitNameUser = userService.findUserByUserName(user.getUserName());

		if (exitIdUser != null) {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("ѧ���Ѿ�ע�ᣡ");
		} else if (exitNameUser != null) {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("�û����Ѵ��ڣ�");
		} else {
			if (userFieldNotNullTest(studentId) && userFieldNotNullTest(user.getUserPassword())
					&& userFieldNotNullTest(user.getUserName())) {

				int schoolId = 10;

				if (userSchoolId != null && "".equals(userSchoolId)) {
					schoolId = Integer.parseInt(userSchoolId);
				}

				boolean b = userService.saveUserRegistInfo(studentId, user.getUserName(), user.getUserPassword(),
						schoolId);

				if (b) {
					jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
					jsonBean.setMsg("ע��ɹ���");
				} else {
					jsonBean.setId(StatusUtils.DATA_UPDATE_FAIL);
					jsonBean.setMsg("��Ϣ����ע��ʧ�ܣ� ������ע�ᣡ");
				}
			} else {
				jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
				jsonBean.setMsg("�û����ݱ����ֶδ��ڿ�ֵ�� ����ɱ����������д");
			}
		}

		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);

		return null;
	}

	/**
	 * ajax ��֤�û����Ƿ����
	 * 
	 * @return
	 */
	public String findByName() {

		jsonBean = new JsonBean<User>();

		User exitNameUser = userService.findUserByUserName(user.getUserName());

		if (exitNameUser != null) {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("�û����Ѵ��ڣ�");
		} else {
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("�û������ã�");
		}

		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}

	// ���ܴ����¼��Ϣ
	public String login() {

		jsonBean = new JsonBean<User>();

		User existUser = null;
		boolean flag = false;
		HttpServletResponse response = ServletActionContext.getResponse();

		if (userFieldNotNullTest(studentId) && userFieldNotNullTest(user.getUserPassword())) {

			// if(cookies != null){
			// for (Entry<String, String> cookie: cookies.entrySet()) {
			// String[] content = cookie.getValue().split("-");
			// for (int i = 0; i < content.length; i++) {
			// if(content[i].equals(studentId)){
			// flag = true;
			// break;
			// }
			// }
			// if(flag){
			// break;
			// }
			// }
			// }

			// if(JSESSIONID != null){
			// flag =
			// JSESSIONID.equals(ServletActionContext.getRequest().getSession().getId());
			// }

			if (flag) {
				jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
				jsonBean.setMsg("cookie������Ϣ��¼�������¼");
			} else {

				existUser = userService.findUserByTwoFiled(studentId, user);

				if (existUser != null) {
					// StringBuffer cookieStr = new
					// StringBuffer(existUser.getUserId().toString() + ":" +
					// existUser.getUserName());
					// if(!"".equals(user.getUserEmail())){
					// cookieStr.append(":");
					// cookieStr.append(existUser.getUserEmail());
					// }
					// String cookieStr = userToCookieUser(existUser);
					// Cookie cookie = new Cookie("cookieUser", cookieStr);
					ServletActionContext.getRequest().getSession().setAttribute("sessionUser", existUser);
					// cookie.setMaxAge(60 * 60 * 24 * 2);
					// response.addCookie(cookie);
					jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
					jsonBean.setMsg("�����¼");
					jsonBean.setData(existUser);
				} else {
					jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
					jsonBean.setMsg("�˺ţ� ����������� ���ٴε�¼��");
				}
			}
		} else {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("�û���¼���ݱ����ֶδ��ڿ�ֵ�� ����ɱ����������д");
		}
		ReturnJsonByResponse.ReturnJson(response, jsonBean);
		return null;
	}

	private String userToCookieUser(User existUser) {

		StringBuffer buffer = new StringBuffer("");

		buffer.append(existUser.getUserId());
		buffer.append("-");
		buffer.append(existUser.getUserName());

		buffer.append("-");
		if (userFieldNotNullTest(existUser.getUserEmail())) {
			buffer.append(existUser.getUserEmail());
		} else {
			buffer.append("null");
		}

		buffer.append("-");
		if (userFieldNotNullTest(existUser.getUserPhone())) {
			buffer.append(existUser.getUserPhone());
		} else {
			buffer.append("null");
		}

		buffer.append("-");
		if (userFieldNotNullTest(existUser.getUserImagePath())) {
			buffer.append(existUser.getUserImagePath());
		} else {
			buffer.append("null");
		}

		buffer.append("-");
		if (userFieldNotNullTest(existUser.getUserAddress())) {
			buffer.append(existUser.getUserAddress());
		} else {
			buffer.append("null");
		}

		buffer.append("-");
		if (userFieldNotNullTest(existUser.getUserDesc())) {

			buffer.append(existUser.getUserDesc());
		} else {
			buffer.append("null");
		}

		return buffer.toString();
	}

	/**
	 * ����ǰ̨�� ajax ��֤studentId �Ƿ��Ѿ�����ע��
	 * 
	 * 
	 */

	public String findById() {

		jsonBean = new JsonBean<User>();

		User existUser = userService.findUserByUserId(Integer.parseInt(studentId));

		if (existUser == null) {
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("����ע��");
		} else {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("ѧ���Ѿ���ע�ᣬ��ֱ�ӽ��е�¼");
		}
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);

		return null;
	}

	/**
	 * ���� http://www.lfork.top/22y/use_editInfo?userId=XXX ·�����д���
	 */
	public String editInfo() {

		jsonBean = new JsonBean<User>();

		User existUser = userService.findUserByUserId(Integer.parseInt(studentId));

		if (existUser == null) {
			jsonBean.setId(StatusUtils.DATA_QUERY_FAIL);
			jsonBean.setMsg("δ���ҵ�ָ����Ա��Ϣ!");
		} else {
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("��Ա�ҵ��� û����ʾ��������ϢΪ��");
			jsonBean.setData(existUser);
		}
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public void setImageFileName(String imageFilename) {
		this.imageFileName = imageFilename;
	}

	/**
	 * ���ܴ�·�� http://www.lfork.top/22y/user_save?userId=XXX ��������
	 * 
	 * @throws FileNotFoundException
	 */
	public String save() throws FileNotFoundException {

		jsonBean = new JsonBean<User>();
		boolean b;

		// ����ҳ�洫������ѧ����Ϣ ���浽ģ�������� ׼������̨��
		user.setUserId(Integer.parseInt(studentId));

		// // ���ϴ���ͼƬ�����ڷ������� Ȼ���������� user �е�ͷ��·���� ׼�������ݣ�Ȼ���� Service ���б������
		// String uploadImagePath = FileUpload.fileUpload(image,
		// imageContentType, imageFilename, "userHeadImage");
		//
		// if(uploadImagePath == null){
		// jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
		// jsonBean.setMsg("�����ļ�����Ϊ��");
		// }else{
		// user.setUserImagePath(uploadImagePath);
		// // ���� Service ��������ݵı���
		b = userService.saveUserEditInfo(user);
		if (!b) {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("ǰ�����ݳ���");
		} else {
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("����ɹ�");
			jsonBean.setData(user);
		}
		// }

		// ����ҳ�汣������ݽ��
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}

	/**
	 * ���ܴ�·�� http://www.lfork.top/22y/user_imageUpload?userId=XXX ��������
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public String imageUpload() throws SQLException, IOException {

		JsonBean<String> bean = new JsonBean<String>();

		if (!userFieldNotNullTest(studentId)) {
			bean.setId(StatusUtils.DATAFRONT_ERROR);
			bean.setMsg("ѧ����Ϣ����");
		} else {
			// ����ҳ�洫������ѧ����Ϣ ���浽ģ�������� ׼������̨��
			user.setUserId(Integer.parseInt(studentId));

			// ���ϴ���ͼƬ�����ڷ������� Ȼ���������� user �е�ͷ��·���� ׼�������ݣ�Ȼ���� Service ���б������
			String uploadImagePath = FileUpload.fileUpload(image, imageContentType, imageFileName, "userHeadImage");

			if (uploadImagePath == null) {
				bean.setId(StatusUtils.DATAFRONT_ERROR);
				bean.setMsg("�����ļ�����Ϊ��");
			} else {
				user.setUserImagePath(uploadImagePath);
				int i = userService.saveUserImage(user);
				if (i == 1) {
					bean.setId(StatusUtils.OPERATION_SUCCESS);
					bean.setMsg("����ɹ�");
					bean.setData(uploadImagePath);
				} else {
					bean.setId(StatusUtils.DATA_UPDATE_FAIL);
					bean.setMsg("����ʧ��");
					bean.setData(null);
				}
			}
		}

		// ����ҳ�汣������ݽ��
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), bean);
		return null;
	}

	public String logout() {

		User sessionUser = (User) ServletActionContext.getRequest().getSession().getAttribute("sessionUser");

		jsonBean = new JsonBean<>();

		if (sessionUser != null) {
			ServletActionContext.getRequest().getSession().removeAttribute("sessionUser");
			jsonBean.setData(null);
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("�û��ɹ��ǳ�");
		}
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);

		return null;
	}

	public String getSessionUser() {

		User sessionUser = (User) ServletActionContext.getRequest().getSession().getAttribute("sessionUser");

		jsonBean = new JsonBean<>();

		if (sessionUser != null) {
			jsonBean.setData(sessionUser);
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("��ѯ�ɹ�");
		} else {
			jsonBean.setData(null);
			jsonBean.setId(StatusUtils.DATA_QUERY_FAIL);
			jsonBean.setMsg("�û���Ϣ�Ѿ��ǳ�");
		}

		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);

		return null;
	}

	/**
	 * �û�������ʱ�����ݷǿ���֤
	 * 
	 * @param user2
	 * @return
	 */
	private boolean userDataNotNullTest(User user2) {

		if (userFieldNotNullTest(user2.getUserId()) && userFieldNotNullTest(user2.getUserEmail())
				&& userFieldNotNullTest(user2.getUserName()) && userFieldNotNullTest(user2.getUserPhone())) {
			return true;
		}

		return false;
	}

	/**
	 * �û������ֶεķǿ�У��
	 * 
	 * @param args
	 * @return
	 */
	private boolean userFieldNotNullTest(Object args) {

		if (args instanceof Integer) {
			args = (Integer) args;
			if (args == null || args.toString().length() != 10) {
				return false;
			}
		} else if (args instanceof String) {
			args = ((String) args).trim();
			if (args == null || "".equals(args)) {
				return false;
			}
		}
		return true;
	}

	private Map<String, String> cookies;

	@Override
	public void setCookiesMap(Map<String, String> cookies) {
		this.cookies = cookies;
	}

}
