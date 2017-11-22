//package cn.dlj.wx.menu;
//
//import cn.dlj.utils.WxConfig;
//
///**
// * 菜单管理器类 
// */
//public class MenuManager {
//
//	/** 
//	 * 组装菜单数据 
//	 *  
//	 * @return 
//	 */
//	public static Menu getTestMenu() {
//		ViewButton btn11 = new ViewButton();
//		btn11.setName("消防知识考试");
//		btn11.setType("view");
//		btn11.setUrl("http://kaoba.101test.com/cand/index?redirect=0&paperId=MCLFYN");
//
//		ViewButton btn12 = new ViewButton();
//		btn12.setName("指尖查");
//		btn12.setType("view");
//		btn12.setUrl("http://qyxf.gzchsoft.com/webapp/wlandefault.aspx");
//
//		ViewButton btn13 = new ViewButton();
//		btn13.setName("随手拍");
//		btn13.setType("view");
//		btn13.setUrl("http://96119.119cp.cn/frmsjindex.aspx");
//
//		ViewButton btn14 = new ViewButton();
//		btn14.setName("微信约");
//		btn14.setType("view");
//		btn14.setUrl("http://xfz.gdfire.gov.cn/default.aspx");
//
//		ViewButton btn15 = new ViewButton();
//		btn15.setName("广东消防网上办事");
//		btn15.setType("view");
//		btn15.setUrl("http://wsbs.gdfire.gov.cn/index.aspx?AspxAutoDetectCookieSupport=1");
//
//		ViewButton btn21 = new ViewButton();
//		btn21.setName("消防法律法规");
//		btn21.setType("view");
//		btn21.setUrl("http://fire.91smy.cn/index.php?s=/List/index/cid/2.html");
//
//		ViewButton btn22 = new ViewButton();
//		btn22.setName("公众消防知识");
//		btn22.setType("view");
//		btn22.setUrl("http://fire.91smy.cn/index.php?s=/List/index/cid/6.html");
//
//		ViewButton btn23 = new ViewButton();
//		btn23.setName("行业消防知识");
//		btn23.setType("view");
//		btn23.setUrl("http://fire.91smy.cn/index.php?s=/List/index/cid/10.html");
//
//		ViewButton btn24 = new ViewButton();
//		btn24.setName("消防动态");
//		btn24.setType("view");
//		btn24.setUrl(
//				"https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzAwOTI4MzY2Mw==&scene=123&uin=MjQwNTIyNDc2Mg%3D%3D&key=5a00ffe7993af6371caf4270ed8683ae33d4d776b52fd979532ed9fdb4a533d30ba1123448e12052712a77f6b30a8fc306e6c0a009662fe0905218a7ea576c9f1f05f3093a2a58be978b954842a6779e&devicetype=iMac+MacBookPro9%2C2+OSX+OSX+10.12.1+build(16B2555)&version=12020610&lang=zh_CN&nettype=WIFI&a8scene=0&fontScale=100&pass_ticket=bAesURfGNgnQPSXjnpf5EfhNyKSA4SgrcKYq8Y7tmU3oASqdMviPAxIy6YNPrci1");
//
//		ViewButton btn25 = new ViewButton();
//		btn25.setName("廉政执法举报投诉");
//		btn25.setType("view");
//		btn25.setUrl("http://www.zs119.net/survey/complain.php");
//
//		ViewButton btn31 = new ViewButton();
//		btn31.setName("@中山消防");
//		btn31.setType("view");
//		btn31.setUrl("https://m.weibo.cn/p/1005052493142094");
//
//		ViewButton btn32 = new ViewButton();
//		btn32.setName("消防业务查询");
//		btn32.setType("view");
//		btn32.setUrl("http://fire.91smy.cn/index.php?s=/index/query");
//		
//		ViewButton btn33 = new ViewButton();
//		btn33.setName("消防微信平台");
//		btn33.setType("view");
//		btn33.setUrl(WxConfig.DOMAIN_NAME + "/wx/xc/index");
//
//		ComButton mainBtn1 = new ComButton();
//		mainBtn1.setName("消防资讯");
//		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14, btn15 });
//
//		ComButton mainBtn2 = new ComButton();
//		mainBtn2.setName("中山消防");
//		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24, btn25 });
//
//		ComButton mainBtn3 = new ComButton();
//		mainBtn3.setName("我的消防");
//		mainBtn3.setSub_button(new Button[] { btn31, btn32 ,btn33});
//
//		/** 
//		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
//		 *  
//		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
//		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
//		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
//		 */
//		Menu menu = new Menu();
//		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
//		return menu;
//	}
//
//	/** 
//	 * 组装菜单数据 
//	 *  
//	 * @return 
//	 */
//	public static Menu getTestMenu2() {
//		ClickButton btn11 = new ClickButton();
//		btn11.setName("微信相册发图");
//		btn11.setType("pic_weixin");
//		btn11.setKey("rselfmenu_1_1");
//		ClickButton btn21 = new ClickButton();
//		btn21.setName("aa");
//		btn21.setType("pic_weixin");
//		btn21.setKey("rselfmenu_1_1");
//		ClickButton btn31 = new ClickButton();
//		btn31.setName("xx");
//		btn31.setType("pic_weixin");
//		btn31.setKey("rselfmenu_1_1");
//
//		ViewButton btn32 = new ViewButton();
//		btn32.setName("消防业务查询");
//		btn32.setType("view");
//		btn32.setUrl(WxConfig.DOMAIN_NAME + "/wx/xc/index");
//
//		ComButton mainBtn1 = new ComButton();
//		mainBtn1.setName("发图");
//		mainBtn1.setSub_button(new Button[] { btn11 });
//
//		ComButton mainBtn2 = new ComButton();
//		mainBtn2.setName("扫码");
//		mainBtn2.setSub_button(new Button[] { btn21 });
//
//		ComButton mainBtn3 = new ComButton();
//		mainBtn3.setName("个人中");
//		mainBtn3.setSub_button(new Button[] { btn31, btn32 });
//
//		Menu menu = new Menu();
//		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
//		return menu;
//	}
//}