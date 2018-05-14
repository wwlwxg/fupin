package com.wwlwxg.springmvc.common.util;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class AuthUtil {
	public static boolean hasPermission(BigDecimal menuId, BigDecimal kindId,Map<BigDecimal,Set<BigDecimal>> menuKindMap){
		if(menuKindMap!=null){
			if (kindId == null){//如果该角色在该菜单下有任何一个权限，则就有查看权限
				if (!menuKindMap.get(menuId).isEmpty()){
					return true;
				}
			}else{
				Set<BigDecimal> kinds = menuKindMap.get(menuId);
				if(kinds!=null){
					for(BigDecimal k:kinds){
						if(k.equals(kindId))return true;
					}
				}
			}
		}
		return false;
	}
}
