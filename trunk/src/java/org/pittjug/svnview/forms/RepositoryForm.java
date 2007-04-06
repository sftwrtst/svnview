/*
 * RepositoryForm.java
 *
 * Created on January 15, 2007, 5:29 AM
 */

package org.pittjug.svnview.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Carl
 * @version
 */

public class RepositoryForm extends ActionForm {
    
    private String projectName;
    
    private String branch;
    
    /**
     *
     */
    public RepositoryForm() {
        super();
    }
    
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
