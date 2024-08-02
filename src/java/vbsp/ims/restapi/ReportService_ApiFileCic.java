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
public class ReportService_ApiFileCic {
    protected URI getBaseURI() {
        return UriBuilder.fromUri("http://10.63.16.52:8009/api/v1/").build(); //16 
    }
}
