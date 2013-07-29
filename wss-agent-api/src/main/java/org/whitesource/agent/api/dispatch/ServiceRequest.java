/**
 * Copyright (C) 2012 White Source Ltd.
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
 */
package org.whitesource.agent.api.dispatch;

import java.io.Serializable;

/**
 * The interface describe the functionality to be exposed by any request to the WhiteSource service.
 * 
 * @author Edo.Shor
 *
 * @param <R> Result type
 */
public interface ServiceRequest<R> extends Serializable {

	/**
	 * @return Request type.
	 */
	RequestType type();
	
	/**
	 * @return Agent type identifier.
	 */
	String agent();
	
	/**
	 * @return Agent version.
	 */
	String agentVersion();

	/**
	 * @return WhiteSource service token of the organization to update.
	 */
	String orgToken();

    /**
     * @return Name or WhiteSource service token of the product to update.
     */
    String product();

    /**
     * @return Version of the product to update.
     */
    String productVersion();

    /**
     * @return Time stamp when the request created (client side)
     */
    long timeStamp() ;

}
