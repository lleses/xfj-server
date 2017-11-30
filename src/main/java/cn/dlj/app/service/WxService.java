package cn.dlj.app.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.WxDao;
import cn.dlj.app.entity.WxXuncha;
import cn.dlj.app.entity.WxXunchaImg;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.WxConfig;

@Service
@Transactional(readOnly = true)
public class WxService {

	@Autowired
	private WxDao wxDao;

	@Transactional
	public void save(String imgStr, Integer num, Integer xunchaId) {
		String picName = img64(imgStr, WxConfig.XUNCHA_IMG_UPLOAD_PATH);
		WxXunchaImg wxImg = wxDao.getNum(xunchaId, num);
		if (wxImg == null) {
			wxImg = new WxXunchaImg();
			wxImg.setFlag(0);
			wxImg.setXunchaId(xunchaId);
			wxImg.setNum(num);
			wxImg.setPicName(picName);
			wxDao.add(wxImg);
		} else {
			wxImg.setPicName(picName);
			wxDao.update(wxImg.getId(), picName);
		}
	}

	/**
	 * basc64转成图片文件
	 * 
	 * @param imgStr
	 *            basc64
	 * @param basePath
	 *            图片保存路径
	 * @return 图片名称
	 */
	private String img64(String imgStr, String basePath) {
		imgStr = imgStr.replaceAll("data:image/jpg;base64,", "");
		imgStr = imgStr.replaceAll("data:image/jpeg;base64,", "");
		String fileName = IdUtils.id32() + ".jpeg";
		String path = basePath + fileName;
		write(imgStr, path);
		return fileName;
	}

	/** 写文件 **/
	private boolean write(String img64, String outPath) {
		FileOutputStream out = null;
		boolean succ = false;
		try {
			out = new FileOutputStream(outPath);
			Base64 B64 = new Base64();
			out.write(B64.decode(img64));
			out.flush();
			succ = true;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return succ;
	}

	/**
	 * 
	 */
	public List<WxXunchaImg> getList(Integer xunchaId) {
		List<WxXunchaImg> list = new ArrayList<WxXunchaImg>();
		if (xunchaId != null) {
			list = wxDao.getList(xunchaId);
		}
		return list;
	}

	public void updateFalg(Integer id, Integer flag, String remark) {
		wxDao.updateFalg(id, flag, remark);
	}

	public List<WxXuncha> getWxWaitList(PagingMySql paging) {
		return wxDao.getWxWaitList(paging);
	}

	public List<WxXuncha> getWxAlreadyList(PagingMySql paging) {
		return wxDao.getWxAlreadyList(paging);
	}

	public List<WxXuncha> getWxGlyWaitList(PagingMySql paging) {
		return wxDao.getWxGlyWaitList(paging);
	}

	public List<WxXuncha> getWxGlyAlreadyList(PagingMySql paging) {
		return wxDao.getWxGlyAlreadyList(paging);
	}

}