/*
 * ShowFileHistoryAction.java
 *
 * Created on January 15, 2007, 9:28 AM
 */

package org.pittjug.svnview.action;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.pittjug.svnview.forms.PathForm;
import org.pittjug.svnview.utils.SvnUrlUtil;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.io.SVNRepository;
/**
 *
 * @author Carl
 * @version
 */

public class ViewFileAction extends Action implements ActionMappingConstants{
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PathForm path = (PathForm)form;
        String version = request.getParameter("version");
        int v = Integer.parseInt(version);
        SVNRepository repository = SvnUrlUtil.getRepository(request);
        Map fileProperties = new HashMap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
            /*
             * Gets the contents and properties of the file located at filePath
             * in the repository at the revision
             */
        String fp = (path != null? path.getPath():"");
        repository.getFile(fp, v, fileProperties, baos);
        //OK, lets put in Entry as a String
        request.setAttribute("fileContents", baos.toString());
        return mapping.findForward(SUCCESS);
    }
}
