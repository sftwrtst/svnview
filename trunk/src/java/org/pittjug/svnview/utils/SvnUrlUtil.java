/*
 * SvnUrlUtil.java
 *
 * Created on January 15, 2007, 5:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.pittjug.svnview.utils;

import javax.servlet.http.HttpServletRequest;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 *
 * @author Carl
 */
public class SvnUrlUtil {
    
    /** Creates a new instance of SvnUrlUtil */
    private SvnUrlUtil() {
    }
    static{
        /*
         * For using over http:// and https://
         */
        DAVRepositoryFactory.setup();
        /*
         * For using over svn:// and svn+xxx://
         */
        SVNRepositoryFactoryImpl.setup();
        
        /*
         * For using over file:///
         */
        FSRepositoryFactory.setup();
    }
    
    public static String getRepositoryURL(String repositoryBase, String projectName){
        if(repositoryBase == null || projectName == null){
            return null;
        }
        repositoryBase.trim();
        projectName.trim();
        String repositoryName = "";
        while(repositoryBase.endsWith("/") && repositoryBase.length() > 0){
            repositoryBase = repositoryBase.substring(0, repositoryBase.length() -1);
        }
        while(projectName.startsWith("/") && projectName.length() > 0){
            projectName = projectName.substring(1);
        }
        while(projectName.endsWith("/")){
            projectName = projectName.substring(0, projectName.length() -1);
        }
        if(projectName.length() == 0){
            return repositoryBase.trim();
        }
        repositoryName = repositoryBase + "/" + projectName;
        
        return repositoryName;
    }
    
    public static String getProjectName(HttpServletRequest request){
        String requestURL = request.getRequestURI();
        String context = request.getContextPath();
        if(context != null && context.length() >0){
            requestURL = requestURL.substring(context.length());
        }
        
        while(requestURL.startsWith("/") && requestURL.length() > 0){
            requestURL = requestURL.substring(1);
        }
        int i = requestURL.indexOf("/");
        String pName = "";
        if(i > -1)
            pName = requestURL.substring(0, i);
        request.setAttribute("projectName", pName);
        return pName;
    }
    
    public static SVNRepository getRepository(HttpServletRequest request)throws SVNException{
        String repositoryBase = request.getSession().getServletContext().getInitParameter("reposBase");
        String name = request.getSession().getServletContext().getInitParameter("name");
        String password = request.getSession().getServletContext().getInitParameter("password");
        String reposURL = getRepositoryURL(repositoryBase, getProjectName(request));
        SVNRepository repository = null;
        System.out.println("Repos Base : " + reposURL);
        System.out.flush();
        if(repositoryBase.startsWith("http")){
          repository = DAVRepositoryFactory.create(SVNURL.parseURIEncoded(reposURL));  
        }else{
         repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(reposURL));
        }
        if(name != null && password != null){
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
            repository.setAuthenticationManager(authManager);
        }
        return repository;
    }
}
