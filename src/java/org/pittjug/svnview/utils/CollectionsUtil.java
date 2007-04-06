/*
 * CollectionsUtil.java
 *
 * Created on January 15, 2007, 7:40 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.pittjug.svnview.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Carl
 */
public class CollectionsUtil {
    
    /** Creates a new instance of CollectionsUtil */
    private CollectionsUtil() {
    }
    
    public static List toList(Collection c){
        List l = new ArrayList(c.size());
        Iterator iter = c.iterator();
        while(iter.hasNext()){
            l.add(iter.next());
        }
        return l;
    }
    
}
