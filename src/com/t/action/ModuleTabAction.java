package com.t.action;
/**
 * 安卓主页三个List得到后获取某个记录的详情
 */

import org.springframework.beans.factory.annotation.Autowired;
import com.t.service.interfaces.IModuleTabService;
import com.t.utils.BaseAction;

public class ModuleTabAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Integer moduleId;
	private Integer itemId;

	@Autowired
	public IModuleTabService mtService;

	public String getModuleItemDetail(){
		try{
			result.put("result",mtService.getDatail(moduleId, itemId));
			return SUCCESS;
		}catch(Exception e){
			return FAIL;
		}
	}

	public Integer getModuleId() {    //
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

}
