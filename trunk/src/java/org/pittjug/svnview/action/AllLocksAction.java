/*
 * AllLocksAction.java
 *
 * Created on January 15, 2007, 9:50 AM
 */

package org.pittjug.svnview.action;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.pittjug.svnview.utils.SvnUrlUtil;
import org.tmatesoft.svn.core.SVNLock;
import org.tmatesoft.svn.core.io.SVNRepository;
/**
 *
 * @author Carl
 * @version
 */

public class AllLocksAction extends Action implements ActionMappingConstants{
    
    
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
        SVNRepository repository = SvnUrlUtil.getRepository(request);
        SVNLock[] lockA = repository.getLocks("");
        String orderBy = request.getParameter("orderBy");
        Comparator c = null;
        if("date".equalsIgnoreCase(orderBy)){
            c = new DateComparator();
        }
        else if("name".equalsIgnoreCase(orderBy)){
            c = new NameComparator();
        }
        else{
            c = new OwnerComparator();
        }
        boolean asc = !"false".equalsIgnoreCase(request.getParameter("asc"));
        if(!asc){
            c = Collections.reverseOrder(c);
        }
        Arrays.sort(lockA, c);
        request.setAttribute("allLocks", lockA);
        return mapping.findForward(SUCCESS);
    }
    
    class DateComparator implements Comparator{
        public int compare(Object o1, Object o2) {
            SVNLock l1 = (SVNLock)o1;
            SVNLock l2 = (SVNLock)o2;
            return l1.getCreationDate().compareTo(l2.getCreationDate());
        }
    }
    class NameComparator implements Comparator{
        public int compare(Object o1, Object o2) {
            SVNLock l1 = (SVNLock)o1;
            SVNLock l2 = (SVNLock)o2;
            return l1.getPath().compareTo(l2.getPath());
        }
    }
    class OwnerComparator implements Comparator{
        public int compare(Object o1, Object o2) {
            SVNLock l1 = (SVNLock)o1;
            SVNLock l2 = (SVNLock)o2;
            return l1.getOwner().compareTo(l2.getOwner());
        }
    }
}
