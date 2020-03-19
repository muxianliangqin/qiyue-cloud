package com.qiyue.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.qiyue.common.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class RequestFilter extends ZuulFilter {

    @Value(value = "check.session.excludes")
    private List checkSessionExcludes;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        // 不检查session的路径
        if (checkSessionExcludes.contains(uri)) {
            // 设置true表示zuul将请求往后传到其他子服务，false表示不再往后传
            ctx.setSendZuulResponse(true);
            return null;
        }
        // 判断session是否存在，如不存在证明已过期
        HttpSession session = request.getSession(false);
        if (null == session) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(Constant.HTTP_RESPONSE_STATUS_SESSION_EXPIRE);
            return null;
        }
        ctx.setSendZuulResponse(true);
        return null;
    }
}
