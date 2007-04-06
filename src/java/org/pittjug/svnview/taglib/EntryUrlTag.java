/*
 * EntryUrlTag.java
 *
 * Created on January 15, 2007, 7:14 AM
 */

package org.pittjug.svnview.taglib;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.pittjug.svnview.action.ActionMappingConstants;
import org.pittjug.svnview.forms.PathForm;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNNodeKind;

/**
 *
 * @author  Carl
 * @version
 */

public class EntryUrlTag extends SimpleTagSupport {
    
    /**
     * Initialization of entry property.
     */
    private SVNDirEntry entry;
    
    /**Called by the container to invoke this tag.
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    public void doTag() throws JspException {
        
        JspWriter out=getJspContext().getOut();
        
        try {
 //           String projectName = (String)getJspContext().getAttribute("projectName", PageContext.REQUEST_SCOPE);
            PathForm pathForm = (PathForm)getJspContext().getAttribute(ActionMappingConstants.PATH_FORM, PageContext.REQUEST_SCOPE);
            if (entry.getKind() == SVNNodeKind.DIR) {
                String s = "<a href=\"./ShowEntry.do?path=";
                s += (pathForm == null || pathForm.getPath().equals("")) ? entry.getName()
                : pathForm.getPath() + "/" + entry.getName();
                s+= "\">";
                s+= "<img src=\"images/dir.png\" border=\"0\"/> ";
                s += (entry.getName() + "</a>");
                out.print(s);
            } else{
                String s = "<a href=\"./ShowFileHistory.do?path=";
                s += (pathForm == null || pathForm.getPath().equals("")) ? entry.getName()
                : pathForm.getPath() + "/" + entry.getName();
                s+= "\">";
                s += "<img src=\"images/text.png\" border=\"0\"/> ";
                s += (entry.getName() + "</a>");
                out.print(s);
            }
            out.flush();
        } catch (java.io.IOException ex) {
            throw new JspException(ex.getMessage());
        }
        
    }
    
    /**
     * Setter for the entry attribute.
     */
    public void setEntry(SVNDirEntry value) {
        this.entry = value;
    }
}
