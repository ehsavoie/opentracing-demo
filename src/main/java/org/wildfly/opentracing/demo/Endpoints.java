/*
 * JBoss, Home of Professional Open Source
 * Copyright 2020, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wildfly.opentracing.demo;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.opentracing.Traced;

@Path("/")
//JAxRS endpoints are always traced. Adding the annotation for clarity doesn't hurt, but not strictly needed 
//@Traced
public class Endpoints {

    @Inject
    private TracedHelloService helloService;

    @GET
    @Path("/json")
    @Produces({"application/json"})
    public String getHelloWorldJSON(@DefaultValue("World") @QueryParam("name") String name) {
        return "{\"result\":\"" + helloService.createHelloMessage(name) + "\"}";
    }

    @GET
    @Path("/xml")
    @Produces({"application/xml"})
    public String getHelloWorldXML(@DefaultValue("World") @QueryParam("name") String name) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><result>" + helloService.createHelloMessage(name) + "</result></xml>";
    }

}
