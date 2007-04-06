/*
 * LockSortArrowsTag.java
 *
 * Created on January 15, 2007, 11:37 AM
 */

package org.pittjug.svnview.taglib;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author  ctrusiak
 * @version
 */

public class LockSortArrowsTag extends SimpleTagSupport {
    
    /**
     * Initialization of type property.
     */
    private java.lang.String type;
    
    /**
     * Initialization of orderBy property.
     */
    private java.lang.String orderBy;
    
    /**
     * Initialization of asc property.
     */
    private java.lang.String asc;
    
    /**Called by the container to invoke this tag.
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    public void doTag() throws JspException {
        
        JspWriter out=getJspContext().getOut();
        String projectName = (String)getJspContext().getAttribute("projectName", PageContext.REQUEST_SCOPE);
        try {
            boolean onAsc = type.equals(orderBy) && !"false".equals(asc);
            boolean onDesc = type.equals(orderBy) && "false".equals(asc);
            if(onAsc){
                out.print("<img src=\"../images/sortArrowBeigeBlack_up_old.gif\"/>");
            } else{
                out.print("<a href=\"./AllLocks.do?orderBy=" + type + "&asc=true\"><img src=\"../images/sortArrowBeigeOpen_up_old.gif\"/ border=\"0\"></a>");
            }
            if(onDesc){
                out.print("<img src=\"../images/sortArrowBeigeBlack_down_old.gif\"/>");
            } else{
                out.print("<a href=\"./AllLocks.do?orderBy=" + type + "&asc=false\"><img src=\"../images/sortArrowBeigeOpen_down_old.gif\"/ border=\"0\"></a>");
            }
            out.flush();
        } catch (java.io.IOException ex) {
            throw new JspException(ex.getMessage());
        }
        
    }
    
    /**
     * Setter for the type attribute.
     */
    public void setType(java.lang.String value) {
        this.type = value;
    }
    
    /**
     * Setter for the orderBy attribute.
     */
    public void setOrderBy(java.lang.String value) {
        this.orderBy = value;
    }
    
    /**
     * Setter for the asc attribute.
     */
    public void setAsc(java.lang.String value) {
        this.asc = value;
    }
}
