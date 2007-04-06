/*
 * ShowEntryAction.java
 *
 * Created on January 15, 2007, 5:30 AM
 */

package org.pittjug.svnview.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNLock;
import org.tmatesoft.svn.core.SVNNodeKind;
/**
 *
 * @author Carl
 * @version
 */

public class ShowEntryAction extends Action  implements ActionMappingConstants{
    static EntryComparator ec = new EntryComparator();
    
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
        try{
        PathForm path = (PathForm)form;
        SVNRepository repository = SvnUrlUtil.getRepository(request);
        Collection entries = repository.getDir(path != null? path.getPath():"", -1, null,
                (Collection) null);
        List entryList = CollectionsUtil.toList(entries);
        Collections.sort(entryList, ec);
        //lets get the properties for each entry
        Iterator iterator = entries.iterator();
        Map entryProperties = new HashMap();
        while (iterator.hasNext()) {
            SVNDirEntry entry = (SVNDirEntry) iterator.next();
            Map fileProperties = new HashMap();
            SVNNodeKind nodeKind = entry.getKind();
            
            if (nodeKind == SVNNodeKind.NONE) {
                continue;
            } else if (nodeKind == SVNNodeKind.DIR) {
                continue;
            }
            /*
             * Gets the contents and properties of the file located at filePath
             * in the repository at the latest revision (which is meant by a
             * negative revision number).
             */
            String prefix = "";
            if(path != null && path.getPath() != null && path.getPath().length() > 0){
                prefix = path.getPath() + "/";
            }
            String fp = prefix + entry.getRelativePath();
            System.out.println(fp);
            SVNLock[] lockA = repository.getLocks(fp);
            if(lockA != null && lockA.length > 0){
                entry.setLock(lockA[0]);
            }
            repository.getFile(fp, -1, fileProperties, null);
            Iterator iterator2 = fileProperties.keySet().iterator();
        /*
         * Displays file properties.
         */
            
            entryProperties.put(entry, fileProperties);
        }
        request.setAttribute("entries", entryList);
        request.setAttribute("entryProps", entryProperties);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }
    
    static class EntryComparator implements Comparator{
        public int compare(Object o1, Object o2) {
            SVNDirEntry e1 = (SVNDirEntry)o1;
            SVNDirEntry e2 = (SVNDirEntry) o2;
            if(e1.getKind() == SVNNodeKind.DIR && e2.getKind() != SVNNodeKind.DIR){
                return -1;
            }
            if(e1.getKind() != SVNNodeKind.DIR && e2.getKind() == SVNNodeKind.DIR){
                return 1;
            }
            return e1.getName().compareTo(e2.getName());
        }
    }
}
