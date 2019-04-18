package com.qianfeng.utils;

import com.qianfeng.common.JsonBean;
import com.qianfeng.common.LayUIListJsonBean;
import com.qianfeng.common.PageInfo;

import java.util.List;

public class JsonUtils {

	public static JsonBean createJsonBean(int code, Object info){
		JsonBean bean = new JsonBean();
		bean.setCode(code);
		bean.setInfo(info);
		
		return bean;
	}

	/**
	 *
	 * @param code
	 * 	layUI的规则，code == 0时代表成功发送数据。
	 * @param data
	 * 	data放入数据
	 * @param count
	 * 	未测试，不需要counb时可以不写
	 * @return
	 * 	返回一个Json数据模型。控制层需要@ResponseBody
	 */
	public static LayUIListJsonBean createListBean(int code, Object data, Integer count, String msg){
		LayUIListJsonBean bean = new LayUIListJsonBean();
		bean.setCode(code);
		bean.setCount(count);
		bean.setData(data);
		if(code == 0 && (msg == null || msg == "")){
			bean.setMsg("success");
		} else {
			bean.setMsg(msg);
		}
		return bean;
	}


	public static LayUIListJsonBean createListBean(int code, Object data, Integer count){
		return createListBean(code, data,count, "");
	}


	public static LayUIListJsonBean createListBean(int code, PageInfo pageInfo){
		Object data = pageInfo.getData();
		int count = pageInfo.getCount();
		return createListBean(code, data,count, "");
	}
}
