/*
 * SvnViewPathDisplayTag.java
 *
 * Created on January 13, 2007, 7:52 AM
 */

package org.pittjug.svnview.taglib;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.pittjug.svnview.action.ActionMappingConstants;
import org.pittjug.svnview.forms.PathForm;

/**
 *
 * @author  Carl
 * @version
 */

public class SvnViewPathDisplayTag extends SimpleTagSupport {
    
    /**Called by the container to invoke this tag.
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    public void doTag() throws JspException {
        
        JspWriter out=getJspContext().getOut();
        
        try {
            String projectName = (String)getJspContext().getAttribute("projectName", PageContext.REQUEST_SCOPE);
            out.print("<a href=\"./ShowEntry.do?path=\">");
            out.print(projectName + "</a>");
            
            PathForm pathForm = (PathForm)getJspContext().getAttribute(ActionMappingConstants.PATH_FORM, PageContext.REQUEST_SCOPE);
            String[] paths = pathForm.getPath().split("/");
            String pathUrl = "";
            for(int i = 0; i < paths.length; i++){
                String sPath = paths[i];
                if(sPath.length() > 0){
                    pathUrl += "/";
                    
                    pathUrl += sPath;
                    out.println("/");
                    if(i < paths.length -1){
                        out.print("<a href=\"./ShowEntry.do?path=" + pathUrl + "\">");
                    }
                    out.print(sPath);
                    out.print("</a>");
                }
            }
            out.flush();
        } catch (java.io.IOException ex) {
            throw new JspException(ex.getMessage());
        }
        
    }
}
