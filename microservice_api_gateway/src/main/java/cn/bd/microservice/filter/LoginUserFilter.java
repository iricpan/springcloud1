package cn.bd.microservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component//加到spring容器
public class LoginUserFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return "pre";//设置过滤器类型
    }

    @Override
    public int filterOrder() {
        return 0;//执行过滤顺序
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext ( );
        HttpServletRequest request = currentContext.getRequest ( );
        String token = request.getParameter ("token");
        if(StringUtils.isEmpty (token)){
            currentContext.setSendZuulResponse (false);//过滤该请求,不对其进行路由
            currentContext.setResponseStatusCode (401);//设置状态码
            return null;
        }
        return null;
    }
}
