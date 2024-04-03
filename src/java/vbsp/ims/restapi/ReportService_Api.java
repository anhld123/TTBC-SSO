/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.restapi;

import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 *
 * @author HP
 */
public class ReportService_Api {
    protected URI getBaseURI() {
        return UriBuilder.fromUri("http://10.63.52.52:8010/api/v1/").build(); //16 
    }
}
