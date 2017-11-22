var _hmt = _hmt || [];
/* 百度统计 */
function baiduTj() {
	var hm = document.createElement("script");
	hm.src = "//hm.baidu.com/hm.js?219596028e82f20f72dc3202c5843b2c";// dlj100.cn
	var s = document.getElementsByTagName("script")[0];
	s.parentNode.insertBefore(hm, s);
}
function debug(msg) {
	//$("#m_debug_msg").html(msg);
}
function refresh() {
	location.href = location.href;
}
/** 关闭 */
function closeM() {
	try {
		WeixinJSBridge.call("closeWindow");
	} catch (e) {
	}
	window.opener = null;
	window.open('', '_self', '');
	window.close();
}
function err99() {
	$.alert("Sorry,请重新进入页面!", function() {
		location.href = contextPath ? contextPath : "/";
	});
}
//滚动屏蔽处理
var overscroll = function(el) {
	el.addEventListener('touchstart', function() {
		var top = el.scrollTop, totalScroll = el.scrollHeight, currentScroll = top + el.offsetHeight
		if (top === 0) {
			el.scrollTop = 1
		} else if (currentScroll === totalScroll) {
			el.scrollTop = top - 1
		}
	});
	el.addEventListener('touchmove', function(evt) {
		if (el.offsetHeight < el.scrollHeight)
			evt._isScroller = true
	});
}
$(document).ready(function() {
	//百度统计
	baiduTj();
	// ios快速click
	FastClick.attach(document.body);
	// debug控件
	//$(document.body).append("<div id='m_debug_msg'></div>");
	//$(document.body).append("<div id='m_refresh' onclick='refresh()'>刷新</div>");
	overscroll(document.getElementById('m_main'));
	document.body.addEventListener('touchmove', function(evt) {
		if (!evt._isScroller) {
			evt.preventDefault();
		}
	});
	// 绑定返回顶部事件
	bindScrollTop("m_main");
});
/**
 * 下拉事件
 * 
 * @param id
 *            对象ID
 * @param callback
 *            回调事件
 * @param tipsPulling
 *            下拉中提示
 * @param tipsFree
 *            下拉到位提示
 * @param tipsLoading
 *            释放后提示
 * @param tipsNoMore
 *            没有更多提示
 * @param delay
 *            回调后清理延迟
 */
function mPullDown(id, callback, tipsPulling, tipsFree, tipsLoading, tipsNoMore, delay) {
	var obj = $("#" + id);
	var tid = "mpull_down_" + id;
	var hid = "mpull_down_h_" + id;
	if ($("#" + tid).length == 0) {
		var h = "";
		h += "<div class='weui-pull-to-refresh-layer' id='" + tid + "'>";
		h += "<div class='pull-to-refresh-arrow'></div>";
		h += "<div class='pull-to-refresh-preloader'></div>";
		h += "<div class='down'>" + (tipsPulling ? tipsPulling : "查看下月数据") + "</div>";
		h += "<div class='up'>" + (tipsFree ? tipsFree : "松开加载") + "</div>";
		h += "<div class='refresh'>" + (tipsLoading ? tipsLoading : "正在加载...") + "</div>";
		h += "</div>";
		h += "<div id='" + hid + "' class='mpull_down_nomore weui-pull-to-refresh-layer'>";
		h += tipsNoMore ? tipsNoMore : "没有更多数据了";
		h += "</div>";
		obj.prepend(h);
	}
	obj.pullToRefresh();
	delay = delay && delay > 0 ? delay : 100;
	obj.on("pull-to-refresh", function() {
		if (null != callback) {
			if (typeof callback == "function") {
				callback();
			} else {
				try {
					eval(callback + "()");
				} catch (e) {
				}
			}
		}
		setTimeout(function() {
			obj.pullToRefreshDone();
		}, delay);
	});
}
/** 恢复下拉提示 */
function mPullDownMore(id) {
	$("#mpull_down_h_" + id).hide();
	$("#mpull_down_" + id).show();
}
/** 隐藏下拉提示 */
function mPullDownNoMore(id) {
	$("#mpull_down_" + id).hide();
	$("#mpull_down_h_" + id).show();
}
/**
 * 滚动加载
 * 
 * <pre>
 * var _msc = new M_SCROLLER($('#obj_id');//滚动加载控件(控件名自定义，支持多个)
 * 需要实现
 * 1、上月按钮触发事件：mScrollerPrv(obj_id)
 * 2、数据加载触发事件：mScrollerLoad(obj_id)
 * 参数为容器ID,同一页面构造多个滚动加载对象时，根据容器id判断
 * </pre>
 * 
 */
var M_SCROLLER = function(el) {
	this.container = el;

	var id = this.container.attr("id");
	var id_lad = "mscroll_" + id + "_lad";
	var id_prv = "mscroll_" + id + "_prv";
	var id_nom = "mscroll_" + id + "_nom";
	var id_fag = "mscroll_" + id + "_fag";
	var h = "";
	h += "<div class='weui-infinite-scroll' id='" + id_lad + "'>";
	h += "<div class='infinite-preloader'></div>";
	h += "正在加载...";
	h += "</div>";
	h += "<div class='weui-infinite-scroll' id='" + id_prv + "'>";
	h += "<a onclick=\"M_SCROLLER_PRV('" + id + "')\" class='weui_btn weui_btn_mini weui_btn_primary'>点击查看上月数据</a>";
	h += "</div>";
	h += "<div class='weui-infinite-scroll' id='" + id_nom + "'>已无更多数据</div>";
	h += "<input type='hidden' id='" + id_fag + "' value='0'/>";
	this.container.append(h);
	this.tips_lad = $("#" + id_lad);
	this.tips_prv = $("#" + id_prv);
	this.tips_nom = $("#" + id_nom);
	this.tips_fag = $("#" + id_fag);
	this.tips_lad.show();

	this.init = function(e) {
		this.tips_fag.val("0");// 非正在加载
		this.container.infinite().on("infinite", function(obj) {
			// console.log("..........."+obj);
			var id = obj.target.id;// 容器ID
			var fg = $("#mscroll_" + id + "_fag");// 加载标志位
			if (fg.val() == "0") {
				fg.val("1");
				if (typeof mScrollerLoad == "function") {
					mScrollerLoad(id);
				}
			}
		});
	}
	this.reinit = function(e) {
		this.tips_fag.val("0");// 非正在加载
		this.container.infinite();
		this.loading(e);
	}
	this.free = function(e) {
		this.tips_fag.val("0");
	}
	this.stop = function(e) {
		this.tips_lad.hide();
		this.tips_nom.hide();
		this.tips_prv.hide();
		this.container.destroyInfinite();
	}
	this.prv = function(e) {
		this.tips_lad.hide();
		this.tips_nom.hide();
		this.tips_prv.show();
		this.container.destroyInfinite();
	};
	this.nomore = function(e) {
		this.tips_lad.hide();
		this.tips_prv.hide();
		this.tips_nom.show();
		this.container.destroyInfinite();
	};
	this.loading = function(e) {
		this.tips_lad.show();
		this.tips_nom.hide();
		this.tips_prv.hide();
	}
	this.init();// 初始化
}
function M_SCROLLER_PRV(id) {
	if (typeof mScrollerPrv == "function") {
		mScrollerPrv(id);
	}
}

/** 月份 */
var M_MONTH = function(mon, limit, monCH, initedCallback) {
	var mreg = new RegExp("20[0-9][0-9]((0[1-9])|(1[0-2]))");
	this.date = new Date();
	this.date.setDate(1);
	this.date.setHours(0, 0, 0, 0);
	this.ch_mon = null != monCH ? ($("#" + monCH)) : null;
	this.now_mon = (this.date.getFullYear() * 100) + this.date.getMonth();// 当前月份
	this.free = (null != limit && (limit == "false" || limit == false));
	this.set = function(mon, cb) {
		if (null != mon && mreg.test(mon)) {
			var _y = parseInt(mon.substring(0, 4));
			var _m = parseInt(mon.substring(5));
			if (_y > 2000 && _y < 2100 && _m < 13) {
				_m--;
				this.date.setFullYear(_y, _m);
			}
		}
		if (null != this.ch_mon) {
			this.ch_mon.html(this.getCH());
		}
		if (null != cb) {
			var ym = this.get();// 修改后的返回月份
			var current = (ym - 1) == this.now_mon;// 是否本月
			if (typeof cb == "function") {
				cb(ym, current);
			} else {
				try {
					eval(cb + "(" + ym + "," + current + ")");
				} catch (e) {
					console.log("M_MONTH.set.callback error, " + cb + ":" + e);
				}
			}
		}
	}
	this.get = function() {
		return (this.date.getFullYear() * 100) + (this.date.getMonth() + 1);
	}
	this.getCH = function() {
		return this.date.getFullYear() + "年" + (this.date.getMonth() + 1) + "月";
	}
	this.next = function(isNext, cb) {
		var y = this.date.getFullYear();// 年
		var m = this.date.getMonth();// 月
		var ym_b = y * 100 + m;// 修改前的年月
		if (isNext) {
			if (m < 11) {
				m++;
			} else {
				y++;
				m = 0;
			}
		} else {
			if (m > 0) {
				m--;
			} else {
				y--;
				m = 11;
			}
		}
		var ym_e = y * 100 + m;// 修改后的年月
		if (this.free || this.now_mon >= ym_e) {
			this.date.setFullYear(y, m);
		} else {
			ym_e = ym_b;
		}
		if (null != cb) {
			var ym = this.get();// 修改后的返回月份
			var current = ym_e == this.now_mon;// 是否本月
			if (typeof cb == "function") {
				cb(ym, current);
			} else {
				try {
					eval(cb + "(" + ym + "," + current + ")");
				} catch (e) {
					console.log("M_MONTH.next.callback error, " + cb + ":" + e);
				}
			}
			if (null != this.ch_mon) {
				this.ch_mon.html(this.getCH());
			}
		}
	}
	this.set(mon, initedCallback);
}
