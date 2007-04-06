/*
 * PathForm.java
 *
 * Created on January 15, 2007, 5:49 AM
 */

package org.pittjug.svnview.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Carl
 * @version
 */

public class PathForm extends org.apache.struts.action.ActionForm {
    
    private String path;

    public String getPath() {
        if(path == null){
            return "";
        }
        return path;
    }

    public void setPath(String path) {
        if(path == null){
            path = "";
        }
        this.path = path.trim();
    }
}
