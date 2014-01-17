package cn.scau.zzzd.xst.base;

import android.os.Environment;
/**
 * 常量类
 * @author gccd
 * 2013-12-13
 */
public final class C {
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	// core settings (important)
	/**
	 *	缓存目录 
	 */
	public static final class dir {
		public static final String base				= Environment.getExternalStorageDirectory().getPath()+"/fellowship/cache";
		public static final String img				= base + "/img";
		public static final String list				= base + "/articles";
		public static final String detail			= base + "/article";
	}
	
	/**
	 * 接口url 
	 */
	public static final class api {
		public static final String base				= "http://szltech.f3322.org:2080/Sodality/phone";
//		public static final String base				= "http://192.168.0.80:8080/Sodality/phone";
		public static final String login				= "/user-login.action";
		public static final String list				= "/article-list.action";
		public static final String applylist				= "/user-activity!getApplyList.action";
		public static final String partlist				= "/user-activity!getParticipateList.action";
		public static final String detail		= "/article-details.action";
		public static final String getCode			= "/user-verification!getcode.action";
		public static final String verifyCode		= "/user-verification!verifyCode.action";
		public static final String updateInfo		= "/user-update!information.action";
		public static final String signin		= "/article-signin.action";
		public static final String modifypsw		= "/user-update!password.action";
		public static final String apply			="/article-apply.action";
		public static final String cancelapply			="/article-apply!cancel.action";
		public static final String getCount 	= "/user-activity!getCount.action";
		
		public static final String img		= "http://szltech.f3322.org:2080/Sodality/articleImg/";
	}
	
	public static final String getFullApi(String api){
		return C.api.base+api;
	}
	/**
	 *	task 标识 
	 */
	public static final class task {
		public static final int index						= 1001;
		
		public static final int getCode						= 1002;
		public static final int verifyCode						= 1003;
		public static final int updateInfo						= 1004;
		
		public static final int setPassword 				= 1005;
		public static final int login 				= 1006;
		public static final int signin 				= 1009;
		public static final int detail 				= 1010;
		
		public static final int search						= 2001;
		
		
		public static final int listupdate		= 9000;
		public static final int listupdate1		= 9001;
		public static final int listupdate2		= 9002;
		public static final int listupdate3		= 9003;
		public static final int listupdate4		= 9004;
		public static final int listupdate5		= 9005;
		public static final int listupdate6		= 9006;
		public static final int listupdate7		= 9007;
		public static final int listupdate8		= 9008;
		public static final int listLoadmore		= 3000;
		public static final int listGalleryUpdate		= 5004;
		public static final int listInfoUpdate			= 5005;
		public static final int listActivityUpdate			= 5002;
		public static final int listActivityLoadMore		= 5003;
		public static final int modifyPsw						=5006;
		public static final int signup							=5007;
		public static final int unsignup							=5008;
		public static final int useurlinfo							=5009;
		
		public static final int listapplyactupdate		= 2500;
		public static final int listapplyactloadmore		= 2501;
		public static final int listpartactupdate		= 2502;
		public static final int listpartactloadmore		= 2503;
		

		public static final int detailcheck = 2030;

		public static final int listInfoLoadMore = 3021;

		public static final int searchloadmore = 3002;

		public static final int getCount = 3001;

	}
	/**
	 * 广播接收器 100改变状态,200清除缓存
	 */
	public static final String ACTION_CODE= "action_code";
	////////////////////////////////////////////////////////////////////////////////////////////////
	// intent & action settings
	
	public static final class INTENT {
		public static final class action {
			
			
			/**
			 * 注册的动作
			 */
			public static final String ACTION_NAME_UI_CHANGE="cn.scau.zzzd.xst.ui.CHANGE";
		}
	}
	
	public static final class packname {
		public static final String packpath			= "cn.scau.zzzd.xst";
	}
	
	public static final class Model{
		public static final String ARTICLE			= "Article";
		public static final String USER			= "User";
	}
	
	public final static String DEFAULT_CATEGORY_JSON="";
	
	public static final class CONFIGKEY{
		public static final String NAME="config";
		public static final String USERID="userid";
		public static final String CARDNB="cardnb";
		public static final String USERNAME="username";
		public static final String NICKNAME="nickname";
		public static final String SEX="sex";
		public static final String PHONE="phone";
		public static final String UNIT="unit";
		public static final String DUTY="duty";
		
		public static final String USEURLOFSINA="useUrlOfSina";
		public static final String USEURLOFTENCENT="useUrlOfTencent";
		public static final String USEIDOFSINA="useIdOfSina";
		public static final String USEIDOFTENCENT="useIdOfTencent";
		
		public static final String USENAMEOFSINA="useNameOfSina";
		public static final String USENAMEOFTENCENT="useNameOfTencent";
		
	}
	
	/**
	 *	网络数据参数 
	 */
	public static final class PARAMSNAME{
		
		//List
		public static final String REFRESHTIME = "refreshTime";
		public static final String KEYWORD	 = "key_word";
		public static final String PHONE	 = "phone";
		public static final String TYPE 	= "type";
		public static final String PAGEINDEX = "pageIndex";
		//verify
		public static final String CARDNB 	= "cardNb";
		public static final String NAME 	= "name";
		public static final String VERIFYCODE 	= "verifycode";
		public static final String PASSWORD 	= "password";
		public static final String ISFORGET 	= "isforget";
		public static final String USERNAME = "username";
		//artcle
		public static final String ARTICLE_ID = "articleId";
		//use
		public static final String UNIT  ="unit";
		public static final String SEX  ="sex";
		public static final String DUTY  ="duty";
		public static final String NICKNAME="nickname";
		public static final String UPDATETIME = "updatetime";
	}
	
	public static final String SEPARATOR = "_";
	public static final String ACTION_CLEAR_BROADCAST = "cn.scau.zzzd.xst.clear.content";
	
	/**
	 *	服务端,响应码 
	 */
	public static final class RESPONECODE{
		public static final String NORMAL    = "0";
		public static final String SUCCESS    = "101";
		public static final String FAIL       = "102";
		public static final String REGED      = "103";
	}
	/**
	 * Intent params
	 */
	public class PARAMS{
		public static final String PAGENO      = "pageno";
		public static final String TYPE      = "type";
		public static final String ARTICLE_ID      = "id";
		public static final String SHOWPIC = "showpic";
		public static final String TITLE = "title";
		public static final String STATE = "state";
		public static final String SIGNINSTATE="issignin";
		
		public static final String ARTICLEID="articleid";
		public static final String APPLYSTATE="applystate";
		public static final String SIGNSTATE="signstate";
		
		public static final String ACT_NUMBER="act_number";
	}
	/**
	 * log
	 * @author gccd
	 * 2013-12-13
	 */
	public static final class err {
		public static final String nonetwork			= "没接通网络哦,请检查~";
		public static final String network			= "网络错误或服务器维护中";
		public static final String message			= "消息错误";
		public static final String jsonFormat		= "消息格式错误";
		public static final String other				= "其他错误";
	}
	/**
	 * 
	 * @author zh_u_
	 * 2013-12-14
	 */
	public enum ARTCLE_TYPE {
		//1-协会概况,2-通知公告,3-新闻动态,4-品牌活动,5-人才政策,6-特色服务,7-活动信息，8-推荐信息（当没有关键词的时候type必须传）
		ASSOCIATION_OVERVIEW(1),
		ANNOUNCEMENT(2),
		NEWACT(3),
		BRAND_EVENTS(4),
		PERSONNEL_POLICY(5),
		SPECIAL_SERVICES(6),
		ACTINFO(7),
		RECOMMENDED_INFO(8);
		int type_num  = 0;
		ARTCLE_TYPE(int type_num) {
			// TODO Auto-generated constructor stub
			this.type_num = type_num;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return ""+type_num;
		}
		
	}
}