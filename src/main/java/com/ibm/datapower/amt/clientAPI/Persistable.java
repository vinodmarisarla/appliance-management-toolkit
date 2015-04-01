/**
 * Copyright 2014 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/


package com.ibm.datapower.amt.clientAPI;

import com.ibm.datapower.amt.Constants;

/**
 * An interface to identify which classes should be persisted to a datastore.
 * Any class that does not implement Persistable should be considered
 * non-persistable and will be constructed with dynamic data. If a class
 * implements this interface, then it is likely there will be a corresponding
 * class in the <a href="../dataAPI/package-summary.html">dataAPI</a> package,
 * probably prefixed with the name "Stored". Do not confuse this interface
 * with the one of the same name in the dataAPI package. 
 * <p>
 * I'd like to put package-access method signatures in here to force
 * implementers to implement them as well as the public-access methods, but I
 * want some of these method signatures to have package access, not public
 * access. Interfaces allow only methods that have public access. Since I don't
 * want package methods to be appearing as public, this will be mostly a marker
 * interface. You'll have to manually promise to implement the commented methods
 * in your class, and those methods should be package access. If not, you might
 * get a runtime error instead of a compile time error. The compiler will not
 * enforce that at compile time. Please read the comments in this source file
 * for the full list of methods that are required (but not enforced by the
 * compiler).
 * <p>
 * For more information about how this interface is intended to work, refer to
 * the <code>PersistenceMapper</code> javadoc. That class is package access,
 * not public access, so the javadoc for it may not be generated by default.
 * <p>
 * 
 * @see com.ibm.datapower.amt.clientAPI.PersistenceMapper
 * @version SCM ID: $Id: Persistable.java,v 1.2 2010/08/23 21:20:27 burket Exp $
 * <p>
 */
//* Created on Aug 17, 2006
public interface Persistable {
    
    public static final String COPYRIGHT_2009_2010 = Constants.COPYRIGHT_2009_2010;
    
    static final String SCM_REVISION = "$Revision: 1.2 $"; //$NON-NLS-1$
    
    /**
     * Get the pre-built String that could be used as a primary key for this
     * object if you need to persist it anywhere or put it in a hash collection.
     * Although this method isn't necessary for users of the clientAPI, it may
     * be helpful for them to have so they don't need to implement it
     * themselves. It is used internally within the clientAPI and is exposed
     * here for your convenience. It is implemented by calling the
     * <code>Stored*.getPrimaryKey()</code> method supplied by the dataAPI
     * implementation.
     * 
     * @return a String that could represent a unique instance of this object.
     *         It will not include the class name. For example, for a
     *         DomainVersion it could return "myManagedSet:domainA:1", which
     *         includes the name of the ManagedSet that the domain is a member
     *         of, the name of the domain, and the version number of the domain.
     * @throws DeletedException if the object you are referencing has been
     *         "destroyed" internally. Likely the dataAPI object has been
     *         deleted from persistence and although you may have a reference to
     *         the clientAPI wrapper object, it has been marked as deleted also
     *         and you should not use that reference.
     */
    public String getPrimaryKey() throws DeletedException;
    
    /**
     * Retrieve a reference to the persisted objected that has the specified
     * primary key. Since static methods aren't allowed in interfaces, the
     * method declaration will be commented out and not enforced by the
     * compiler. But you should still include this method in the implementing
     * class.
     * 
     * @param targetKey the primary key to search for
     * @return the persisted object that has the specified primary key. May
     *         return <code>null</code> if no persisted object with the
     *         specified primary key was found.
     * @see #getPrimaryKey()
     */
    // public static Persistable getByPrimaryKey(String primaryKey);
    
    /**
     * Remove the object from any containers (ie, remove a DomainVersion from a
     * Domain) and delete the object from persistence. Once an object has been
     * destroyed you should remove all references to it and not try to use it
     * after that point. The intention is that the object is eligible for
     * garbage collection. This method should have package access since
     * clientAPI users shouldn't have free reign to destroy objects.
     * <p>
     * Because interfaces can't define package-access methods (public only),
     * this method declaration will be commented out and not enforced by the
     * compiler. But you should still include this method in the implementing
     * class.
     */
    // void destroy();
    
    /**
     * This object is a wrapper for a Stored* object from the dataAPI, and this
     * method will return the wrapped Stored* object.
     * <p>
     * This method should have package access since clientAPI users should be
     * using the clientAPI wrapper objects instead of the dataAPI Stored*
     * classes. Because interfaces can't define package-access methods (public
     * only), this method declaration will be commented out and not enforced by
     * the compiler. But you still include this method in the implementing
     * class.
     * <p>
     * To do the reverse (find a clientAPI wrapper object from a dataAPI
     * object), see the PersistenceMapper class.
     * 
     * @return the dataAPI object that this clientAPI object wraps.
     */
    // Object getStoredInstance(); 
    
}
