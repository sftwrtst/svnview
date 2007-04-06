/*
 * ShowFileHistoryAction.java
 *
 * Created on January 15, 2007, 9:28 AM
 */

package org.pittjug.svnview.action;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.pittjug.svnview.forms.PathForm;
import org.pittjug.svnview.utils.CollectionsUtil;
import org.pittjug.svnview.utils.SvnUrlUtil;
import org.tmatesoft.svn.core.io.SVNRepository;
/**
 *
 * @author Carl
 * @version
 */

public class ShowFileHistoryAction extends Action implements ActionMappingConstants{
    
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
        SVNRepository repository = SvnUrlUtil.getRepository(request);
        Collection logEntries = repository.log(new String[] {path.getPath()}, null,
                0, -1, true, true);
        List entries = CollectionsUtil.toList(logEntries);
        Collections.reverse(entries);
        request.setAttribute("logEntries", entries);
        return mapping.findForward(SUCCESS);
    }
}
