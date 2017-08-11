package com.weiweb.core.tags;


import org.springframework.web.servlet.tags.RequestContextAwareTag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;


public class VersionTag extends RequestContextAwareTag {
    
	private static final long serialVersionUID = -7029902933038248127L;

	@Override
    protected int doStartTagInternal() throws Exception {

        return 0;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
//            String  version = SystemConfigCache.findValueByName(DataFieldEnum.JSANDCSSVERSION);
//            if(StringUtils.isNotBlank(version)){
                out.write("1.0.0");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
