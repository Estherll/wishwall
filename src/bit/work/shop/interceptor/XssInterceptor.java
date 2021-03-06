package bit.work.shop.interceptor;
import java.util.Map;

import org.apache.struts2.dispatcher.HttpParameters;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import bit.work.shop.utils.XssShieldUtil;

public class XssInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 7296129163234205866L;

	/**
	 * 预防xxs攻击...未测试
	 */
	
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
    	
    	ActionContext actionContext = invocation.getInvocationContext();
        HttpParameters parameters = actionContext.getParameters();
        Map<String, String[]> map = parameters.toMap();
        for (String key:map.keySet()) {
        	String values[]=map.get(key);
        	for (int i = 0; i < values.length; i++) {
				values[i]=XssShieldUtil.stripXss(values[i]);
			}
        	map.put(key, values);
		}
        
        return invocation.invoke();
    	
    }
}