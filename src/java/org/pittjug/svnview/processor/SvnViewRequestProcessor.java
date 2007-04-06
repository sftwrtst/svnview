/*
 * RequestProcessor.java
 *
 * Created on January 16, 2007, 8:45 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.pittjug.svnview.processor;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.RequestProcessor;

/**
 *
 * @author ctrusiak
 */
public class SvnViewRequestProcessor extends RequestProcessor {
    
        /**
     * <p>Identify and return the path component (from the request URI) that
     * we will use to select an <code>ActionMapping</code> with which to dispatch.
     * If no such path can be identified, create an error response and return
     * <code>null</code>.</p>
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     *
     * @exception IOException if an input/output error occurs
     */
    protected String processPath(HttpServletRequest request,
                                 HttpServletResponse response)
        throws IOException {

        String path = null;

        // For prefix matching, match on the path info (if any)
        path = (String) request.getAttribute(INCLUDE_PATH_INFO);
        if (path == null) {
            path = request.getPathInfo();
        }
        if ((path != null) && (path.length() > 0)) {
            return (path);
        }

        // For extension matching, strip the module prefix and extension
        path = (String) request.getAttribute(INCLUDE_SERVLET_PATH);
        if (path == null) {
            path = request.getServletPath();
        }
        String prefix = moduleConfig.getPrefix();
        if (!path.startsWith(prefix)) {
            String msg = getInternal().getMessage("processPath");
            
            log.error(msg + " " + request.getRequestURI());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);

            return null;
        }
        
        path = path.substring(prefix.length());
        int slash = path.lastIndexOf("/");
        int period = path.lastIndexOf(".");
        if ((period >= 0) && (period > slash)) {
            path = path.substring(slash, period);
        }
        return (path);

    }
    
}
